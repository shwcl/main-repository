import re
import datetime
import sys
import paramiko
import time
import logging
import shutil
import pandas as pd
import snowflake.connector

# Enable logging
logging.basicConfig(filename='c:\\...data.log', level=logging.INFO, format = '%(asctime)s  %(levelname)-10s %(processName)s  %(name)s %(message)s', datefmt='%Y%m%d %H:%M:%S')
logging.info('Start Get Data process')

# Login to switch; generate data and write it to a text file 
ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect('xxx.xxx.xxx.xxx', port = xx, username='xxxx', password='xxxx')
time.sleep(5)
stdIn, stdout, stderr = ssh.exec_command('............')
data = stdout.readlines()

now = datetime.datetime.now()
timestamp = now.strftime('%Y%m%d%H%M%S')
writerObj = open('c:\\sample\\in\\file_' + timestamp + '.txt','w')
writerObj.writelines(data)
logging.info('Dump file generated from switch successfully')

try:
    logging.info('Verifying dump file exists.. ')
    f = open('c:\\sample\\in\\file_' + timestamp + '.txt','r')
    f.close()

except IOError as e:
    logging.error('Error: dump file not found. ' + str(e))
    sys.exit()


# Function to search text for patterns
def findPattern(text):

    reObject = re.compile('ASA-esnmsisdn: 000' + r'\d\d\d\d\d\d\d')
    reObject2 = re.compile('ASA-esnmsisdn:')
    reObject3 = re.compile('ASA-esnLocationState:' + r'\s\D\D\D\D\D\D\D')
    reObject4 = re.compile('ASA-esnLocationState:' + r'\s\D\D\D\D\D\D')

    mo = reObject.search(text)

    if mo is None:
        print("pattern 1 not found")
    else:
        print("pattern 1 found")
        pattern = mo.group()
        return pattern[15:]

    mo2 = reObject2.search(text)
    if mo2 is None:
        print("pattern 2 not found")
    else:
        print("pattern 2 found")
        pattern = mo2.group()
        return pattern[14:]

    mo3 = reObject3.search(text)
    if mo3 is None:
        print("pattern 3 not found")
    else:
        print("pattern 3 found")
        pattern = mo3.group()
        return pattern[22:]

    mo4 = reObject4.search(text)

    if mo4 is None:
        print("pattern 4 not found")
        return None
    else:
        print("pattern 4 found")
        pattern = mo4.group()
        return pattern[22:]

      
# Parse text file based on specific text patterns and save to csv file
sourceFilename = 'file_' + timestamp + '.txt'
sourceFile = open('C:\\sample\\in\\' + sourceFilename)

tempList = []

for line in sourceFile:
    data = line.split(',')
    for i in range(len(data)):

        if (findPattern(data[i]) is not None):
            text = findPattern(data[i])
            tempList.append(text)
            if len(tempList) == 2:
                with open('C:\\sample\\out\\file_output.csv', 'a') as outputFile:
                    outputFile.write(tempList[0] + ',' + tempList[1] + ',' + sourceFilename + ',' + '\n')
                del tempList[:]


sourceFile.close()
outputFile.close()


# Verify output file exists
try:
    logging.info('Verifying file_output.csv file exists @ file path...')
    f = open('c:\\sample\\out\\file_output.csv','r')
    f.close()

except IOError as e:
    logging.error('csv file not found. ' + str(e))
    sys.exit()


# Load data from csv file to dataframe, transform and load to to Snowflake 
df = pd.read_csv('c:\\sample\\out\\file_output.csv', dtype=str)
df.columns = ['msisdn','location_state','filename']
df['msisdn'].fillna('', inplace=True)

conn = snowflake.connector.connect(user='xxxx', password='xxxx', account='xxxx')
cursor = conn.cursor()

cursor.execute("use warehouse xxxx")
cursor.execute("use database xxxx")
cursor.execute("use role xxxx")

for i,row in df.iterrows():
    sql = "INSERT INTO prod.esm_switch_data (msisdn, location_state, source_filename) VALUES(" + "%s," * (len(row)-1) + "%s)" 
    cursor.execute(sql, tuple(row))
    conn.commit()

# move output file to archive folder
now = datetime.datetime.now()
timestamp = now.strftime('%Y%m%d%H%M%S')
shutil.move('c:\\sample\\out\\file_output.csv', 'c:\\sample\\archive\\file_output_' + timestamp + '.csv')
