docker run --name discovery-service -d -p 8761:8761 art.local:8081/docker/notebook-k8s/discovery-service:latest
docker run --name zipkin-service -d -p 9411:9411 art.local:8081/docker/notebook-k8s/zipkin-service:latest
sleep 10
docker run --name notebook-service -d -p 1111:1111 --link discovery-service:eureka-server --link zipkin-service:zipkin-server art.local:8081/docker/notebook-k8s/notebook-service:latest
sleep 10
docker run --name geteway-service -d -p 8765:8765 --link discovery-service:eureka-server --link zipkin-service:zipkin-server art.local:8081/docker/notebook-k8s/gateway-service:latest

