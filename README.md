# Driver Catraca Control ID

# Microserviço de Comunicação com a Catraca Control ID

Este projeto é um microserviço desenvolvido em Java 22 para integrar e comunicar com a catraca Control ID. Embora tenha sido desenvolvido para atender às necessidades da Atlantic Nickel, o projeto é open-source e está licenciado sob a [Licença GPL](LICENSE).

## Descrição

O microserviço facilita a comunicação e o controle de acessos por meio das catracas Control ID. Baseado em Spring Boot e utilizando Spring Security com JWT para segurança e autenticação, o serviço oferece:

- Envio de comandos para a catraca
- Recebimento e processamento de eventos de acesso
- Gerenciamento de logs e acessos

## Requisitos

Antes de executar o microserviço, certifique-se de que possui os seguintes requisitos:

- Java 22
- Maven 3.6 ou superior
- SQLite (para persistência de dados)
- Configuração da catraca Control ID

## Instalação

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/seu-usuario/nome-do-repositorio.git
    ```

2. **Navegue até o diretório do projeto:**

    ```bash
    cd nome-do-repositorio
    ```

3. **Compile o projeto utilizando o Maven:**

    ```bash
    mvn clean install
    ```

4. **Configure o arquivo de propriedades:**

    Edite o arquivo `src/main/resources/application.properties` com as configurações apropriadas. Um exemplo de configuração é fornecido abaixo.

## Configuração

Preencha o arquivo `src/main/resources/application.properties` com as seguintes propriedades:

```properties
spring.application.name=turnstiles
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect

# Server
server.address=0.0.0.0
server.port=8080

# Persistence
spring.datasource.driverClassName=org.sqlite.JDBC
spring.datasource.url=jdbc:sqlite:turnstiles.sqlite
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Application
spring.application.acess.token=Bearer <YOUR_ACCESS_TOKEN>
spring.application.acess.url=http://127.0.0.1:3000
spring.application.hostname=10.10.2.130
spring.application.port=8080

# Security
security.jwt.secret-key=<YOUR_SECRET_KEY>
security.jwt.expiration-time=365
security.user=<YOUR_USERNAME>
security.password=<YOUR_PASSWORD>

# Catraca
spring.application.catraca.login=<CATRACA_LOGIN>
spring.application.catraca.password=<CATRACA_PASSWORD>
```

Certifique-se de substituir os valores de exemplo com as configurações reais do seu ambiente e da sua catraca Control ID.

## Uso

Para iniciar o microserviço, execute o seguinte comando:

```bash
mvn spring-boot:run
```

## Endpoints

O microserviço expõe os seguintes endpoints REST:

- **`GET /status`**: Verifica o status da comunicação com a catraca.
- **`POST /acessar`**: Envia um comando para a catraca para permitir acesso.

Exemplo de chamada para o endpoint de acesso:

```bash
curl -X POST http://localhost:8080/acessar -H "Content-Type: application/json" -d '{"usuarioId": "12345"}'
```

## Contribuição

Contribuições são bem-vindas! Para contribuir com este projeto:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature (`git checkout -b minha-nova-feature`).
3. Faça commit das suas alterações (`git commit -am 'Adiciona nova feature'`).
4. Faça um push para a branch (`git push origin minha-nova-feature`).
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a [Licença GPL](LICENSE).

## Contato

Para mais informações ou suporte, entre em contato com a equipe de desenvolvimento da Atlantic Nickel:

### Guilherme Rialli Oliveira 
- Email: gui.rialli@gmail.com
### Mateus Lima Ribeiro
- Email: matheusohn@gmail.com
### Ricamar Souza de Santana
- Email: ricamarsantana@gmail.com

Obrigado por utilizar e contribuir para este projeto open-source!
