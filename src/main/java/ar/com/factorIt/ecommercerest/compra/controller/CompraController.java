package ar.com.factorIt.ecommercerest.compra.controller;

import ar.com.factorIt.ecommercerest.compra.dto.CompraDto;
import ar.com.factorIt.ecommercerest.compra.controller.request.CompraRequest;
import ar.com.factorIt.ecommercerest.compra.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<CompraDto> getCompras(@Valid @RequestBody CompraRequest request) {

        return compraService.getCompras(request);
    }

}
