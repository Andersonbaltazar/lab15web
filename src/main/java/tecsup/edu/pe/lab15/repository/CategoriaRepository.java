package tecsup.edu.pe.lab15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tecsup.edu.pe.lab15.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos adicionales personalizados pueden ser agregados aquí
}
