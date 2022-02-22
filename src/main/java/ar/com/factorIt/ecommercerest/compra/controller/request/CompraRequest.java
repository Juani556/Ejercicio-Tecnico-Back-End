package ar.com.factorIt.ecommercerest.compra.controller.request;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
public class CompraRequest {

    @NotNull
    private String documento;

    private String from;

    private String to;

    @Pattern(regexp = "monto|fecha", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String orden;


}
