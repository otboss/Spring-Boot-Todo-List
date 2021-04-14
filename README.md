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
    </li>
    <li>
        Exit the postgres client and start the Spring Boot server using the command:
        <pre>mvn spring-boot:run</pre>
    </li>
</ol>


<h3>Some Curl Commands</h3>
<span>Create Account</span>
<pre>curl -d '{"email":"example@gmail.com", "password":"password"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/auth/register</pre>
<span>Sign In</span>
<pre>curl -d '{"email":"example@gmail.com", "password":"password"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/auth/login</pre>