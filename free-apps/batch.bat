mvn clean
mvn install '-Dmaven.test.skip=true'
docker build -t coderdream/object-code:1.0.0 .
docker push coderdream/object-code:1.0.0
