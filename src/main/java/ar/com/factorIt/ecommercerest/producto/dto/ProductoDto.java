package ar.com.factorIt.ecommercerest.producto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoDto {

    private String nombre;

    private Integer cantidad;

    private Integer precio;

}
