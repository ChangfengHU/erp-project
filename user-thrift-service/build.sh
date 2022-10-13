#!/usr/env bash
#mvn clean package
docker build -t  user-service:latest .
#docker tag user-service:latest 47.97.167.185:9999/article-service/user-service:latest
#docker push 47.97.167.185:9999/article-service/user-service:latest
docker run -it user-service:latest --mysql.address=192.168.57.1