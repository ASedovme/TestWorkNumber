Spring Boot Application with Swagger UI
Этот проект — пример Spring Boot приложения с интеграцией Swagger UI для автоматической документации REST API.
Выполнен в рамках технического тестового задания. Суть программы принять файл EXEL, где в столбик указаны простые числа, принять число N, в сервисе построить последовательность чисел по возрастанию и найти минимальное по порядку N число.

Требования
Java 11 или выше
Maven или Gradle (зависит от используемой системы сборки)
IDE (например, IntelliJ IDEA, Eclipse) — по желанию
Как запустить проект
1. Клонируйте репозиторий
bash
Копировать код
git clone https://github.com/ASedovme.git
cd TestWorkNumber

или по ссылке в IDEA https://github.com/ASedovme/TestWorkNumber
2. Соберите проект
Для Maven:
bash
Копировать код
mvn clean install
Для Gradle:
bash
Копировать код
gradle build
3. Запустите приложение
Для Maven:
bash
Копировать код
mvn spring-boot:run


Доступ к API и документации
Запуск приложения:
По умолчанию приложение запускается на http://localhost:8080

Swagger UI:
Документация API доступна по адресу:
http://localhost:8080/swagger-ui/index.html

