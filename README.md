# Documentación de la API

Esta API proporciona acceso a recursos relacionados con carros.

## BD MySql 
Antes de ejecutar el proyecto debemos crear una bd en mysql llamada carsdb 
que se ejecute en el puerto 3306, una vez ejecutemos el proyecto se crearán 
las tablas automáticamente 

## POST crear una marca 
Esta solicitud permite crear una marca
Ejemplo de uso:
POST http://localhost:8020/brands
JSON {
"name": "Renault"
}

### POST crear automovil
Ejemplo de uso:
POST http://localhost:8020/api
JSON {
"model": "2024",
"description": "kedjbvndjirfgtjiovsdnjwdsyhireknv",
"price": "93000000",
"mileage": "108615",
"brands": {
"brand_id": 1
}
}
### Consultar filtros
Esta solicitud permite filtrar autos por modelo precio y kilometraje
GET http://localhost:8020/api/filters?model=2011
    http://localhost:8020/api/filters?minPrice=20000000
    http://localhost:8020/api/filters?maxPrice=50000000
    http://localhost:8020/api/filters?mileage=108365

### Obtener todos los carros GET /api
Esta solicitud devuelve una lista de todos los carros disponibles.

Ejemplo de uso:

GET http://localhost:8020/api

### Consultar auto por ID
GET http://localhost:8020/api/1


