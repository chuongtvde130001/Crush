#!/bin/bash 
if [ -z "$1"]
then
	echo "Input message please!!!"
else
	cd Assignment
	git add src web
	git reset -- src/java/dbconfig/DBConfig.java
	git commit -m $1
	git push
	cd ..
fi
