package ar.com.factorIt.ecommercerest.usuario.repository;

import ar.com.factorIt.ecommercerest.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByDocumento(String documento);
}
