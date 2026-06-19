# Spring Boot Todo API - Docker Practice

Docker image build 学習のため、簡単なSpring Boot REST APIです。

## Requirements

- Java 17+
- Maven 3.9+
- Docker

## Local Run

```bash
mvn spring-boot:run
```

## Docker Build

```bash
docker build -t todo-api:1.0 .
```

## Docker Run

```bash
docker run --name todo-api -p 8080:8080 todo-api:1.0
```

バックグラウンド 実行:

```bash
docker run -d --name todo-api -p 8080:8080 todo-api:1.0
```

中止・削除:

```bash
docker stop todo-api
docker rm todo-api
```

## Health Check

```bash
curl http://localhost:8080/actuator/health
```

## API Test

### Create Todo

```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"Docker image build practice"}'
```

### Get Todos

```bash
curl http://localhost:8080/api/todos
```

### Get One Todo

```bash
curl http://localhost:8080/api/todos/1
```

### Update Todo

```bash
curl -X PATCH http://localhost:8080/api/todos/1 \
  -H "Content-Type: application/json" \
  -d '{"completed":true}'
```

### Delete Todo

```bash
curl -X DELETE http://localhost:8080/api/todos/1
```

## Docker Commands to Practice

```bash
# イメージ確認
docker images

# コンテナ確認
docker ps

# 全てのコンテナ確認
docker ps -a

# ログ確認
docker logs todo-api

# コンテナ内部接続
docker exec -it todo-api sh

# イメージ削除
docker rmi todo-api:1.0
```
