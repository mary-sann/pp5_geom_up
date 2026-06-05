# 1. ビルド環境（Java21環境）
FROM maven:3-eclipse-temurin-21 AS build

# 作業フォルダを作成して、そこに移動する（これで衝突を防ぎます）
WORKDIR /app

# ファイルをコピーしてビルド
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

# 2. 実行環境
FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]