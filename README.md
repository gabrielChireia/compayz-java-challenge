# Desafio de Clientes, Produtos e Pedidos

Você deverá criar 2 aplicações para cadastramento de clientes, produtos e pedidos:

**Back-end:** aplicação JavaEE baseada em Web Services no padrão RESTful JAX-RS.

**Front-end:** Single Page Application que se comunique com estes serviços.

**Requisitos:**

- Permitir o cadastro de clientes
- Permitir o cadastro de produtos
- Permitir o registro de pedidos para clientes

O cadastro do cliente deve conter as seguintes informações:

* Código (Número Sequencial Automático)
* Nome (Campo Obrigatório)
* CPF (Validar CPF e máscara)
* Telefone (Campo Obrigatório com máscara)
* E-mail (Validar E-mail, máscara e campo obrigatório)

O cadastro de produto deve contar as seguintes informações:

* Código (Número Sequencial Automático)
* Descrição (Campo Obrigatório)
* Unidade (Campo Obrigatório)
* Valor (Campo Obrigatório)

Os Pedidos contém as seguintes informações:

* Numero da Pedido (Número Sequencial Automático)
* Data de emissão (Data automática)
* Descrição (Apenas texto)
* Lista de Produtos (Obrigatório ao menos 1 produto)
* Valor Total (Deverá ser calculado através da soma do valor de todos os produtos vinculados ao pedido)

### Tecnologias

Escolha umas das opções abaixo para implementar sua solução. A modelagem dos dados fica a seu critério. Não se preocupe com autenticação.

#### BACK-END

**Opção 1**

* Aplicação JavaEE utilizando framework Quarkus(preferencial) ou Spring
* Banco de dados PostgreSQL
* RESTFul API ou Jersey JAX-RS

**Opção 2**

* Aplicação pura Java EE (não utilize Struts, EJB, etc)
* RESTful API JAX-RS utilizando Servlets ou framework Jersey
* Banco de dados SQL (MySQL, PostgreSQL, HSQLDB) com JPA

####  FRONT-END
*  Vue.js com Nuxt (preferencialmente)
* React
* Single Page Application utilizando apenas HTML5 e CSS3
* Javascript puro / JQuery (e plugins)

Utilizar Bootstrap (http://getbootstrap.com/)

**Recomendações gerais:**

* Não utilize frameworks ou BD que não foram indicados
* Para servidor de aplicação utilize Jetty ou Tomcat (Não utilize: JBOSS, Wildfly ou qualquer outro servidor. Por quê? Critério de facilidade de configuração)
* Utilize o Maven para gerenciamento de dependências


### Arquitetura e documentação

No arquivo README do projeto explique o funcionamento e a arquitetura da solução adotada na sua implementação. Descreva também os passos para executar corretamente seu projeto.

### Avaliação

Entre os critérios de avaliação estão:

* Facilidade de configuração do projeto
* Performance
* Código limpo e organização
* Documentação de código
* Documentação do projeto (readme)
* Arquitetura
* Boas práticas de desenvolvimento
* Design Patterns

### DICA

* Utilize testes automatizados para inserir os dados
* Utilize alguma ferramenta(eq. Postman) para realizar as chamadas via get/post para inserir e exibir os dados
