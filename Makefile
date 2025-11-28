package:
	mvn clean package

fastpack:
	mvn clean package -DskipTests

test:
	mvn clean test

run: fastpack
	java -jar target/accrdyn-web-api-0.0.1-SNAPSHOT.jar

docker: fastpack
	docker build -t accrdyn:latest .