# App para la localización de buses a nivel intermunicipal en la ciudad e Granada
## autor: Ivan Andres Trochez Ramos
## Estado: Hito 2: Empaquetado Cloud Native, Entorno Local e Integración Continua (CI)


MiBus es una propuesta de solución tecnológica orientada a mejorar la gestión del transporte público intermunicipal mediante una arquitectura de microservicios, utilizando Java, Spring Boot, PostgreSQL y Docker. El proyecto busca establecer una infraestructura modular, escalable y portable que permita incorporar progresivamente nuevas funcionalidades relacionadas con la movilidad y el seguimiento del transporte público.

## 1. Problema de negocio
Actualmente, uno de los principales problemas del transporte público intermunicipal es la falta de información precisa y actualizada sobre los horarios, retrasos y disponibilidad de los autobuses. Las plataformas existentes pueden presentar información que no corresponde con el estado real del servicio, generando incertidumbre y tiempos de espera innecesarios para los pasajeros.

## 2. Solución
Se propone el desarrollo de MiBus, una plataforma backend basada en una arquitectura de microservicios que permita gestionar de manera independiente los diferentes dominios funcionales del sistema de transporte.

## La solución incorpora los siguientes elementos:

- Gestión de autobuses y sus estados operativos.

- Gestión de usuarios mediante operaciones CRUD.

- Autenticación mediante JSON Web Token (JWT).

- Protección de endpoints mediante Spring Security.

- Cifrado unidireccional de contraseñas mediante BCrypt.

- Persistencia independiente mediante PostgreSQL.

- Contenerización de los servicios mediante Docker.

- Orquestación local de contenedores mediante Docker Compose.

- Gestión y versionado del código fuente mediante Git y GitHub.

- Publicación de imágenes mediante GitHub Container Registry (GHCR).

- Configuración externa mediante variables de entorno.


## 3. Principales casos de uso: 

## 1. CU-01. Registro de usuarios

| Atributo | Descripción |
|---|---|
| Actor | Usuario o administrador |
| Descripción | Permite registrar un nuevo usuario en el sistema. |
| Entrada | Nombre, correo electrónico, nombre de usuario, contraseña y teléfono. |
| Resultado | Usuario registrado en la base de datos. |
| Microservicio | UserService |


### CU-02. Autenticación de usuarios

| Atributo | Descripción |
|---|---|
| Actor | Usuario |
| Descripción | Permite validar las credenciales de acceso al sistema. |
| Entrada | Correo electrónico y contraseña. |
| Resultado | Generación de un token JWT cuando las credenciales son válidas y el usuario está activo. |
| Microservicio | UserService |

### CU-03. Gestión de usuarios

| Atributo | Descripción |
|---|---|
| Actor | Usuario autorizado |
| Descripción | Permite consultar, actualizar y eliminar usuarios registrados. |
| Precondición | Autenticación válida para las operaciones protegidas. |
| Resultado | Información de usuarios consultada o modificada. |
| Microservicio | UserService |

### CU-04. Registro de autobuses

| Atributo | Descripción |
|---|---|
| Actor | Usuario autorizado |
| Descripción | Permite incorporar un nuevo vehículo al sistema. |
| Entrada | Matrícula, modelo, capacidad y estado operativo. |
| Resultado | Vehículo registrado en la base de datos. |
| Microservicio | BusService |

### CU-05. Consulta de autobuses

| Atributo | Descripción |
|---|---|
| Actor | Usuario del sistema |
| Descripción | Permite consultar los vehículos registrados y su estado operativo. |
| Resultado | Listado de autobuses disponibles en el sistema. |
| Microservicio | BusService |

### CU-06. Actualización de autobuses

| Atributo | Descripción |
|---|---|
| Actor | Usuario autorizado |
| Descripción | Permite modificar la información y el estado operativo de un vehículo. |
| Resultado | Información del vehículo actualizada. |
| Microservicio | BusService |

### CU-07. Eliminación de autobuses

| Atributo | Descripción |
|---|---|
| Actor | Usuario autorizado |
| Descripción | Permite eliminar un vehículo registrado. |
| Entrada | Identificador del vehículo. |
| Resultado | Vehículo eliminado del sistema. |
| Microservicio | BusService |



### 4. Arquitectura de datos

Se utiliza el patrón Database per Service, mediante el cual cada microservicio dispone de su propia base de datos.

| Microservicio | Base de datos | Motor |
|---|---|---|
| UserService | user_db | PostgreSQL |
| BusService | bus_db | PostgreSQL |

Este patrón proporciona:

- Independencia de los datos.
- Reducción del acoplamiento.
- Autonomía de los microservicios.
- Facilidad de mantenimiento.
- Posibilidad de escalado independiente.


### 5. Códigos de respuesta HTTP

| Código | Estado | Descripción |
|---|---|---|
| 200 | OK | Operación realizada correctamente. |
| 201 | Created | Recurso creado correctamente. |
| 204 | No Content | Operación exitosa sin contenido de respuesta. |
| 400 | Bad Request | Datos de entrada incorrectos. |
| 401 | Unauthorized | Credenciales o token de autenticación inválidos. |
| 403 | Forbidden | Usuario autenticado sin permisos suficientes. |
| 404 | Not Found | Recurso solicitado inexistente. |
| 409 | Conflict | Conflicto con un recurso existente. |
| 500 | Internal Server Error | Error interno del microservicio. |

### 6. Configuración de las variables de entorno a partir de env.example

- Crear el archivo .env

Desde la raíz del proyecto, ejecutar:

```bash
cp .env.example .env
```

- Para generar una clave segura codificada en Base64, ejecutar:

```bash
openssl rand -base64 32
```

- Copiar el resultado y reemplazar el valor de `JWT_SECRET`
en el archivo `.env`.


> [!IMPORTANT]
> Reemplazar `REPLACE_WITH_BASE64_SECRET` por la clave generada.
> Modificar las contraseñas de ejemplo antes del despliegue.
> Las credenciales de PostgreSQL deben coincidir con las
> configuradas en sus respectivos microservicios.

> [!WARNING]
> El archivo `.env` contiene información sensible y no debe
> publicarse en GitHub. Únicamente se debe versionar
> el archivo `.env.example`.
