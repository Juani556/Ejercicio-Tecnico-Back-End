package ar.com.factorIt.ecommercerest.compra.service;


import ar.com.factorIt.ecommercerest.compra.dto.CompraDto;
import ar.com.factorIt.ecommercerest.compra.entity.Compra;
import ar.com.factorIt.ecommercerest.compra.mapper.CompraMapper;
import ar.com.factorIt.ecommercerest.compra.controller.repository.CompraRepository;
import ar.com.factorIt.ecommercerest.compra.controller.request.CompraRequest;
import ar.com.factorIt.ecommercerest.usuario.service.UserService;
import ar.com.factorIt.ecommercerest.usuario.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CompraService {

    public static final String FORMATO_FECHA = "dd/MM/yyyy";

    @Autowired
    private UserService userService;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CompraMapper compraMapper;



    public List<CompraDto> getCompras(CompraRequest request) {

        Usuario usuario = userService.getUsuario(request.getDocumento());

        if (usuario == null) {
            return null;
        }

        Map<String, Object> params = new HashMap<>();

        armarMapParams(params, request);

        params.put("usuario", usuario.getIdUsuario());

        List<Compra> compras = compraRepository.getCompras(params);

        return compraMapper.toDto(compras);


    }

    private void armarMapParams(Map<String, Object> params, CompraRequest request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);

        if (request.getFrom() != null) {
            params.put("from", LocalDate.parse(request.getFrom(), formatter));
        }

        if (request.getTo() != null) {
            params.put("to", LocalDate.parse(request.getTo(), formatter));
        }

        if (request.getOrden() != null) {
            if ("Monto".equalsIgnoreCase(request.getOrden())) {
                params.put("orden", "precio");
            } else {
                params.put("orden", "fecha");
            }
        }
    }

}
