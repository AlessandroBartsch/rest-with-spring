# rest-with-spring


## Para a parte de banco de dados eu vou usar um container para não precisar instalar o mysql local só pr aisso.
Rodar:

//Cria o container e roda
docker run -it --name my-mysql-container -e MYSQL_ROOT_PASSWORD=admin123 -e MYSQL_DATABASE=rest_with_spring_boot_erudio -p 3306:3306 -d mysql:8

//Acessa o banco do container (senha admin123)
docker exec -it <ConatinerId> mysql -u root -p