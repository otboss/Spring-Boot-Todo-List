<h1>Simple Spring Boot TO-DO</h1>

<span>A spring boot application that uses MVC and the Hibernate ORM to conect to a Postgres database</span>

<h3>Getting Started</h3>
<ol>
    <li>
        Start Postgres database using the command:
        <pre>docker-compose up</pre>
        <span>[Development Only]: Once the database server has been started it can be connected to using the command:</span>
        <pre>psql -h localhost -p 5440 -U admin</pre>
        Once prompted for the password enter: <b>admin100%</b>
    </li>
    <li>
        Once signed into the postgres client cli tool, create the todo database using the command
        <pre>CREATE DATABASE todo;</pre>
        The database can also be created using the single command below:
        <pre>PGPASSWORD=admin100% psql -U admin -h localhost -p 5440 -c 'create database todo;'</pre>
    </li>
    <li>
        Exit the postgres client and start the Spring Boot server using the command:
        <pre>mvn spring-boot:run</pre>
    </li>
</ol>

<h3>Testing</h3>
<span>Test this project using the command:</span>
<pre>mvn test</pre>

<h3>Sample Curl Requests</h3>
<span>Create Account</span>
<pre>curl -d '{"email":"example@gmail.com", "password":"password"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/auth/register</pre>
<span>Sign In</span>
<pre>curl -d '{"email":"example@gmail.com", "password":"password"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/auth/login</pre>
<span>Create TODO List Item</span>
<pre>curl -d '{"entry": "my test todo item"}' -H "Authorization: Bearer [JWT TOKEN]" -H "Content-Type: application/json" -X POST 'http://localhost:8080/api/v1/list'</pre>
<span>Get TODO List Items</span>
<pre>curl -H "Authorization: Bearer [JWT TOKEN]" -H "Content-Type: application/json" -X GET 'http://localhost:8080/api/v1/list?filter=&offset=0&limit=40'</pre>
<span>Update TODO List Item</span>
<pre>curl -d '{"id": 1, "entry": "hello world 2", "checked": true}' -H "Authorization: Bearer [JWT TOKEN]" -H "Content-Type: application/json" -X PUT 'http://localhost:8080/api/v1/list'</pre>
<span>Delete TODO List Item</span>
<pre>curl -d '{"id": 1}' -H "Authorization: Bearer [JWT TOKEN]" -H "Content-Type: application/json" -X DELETE 'http://localhost:8080/api/v1/list'</pre>