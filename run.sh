cd result
./gradlew clean build
cd ../vote
./gradlew clean build
cd ../worker
./gradlew clean build

docker-compose up -d --build
