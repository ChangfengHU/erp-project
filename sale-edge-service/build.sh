##!/usr/env bash
#mvn clean package
docker tag sale-edge-service:latest 42.192.76.49:81/erp-service/sale-edge-service:latest
docker push 42.192.76.49:81/erp-service/sale-edge-service:latest
