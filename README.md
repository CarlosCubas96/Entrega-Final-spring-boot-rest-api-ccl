# Proyecto API de Alquiler de Bicicletas

Este proyecto consiste en una API para gestionar el alquiler de bicicletas. Permite a los usuarios registrados alquilar y devolver bicicletas, así como consultar su historial de alquileres. Además, proporciona endpoints para administrar la información de las bicicletas y los usuarios.

## Tecnologías Utilizadas

- Spring Boot: Marco de desarrollo de Java para crear aplicaciones basadas en Spring.
- Spring Data JPA: Facilita el acceso y la manipulación de datos en la capa de persistencia.
- H2 Database: Base de datos en memoria para desarrollo y pruebas.
- Spring Security: Proporciona autenticación y autorización para la API.
- Maven: Herramienta de gestión de proyectos y construcción de software.
- Postman: Herramienta para probar y documentar API.

## Configuración del Proyecto

### Base de Datos
La aplicación utiliza una base de datos H2 en memoria para facilitar el desarrollo. Puedes encontrar la configuración en `application.properties`.

### Seguridad
La seguridad está configurada en la clase SecurityConfig. En este ejemplo, se requiere autenticación para todas las rutas bajo "/bicycles":



## Estructura del Proyecto
El proyecto está estructurado en los siguientes paquetes:

- *com.proyectdwes.api.proyect.controllers: Controladores que manejan las solicitudes HTTP y gestionan las respuestas.*
- com.proyectdwes.api.proyect.models: Modelos de datos que representan las entidades del sistema.*
- com.proyectdwes.api.proyect.repository: Interfaces que extienden Spring Data JPA para interactuar con la base de datos.*
- com.proyectdwes.api.proyect.services: Servicios que contienen la lógica de negocio de la aplicación.*

## Documentación de la API

### Usuarios:
- *GET /users:* Obtener todos los usuarios.
- *GET /users/{userId}:* Obtener un usuario por ID.
- *POST /users:* Crear un nuevo usuario.
- *PUT /users/{userId}:* Actualizar un usuario existente.
- *DELETE /users/{userId}:* Eliminar un usuario.

### Bicicletas:
- *GET /bicycles:* Obtener todas las bicicletas.
- *GET /bicycles/{bicycleId}:* Obtener una bicicleta por ID.
- *POST /bicycles:* Crear una nueva bicicleta.
- *PUT /bicycles/{bicycleId}:* Actualizar una bicicleta existente.
- *DELETE /bicycles/{bicycleId}:* Eliminar una bicicleta.

### Alquileres:
- *POST /rentals/createRental:* Crear un nuevo alquiler.
- *GET /rentals/all:* Obtener todos los alquileres.
- *GET /rentals/{rentalId}:* Obtener un alquiler por ID.
- *GET /rentals/rentalHistory/{userId}:* Obtener el historial de alquileres de un usuario.
- *PUT /rentals/updateRental/{rentalId}:* Actualizar la fecha de finalización de un alquiler.
- *DELETE /rentals/deleteRental/{rentalId}:* Eliminar un alquiler.

## Licencia
- Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para obtener más detalles.
