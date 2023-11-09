#!/bin/bash

#helm repo add pinot https://raw.githubusercontent.com/apache/pinot/master/helm

helm install pinot pinot/pinot \
 -n ed-testing \
 --set cluster.name=Pinot-Cluster \
 --set server.replicaCount=1 \
 --set server.persistence.size=2G \
 --set controller.replicaCount=1\
 --set controller.persistence.size=1G \
 --set broker.replicaCount=1 \
 --set minion.replicaCount=0 \
 --set minion.persistence.size=2G \
 --set minionStateless.persistence.size=2G \
 --set zookeeper.replicaCount=1 \
 --set zookeeper.autopurge.snapRetainCount=3 \
 --set zookeeper.persistence.size="2Gi"

