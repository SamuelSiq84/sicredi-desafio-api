IDEA: Intellij-IDEA
JAVA 
Gradle
Allure report
REST-Assured
Junit 5


---------------- Cenários de Teste ------------------------

---
CT001 - Validar o método GET o retorno de USERS

Pré-condição: URL: https://dummyjson.com/users

Dado que no método GET USERS
Quando na requisição passo a URL
Então no response o status 200 da requisição a lista de USERS conforme esperado

-----
CT002 - Validar o método GET o response do ID do USER - alternativo

Pré-condição: URL: https://dummyjson.com/users

Dado que no método GET USERS
Quando na requisição passo o "{id}"
Então no response o status 201 com o assert da requisição o ID, username e password do USER

----
CT003 - Validar o método POST authorization login do USER

Pré-condição: URL: https://dummyjson.com/auth/login

Dado que no método POST AUTH_LOGIN
Quando na request body da requisição passo na estrutura JSON "username" e "password" do USER
Então no response o status 201 com o assert contendo o valor do username e o ID

----
CT004 - Validar o método GET o retorno a lista de "products" pelo authorization do USER

Pré-condição: URL: https://dummyjson.com/auth/products

Dado que no método GET AUTH_PRODUCTS
Quando na request header o authorization do USER
Então no response o status 200 com a lista de "products" conforme esperado

---
CT005 - Validar o authorization e login para o método GET o retorno da lista de "products" do USER - alternativo

Pré-condição: URL: https://dummyjson.com/auth/products

Dado que no método POST AUTH_LOGIN
Quando na request body da requisição passo na estrutura do JSON Usernam e Password do USER
Então no response o status 201 com extração do token do USER
E que no método GET AUTH_PRODUCTS
Quando na request header o authorization do USER
Então no response o status 200 com a lista de "products" conforme esperado

---
CT006 - Validar método POST add product

Pré-condição: URL: https://dummyjson.com/auth/products/add

Dado que no método POST PRODUCTS_ADD
Quando na request body passo na estrutura JSON "product"
Entáo no response o status 200 e o assert do "title" e "price" do product

----
CT007 - Validar método GET a lista de products

Pré-condição: URL: https://dummyjson.com/auth/products

Dado que no método GET PRODUCTS
Quando na request da requisição a URL
Então no response o status 200 e a lista de products

----
CT008 - Validar o método GET o response body o "ID" e "TITLE" do product - alternativo

Pré-condição: URL: https://dummyjson.com/auth/products

Dado que no método GET PRODUCTS
Quando na request da requisição a URL
Então no response o status 200 o assert body do ID e Title do product

-----
Report de execução

Bugs
nenhum bug reportado.

Melhorias
Instabilidade em algum momento retorna status code 500




