# Considerações iniciais

É necessário ter instalado o banco PostgreSQL e criar um banco chamado "register_db" no seu servidor

### Se seu usuário tiver nome diferente de: postgres ou senha diferente de: root

* abra a pasta register-app-test e abra o arquivo applicaiton.properties localizado em ./src/main/resources
* altere para seu usuário onde está escrito "postgres" no campo: spring.datasource.username=postgres
* altere para sua senha onde está escrito "root" no campo: spring.datasource.password=root

# Rodar projeto

**Opção 1**

* entrar na pasta register-app-test
* abrir um terminal
* executar o comando "./mvnw spring-boot:run"

**Opção 2**

* abrir a pasta register-app-test em uma IDE de sua escolha
* executar o arquivo "RegisterAppTestApplication.java" localizado no diretório: ./src/main/java/chireia/pc/registerapptest

# Ferramentas utilizadas na aplicação

* Java 17
* Spring Boot
* Tomcat
* RESTFul API