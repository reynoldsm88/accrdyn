build:
	mvn clean package

test:
	mvn clean test

run: build
	java -jar target/accrdyn-web-api-0.0.1-SNAPSHOT.jar