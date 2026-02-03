# Traffic Police API

Spring Boot приложение для учета водителей, водительских прав, автомобилей, правонарушений и штрафов.

## Возможности

- Хранение данных о водителях, водительских правах и транспортных средствах.
- Хранение видов правонарушений с базовым штрафом и правилами повторных нарушений.
- Регистрация правонарушений с местом (город, район, область) и автоматическим расчетом штрафа.
- Автоматическое изъятие/приостановка прав при повторных нарушениях (на срок или полностью).
- Получение статистики правонарушений за период с фильтром по городу/району/области.

## Быстрый старт

```bash
mvn spring-boot:run
```

## Примеры запросов

### Создать тип правонарушения

```http
POST /api/violation-types
Content-Type: application/json

{
  "code": "12.9",
  "description": "Превышение скорости",
  "baseFine": 100.00,
  "repeatFineIncreasePercent": 50,
  "repeatSuspensionDays": 30,
  "revokeOnRepeat": false
}
```

### Создать водителя

```http
POST /api/drivers
Content-Type: application/json

{
  "fullName": "Иван Иванов",
  "birthDate": "1988-05-12",
  "licenseNumber": "AB123456",
  "issuedDate": "2020-01-01",
  "expiryDate": "2030-01-01"
}
```

### Создать автомобиль

```http
POST /api/vehicles
Content-Type: application/json

{
  "ownerId": 1,
  "plateNumber": "7777 AB-7",
  "model": "Toyota Camry"
}
```

### Зарегистрировать правонарушение

```http
POST /api/violations
Content-Type: application/json

{
  "driverId": 1,
  "vehicleId": 1,
  "violationTypeId": 1,
  "violationDate": "2024-05-30T14:30:00",
  "city": "Минск",
  "district": "Московский",
  "region": "Минская"
}
```

### Статистика по городу за период

```http
GET /api/statistics?start=2024-01-01T00:00:00&end=2024-12-31T23:59:59&city=Минск
```

Можно указать несколько значений через запятую: `city=Минск,Гомель`.
