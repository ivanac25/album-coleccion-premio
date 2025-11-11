# Módulo Colección & Premios (Spring Boot + MySQL)

## Cómo correr (VS Code)
1. Requisitos: Java 17, Maven, MySQL en localhost con DB `albumdb` (crear: `CREATE DATABASE albumdb;`).
2. Importar el proyecto en VS Code (Java) o IntelliJ.
3. Ejecutar:
   ```bash
   mvn spring-boot:run
   ```
4. Swagger UI: http://localhost:8080/swagger-ui/index.html

## Endpoints
- GET `/api/collection/{userId}?albumId=1` → faltantes, duplicadas y progreso.
- POST `/api/rewards/albums/1/claim?userId=1` → reclama premio (State).

## Patrones
- **State**: `PremioContext` + estados (`PremioDisponible`, `PremioReclamado`, `PremioEntregado`).
- **Observer**: `AlbumEventPublisher` + `NotificacionObserver` (se usa desde `ColeccionService.evaluarComplecionYNotificar`).

> Proyecto mínimo y didáctico para el TP. Expandir con seguridad, usuarios reales y validaciones.
