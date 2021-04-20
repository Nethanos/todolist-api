

# Stack
- Java 11
- Maven
- Springboot
- H2 database
  
 # Funções implementadas
 - Autenticação via JWT
 - CRUD de Tarefas com visualização por usuário ou visualização de todas as tarefas em caso de user admin
 - Autorização de acesso a endpoints via roles(USER, ADMIN)
 - Healthcheck
- Documentação de API via swagger
 
 # How to download
 Para ser possível rodar a aplicação, checar os seguintes passos:
 - Baixar o repositório localmente

- Checar se há uma versão do Java compatível(preferencialmente, java 11).

# How to Run
Para rodar a aplicação de fato, utilize os seguintes passos:
- Com o cmd/git bash ou interface de linha de comando preferida, vá até a raíz do repositório e execute `mvn clean install package`(Este comando vai limpar o target, baixar as dependências, buildar o projeto e compilará um .jar na pasta /target da aplicação) <br>
<strong>OBS</strong>: Se não tiver o maven instalado ou setado como variável de ambiente em sua máquina, não tem problema, basta substituir o mvn por `./mvnw` <strong>sempre na pasta raíz do projeto</strong> em todos os comandos e assim você irá usar o wrapper do maven contido no projeto. Ex: `./mvnw clean package`.

- Após o build do projeto, ainda na pasta raíz basta rodar `java -jar target/todolist-0.0.1-SNAPSHOT.jar` e esperar o projeto rodar na porta 8080 de sua máquina!

- Também é possível rodar via IDE. no Intellij, basta importar o projeto como um projeto maven, e executar a classe `TodoListApplication`

# Informações úteis
- O link para a documentação da API se encontra em `http://localhost:8080/swagger-ui.html`(não está habilitado testar via swagger por causa das rotas de autenticação, sempre dará unauthorized)
- Para checar informações sobre o estado da aplicação, basta acessar `http://localhost:8080/actuator/health`
- O banco escolhido foi o H2 por motivos de facilidade e compatibilidade de rodar em qualquer máquina e seu paradgima SQL que, em minha opinião, se assemelha ao relacionamento entre entidades apresentado no case.
- Para ter acesso ao console do H2 basta acessar `http://localhost:8080/h2-console` e usar `sa` como username, e deixar o password vazio.
- Na pasta raíz do projeto há uma <strong> collection do postman </strong> caso deseje não usar o curl, basta realizar a primeira chamada`(POST /auth)` e mudar o <strong> authorization para Bearer nas seguintes, copiando o retorno da primeira chamada(sem o nome Bearer) e setando no box de Authorization das chamadas seguintes </strong>

# Testando a aplicação:
<strong> OBS: aconselho fortemente a executar estes comandos no git bash, pois ao rodar via CMD, será necessário adicionar um caractere de escape em cada aspas dentro do JSON</strong>
- Como requisitado, para testar a aplicação via curl, é necessário primeiramente se logar para receber o token de autenticação, no git bash, com a aplicação rodando, basta executar:
`curl -H "Content-type: application/json" -d '{"username": "johnDoe", "password": "1234"}' 'http://localhost:8080/auth' `
- Com este token, é possível navegar pelas urls da API de forma livre enquanto o mesmo não expirar. Abaixo, exemplos dos comandos possíveis com o Authorization header setado,(subtituia a palavra <strong>meutoken</strong> pelo token retornado ao logar, tomando cuidado para não repetir a palavra <strong>Bearer</strong>:

- <strong>Criar nova tarefa: </strong> `curl -XPOST -H 'authorization: Bearer MEUTOKEN' -H "Content-type: application/json" -d '{"summary": "thirdTask", "description": "thirdDescription" }' 'http://localhost:8080/task' `

- <strong> Listar tarefas: </strong> `curl -XGET -H 'authorization: Bearer MEUTOKEN' -H "Content-type: application/json" 'http://localhost:8080/task` É possível filtrar as tarefas por status adicionando a query string `status` ao final da url. Ex: `http://localhost:8080/task?status=pending`

- <strong> Atualizar uma tarefa: </strong>: `curl -XPUT -H 'authorization: Bearer MEUTOKEN' -H "Content-type: application/json" -d '{"summary": "new summary", "description": "thirdDescription"}' 'http://localhost:8080/task/2c93908178ec6b800178ec6d1a730000'`

- <strong> Excluir uma tarefa: </strong>: `curl -XDELETE -H 'authorization: Bearer MEUTOKEN' -H "Content-type: application/json" 'http://localhost:8080/task/2c93908178ec6b800178ec6d1a730000'`

- <strong> Para usar o modo admin</strong>, use o primeiro comando curl, passando como username admin e password 1234: `curl -H "Content-type: application/json" -d '{"username": "admin", "password": "1234"}' 'http://localhost:8080/auth'`. <br> Pegue o bearer token retornado para usar na request abaixo: `curl -XGET -H 'authorization: Bearer MEUTOKEN' -H "Content-type: application/json" 'http://localhost:8080/task/all'`

# Usuários disponíveis:
- admin(login: admin, password: 1234) - ROLE ADMIN
- johnDoe(login:johnDoe, password: 1234) - ROLE USER
- jack(login:jack, password: 1234) - ROLE USER

# Testes unitários:
- Por padrão, ao compilar o projeto com o `mvn clean package`, os testes serão executados de forma automatizada, mas é possível rodá-los de forma manual chamando `mvn test` na pasta raíz do projeto

# Sugestão de estratégia de deploy:
- Para deploy, automatizei o build e os testes com o Github Actions, ferramenta de workflow que considero excelente e tem integração com a maioria das grandes clouds como GCP, AWS ou Azure.
