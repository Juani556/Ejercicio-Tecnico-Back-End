package ar.com.factorIt.ecommercerest.carrito.controller.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrearCarritoRequest {

    private String documento;
    private Boolean isSpecial;
}
