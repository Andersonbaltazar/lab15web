# Ejemplos de Peticiones para la API REST de Productos y Categorías

## Categorías

### 1. Crear una nueva categoría
```bash
curl -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Electrónicos","descripcion":"Dispositivos electrónicos y tecnología"}'
```

### 2. Crear otra categoría
```bash
curl -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Ropa","descripcion":"Prendas de vestir y accesorios"}'
```

### 3. Obtener todas las categorías
```bash
curl -X GET http://localhost:8080/api/categorias
```

### 4. Obtener categoría por ID
```bash
curl -X GET http://localhost:8080/api/categorias/1
```

### 5. Actualizar categoría
```bash
curl -X PUT http://localhost:8080/api/categorias/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Electrónicos y Tecnología","descripcion":"Dispositivos electrónicos, computadoras y accesorios tecnológicos"}'
```

### 6. Eliminar categoría
```bash
curl -X DELETE http://localhost:8080/api/categorias/2
```

## Productos

### 1. Crear un nuevo producto
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Laptop Dell Inspiron","descripcion":"Laptop Dell Inspiron 15 con procesador Intel i5","precio":1299.99,"stock":50,"categoria":{"id":1}}'
```

### 2. Crear otro producto
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Mouse Logitech","descripcion":"Mouse inalámbrico Logitech MX Master 3","precio":99.99,"stock":100,"categoria":{"id":1}}'
```

### 3. Crear un tercer producto
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Teclado Mecánico","descripcion":"Teclado mecánico RGB para gaming","precio":159.99,"stock":75,"categoria":{"id":1}}'
```

### 4. Obtener todos los productos
```bash
curl -X GET http://localhost:8080/api/productos
```

### 5. Obtener producto por ID
```bash
curl -X GET http://localhost:8080/api/productos/1
```

### 6. Obtener productos por categoría
```bash
curl -X GET http://localhost:8080/api/productos/categoria/1
```

### 7. Buscar productos por nombre
```bash
curl -X GET "http://localhost:8080/api/productos/buscar?nombre=laptop"
```

### 8. Actualizar producto
```bash
curl -X PUT http://localhost:8080/api/productos/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Laptop Dell Inspiron 15","descripcion":"Laptop Dell Inspiron 15 con procesador Intel i5, 8GB RAM, 256GB SSD","precio":1199.99,"stock":45,"categoria":{"id":1}}'
```

### 9. Eliminar producto
```bash
curl -X DELETE http://localhost:8080/api/productos/3
```

## Comandos PowerShell (para Windows)

Si estás usando PowerShell en Windows, usa estos comandos en su lugar:

### Crear categoría (PowerShell)
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/categorias" -Method POST -ContentType "application/json" -Body '{"nombre":"Electrónicos","descripcion":"Dispositivos electrónicos y tecnología"}'
```

### Obtener todas las categorías (PowerShell)
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/categorias" -Method GET
```

### Crear producto (PowerShell)
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/productos" -Method POST -ContentType "application/json" -Body '{"nombre":"Laptop Dell Inspiron","descripcion":"Laptop Dell Inspiron 15 con procesador Intel i5","precio":1299.99,"stock":50,"categoria":{"id":1}}'
```

### Obtener todos los productos (PowerShell)
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/productos" -Method GET
```

## Datos de Ejemplo JSON

### Categoría
```json
{
    "nombre": "Electrónicos",
    "descripcion": "Dispositivos electrónicos y tecnología"
}
```

### Producto
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

## Respuestas Esperadas

### Respuesta de Categoría creada
```json
{
    "id": 1,
    "nombre": "Electrónicos",
    "descripcion": "Dispositivos electrónicos y tecnología"
}
```

### Respuesta de Producto creado
```json
{
    "id": 1,
    "nombre": "Laptop Dell Inspiron",
    "descripcion": "Laptop Dell Inspiron 15 con procesador Intel i5",
    "precio": 1299.99,
    "stock": 50,
    "categoria": {
        "id": 1,
        "nombre": "Electrónicos",
        "descripcion": "Dispositivos electrónicos y tecnología"
    }
}
```

## Herramientas Recomendadas para Pruebas

1. **Postman** - Interfaz gráfica para probar APIs
2. **Thunder Client** - Extensión de VS Code para pruebas de API
3. **Insomnia** - Cliente REST alternativo
4. **cURL** - Línea de comandos (incluido en ejemplos arriba)
5. **PowerShell Invoke-RestMethod** - Para usuarios de Windows

## Base de Datos

Antes de ejecutar el proyecto, asegúrate de:

1. Tener MySQL ejecutándose en el puerto 3306
2. Crear la base de datos: `CREATE DATABASE comercial;`
3. Configurar las credenciales en `application.properties`

Las tablas se crearán automáticamente al ejecutar la aplicación por primera vez.
