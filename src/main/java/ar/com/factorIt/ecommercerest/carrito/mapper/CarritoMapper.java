package ar.com.factorIt.ecommercerest.carrito.mapper;

import ar.com.factorIt.ecommercerest.carrito.dto.CarritoDto;
import ar.com.factorIt.ecommercerest.carrito.entity.Carrito;
import ar.com.factorIt.ecommercerest.producto.dto.ProductoDto;
import ar.com.factorIt.ecommercerest.producto.mapper.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarritoMapper {

    @Autowired
    ProductoMapper productoMapper;

    public CarritoDto toDto(Carrito carrito) {

        CarritoDto dto = new CarritoDto();
        dto.setId(carrito.getIdCarrito());
        List<ProductoDto> list = carrito.getProductos().stream().map(productoMapper::toDto).collect(Collectors.toList());
        dto.setProductos(list);

        return dto;
    }
}
