##!/usr/env bash
#mvn clean package
#docker build -t  sale-service:latest .
docker tag sale-edge-service:latest 42.192.76.49:81/erp-service/sale-service:1.1
docker push 42.192.76.49:81/erp-service/sale-service:1.1