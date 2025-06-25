package tecsup.edu.pe.lab15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tecsup.edu.pe.lab15.excepcion.ResourceNotFoundExcention;
import tecsup.edu.pe.lab15.model.Categoria;
import tecsup.edu.pe.lab15.model.Producto;
import tecsup.edu.pe.lab15.repository.CategoriaRepository;
import tecsup.edu.pe.lab15.repository.ProductoRepository;

@Controller
public class WebController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    // Página principal
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    // === CATEGORÍAS ===
    
    // Listar categorías
    @GetMapping("/categorias")
    public String listCategorias(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "categorias/list";
    }
    
    // Formulario para nueva categoría
    @GetMapping("/categorias/new")
    public String newCategoriaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/form";
    }
    
    // Formulario para editar categoría
    @GetMapping("/categorias/edit/{id}")
    public String editCategoriaForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcention("Categoría no encontrada con id: " + id));
        model.addAttribute("categoria", categoria);
        return "categorias/form";
    }
    
    // Guardar categoría (crear o actualizar)
    @PostMapping("/categorias/save")
    public String saveCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }
    
    // Eliminar categoría
    @GetMapping("/categorias/delete/{id}")
    public String deleteCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return "redirect:/categorias";
    }
    
    // === PRODUCTOS ===
    
    // Listar productos
    @GetMapping("/productos")
    public String listProductos(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "productos/list";
    }
    
    // Formulario para nuevo producto
    @GetMapping("/productos/new")
    public String newProductoForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "productos/form";
    }
    
    // Formulario para editar producto
    @GetMapping("/productos/edit/{id}")
    public String editProductoForm(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcention("Producto no encontrado con id: " + id));
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "productos/form";
    }
    
    // Guardar producto (crear o actualizar)
    @PostMapping("/productos/save")
    public String saveProducto(@ModelAttribute("producto") Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }
    
    // Eliminar producto
    @GetMapping("/productos/delete/{id}")
    public String deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }
    
    // Buscar productos
    @GetMapping("/productos/search")
    public String searchProductos(@RequestParam(required = false) String nombre, Model model) {
        if (nombre != null && !nombre.isEmpty()) {
            model.addAttribute("productos", productoRepository.findByNombreContainingIgnoreCase(nombre));
        } else {
            model.addAttribute("productos", productoRepository.findAll());
        }
        model.addAttribute("searchTerm", nombre);
        return "productos/list";
    }
}
