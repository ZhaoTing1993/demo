# demo
springmvc demo project

## before start

### init database
```mysql

CREATE DATABASE db_ting_common;
CREATE DATABASE test1;
CREATE DATABASE test2;
  
USE db_ting_common;
CREATE TABLE `ting_admin_datasource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datasource_name` varchar(50) NOT NULL DEFAULT '',
  `url` varchar(200) NOT NULL DEFAULT '',
  `username` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ting_admin_datasource_datasource_name_uindex` (`datasource_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO ting_admin_datasource (datasource_name, url, username) VALUES
  ('test1','jdbc:mysql://127.0.0.1:3306/test1','root'),
  ('test2','jdbc:mysql://127.0.0.1:3306/test2','root');  
  
USE test1;
CREATE TABLE account (
  id int not null AUTO_INCREMENT,
  user_id INT NOT NULL,
  money DOUBLE NOT NULL ,
  PRIMARY KEY (id)
)CHARSET ='utf8';
INSERT INTO account(user_id, money) VALUES
  (1,100),(2,100),(3,100);
  
USE test2;
CREATE TABLE account (
  id int not null AUTO_INCREMENT,
  user_id INT NOT NULL,
  money DOUBLE NOT NULL ,
  PRIMARY KEY (id)
)CHARSET ='utf8';
INSERT INTO account(user_id, money) VALUES
  (1,100),(2,100),(3,100);
```
