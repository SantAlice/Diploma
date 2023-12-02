##  Инструкция по запуску

1. Клонируем репозиторий https://github.com/SantAlice/Diploma
2. Открыть проект в Intellij idea
3. Открыть приложение Docker для удобства манипуляций с контейнерами
4. В командной строке проекта запустить контейнеры с помощью команды:
   docker-compose -f docker-compose.yml up -d
5. Запустить приложение:
   Запуск приложения на базе mysql с помощью команды:
   java "-Dspring.datasource.url-jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar
   Запуск приложения на базе postgresql с помощью команды:
   java "-Dspring.datasource.url-jdbc:mysql://localhost:5432/app" -jar aqa-shop.jar
6. Инициировать запуск тестов с помощью команды:
   gradlew clean test allureReport
7. Сформировать отчёт с помощью команды
   gradlew allureServe
8. При желании можно установить программное обеспечение DBeaver для более удобной работы с базой данных, скриншот нужных конфигураций: 


Запоминание пароля в данном случае лучше отключить и вводить пароль вручную при подключении к соединению 
