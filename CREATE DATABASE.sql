CREATE DATABASE CRUSH
USE CRUSH
CREATE TABLE USERS(
	UID int IDENTITY(1,1) PRIMARY KEY,
	UserName varchar(20),
	PassWord char(32),
	FullName nvarchar(20),
	Age int,
	Gender char(6), --MALE | FEMALE | LGBT
	Email varchar(30),
	Avatar varchar(MAX),
	ProfileImage varchar(MAX),
	Description nvarchar(300),
	LastLogin DateTime,
	Status int, --1 IS INACTIVE EMAIL | 2 IS INACTIVE USERINFO | 0 IS OK
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
--FAKE INFO PASS 1234
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight,Avatar) VALUES ('tomcat123','81dc9bdb52d04dc20036dbd8313ed055','Tom Allen',18,'MALE','a&b.c',0,3,'avatars/2.png')
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight,Avatar) VALUES ('tomcat456','81dc9bdb52d04dc20036dbd8313ed055','Tom Alex',18,'MALE','a&b.c',0,3,'avatars/2.png')
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight,Avatar) VALUES ('tomcat789','81dc9bdb52d04dc20036dbd8313ed055','Harry',18,'FEMALE','a&b.c',0,3,'avatars/2.png')

INSERT INTO FRIENDS(UserA,UserB,FR) Values (1,2,getdate())
INSERT INTO FRIENDS(UserA,UserB,FR) Values (3,1,getdate())
--FAKE INFO

DELETE FROM MESSAGES

SELECT * FROM MESSAGES

SELECT * FROM USERS

SELECT * FROM FRIENDS

--Friend
SELECT * FROM USERS WHERE UID != 1 and UID in (
(
SELECT UserA FROM FRIENDS WHERE UserB = 1)
UNION
(SELECT UserB FROM FRIENDS WHERE UserA = 1)
)
--get FID
SELECT
CASE
    WHEN UserA != 1 THEN UserA
    ELSE UserB
END
FROM FRIENDS
WHERE FID = 1

--get Friends
SELECT FID, USERS.UID, USERS.FullName, USERS.Avatar FROM
(SELECT FID,
CASE
    WHEN UserA != 1 THEN UserA
    ELSE UserB
END AS UID
FROM FRIENDS
WHERE UserA = 1 OR UserB = 1
) AS R1
LEFT JOIN USERS ON (R1.UID = USERS.UID)


--SELECT * FROM USERS
--SELECT USER LOGIN
--CREATE FUNCTION login (@username nvarchar(20), @pasword char(32))
--RETURNS TABLE
--AS
--	RETURN SELECT * FROM USERS WHERE USERS.UserName = @username
--select login('a','b') from users
