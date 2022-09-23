
create_table_songs = """
create table if not exists songs(
	song_id varchar(30),
	title varchar(60),
	artist_id varchar(30),
	duration numeric(10,5),
	year int
);
"""

create_table_artists = """
create table if not exists artists(
	artist_id varchar(30),
	artist_name varchar,
	artist_location varchar(60),
	artist_latitude varchar(60),
	artist_longitude varchar(60)
);
"""

create_table_users = """
create table if not exists users(
	user_id int primary key,
	first_name varchar(30),
	last_name varchar(30),
	gender varchar(10),
	level varchar(10)
);
"""

create_table_time = """
create table if not exists time(
	 start_time timestamp,
	 hour integer,
	 day integer,
	 week integer,
	 weekday integer,
	 month integer,
	 year integer
);
"""

create_table_songplays_stage = """
create table if not exists songplays_stage(
    songplay_id SERIAL primary key,
    start_time timestamp,
    song varchar(200),
    artist varchar(200),
    length numeric(10,5),
    user_id varchar (60),
    session_id int,
    location varchar(60),
    user_agent varchar(200),
    page varchar(30)
);
"""

create_table_songplays = """
create table if not exists songplays(
    songplay_id SERIAL primary key,
    start_time timestamp,
    user_id integer,
    level varchar(10),
    song_id varchar(30),
    artist_id varchar(30),
    session_id int,
    location varchar(60),
    user_agent varchar(200)
);
"""

sql_select = """
SELECT s.song_id, s.artist_id 
FROM songs s
JOIN artists a
 on s.artist_id = a.artist_id
WHERE s.title = %s and a.artist_name = %s and s.duration = %s;
"""


artists_table_insert ="""INSERT INTO ARTISTS(artist_id, artist_name, artist_location, artist_latitude, artist_longitude)
                         VALUES(%s, %s, %s, %s, %s);"""

songs_table_insert = """INSERT INTO SONGS(song_id, title, artist_id, duration, year) 
                        VALUES(%s, %s, %s, %s, %s);"""

users_table_insert = """INSERT INTO USERS(user_id, first_name, last_name, gender, level) 
                        VALUES(%s, %s, %s, %s, %s);"""

time_table_insert = """INSERT INTO TIME(start_time, hour, day, week, weekday, month, year) 
                        VALUES(%s, %s, %s, %s, %s, %s, %s);"""

songplays_table_insert = """INSERT INTO SONGPLAYS(start_time, user_id, level, song_id, artist_id, session_id, location, user_agent) 
                        VALUES(%s, %s, %s, %s, %s, %s, %s, %s);"""

drop_table_artists = """DROP TABLE IF EXISTS artists;"""
drop_table_songs = """DROP TABLE IF EXISTS songs;"""
drop_table_users = """DROP TABLE IF EXISTS users;"""
drop_table_time = """DROP TABLE IF EXISTS time_data;"""
drop_table_songplays = """DROP TABLE IF EXISTS songplays"""


create_table_queries = [create_table_songs, create_table_artists, create_table_users, create_table_time]
drop_table_queries = [drop_table_artists,drop_table_songs,drop_table_users,drop_table_time]

