# Coleta Consciente
<i>Baseando-se nos Objetivos de Desenvolvimento Sustentável da Agenda 2030 (ONU) e verificando-se a importância da destinação regular de resíduo urbano na transformação das cidades em 
infraestruturas sustentáveis e resilientes, assegurando a conservação da qualidade do solo e corpos d'águas, há a idealização de uma API Rest para conectar cidadãos aos serviços de coleta
e descarte residual.</i>

<h2>Pré-requisitos:</h2><a id="pre-requisitos"></a>

Antes de começar você precisará ter em sua máquina as seguintes ferramentas: [Git](https://git-scm.com/),
[Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html), [Maven](https://maven.apache.org/download.cgi) e ferramentas do [SQL](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads). 
Além disto, é necessário ter um editor para trabalhar com o código como o [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download/).
 
<h2>Tecnologias:</h2><a id="tecnologias"></a>
  
 <ul>
  <li>Java 11</li>
  <li>Maven</li>
  <li>Spring Boot</li>
  <li>JPA</li>
  <li>Hibernate</li>
  <li>Microsoft SQL Server</li>
  <li>Lombok</li>
  </ul>
  
  <h2>Endpoints:</h2>
  <h3>Cadastrar um novo cliente:</h3>
  Requisição POST para /cliente/cadastrar  e envio dos parâmetros exigidos pela classe ClienteRequest como exemplo abaixo.
 
```html
 {
  "cpf": 109192,
  "nome": "Ricardo",
  "sobrenome": "Francisco ",
  "estado": "RJ",
  "cidade": "Rio de Janeiro",
  "endereco": "Rua Tenente-Coronel Cunha",
  "tipoResiduo": "RADIOATIVO",
  "quantidade": 20
   }
```

  
  <h3>Cadastrar um novo ponto de coleta:</h3>
  Requisição POST para /ponto-coleta/cadastrar e envio dos parâmetros exigidos pela classe PontoColetaRequest como exemplo abaixo.
  

```html
  {
  "cnpj": 210235,
  "email": "sistema@felipeeyasmincasanoturnaltda.com.br",
  "estado": "SP",
  "cidade": "São Paulo",
  "endereco": "Rua Emílio Bramante",
  "nomeSocial": "Felipe e Yasmin Casa Noturna Ltda",
  "preco": 23,
  "tipoEmpresa": "PRIVADO",
  "tipoResiduo": "INFECTANTE",
  "tipoServico": "RETIRADA"
  }
```
  
  <h3>Consulta:</h3>
  Requisição GET para /servico e envio dos parâmetros exigidos pela classe PontoColetaRequest:
  
  ```html
  {
  "estado": "SP",
  "tipoEmpresa": "PRIVADO",
  "tipoResiduo": "INFECTANTE",
  "tipoServico": "RETIRADA",
  "preco": 25,
  "cpf": 109192
  }
  ```
  
  
  <h3>Atualização de cliente:</h3>
  Requisição PUT para /cliente/atualizar e envio dos parâmetros exigidos pela classe ClienteRequest como exemplificado acima.
  
  <h3>Atualização de ponto de coleta:</h3>
  Requisição PUT para /ponto-coleta/atualizar e envio dos parâmetros exigidos pela classe ClienteRequest como exemplificado acima.
    
  <h3> Login e Logout:</h3>
  O endpoint de login e logout são definidos pelo método .formLogin() implementado na classe WebSecutiryConfig baseada nas configurações do Spring Security.
  Rrequisição POST para /login e /logout e envio dos parâmetros exigidos pela classe Usuario:
  
  ```html
 {
 "email": ricardofrancisco@email.com
 "senha": *****
 }
  ```
  
  
  <i>Os demais métodos foram reservados ao perfil ADMIN.</i>
     
  <h2> Documentação completa:</h2>  
  
  Inicie a aplicação e siga para o [Swagger](http://localhost:8080/swagger-ui.html).  
  A API roda na porta 8080, caso ela esteja ocupada, basta entrar no arquivo `application.properties` da pasta `resources` e atribuir um novo valor na linha que contém `server.port=NOVO VALOR`. 
  Lembre-se de alterá-la também na url do localhost.



