# CRUD de Productos y Categorías (API REST con Spring Boot)

Este proyecto es una aplicación backend desarrollada con Spring Boot que expone una API RESTful para gestionar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las entidades de **Producto** y **Categoría**.

## Objetivo del Laboratorio

El objetivo de este laboratorio es implementar los Web Services para el CRUD completo de las tablas `producto` y `categoría`, siguiendo los principios de una API REST. Esta implementación se realiza exclusivamente en el backend, sin la necesidad de integrar frameworks de frontend como React o Angular, ni el uso de motores de plantillas del lado del servidor. La interacción con la base de datos se maneja a través de **Hibernate** y **Spring Data JPA**.

## Tecnologías Utilizadas

- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **Hibernate** (ORM)
- **MySQL 8**
- **Lombok**
- **Maven**
- **Java 17**

## Configuración de Base de Datos

El proyecto está configurado para usar MySQL. Asegúrate de tener MySQL ejecutándose y crear la base de datos:

```sql
CREATE DATABASE comercial;
```

La configuración de conexión está en `src/main/resources/application.properties`:

```properties
# Configuración para MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/comercial?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── tecsup/edu/pe/lab15/
│   │       ├── Lab15Application.java
│   │       ├── controller/
│   │       │   ├── CategoriaController.java
│   │       │   ├── ProductoController.java
│   │       │   └── EmpleadoController.java
│   │       ├── model/
│   │       │   ├── Categoria.java
│   │       │   ├── Producto.java
│   │       │   └── Empleado.java
│   │       ├── repository/
│   │       │   ├── CategoriaRepository.java
│   │       │   ├── ProductoRepository.java
│   │       │   └── EmpleadoRepository.java
│   │       └── excepcion/
│   │           └── ResourceNotFoundExcention.java
│   └── resources/
│       └── application.properties
```

## Entidades

### Categoría
- **id**: Long (Primary Key, Auto-increment)
- **nombre**: String (100 caracteres, obligatorio)
- **descripcion**: String (255 caracteres, opcional)
- **productos**: List<Producto> (Relación One-to-Many)

### Producto
- **id**: Long (Primary Key, Auto-increment)
- **nombre**: String (100 caracteres, obligatorio)
- **descripcion**: String (255 caracteres, opcional)
- **precio**: BigDecimal (obligatorio, precisión 10, escala 2)
- **stock**: Integer (obligatorio)
- **categoria**: Categoria (Many-to-One, obligatorio)

## API Endpoints

### Entidad Categoría

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/categorias` | Listar todas las categorías |
| GET | `/api/categorias/{id}` | Obtener categoría por ID |
| POST | `/api/categorias` | Crear nueva categoría |
| PUT | `/api/categorias/{id}` | Actualizar categoría |
| DELETE | `/api/categorias/{id}` | Eliminar categoría |

#### Ejemplos de uso para Categorías:

**Crear Categoría (POST /api/categorias):**
```json
{
    "nombre": "Electrónicos",
    "descripcion": "Dispositivos electrónicos y tecnología"
}
```

**Actualizar Categoría (PUT /api/categorias/1):**
```json
{
    "nombre": "Electrónicos y Tecnología",
    "descripcion": "Dispositivos electrónicos, computadoras y accesorios tecnológicos"
}
```

### Entidad Producto

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/productos` | Listar todos los productos |
| GET | `/api/productos/{id}` | Obtener producto por ID |
| GET | `/api/productos/categoria/{categoriaId}` | Obtener productos por categoría |
| GET | `/api/productos/buscar?nombre={nombre}` | Buscar productos por nombre |
| POST | `/api/productos` | Crear nuevo producto |
| PUT | `/api/productos/{id}` | Actualizar producto |
| DELETE | `/api/productos/{id}` | Eliminar producto |

#### Ejemplos de uso para Productos:

**Crear Producto (POST /api/productos):**
```json
{
    "nombre": "Laptop Dell Inspiron",
    "descripcion": "Laptop Dell Inspiron 15 con procesador Intel i5",
    "precio": 1299.99,
    "stock": 50,
    "categoria": {
        "id": 1
    }
}
```

**Actualizar Producto (PUT /api/productos/1):**
```json
{
    "nombre": "Laptop Dell Inspiron 15",
    "descripcion": "Laptop Dell Inspiron 15 con procesador Intel i5, 8GB RAM, 256GB SSD",
    "precio": 1199.99,
    "stock": 45,
    "categoria": {
        "id": 1
    }
}
```

## Cómo ejecutar el proyecto

1. **Clonar el repositorio** (si aplica)
2. **Configurar MySQL:**
   - Instalar MySQL 8
   - Crear la base de datos `comercial`
   - Ajustar las credenciales en `application.properties`

3. **Ejecutar el proyecto:**
   ```bash
   mvn spring-boot:run
   ```

4. **El servidor estará disponible en:** `http://localhost:8080`

## Pruebas de la API

Puedes probar la API usando herramientas como:
- **Postman**
- **cURL**
- **Thunder Client** (extensión de VS Code)
- **Insomnia**

### Ejemplos con cURL:

**Listar todas las categorías:**
```bash
curl -X GET http://localhost:8080/api/categorias
```

**Crear una nueva categoría:**
```bash
curl -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Ropa","descripcion":"Prendas de vestir y accesorios"}'
```

**Listar todos los productos:**
```bash
curl -X GET http://localhost:8080/api/productos
```

**Crear un nuevo producto:**
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Camiseta Nike","descripcion":"Camiseta deportiva Nike talla M","precio":29.99,"stock":100,"categoria":{"id":1}}'
```

## Relaciones entre Entidades

- **Categoría** tiene una relación **One-to-Many** con **Producto**
- **Producto** tiene una relación **Many-to-One** con **Categoría**
- Se utilizan anotaciones `@JsonManagedReference` y `@JsonBackReference` para evitar referencias circulares en JSON

## Características Implementadas

✅ CRUD completo para Categorías  
✅ CRUD completo para Productos  
✅ Relación bidireccional entre Categoría y Producto  
✅ Búsqueda de productos por categoría  
✅ Búsqueda de productos por nombre  
✅ Validación de existencia de categoría al crear/actualizar productos  
✅ Manejo de excepciones personalizadas  
✅ Configuración CORS para permitir peticiones desde frontend  
✅ Generación automática de tablas con Hibernate  

## Notas Importantes

- Las tablas se crean automáticamente al ejecutar la aplicación (ddl-auto=update)
- Se recomienda usar `ddl-auto=create` solo en desarrollo para recrear las tablas
- La aplicación incluye logging SQL para depuración
- Los endpoints incluyen validación de datos y manejo de errores
- La API devuelve respuestas en formato JSON
