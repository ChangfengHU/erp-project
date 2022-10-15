#!/usr/env bash
#mvn clean package
#docker build -t  api-gateway-zuul:latest .
docker tag api-gateway-zuul:latest api-gateway-zuul:latest
docker push 42.192.76.49:81/erp-service/api-gateway-zuul:latest
