package ar.com.factorIt.ecommercerest.producto.repository;

import ar.com.factorIt.ecommercerest.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    public Producto findByNombreAndCarrito(String nombre, Long carrito);
}
