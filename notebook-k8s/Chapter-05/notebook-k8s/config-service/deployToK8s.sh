#!/usr/bin/env bash

kubectl delete -f ../kube-deploy/config.yaml
kubectl create -f ../kube-deploy/config.yaml
