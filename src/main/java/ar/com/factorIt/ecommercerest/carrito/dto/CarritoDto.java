package ar.com.factorIt.ecommercerest.carrito.dto;

import ar.com.factorIt.ecommercerest.producto.dto.ProductoDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CarritoDto {

    private Long id;

    private List<ProductoDto> productos;

}
