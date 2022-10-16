##!/usr/env bash
mvn clean package
docker build -t  sale-service:latest .
docker tag sale-service:latest 42.192.76.49:81/erp-service/sale-service:latest
docker push 42.192.76.49:81/erp-service/sale-service:latest