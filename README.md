# desafio-sicredi
Desafio técnico Sicredi - Sessão de Votação

## Tecnologias e ferramentas
- Java 17
- Maven
- SpringBoot
- Spring Data
- Spring Web
- MySQL
- OpenAPI 3.0
- Apache JMeter
- Postman

## Projeto e Deploy
A gestão de dependência e deploy são realizados com Maven.

A classe principal da aplicação SpringBoot é [com/lucasdf/desafio/sicredi/voto/Application.java]()

## Banco de Dados
Para esta implementação foi considerado o cenário que o BD é de controle externo a aplicação.

A aplicação deve aderir ao formato do BD e o ORM não deve definir ou manipular a estrutura. 
Para este fim a **propriedade spring.jpa.hibernate.ddl-auto** foi deixada com o valor **none**.

Também é fornecido no projeto o arquivo [sql/votos.sql](sql/votos.sql) para a criação do banco.
O SQL está bem sucinto é pode ser implantado em outros bancos de dados, apesar de ser sido testado no MySQL.
Lembrando de mudar os parâmetros de configuração do arquivo [application.properties](src/main/resources/application.properties)

## Integração externa
Uma tarefa bônus do desafio pedia a integração externa com [https://user-info.herokuapp.com/users/{cpf}](https://user-info.herokuapp.com/users/%7Bcpf%7D).
Este endereço não está disponível ou não acessível, então optei por fornecer um mock dentro do projeto para funcionar como placeholder para o sistema externo, reproduzindo os possíveis retornos descritos no desafio.

O controller do mock pode ser encontrado no pacote [src/main/java/com/lucasdf/desafio/sicredi/voto/controller/mock](src/main/java/com/lucasdf/desafio/sicredi/voto/controller/mock).

Para a implementação da integração a variável **url.validacao.cpf** precisa ser ajustada para o caminho real da API externa ou de publicação do mock.

## Plano de Testes
Para a demonstração do funcionamento das operações é fornecida uma collection do Postman em [plano de teste/Desafio Sicredi - Votos.postman_collection.json](plano%20de%20teste/Desafio%20Sicredi%20-%20Votos.postman_collection.json).

O projeto também publica os contratos das APIs com Swagger-UI em [\<dominio de publicacao\>/votacao/swagger-ui/index.html](/votacao/swagger-ui/index.html)

Um roteiro de testes utilizando Apache JMeter está disponível em [plano de teste/Plano de testes - Desafio Sicredi.jmx](plano%20de%20teste/Plano%20de%20testes%20-%20Desafio%20Sicredi.jmx).