build:
	sam build

start: build
	sam local start-api --warm-containers eager
