
[План](https://github.com/SantAlice/Diploma/blob/main/Project_docs/Plan.md)
[Отчёт по тестированию](https://github.com/SantAlice/Diploma/blob/main/Project_docs/Report.md)
[Отчёт по автоматизации](https://github.com/SantAlice/Diploma/blob/main/Project_docs/Summary.md)


##  Инструкция по запуску

- [ ] Клонировать репозиторий https://github.com/SantAlice/Diploma
- [ ] Открыть проект в Intellij idea
- [ ] Открыть приложение Docker для удобства манипуляций с контейнерами
- [ ] В командной строке проекта запустить контейнеры с помощью команды:
  `docker-compose -f docker-compose.yml up -d`

- [ ]  Запустить приложение на базе mysql

Запуск приложения на базе mysql с помощью команды:

`java "-Dspring.datasource.url-jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar`

- Инициировать запуск тестов с помощью команды:
  `gradlew clean test allureReport`
- После прогона тестов сформировать отчёт с помощью команды
  `gradlew allureServe`
- При желании можно установить программное обеспечение DBeaver для более удобной работы с базой данных, скриншот нужных конфигураций:

- [ ]  Запустить приложение на базе postgresql

Запуск приложения на базе postgresql с помощью команды:

 `java "-Dspring.datasource.url-jdbc:postgresql://localhost:5432/app" -jar aqa-shop.jar`

- Инициировать запуск тестов с помощью команды:
  `gradlew clean test allureReport`
- После прогона тестов сформировать отчёт с помощью команды
  `gradlew allureServe`
- При желании можно установить программное обеспечение DBeaver для более удобной работы с базой данных, скриншот нужных конфигураций:



Запоминание пароля в данном случае лучше отключить и вводить пароль вручную при подключении к соединению 
