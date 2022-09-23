import os
import glob
import psycopg2
import pandas as pd
from sql_queries import *

#connect to sparkify database
conn = psycopg2.connect(host="localhost", database="sparkify_db", user="postgres", password="password")
cur = conn.cursor()

def process_song_file(cur, file_path):
    df = pd.read_json(file_path, lines=True)

    songs_df = df[["song_id", "title", "artist_id", "duration", "year"]]
    for i, row in songs_df.iterrows():
        cur.execute(songs_table_insert, tuple(row))

    artists_df = df[["artist_id", "artist_name", "artist_location", "artist_latitude", "artist_longitude"]]
    for i, row in artists_df.iterrows():
        cur.execute(artists_table_insert, tuple(row))


def process_log_file(cur, file_path):
    df = pd.read_json(file_path, lines=True)
    filter_in_NextSong = df['page'] == 'NextSong'
    df = df[filter_in_NextSong]

    users_df = df[["userId", "firstName", "lastName", "gender", "level"]]
    for i, row in users_df.iterrows():
        cur.execute(users_table_insert, tuple(row))

    t = pd.to_datetime(df['ts'], unit='ms')
    time_data = list((t, t.dt.hour, t.dt.day, t.dt.isocalendar().week, t.dt.weekday, t.dt.month, t.dt.year))
    time_data_headers = list(('start_time', 'hour', 'day', 'week', 'weekday', 'month', 'year'))
    time_df = pd.DataFrame.from_dict(dict(zip(time_data_headers, time_data)))

    for i, row in time_df.iterrows():
        cur.execute(time_table_insert, tuple(row))

    for index, row in df.iterrows():
        cur.execute(sql_select, [row.song, row.artist, row.length])
        result = cur.fetchone()
        if result:
            song_id, artist_id = result
        else:
            song_id, artist_id = None, None

        start_time = pd.to_datetime(row.ts, unit='ms')
        cur.execute(songplays_table_insert,[start_time, row.userId, row.level, song_id, artist_id, row.sessionId, row.location, row.userAgent])

# walk the directory that contains the song data and store each file/path into a list..
song_files = []

for curr_dir, sub_dirs, files in os.walk('c:\\data\\song_data'):
    result_files = glob.glob(os.path.join(curr_dir, '*json'))
    for f in result_files:
        song_files.append(os.path.abspath(f))

for count, file in enumerate(song_files, 1):
    process_song_file(cur, file)
    num_files = len(song_files)
    print('{}/[] files processed'.format(count, num_files))


log_files = []

for curr_dir, sub_dirs, files in os.walk('c:\\data\\log_data'):
    result = glob.glob(os.path.join(curr_dir,'*json'))
    for f in result:
        log_files.append(os.path.abspath(f))

for count, file in enumerate(log_files, 1):
    process_log_file(cur, file)
    print('{}/{} log files processed'.format(count, len(log_files)))

conn.commit()
cur.close()
conn.close()