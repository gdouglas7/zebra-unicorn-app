# Practise Docker and Docker Swarm
## zebra-unicorn voting app

### Purpose:
The purpose of this project is just to practise some Docker and Docker Swarm commands/functionality.

### The name:
The name comes from the start up universe, where we have unicorns start ups, companies that reach 1 billion dollar of market value, and zebras start ups, companies that are dedicated to balance both profit and purpose.
More info of Zebras companies you can find [here](https://zebrasunite.mn.co/).

### Architecture:
![Architecture diagram](./docs/architecture.png)


### Functionality and Stack
- The functionality of the app is just for to choice which kind of thinking do you identify the most: Zebras or Unicorn.
- The services were building with Kotlin, Spring boot, Gradle and others libs.
- The vote-app receive the vote and store it on Redis. The worker-app read the data from Redis with simple scheduler that run in each 3secs and store it in Postgres. The result-app show the ranking result getting it from Postgres.
- The Redis and Postgres containers are building with official images.
- Interfaces for voting and viewing the result are provided for Swagger Interfaces.

### How build and run:
For run locally we need to run `./gradlew build` in each app folder(vote/worker/result) before run `docker-compose up -d`, or in the root of the projet just run `./run.sh`

For run with Swarm, active it by running in the terminal: `docker swarm init`.  
The images used in the docker-stack are publics in docker.hub. Then execute the command below in terminal to run stack:  
`docker stack deploy --compose-file docker-stack.yml zebra-unicorn-app`

### How to access the apps locally:
- For voting, you can access, [here](http://localhost:8080/api/swagger-ui.html) and try the api `/api/vote` like the image below.  
![vote request example](./docs/vote.png)

- For look at the result, you can access [here](http://localhost:8081/api/swagger-ui.html) and try the api `/api/result/votes` like the image below.
![result request example](./docs/result.png)

- There's a visualizer too, an interface for looking the docker nodes in Docker Swarm and you can access it [here](http://localhost:8083). The image below show the interface in localhost
![localhost-visualizer](./docs/localhost-visualizer.png)
The image below show all the nodes running in 3 differents machines in Digital Ocean. In this case all the machines are managers.
![digital-ocean-visualizer](./docs/digital-ocean-visualizer.png)

### References

The project inspiration comes from the course [Docker Mastery](https://www.udemy.com/course/docker-mastery/)






