#!/usr/bin/env bash

#cd ./config/
#./buildAndPushImage.sh

cd ./discovery-service/
./buildAndPushImage.sh
#
cd ../notebook-service/
./buildAndPushImage.sh

cd ../gateway-service/
./buildAndPushImage.sh



