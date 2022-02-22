package ar.com.factorIt.ecommercerest.carrito.controller.request;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class ProductoRequest {

    private String nombre;

    @Min(100)
    private Integer precio;

    private Long idCarrito;
}
