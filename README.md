# FundraiseUpTask

Для запуска автотестов нужно перейти в src/test/PaymentUITests и запустить необходимые тесты.
Для параллельно запуска необходимо добавить в конфигурацию запуска параметр
```
-Djunit.jupiter.execution.parallel.enabled=true

Для получения Allure отчета необходимо прописать в Execute Maven Goal
mvn allure:serve
 ```bash
 
