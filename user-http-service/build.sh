#!/usr/env bash
#mvn clean package
#docker build -t  user-http-service:latest .
docker tag user-http-service:latest 42.192.76.49:81/erp-service/user-http-service:1.0.6
docker push 42.192.76.49:81/erp-service/user-http-service:1.0.6


