#!/usr/env bash
mvn clean package
docker build -t  user-service:latest .
docker tag user-service:latest 42.192.76.49:81/erp-service/user-service:1.0.6
docker push 42.192.76.49:81/erp-service/user-service:1.0.6