docker stop zipkin-service
docker stop geteway-service
docker stop notebook-service
docker stop discovery-service

sleep 10

docker rm zipkin-service
docker rm geteway-service
docker rm notebook-service
docker rm discovery-service
