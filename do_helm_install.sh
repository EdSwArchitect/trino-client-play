#!/bin/bash

# https://strimzi.io/quickstarts/

kubectl create -f 'https://strimzi.io/install/latest?namespace=ed-testing' -n ed-testing


kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-persistent-single.yaml -n ed-testing 

kubectl wait kafka/my-cluster --for=condition=Ready --timeout=300s -n ed-testing 


