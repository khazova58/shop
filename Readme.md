# Проект "Shop"

## Проект представляет собой пример веб-приложения на основе Spring Boot для управления товарами и корзинами покупок.

### Технологии

* Java 17
* Spring Boot 3.2.0
* Spring Data JPA
* Spring Web
* Springdoc OpenAPI
* MapStruct
* H2 Database
* Liquibase
* Validation API
* Lombok

### Запуск приложения

Для локального запуска приложения требуется:

* задать логин/пароль для базы данных в переменных окружения через плейсхолдеры:
    * ${DATABASE_USERNAME}
    * ${DATABASE_PASSWORD}<br>
      Тестовый доступ по admin/root.
* выполнить следующую команду: `mvn spring-boot:run`

Приложение будет доступно по адресу http://localhost:8083

### Миграция базы данных

База данных проекта управляется с использованием Liquibase.<br>
Миграции находятся в [src/main/resources/db/changelog](src/main/resources/db/changelog).
При запуске приложения Liquibase автоматически применяет миграции для создания или обновления схемы базы данных.

### API Documentation

Документация API доступна по адресу http://localhost:8083/swagger-ui/index.html и http://localhost:8083/v3/api-docs.

### Структура проекта

* `src/main/java/com/shop`
    * `controller`: Контроллеры для обработки HTTP-запросов
        * `BasketController`: Контроллер для работы с корзиной
        * `BasketProductController` Контроллер для работы с товарами в конкретной корзине
        * `ProductController` Контроллер для работы с товарами
    * `exception` Обработка ошибок
    * `model`: Модели данных (DTO, сущности)
    * `repository`: Репозитории для взаимодействия с базой данных
    * `service`: Сервисы для бизнес-логики
    * `mapper`: Классы для маппинга между сущностями и DTO
* `src/main/resources`
    * `db/changelog` Скрипты инициализации базы данных
* `src/test/java/com/shop`: Тесты

### Логирование

Логирование осуществляется с использованием библиотеки Logback. Логи записываются в файл `logs/app.log`

### Сборка проекта

Для сборки проекта выполните команду: `./mvnw clean install`
