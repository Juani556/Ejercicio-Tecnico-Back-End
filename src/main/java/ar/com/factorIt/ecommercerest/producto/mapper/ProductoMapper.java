package ar.com.factorIt.ecommercerest.producto.mapper;


import ar.com.factorIt.ecommercerest.producto.dto.ProductoDto;
import ar.com.factorIt.ecommercerest.producto.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDto toDto(Producto producto) {

        ProductoDto dto = new ProductoDto();
        dto.setNombre(producto.getNombre());
        dto.setCantidad(producto.getCantidad());
        dto.setPrecio(producto.getPrecio());

        return dto;
    }
}
