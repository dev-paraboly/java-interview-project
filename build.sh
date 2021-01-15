./mvnw clean
./mvnw -DskipTests package
docker build -t kgmmicroservices/internal-control-app-v2:latest .