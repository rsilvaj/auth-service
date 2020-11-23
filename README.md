## Spring boot service + Spring Security + JWT + memory database H2

JWT(JSON Web Token) is a stateless autorization pattern very useful, it does not need to save sessions id to keep using the validate token - as soon as the token is generate it can be used for many services that share the same secret. 
More information: https://jwt.io/

Service in Spring boot to create an JWT authentication. Guide to use and test:

1. authentication
  - user email@email.com 
  - password 12345678
2. example of services
  -  POST /api/auth body {"username": "email@email.com", "password": "12345678"}
  - GET /api/users header "Authorization Bearer TOKEN" list all users
