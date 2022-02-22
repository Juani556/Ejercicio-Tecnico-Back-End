package ar.com.factorIt.ecommercerest.compra.controller.repository;

import ar.com.factorIt.ecommercerest.compra.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>, CompraRepositoryCustom {

}
