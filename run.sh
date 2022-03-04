cd result
./gradlew build
cd ../vote
./gradlew build
cd ../worker
./gradlew build

docker-compose up -d --build
