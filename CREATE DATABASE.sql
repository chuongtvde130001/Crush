CREATE DATABASE CRUSH
USE CRUSH
CREATE TABLE USERS(
	UID int IDENTITY(1,1) PRIMARY KEY,
	UserName varchar(20),
	PassWord char(32),
	FullName nvarchar(20),
	Age int,
	Gender int, --MALE 2 | FEMALE 3 | LGBT 5
	Email varchar(30),
	Avatar varchar(MAX),
	ProfileImage varchar(MAX),
	Description nvarchar(300),
	LastLogin DateTime,
	Status int, --1 IS INACTIVE EMAIL | 2 IS INACTIVE USERINFO | 0 IS OK
	UserRight int -- 1,2,3 (3 is user,2 is staff, 1 is admin
)
CREATE TABLE WANT(
	WID int IDENTITY(1,1) Primary Key,
	AgeBegin int,
	AgeEnd int,
	Gender int,
	FOREIGN KEY(WID) REFERENCES USERS(UID)
)
--Note: Gender is sum of male(2), female(3), lgbt(5) if yes add to Gender 
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
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight,Avatar) VALUES ('tomcat123','81dc9bdb52d04dc20036dbd8313ed055','Tom Allen',18,2,'a&b.c',0,3,'avatars/2.png')
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight,Avatar) VALUES ('tomcat456','81dc9bdb52d04dc20036dbd8313ed055','Tom Alex',18,2,'a&b.c',0,3,'avatars/2.png')
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight,Avatar) VALUES ('tomcat789','81dc9bdb52d04dc20036dbd8313ed055','Harry',18,3,'a&b.c',0,3,'avatars/2.png')
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight,Avatar) VALUES ('tomcatAAA','81dc9bdb52d04dc20036dbd8313ed055',N'Thị Nở',30,3,'a&b.c',0,3,'avatars/2.png')
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight,Avatar) VALUES ('tomcatBBB','81dc9bdb52d04dc20036dbd8313ed055','Taylor Swift',20,5,'a&b.c',0,3,'avatars/2.png')
INSERT INTO Users(UserName,Password,FullName,Age,Gender,Email,Status,UserRight,Avatar) VALUES ('tomcatCCC','81dc9bdb52d04dc20036dbd8313ed055','Katy Perry',50,3,'a&b.c',0,3,'avatars/2.png')

INSERT INTO FRIENDS(UserA,UserB,FR) Values (1,2,getdate())
INSERT INTO FRIENDS(UserA,UserB,FR) Values (3,1,getdate())

INSERT INTO WANT(AgeBegin, AgeEnd, Gender) Values (20,90,6);
INSERT INTO WANT(AgeBegin, AgeEnd, Gender) Values (18,20,5);
--FAKE INFO

DELETE FROM MESSAGES

SELECT * FROM MESSAGES

SELECT * FROM USERS

SELECT * FROM FRIENDS

SELECT * FROM WANT

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

--get user match want
DECLARE @AB int, @AE int, @GENDER int
SELECT TOP 1 @AB=AgeBegin, @AE=AgeEnd, @GENDER=Gender FROM WANT WHERE WID = 1
SELECT UID,FullName,Age,Gender,Avatar FROM USERS WHERE Age >= @AB AND Age <= @AE AND @GENDER%Gender=0

--SELECT * FROM USERS
--SELECT USER LOGIN
--CREATE FUNCTION login (@username nvarchar(20), @pasword char(32))
--RETURNS TABLE
--AS
--	RETURN SELECT * FROM USERS WHERE USERS.UserName = @username
--select login('a','b') from users
