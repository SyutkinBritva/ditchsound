ЗАДАЧИ:
1. Разобраться что такое int size\page\sort и тд
 в параметрах Pageable. Добавить в бд 30 записей барабанов
 и протестировать в режиме дебаг работу контроллера с 
 разными вариантами параметров pageSize и pageNumber.
 Добавить в готовый метод сортировку, как это сделано 
 в parking-cards. Реализовать Pageable для всех контроллеров
 и методов FindAll
2. реализовать ВСЕ остальные методы для всех остальных entity по созданию\редактированию сущности в базе данных.
3. liquibase, добавить в проект (https://youtu.be/JTdcd4DYgEI)
4. добавить новый скрипт с liquibase "add-releaseType-varchar" 
(епи-сингл-альбом будет ENUM в джаве, varchar(128) в базе)
5. Логгирование sl4j, сохранение логов в отдельный текстовый документ на жестком диске
6. @Scheduled метод, придумать метод работающий по расписанию/ таймеру
7. Динамический поиск * (CriteriaQuery)