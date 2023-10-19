# rest-with-spring


## Para a parte de banco de dados eu vou usar um container para não precisar instalar o mysql local só pr aisso.
Rodar:

docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=admin123 -e MYSQL_DATABASE=rest_with_spring_boot_erudio -p 3306:3306 mysql:8.0

