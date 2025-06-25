package tecsup.edu.pe.lab15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tecsup.edu.pe.lab15.excepcion.ResourceNotFoundExcention;
import tecsup.edu.pe.lab15.model.Categoria;
import tecsup.edu.pe.lab15.model.Producto;
import tecsup.edu.pe.lab15.repository.CategoriaRepository;
import tecsup.edu.pe.lab15.repository.ProductoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    // GET /api/productos - Listar todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    
    // GET /api/productos/{id} - Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcention("Producto no encontrado con id: " + id));
        return ResponseEntity.ok(producto);
    }
    
    // GET /api/productos/categoria/{categoriaId} - Obtener productos por categoría
    @GetMapping("/categoria/{categoriaId}")
    public List<Producto> getProductosByCategoria(@PathVariable Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    
    // GET /api/productos/buscar?nombre={nombre} - Buscar productos por nombre
    @GetMapping("/buscar")
    public List<Producto> buscarProductosPorNombre(@RequestParam String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    // POST /api/productos - Crear nuevo producto
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        // Validar que la categoría existe
        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(() -> new ResourceNotFoundExcention("Categoría no encontrada con id: " + producto.getCategoria().getId()));
        
        producto.setCategoria(categoria);
        Producto savedProducto = productoRepository.save(producto);
        return ResponseEntity.ok(savedProducto);
    }
    
    // PUT /api/productos/{id} - Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcention("Producto no encontrado con id: " + id));
        
        // Validar que la categoría existe
        Categoria categoria = categoriaRepository.findById(productoDetails.getCategoria().getId())
                .orElseThrow(() -> new ResourceNotFoundExcention("Categoría no encontrada con id: " + productoDetails.getCategoria().getId()));
        
        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStock(productoDetails.getStock());
        producto.setCategoria(categoria);
        
        final Producto updatedProducto = productoRepository.save(producto);
        return ResponseEntity.ok(updatedProducto);
    }
    
    // DELETE /api/productos/{id} - Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcention("Producto no encontrado con id: " + id));
        
        productoRepository.delete(producto);
        return ResponseEntity.ok().build();
    }
}
