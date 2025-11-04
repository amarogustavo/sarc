# Diagrama lógico
<img width="989" height="574" alt="image" src="https://github.com/user-attachments/assets/ffe6de9b-9076-4a7f-b9c6-f57c221db20a" />

------

# Endpoints
### Class Service (8081)

- `GET /api/v1/classes/{code}`
- `POST /api/v1/classes`
- `POST /api/v1/classes/{code}/students/{studentId}`
- `PUT /api/v1/classes/{code}/schedule` → `{ "schedule": "MW" }`
- `GET /api/v1/disciplines?name=Intro`
- `GET /api/v1/classes/{code}/schedule`

### User Service (8082)

- `GET /api/v1/users/{registration}`
- `GET /api/v1/users?name=Alice`
- `POST /api/v1/users`

### Booking Service (8083)

- `GET /api/v1/bookings/{code}`
- `GET /api/v1/bookings/time/{time}`
- `POST /api/v1/bookings`
- `POST /api/v1/bookings/rooms`
- `POST /api/v1/bookings/peripherals`
