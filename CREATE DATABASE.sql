CREATE DATABASE CRUSH
USE CRUSH
CREATE TABLE USERS(
	UID int IDENTITY(1,1) PRIMARY KEY,
	UserName varchar(20),
	PassWord char(32),
	FullName nvarchar(20),
	Age int,
	Gender char(5), --MALE | FEMALE | LGBT
	Email varchar(30),
	Avatar varchar(MAX),
	LastLogin DateTime,
	Status bit, --FALSE IS INACTIVE | TRUE IS ACTIVE
	UserRight int -- 1,2,3 (3 is user,2 is staff, 1 is admin
)
CREATE TABLE WANT(
	WID int Primary Key,
	AgeBegin int,
	AgeEnd int,
	Male bit,
	Female bit,
	Lgbt bit,
	FOREIGN KEY(WID) REFERENCES USERS(UID)
)
CREATE TABLE CRUSH(
	CID int IDENTITY(1,1) PRIMARY KEY,
	UserA int FOREIGN KEY REFERENCES USERS(UID),
	UserB int FOREIGN KEY REFERENCES USERS(UID),
)
CREATE TABLE FRIENDS(
	FID int IDENTITY(1,1) PRIMARY KEY,
	UserA int FOREIGN KEY REFERENCES USERS(UID),
	UserB int FOREIGN KEY REFERENCES USERS(UID),
	FR DateTime
)
CREATE TABLE MESSAGES(
	MID int IDENTITY(1,1) PRIMARY KEY,
	FID int FOREIGN KEY REFERENCES FRIENDS(FID),
	FR int FOREIGN KEY REFERENCES USERS(UID),
	Content nvarchar(MAX),
	Time DateTime,
	Status bit -- false IS UNREADED | true IS READED
)

INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight) VALUES ('tomcat123','81dc9bdb52d04dc20036dbd8313ed055','Tom Allen',18,'MALE','a&b.c',0,3)
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight) VALUES ('tomcat456','81dc9bdb52d04dc20036dbd8313ed055','Tom Alex',18,'MALE','a&b.c',0,3)

INSERT INTO FRIENDS(UserA,UserB,FR) Values (1,2,getdate())

INSERT INTO MESSAGES (FID,FR,Content,Time,Status) VALUES (1,1,'hello',getdate(),0) 

DELETE FROM MESSAGES

SELECT * FROM MESSAGES

--SELECT * FROM USERS
--SELECT USER LOGIN
--CREATE FUNCTION login (@username nvarchar(20), @pasword char(32))
--RETURNS TABLE 
--AS 	
--	RETURN SELECT * FROM USERS WHERE USERS.UserName = @username
--select login('a','b') from users