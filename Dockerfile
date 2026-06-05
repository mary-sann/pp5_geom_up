# 1. ビルド環境（Java21とMavenを使ってアプリをビルド）
FROM maven:3-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

# 2. 実行環境（軽量なJava環境でビルドされたアプリを実行）
FROM eclipse-temurin:21-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]