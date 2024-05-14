.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

run:
	./gradlew run

run-dist:
	./build/install/app/bin/app $(ARGS)

test:
	./gradlew test

build-run: build run

.PHONY: build