# zebra-unicorn-app

Purpose:
Practise Docker and Docker Swarm commands with some functionality

The name:
zebra-unicorn-app

Architecture:
image (put stack in nodes)



How to run:
local:

Swarm:



### To build and run

#### In the root of the project: `./run.sh`  


-Projeto com input, redis, worker para ler do redis e inserir em postgres, postgres, projeto exibição de informação.
-Criar dois ou mais projetos que se comuniquem entre si e consiga subir as aplicações na Digital Ocean/Heroku utilizando docker. Necessario utilizar dockerfile, docker-compose (local) e docker swarm (em prod)


local
http://localhost:8080/api/swagger-ui.html


`local.env` is not a best practise. But for building locally, it's Ok.

Before run docker-stack, active the Swarm by running in the terminal:
`docker swarm init`

The images used in the docker-stack are publics in docker.hub

Execute the command below in terminal to run stack
`docker stack deploy --compose-file docker-stack.yml zebra-unicorn-app`

