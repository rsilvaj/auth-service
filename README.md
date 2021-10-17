## Spring boot service + Spring Security + JWT + memory database H2

JSON Web Tokens are an open, industry standard RFC 7519 method for representing claims securely between two parties.
More information: https://jwt.io/

Service in Spring Boot to create an JWT authentication. Guide to use and test:

1. authentication
  - user email@email.com 
  - password 12345678
2. example of services
  -  POST /api/auth body {"username": "email@email.com", "password": "12345678"}
  - GET /api/users header "Authorization Bearer TOKEN" list all users
