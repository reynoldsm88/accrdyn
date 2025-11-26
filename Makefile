build:
	mvn clean package

test:
	mvn clean test

run:
	java -jar target/accrdyn-web-api-0.0.1-SNAPSHOT.jar