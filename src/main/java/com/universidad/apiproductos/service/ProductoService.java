package com.universidad.apiproductos.service;

import com.universidad.apiproductos.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoService {

    private final Map<Long, Producto> productos = new LinkedHashMap<>();
    private long contadorId = 1;

    public ProductoService() {
        guardar(new Producto(null, "Laptop", "Laptop 15 pulgadas", 3200.0));
        guardar(new Producto(null, "Mouse", "Mouse inalambrico", 95.0));
        guardar(new Producto(null, "Teclado", "Teclado mecanico", 220.0));
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    public Optional<Producto> buscarPorId(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(contadorId++);
        }
        productos.put(producto.getId(), producto);
        return producto;
    }

    public Optional<Producto> actualizar(Long id, Producto producto) {
        if (!productos.containsKey(id)) {
            return Optional.empty();
        }
        producto.setId(id);
        productos.put(id, producto);
        return Optional.of(producto);
    }

    public boolean eliminar(Long id) {
        return productos.remove(id) != null;
    }
}
