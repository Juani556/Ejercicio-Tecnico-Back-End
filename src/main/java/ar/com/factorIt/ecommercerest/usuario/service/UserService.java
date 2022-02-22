package ar.com.factorIt.ecommercerest.usuario.service;

import ar.com.factorIt.ecommercerest.usuario.entity.Usuario;
import ar.com.factorIt.ecommercerest.usuario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    public static final Integer DESCUENTO_USUARIO_VIP = 5000;

    @Autowired
    private UserRepository userRepository;

    public Usuario registrarUsuario(String documento) {
        Usuario usuario = new Usuario();
        usuario.setDocumento(documento);
        return userRepository.save(usuario);
    }

    public Usuario getUsuario(String documento) {
        return userRepository.findByDocumento(documento);
    }

    public Boolean isVip(Long idUsuario) {

        Usuario usuario = userRepository.findById(idUsuario).get();

        Integer totalCompras = usuario.getCompras().stream()
                .filter(x -> x.getFecha().getMonthValue() == LocalDate.now().getMonthValue())
                .map(x -> x.getPrecio())
                .reduce(0, Integer::sum);

        return totalCompras > DESCUENTO_USUARIO_VIP;

    }

}
