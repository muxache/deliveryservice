# *Технологии разработки программного обеспечения*
## *Лабораторная работа № 1: создание микросервиса на Spring Boot с базой данных*
### *Черезов Михаил Сергеевич ЗМАС2031*
### *Цель лабораторной работы:*
Знакомство с проектированием многослойной архитектуры Web-API (веб-приложений, микро-сервисов).
Сооздание микросервиса, реализующего CRUD  на примере сервиса службы доставки.
### *Инструкция по использованию*
deliveryservice-это сервис написанный на JAVA с использованием фреймворка Spring, с добавленной БД postgres в виде docker образа.

---

1.Загрузите проект из Github. Для клонирования репозитория необходимо выполнить команду:



`git clone https://github.com/muxache/deliveryservice.git`



2.Необходимо подготовить виртуальную среду для развёртывания БД . Установите [Docker Toolbox](https://github.com/docker/toolbox/releases), 


3.Установите postgresql  (локально или на удаленном сервере). Чтобы установить PostgreSQL образ, вы можете выполнить следующую команду в Docker Quickstart Terminal:



`docker pull postgres`



4.Подготовка базы данных для добавления к Spring-приложению. Чтобы запустить Postgres Docker-контейнер, необходимо выполнить следующие команды в Docker Quickstart Terminal:



` docker run -d --name postgres-docker -e POSTGRES_PASSWORD=root -p 5432:5432`



5.Добавляем базу данных (БД) в Spring приложение. Зайдите в репозиторий с проектом `...\src\main\resources`. Заполните следующие поля в файле `...\src\main\resources..application.properties`.

Настройки БД для Postgres:



`spring.datasource.username=postgres
spring.datasource.password=root
spring.datasource.url=jdbc:postgresql://XXX.XXX.XX.XX:5432/postgres`



где, XXX.XXX.XXX.XXX:5432 -IP адрес виртуальной машины Docker

Чтобы узнать IP виртуальной машины Docker, необходимо в Docker Terminal выполнить следующую команду:



`docker-machine ip default`



После настройки БД, необходимо пересобрать приложение, используя для этого [maven](https://maven.apache.org/download.cgi). 

6.Для сборки приложения из командной строки с помощью maven, необходимо выполнить следующую команду из корневой директории проекта:



`./mvn package`
  
  
  
  Создастся JAR-файл в директории проекта `.../target/XXX-1.0.jar`. Необходимо в Dockerfile заменить строчки для пересобранного проекта:
  
  
  
`COPY ./target/${JAR_FILE} /apps/app.jar`

`ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/XXX-1.0.jar"]`



где, XXX-1.0.jar -ваш пересобранный проект (deliveryservice-1.0.jar)
  

7.Создаем образ Docker для того, чтобы запустить приложения в контейнере. Чтобы создать образ, необходимо выполнить следующую команду из директории проекта:



`docker buil . -t <ImageName>`



8.В терминале Docker запускаем контейнер с нашим приложением следующей командой:



`docker run -p 8080:8080 <ImageName>`
  
  
  
  -p, означает что порт в локальной системе привязан к порту  docker.
  
  ---

### *Примеры CURL запросов:*

Где,  XXX.XXX.XXX.XXX:8080 -  IP и порт виртуальной машины  docker на которой работает приложение.


1. Получить список курьеров:



`curl  http://XXX.XXX.XXX.XXX:8080/api/deliver`



В ответ будет получен JSON ответ с информацией о всех курьерах которые имеются в БД.


2. Получить информацию о курьере по id:



`curl  http://XXX.XXX.XXX.XXX:8080/api/deliver/{id} `



В ответ будет получен JSON ответ с информацией о пользователе.


3. Добавить запись о курьере:

Формат: `{firstname: "string", lastname: "string "}`



`curl -X POST -d "{"firstname": "Ivan", "lastname": "Ivanov"}" http://XXX.XXX.XXX.XXX:8080/api/deliver -H "Content-Type:application/json"`



4. Удалить запись о курьере:




`curl -i -X DELETE http://XXX.XXX.XXX.XXX:8080/api/deliver/{id}`





5. Обновить запись о курьере:




`curl -X PUT -d "{"id": *,"firstname": "Ivan", "lastname": "Sidorov"}" http://XXX.XXX.XXX.XXX:8080/api/deliver -H "Content-Type:application/json"`





6. Получит значение hostname:



`curl  http://XXX.XXX.XXX.XXX:8080/api/hostname` 



В ответ будет получен JSON в виде {hostname: "hostname"}.

---
## *Лабораторная работа №3: CI/CD и деплой приложения в Heroku*
### *Цель лабораторной работы:*
Знакомство с CI/CD и его реализацией на примере Travis CI и Heroku.

---
### *Ссылка на развернутое приложение на платформе Heroku:*

<https://deliveryservice-lab3.herokuapp.com/api/hostname>

<https://deliveryservice-lab3.herokuapp.com/api/deliver>

<https://deliveryservice-lab3.herokuapp.com/api/product>

<https://deliveryservice-lab3.herokuapp.com/api/order>

### *Ссылка на отчет о сборке на travis-ci.org:*

<https://travis-ci.org/github/muxache/deliveryservice/builds/744376073>
