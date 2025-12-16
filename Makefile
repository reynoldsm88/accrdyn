package:
	mvn clean package

fastpack:
	mvn clean package -DskipTests

test:
	mvn clean test

dt:
	mvn clean -Dmaven.surefire.debug test

run: fastpack
	java -jar target/accrdyn-web-api-0.0.1-SNAPSHOT.jar

docker: fastpack
	docker build -t accrdyn:latest .

compose: docker
	docker compose up -d
