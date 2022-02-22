package ar.com.factorIt.ecommercerest.compra.dto;

import ar.com.factorIt.ecommercerest.carrito.dto.CarritoDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CompraDto {

    private Long id;
    private CarritoDto carrito;
    private LocalDate fecha;
    private Integer precio;


}
