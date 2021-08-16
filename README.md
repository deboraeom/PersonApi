"# PersonApi" 
Esse é uma api para controle de pessoas, nele é possível registrar as informações de pessoas assim como criar lista de telefones e informações de saude:<br>
As tecnologias utilizadas são:<br>
<ul>
<li>Java</li>
<li>Spring Boot</li>
<li>Jpa</li>
<li>Mysql</li>
<li>Junit</li>
</ul><br>

Os dados de pessoas a serem registrados são:<br>
<ul>
<li>firstName</li>
<li>lastName</li>
<li>birthDate</li>
<li>cpf</li>
<li>phones</li>
<li>healthiInfo</li>
</ul><br>
<br>

Será necesserário criar um banco de dandos Mysql com nome person, e um usuário Maria com as permissões de criar, alterar, deletar tabelas, assim como as permissões necessárias para manipular os dados da tabela, no mysql faça: <br> 
<ul>
<li>create user maria;</li>
<li>create database person;</li>
<li>GRANT ALL PRIVILEGES ON person. * TO 'maria'@'localhost';</li>
</ul><br>

Ou reconfigurar o aquivo ApplicationProperties de acordo com seu banco e nomes de preferência<br>

Para listar, criar, e atualizar  "Person" use a url:<br>
http://localhost:8080/api/v1/person<br>

Ao criar envie no corpo da mensagem os dados da pessoa sem id(caso envie será ignorado).<br>
E ao atualizar envie no corpo da mensagem os dados da pessoa com id.<br>

Para excluir ou procurar uma pessoa específica use a url com id da pessoa:<br>
http://localhost:8080/api/v1/person/id<br>

Exemplo: pessoa com o id 1:<br>
http://localhost:8080/api/v1/person/1<br>

O retorno das exceptions são padronizadas:<br>

Quando se envia com preenchimento errado, retorna-se:<br>

{<br>
    "title": "Bad Request Exception, Invalid Fields",<br>
    "status": 400,<br>
    "details": "Check the field(s) error",<br>
    "developerMessage": "org.springframework.web.bind.MethodArgumentNotValidException",<br>
    "timestamp": "[Hora que ocorreu]",<br>
    "fields": "[campos que ocorreram",<br>
    "fieldsMessage": "[o que faltou]"<br>
}<br>

Quando a pessoa requisitada não existe retorn-se<br>:

{<br>
    "title": "NOT FOUND",<br>
    "status": 404,<br>
    "details": "Person with ID [id] not found!",<br>
    "developerMessage": "com.example.PersonApi.Exception.PersonNotFound",<br>
    "timestamp": "[Hora que ocorreu]"<br>
}<br>

Essa api tem testes unitários desenvolvidos com Junit<br>




