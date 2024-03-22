# Семейный чат с просьбой перевода денег
Для начала работы:
1. Склониовать себе репозиторий
2. В файле application.yaml изменить spring.datasource.url на свою, также поменять username и password
3. Запустить проект
4. Перейти на адрес http://localhost:8080/login

Данные для тестирования:
- Учетная запись с ролью "FAMILY_HEAD"
  - Логин: user1
  - Пароль: password
- Учетная запись с ролью "USER"
    - Логин: user2
    - Пароль: password

# Структура базы данных проектка
![sberChat schema.jpg](src/main/resources/static/sberChat%20schema.jpg)