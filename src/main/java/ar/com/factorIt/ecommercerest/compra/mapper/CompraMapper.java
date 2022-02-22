package ar.com.factorIt.ecommercerest.compra.mapper;

import ar.com.factorIt.ecommercerest.carrito.mapper.CarritoMapper;
import ar.com.factorIt.ecommercerest.compra.dto.CompraDto;
import ar.com.factorIt.ecommercerest.compra.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompraMapper {

    @Autowired
    private CarritoMapper carritoMapper;

    public CompraDto toDto(Compra compra) {
        CompraDto dto = new CompraDto();
        dto.setCarrito(carritoMapper.toDto(compra.getCarrito()));
        dto.setId(compra.getIdCompra());
        dto.setFecha(compra.getFecha());
        dto.setPrecio(compra.getPrecio());

        return dto;
    }

    public List<CompraDto> toDto (List<Compra> compras) {

        List<CompraDto> lista = new ArrayList<>();

        for (Compra compra : compras) {
            lista.add(toDto(compra));
        }

        return lista;
    }

}
