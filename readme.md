#   Desafio RPG

Junte-se a batalha épica estilo Advanced Dungeons & Dragons (AD&D)!

##   Requisitos

 - Java 17
 - Docker
 - Git

##   Como Executar o projeto
 
 - Executar o comando ***docker-compose up -d*** na pasta raiz do projeto
 - Executar o projeto maven com o comando ***.\mvnw spring-boot:run*** ou utilizar uma IDE (IntelliJ, Eclipse, etc.)
   - Note que não é necessario possuir o maven instalado, pois o arquivo mvnw realiza as configurações necessarias para executá-lo
 - Realizar as requisições por HTTP: 
   - Com um programa praparado para isso (Postman, Insomnia, etc.)
   - Acessando o link http://localhost:8080/swagger-ui/index.html

##  Passo a passo do jogo
 
 - Para iniciar o jogo é Necessario criar um personagem
   - Realize uma Requisição **GET /personagens/classes**
     - O Endpoint retornará os atributos de cada classe de persongem
   - Realize uma requisição **POST /personagens/*\<classe desejada\>***
     - Passando no body qual é o nome do personagem
     - Retornará o personagem criado, salve o id gerado para futuras requisições
 - Após isso sera necessário iniciar uma batalha
   - Realize uma Requisição **POST /batalhar** 
     - É necessario passar o id do personagem criado no body da requisição com o nome "chosenHero"
     - É possivel também passar no body o parâmetro "chosenMonster" com o id de outro personagem criado para ser o inimigo
     - Retornará a batalha criada com o heroi selecionado e caso não tenha escolhido um inimigo será utilizado um dos monstros aleatóriamente
       - Salve o id da batalha para futuras requisições
 - Após iniciar a batalha é necessario rolar a iniciativa para saber quem tera prioridade de ataque nos turnos
   - Para isso realize uma requisição **PATCH /iniciativa** passando no body o id da batalha gerado
     - O resultado da iniciativa definirá quem terá prioridade de ataque em cada turno
 - Após a iniciativa finalmente está na hora de atacar/defender
   - Para isso realize uma requisição **PATCH /ataque** ou **PATCH /defesa** passando no body o número do id da batalha
     - Tenha em mente que um turno possui duas partes, ataque e defesa definido pela iniciativa
- Quando valor de PV do heroi ou do monstro chegar a 0 a batalha termina
- É possivel buscar o histórico das batalhas 
  - Realizando a requisição **GET /historico** recebera de forma paginada o historico de todas as batalhas que foram realizadas
  - Realizando a requisição **GET /historico/{id}** recebera todos os dados pertinentes da batalha de id selecioanado

