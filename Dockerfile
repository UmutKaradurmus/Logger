# 1. Adım: Temel imaj olarak OpenJDK 17 Alpine kullanalım
FROM openjdk:17-jdk-alpine

# 2. Uygulama jar dosyasının konumunu belirt
ARG JAR_FILE=target/log-service-0.0.1-SNAPSHOT.jar

# 3. JAR'ı konteynere kopyala
COPY ${JAR_FILE} log-service.jar

# 4. Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "log-service.jar"]
