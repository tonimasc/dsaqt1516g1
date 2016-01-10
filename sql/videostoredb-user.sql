drop user 'videostore'@'localhost';
create user 'videostore'@'localhost' identified by 'videostore';
grant all privileges on videostoredb.* to 'videostore'@'localhost';
flush privileges;
