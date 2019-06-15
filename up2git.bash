#!/bin/bash 
cd Assignment
git add src web
git reset -- src/java/dbconfig/DBConfig.java
git commit -m $1
git push
cd ..
