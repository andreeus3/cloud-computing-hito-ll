## 1. UserService

URL base de referencia: `http://localhost:8091`

| Método | Endpoint | Descripción |
|---|---|---|
| POST | /auth/login | Autenticación de usuarios. |
| GET | /user/allUsers | Consulta de todos los usuarios. |
| GET | /user/findById/{id} | Consulta de un usuario por identificador. |
| POST | /user/add-user | Registro de un usuario. |
| PATCH | /user/update-user/{id} | Actualización de un usuario. |
| DELETE | /user/delete-use/{id} | Eliminación de un usuario. |

## 1.1. Importante: Crear un usuario

Antes de realizar una petición de login se debe crear un usuario. El cual la configuración esta adecuada para que /auth/login y /user/add-user
Se puedan realizar sin necesidad de token JWT

```json
{
  "name": "Usuario Ejemplo",
  "email": "usuario@example.com",
  "username": "usuario01",
  "password": "examplePassword",
  "phone": "600000000",
  "active": true 
}
```

## 1.2. Autenticación de usuarios

**Método:** POST

**Endpoint:** `/auth/login`

**Cuerpo de la petición:**

```json
{
  "email": "usuario@example.com",
  "password": "examplePassword"
}
```

## 1.3. Autenticación de los otros métodos *CONFIGURACIÓN ÚNICA PARA USER SERVICE* 

Una vez inicie sesión el usuario, copiar el JWT generado y utilizarlo en la petición de los otros métodos. 

<img width="938" height="216" alt="image" src="https://github.com/user-attachments/assets/1d2e2a51-d711-4488-a191-6798d82b037f" />

Se recomienda crear un script en postman para que al momento de genera un nuevo inicio de sesión se actualice automáticamente y no se 
deba hacer el proceso manualmente. 

Este script debe ir en la consulta de postman - *auth/login* y dar permisos a postman de acceder al vault. 

```js
const response = pm.response.json();
pm.vault.set("login_jwt", response.token);
```

### 2. BusService

URL base de referencia: `http://localhost:8081`

| Método | Endpoint | Descripción |
|---|---|---|
| GET | /bus/all-buses | Consulta de todos los autobuses. |
| GET | /bus/getById/{id}| Consulta de un autobús por identificador. |
| GET | /bus/add-many-buses | Agregar muchos buses a la vez. |
| POST | /bus/add-bus| Registro de un autobús. |
| PATCH | /bus/update-bus/{id} | Actualización de un autobús. |
| DELETE | /bus/delete-bus/{id} | Eliminación de un autobús. |


## 2.1 Registro de un autobus

**Método:** POST

**Endpoint:** `/bus/add-bus`

**Cuerpo de la petición:**

```json
{
  "plate": "LMN-321",
  "model": "2025",
  "capacity": 50,
  "status": "OUT_OF_SERVICE" -> TIENE 3 VALORES DEBIDO A QUE ES UN ENUM {AVAILABLE,IN_SERVICE,OUT_OF_SERVICE}
}
```

**Respuesta de referencia — HTTP 201 Created:**

```json
{
  "id": 1,
  "plate": "LMN-321",
  "model": "2025",
  "capacity": 50,
  "status": "OUT_OF_SERVICE"
}
```

