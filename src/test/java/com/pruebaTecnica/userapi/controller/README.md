# User API – Prueba Técnica

API REST desarrollada con **Spring Boot 3** para la gestión de usuarios, que incluye autenticación básica, documentación con Swagger, pruebas unitarias y ejecución en contenedores con Docker.

---

## 🚀 Tecnologías utilizadas

* Java 21
* Spring Boot 3
* Spring Web
* Spring Validation
* Swagger / OpenAPI
* JUnit 5
* Mockito
* Docker / Docker Compose
* Maven

---

## 📌 Funcionalidades

* 🔐 Login de usuario (`/auth/login`)
* 👥 CRUD de usuarios (`/users`)

  * Obtener usuarios (con filtro y ordenamiento)
  * Crear usuario
  * Actualizar usuario
  * Eliminar usuario
* 📄 Documentación interactiva con Swagger
* 🧪 Pruebas unitarias (service + controller)

---

## ⚙️ Cómo ejecutar el proyecto

### 🔹 Opción 1: Ejecutar localmente

```bash
./mvnw spring-boot:run
```

Acceder a:

```
http://localhost:8080/swagger-ui/index.html
```

---

### 🔹 Opción 2: Ejecutar con Docker

```bash
docker compose up --build
```

Acceder a:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 Ejecutar pruebas

```bash
./mvnw test
```

---

## 📚 Endpoints principales

### 🔐 Login

```
POST /auth/login
```

**Request:**

```json
{
  "username": "user1",
  "password": "123456"
}
```

**Response:**

* 200 OK → Login correcto
* 401 Unauthorized → Credenciales incorrectas

---

### 👥 Usuarios

| Método | Endpoint    | Descripción        |
| ------ | ----------- | ------------------ |
| GET    | /users      | Obtener usuarios   |
| POST   | /users      | Crear usuario      |
| PATCH  | /users/{id} | Actualizar usuario |
| DELETE | /users/{id} | Eliminar usuario   |

---

## 🧠 Decisiones técnicas

* Se utilizó **DTOs** para desacoplar la capa de presentación.
* Se implementó manejo de errores devolviendo **HTTP 401** en autenticación fallida.
* Se usó **MockMvc** para pruebas de endpoints sin levantar servidor real.
* Se utilizó **Mockito** para simular dependencias en tests.
* Se estructuró el proyecto en capas: `controller`, `service`, `dto`, `model`.

---

## 🧪 Cobertura de pruebas

* ✔ UserService (lógica de negocio)
* ✔ AuthController (login)
* ✔ Validación de respuestas HTTP

---

## 🐳 Docker

El proyecto incluye:

* `Dockerfile`
* `docker-compose.yml`

Permite levantar la aplicación en un contenedor fácilmente.

---

## 👨‍💻 Autor

Desarrollado como parte de una prueba técnica backend.

---

## 📌 Notas

* No se utiliza base de datos (datos en memoria).
* Proyecto enfocado en demostrar arquitectura, testing y buenas prácticas.

---
