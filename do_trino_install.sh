#!/bin/bash

helm install my-trino trino/trino --version 0.14.0 --namespace ed-testing  -f trino-values.yaml \
  -f kafka-description-json.yaml
