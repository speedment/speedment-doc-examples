-- Make sure that you maintain a safe environment. 
-- This is just an example of how to setup a 'hare' user
-- Do not use this on a producion server where you should always pick a "safe" password

CREATE USER 'hare'@'localhost' IDENTIFIED BY 'hare';
GRANT ALL PRIVILEGES ON *.* TO 'hare'@'localhost';
FLUSH PRIVILEGES;

commit;