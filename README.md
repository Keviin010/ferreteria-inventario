# Ferretería El Tornillo — Sistema de Gestión de Inventario

Aplicación web full-stack para la gestión de inventario de una ferretería, desarrollada con Spring Boot, Thymeleaf y MySQL. El proyecto implementa una arquitectura en capas (controlador, servicio, repositorio) sobre el patrón MVC, e incluye tanto una interfaz web como una API REST para el mismo dominio de datos.

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Maven](https://img.shields.io/badge/Build-Maven-red)

## Sobre este proyecto

Este sistema fue construido como ejercicio práctico para consolidar el desarrollo backend y frontend dentro del ecosistema Spring. Su objetivo es demostrar el manejo de conceptos aplicados en entornos profesionales de desarrollo Java:

- Diseño de una API REST siguiendo convenciones estándar de HTTP.
- Persistencia de datos con JPA/Hibernate sobre una base de datos relacional.
- Renderizado de vistas del lado del servidor con Thymeleaf.
- Separación de responsabilidades mediante arquitectura en capas (controller, service, repository, model).
- Gestión de dependencias y ciclo de vida del proyecto con Maven.

## Características

- Listado de inventario con código, nombre, categoría, stock y precio.
- Buscador en tiempo real por nombre o categoría, con filtrado del lado del cliente.
- Registro de nuevos productos mediante formulario web.
- Edición de productos existentes.
- Eliminación de productos con confirmación previa.
- Indicador visual del estado de stock: disponible, stock bajo o agotado.
- API REST (`/api/productos`) independiente de la interfaz web (`/productos`), sobre el mismo servicio de negocio.


## Tecnologías utilizadas

- Java 25
- Spring Boot 4.1.1 (Web, Data JPA, Thymeleaf)
- Hibernate / JPA
- MySQL (mediante MySQL Connector/J)
- Thymeleaf para el renderizado de vistas
- Maven como gestor de dependencias y builds
- CSS propio, sin frameworks externos

## Estructura del proyecto

```
src/main/java/com/trabajo/ferreteria/
├── controller/
│   ├── ProductoController.java       # API REST (/api/productos)
│   └── ProductoWebController.java    # Vistas web (/productos)
├── dto/
├── models/
│   └── Producto.java
├── repository/
│   └── IProductoRepository.java
├── service/
│   ├── IProductoService.java
│   └── ProductoService.java
└── FerreteriaApplication.java

src/main/resources/
├── static/css/styles.css
├── templates/producto/
│   ├── lista.html
│   └── formulario.html
└── application.properties
```

## Requisitos previos

- JDK 25
- Maven, o el wrapper incluido (`mvnw` / `mvnw.cmd`)
- Servidor MySQL en ejecución

## Instalación y ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Keviin010/ferreteria-inventario.git
   cd ferreteria-inventario
   ```

2. Crear la base de datos en MySQL:
   ```sql
   CREATE DATABASE ferreteria;
   ```

3. Configurar la conexión en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3310/ferreteria?connectionTimeZone=UTC
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```
   El puerto debe ajustarse al que utilice el servidor MySQL local.

4. Ejecutar la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
   En Windows: `mvnw.cmd spring-boot:run`

5. Acceder desde el navegador:
   - Interfaz web: `http://localhost:8080/productos`
   - API REST: `http://localhost:8080/api/productos`

## Endpoints principales

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/productos` | Lista todos los productos (vista web) |
| GET | `/productos/nuevo` | Formulario para crear un producto |
| GET | `/productos/editar/{codproducto}` | Formulario para editar un producto |
| POST | `/productos/guardar` | Guarda un producto nuevo o editado |
| GET | `/productos/eliminar/{codproducto}` | Elimina un producto |
| GET/POST/PUT/DELETE | `/api/productos/**` | API REST equivalente en formato JSON |

## Estado actual y próximas mejoras

El proyecto se encuentra en desarrollo activo. Las siguientes funcionalidades están planificadas como siguientes pasos, orientadas a acercar el sistema a un estándar de producción:

- Autenticación de usuarios con Spring Security, incluyendo un rol de administrador con acceso diferenciado.
- Sistema de login y control de sesiones.
- Autorización basada en roles para restringir operaciones según el tipo de usuario.
- Cobertura de pruebas automatizadas: pruebas unitarias con JUnit y Mockito para la capa de servicio, y pruebas de integración con Spring Boot Test para los controladores y el acceso a datos.
- Validación de datos de entrada con Bean Validation.
- Paginación del listado de productos para conjuntos de datos más grandes.

# Agradecimientos

Este proyecto se apoyó en el contenido educativo del canal TodoCode, cuyos tutoriales sobre Spring Boot y desarrollo backend en Java fueron de utilidad durante el proceso de aprendizaje y construcción de este sistema.

## Licencia

Proyecto de uso académico y de portafolio personal. Puede adaptarse esta sección para publicarlo bajo una licencia específica, como MIT o Apache 2.0, si se desea distribuir su uso.
