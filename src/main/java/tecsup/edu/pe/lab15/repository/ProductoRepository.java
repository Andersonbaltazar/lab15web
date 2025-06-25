package tecsup.edu.pe.lab15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tecsup.edu.pe.lab15.model.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Buscar productos por categoría
    List<Producto> findByCategoriaId(Long categoriaId);
    
    // Buscar productos por nombre (búsqueda parcial)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
