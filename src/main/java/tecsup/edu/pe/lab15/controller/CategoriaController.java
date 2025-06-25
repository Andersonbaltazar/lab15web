package tecsup.edu.pe.lab15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tecsup.edu.pe.lab15.excepcion.ResourceNotFoundExcention;
import tecsup.edu.pe.lab15.model.Categoria;
import tecsup.edu.pe.lab15.repository.CategoriaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    // GET /api/categorias - Listar todas las categorías
    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }
    
    // GET /api/categorias/{id} - Obtener categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcention("Categoría no encontrada con id: " + id));
        return ResponseEntity.ok(categoria);
    }
    
    // POST /api/categorias - Crear nueva categoría
    @PostMapping
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    // PUT /api/categorias/{id} - Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetails) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcention("Categoría no encontrada con id: " + id));
        
        categoria.setNombre(categoriaDetails.getNombre());
        categoria.setDescripcion(categoriaDetails.getDescripcion());
        
        final Categoria updatedCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.ok(updatedCategoria);
    }
    
    // DELETE /api/categorias/{id} - Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcention("Categoría no encontrada con id: " + id));
        
        categoriaRepository.delete(categoria);
        return ResponseEntity.ok().build();
    }
}
