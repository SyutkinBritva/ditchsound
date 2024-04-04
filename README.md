ЗАДАЧИ:
1. Разобраться что такое int size\page\sort и тд
 в параметрах Pageable. Добавить в бд 30 записей барабанов
 и протестировать в режиме дебаг работу контроллера с 
 разными вариантами параметров pageSize и pageNumber.
 Добавить в готовый метод сортировку, как это сделано 
 в parking-cards. Реализовать Pageable для всех контроллеров
 и методов FindAll
2. реализовать ВСЕ остальные методы для всех остальных entity по созданию\редактированию сущности в базе данных.
3. заменить bigint для primary key на serial, почитать что такое sequence в базе данных
4. liquibase, добавить в проект (https://youtu.be/JTdcd4DYgEI)
5. добавить новый скрипт с liquibase "add-releaseType-varchar" 
(епи-сингл-альбом будет ENUM в джаве, varchar(128) в базе)
6. Логгирование sl4j, сохранение логов в отдельный текстовый документ на жестком диске
7. @Scheduled метод, придумать метод работающий по расписанию/ таймеру
8. Динамический поиск * (CriteriaQuery)






666. Добавить .sql скрипт, который не создает таблицы, а наполняет их тестовыми данными. 
Может случится так что придется дропать базу, и с нуля заполнять её каждый раз тестовыми 
релизами/жанрами/барабанами будет герморойно, и поэтому можно будет вручную этот скрипт 
запускать, который будет insert'ить пачку всех сущностей во все таблицы. 
Можно его даже не добавлять к liquibase (обычно в ликвибейзе хранится именно структура 
таблиц базы данных, без её наполнения)