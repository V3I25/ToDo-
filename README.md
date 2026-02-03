# Traffic Violations Service

Spring Boot приложение для учета водителей, водительских прав, транспортных средств, правонарушений и штрафов, а также получения статистики по локациям.

## Возможности
- Ведение данных о водителях и их водительских правах (активные, приостановленные, изъятые).
- Учет транспортных средств.
- Справочник видов правонарушений с базовым штрафом и правилами повторных нарушений.
- Регистрация правонарушений с автоматическим расчетом штрафа и санкций.
- Статистика нарушений за период по городам, районам, областям или по республике.

## Запуск
```bash
mvn spring-boot:run
```

## Примеры запросов
### Создать водителя
```bash
curl -X POST http://localhost:8080/api/drivers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Иван Иванов",
    "birthDate": "1985-05-10",
    "licenseNumber": "AB123456",
    "licenseIssuedDate": "2010-06-01",
    "licenseExpirationDate": "2030-06-01"
  }'
```

### Создать вид правонарушения
```bash
curl -X POST http://localhost:8080/api/violation-types \
  -H "Content-Type: application/json" \
  -d '{
    "articleCode": "12.9",
    "description": "Превышение скорости",
    "baseFineAmount": 100.0,
    "repeatFineIncreasePercent": 50.0,
    "suspensionDaysFirst": 0,
    "suspensionDaysRepeat": 30,
    "revokeOnRepeat": false
  }'
```

### Зарегистрировать нарушение
```bash
curl -X POST http://localhost:8080/api/violations \
  -H "Content-Type: application/json" \
  -d '{
    "driverId": 1,
    "vehicleId": 1,
    "violationTypeId": 1,
    "violationDate": "2024-01-15",
    "city": "Минск",
    "district": "Центральный",
    "region": "Минская"
  }'
```

### Статистика по городу
```bash
curl "http://localhost:8080/api/statistics?from=2024-01-01&to=2024-12-31&cities=Минск&scope=ГОРОД"
```
