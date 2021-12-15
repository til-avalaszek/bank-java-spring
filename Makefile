SERVICE_NAME       := bank-java-spring
SERVICE_PORT       := 8080
IMAGE_NAME         := bank-java-spring
IMAGE_TAG          := latest

.PHONY:

.SILENT:

run:
	./gradlew bootRun

build:
	./gradlew clean build

c-build:
	make build
	docker build -t $(IMAGE_NAME):$(IMAGE_TAG) --build-arg SERVICE_NAME=$(SERVICE_NAME) .

start:
	make c-build
	docker-compose up

stop:
	docker-compose down

c-run:
	docker run -d -p $(SERVICE_PORT):$(SERVICE_PORT) --name $(SERVICE_NAME) --rm $(IMAGE_NAME):$(IMAGE_TAG)
	make c-logs

c-shell:
	docker exec -it $(SERVICE_NAME) bash

c-stop:
	docker stop $(SERVICE_NAME)

c-logs:
	docker logs $(SERVICE_NAME)

c-clean:
	docker rmi -f $(IMAGE_NAME):$(IMAGE_TAG)