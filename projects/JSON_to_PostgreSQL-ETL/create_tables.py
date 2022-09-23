import psycopg2
from sql_queries import create_table_queries, drop_table_queries

#connect to default database
conn = psycopg2.connect(host="localhost", database="udacity", user="postgres", password="password")
conn.set_session(autocommit=True)
print(conn)

#create new database
cur = conn.cursor()
cur.execute("drop database if exists sparkify_db")
cur.execute("create database sparkify_db")

#close default database
cur.close()
conn.close()

#connect to sparkify database
conn = psycopg2.connect(host="localhost", database="sparkify_db", user="postgres", password="password")
cur = conn.cursor()

# create tables
for query in create_table_queries:
    cur.execute(query)
    conn.commit()

