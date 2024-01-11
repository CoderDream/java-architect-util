#!/usr/bin/env bash

#kill -9 $(lsof -n -i4TCP:8888 | grep LISTEN  | awk '{ print $2 }');
kill -9 $(lsof -n -i4TCP:8761 | grep LISTEN  | awk '{ print $2 }');
kill -9 $(lsof -n -i4TCP:1111 | grep LISTEN  | awk '{ print $2 }');
#kill -9 $(lsof -n -i4TCP:9411 | grep LISTEN  | awk '{ print $2 }');
kill -9 $(lsof -n -i4TCP:8765 | grep LISTEN  | awk '{ print $2 }');


#kubectl delete -f kube-deploy/config.yaml
kubectl delete -f k8s-deploy/discovery.yaml -n dev
kubectl delete -f k8s-deploy/gateway.yaml -n dev
kubectl delete -f k8s-deploy/notebook.yaml -n dev
#kubectl delete -f kube-deploy/openzipkin.yaml -n dev

kubectl delete -f k8s-deploy/discovery.yaml -n prod
kubectl delete -f k8s-deploy/gateway.yaml -n prod
kubectl delete -f k8s-deploy/notebook.yaml -n prod
#kubectl delete -f kube-deploy/openzipkin.yaml -n prod

