

import re
import datetime
import sys
import paramiko
import time
import logging
import pandas as pd
import snowflake.connector as snow

logging.basicConfig(filename='c:\sample\HSS\hss_get_data.log', level=logging.INFO,
                    format = '%(asctime)s  %(levelname)-10s %(processName)s  %(name)s %(message)s', datefmt='%Y%m%d %H:%M:%S')
logging.info('Start HSS Get Data process')

# Login to HSS switch; generate data and write it to a text file #
ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect('********', port = 22, username='******', password='********')
time.sleep(5)
stdIn, stdout, stderr = ssh.exec_command('ldapsearch -x  -h "******" -p 7323 -D "administratorName=*****,'
                                         ' nodeName=****" -w Meeyousal44 -b HSS-EsmSubscriptionContainerName=****,'
                                         'applicationName=****,nodeName=*****')
data = stdout.readlines()

now = datetime.datetime.now()
time_stamp = now.strftime('%Y%m%d%H%M%S')
writerObj = open('c:\\sample\\HSS' + time_stamp + '.txt','w')
writerObj.writelines(data)
logging.info('Dump file generated from switch successfully')


# Checking that dump file exists
try:
    logging.info('Verifying dump file exists.. ')
    f = open('c:\\sample\\HSS' + time_stamp + '.txt','r')
    f.close()

except IOError as e:
    logging.error('Dump file not found. ' + str(e))
    sys.exit()

re_Object = re.compile('HSS-EsmMsisdn: 592' + r'\d\d\d\d\d\d\d')
re_Object2 = re.compile('HSS-EsmMsisdn:')
re_Object3 = re.compile('HSS-EsmLocationState:' + r'\s\D\D\D\D\D\D\D')
re_Object4 = re.compile('HSS-EsmLocationState:' + r'\s\D\D\D\D\D\D')
patterns = [re_Object, re_Object2, re_Object3, re_Object4]

def find_pattern(text, patterns):
    for pattern in patterns:
        if pattern.search(text) is not None:
            mo = pattern.search(text)
            return mo.group()
    return None


file_path = 'c:\\sample\\HSS' + time_stamp + '.txt'
input_file = open(file_path)

temp_list = []

for row in input_file:
    if (find_pattern(row, patterns))is not None:
        result = find_pattern(row, patterns)
        temp_list.append(result)
        if len(temp_list) == 2:
            output_file = open('c:\\sample\\hss_output_' + time_stamp + '.csv', 'a'
                                                                                '')
            output_file.write(temp_list[0] + ',' + temp_list[1] + ',' + time_stamp + '\n')
            output_file.close()
            temp_list = []

input_file.close()

conn = snow.connect(user='******', password='******', account='******')
cur = conn.cursor()

cur.execute("use warehouse ****")
cur.execute("use database ****")
cur.execute("use role ****")

hss_df = pd.read_csv('c:\\sample\\hss_output_' + time_stamp + '.csv')
hss_df.columns = ['msisdn', 'location_state', 'date_time']

insert_query = """INSERT INTO rep_prod.reporting_schema.hss_stage(msisdn, location_state, date_time)
                 VALUES(%s, %s, %s);"""

for index, row in hss_df.iterrows():
    cur.execute(insert_query, [row.msisdn, row.location_state, row.date_time])
    print('processing row.. {}'.format(index+1))

# close connection
conn.commit()
cur.close()
conn.close()
