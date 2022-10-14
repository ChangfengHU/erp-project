#!/usr/env bash
mvn clean package
docker build -t  user-http-service:latest .
#docker tag user-edge-service:latest 47.97.167.185:9999/article-service/user-edge-service:latest
#docker push 47.97.167.185:9999/article-service/user-edge-service:latest