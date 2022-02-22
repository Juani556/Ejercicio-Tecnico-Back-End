package ar.com.factorIt.ecommercerest.carrito.repository;

import ar.com.factorIt.ecommercerest.carrito.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    public Carrito findByUsuarioAndFinalizadoIsFalse(Long usuario);

    public void deleteByIdCarritoAndFinalizadoIsFalse(Long idCarrito);
}
