CREATE DATABASE IF NOT EXISTS `devdb`;
CREATE DATABASE IF NOT EXISTS `testdb`;

-- CREATE USER 'root'@'localhost' IDENTIFIED BY 'local';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
