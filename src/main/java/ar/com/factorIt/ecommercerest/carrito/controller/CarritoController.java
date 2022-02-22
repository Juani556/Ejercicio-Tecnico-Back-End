package ar.com.factorIt.ecommercerest.carrito.controller;


import ar.com.factorIt.ecommercerest.carrito.dto.CarritoDto;
import ar.com.factorIt.ecommercerest.carrito.service.CarritoService;
import ar.com.factorIt.ecommercerest.carrito.controller.request.CrearCarritoRequest;
import ar.com.factorIt.ecommercerest.carrito.controller.request.ProductoRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/new")
    public String crearCarrito(@RequestBody CrearCarritoRequest request) {


        return carritoService.crearCarrito(request.getDocumento(), request.getIsSpecial());
    }

    @DeleteMapping("/{idCarrito}")
    public String borrarCarrito(@PathVariable Long idCarrito) {

        return carritoService.borrarCarrito(idCarrito);
    }

    @PostMapping("/add")
    public String agregarProducto(@Valid @RequestBody ProductoRequest request) {

        return carritoService.agregarProducto(request.getIdCarrito(), request.getNombre(), request.getPrecio());

    }

    @PostMapping("/delete")
    public String borrarProducto(@RequestBody ProductoRequest request) {

        return carritoService.borrarProducto(request.getIdCarrito(), request.getNombre());
    }

    @GetMapping
    public CarritoDto getCarrito(@RequestParam Long idUsuario) {

        return carritoService.getCarrito(idUsuario);
    }

    @PostMapping("/finalizar/{idCarrito}")
    public String finalizarCarrito(@PathVariable Long idCarrito) {

        String response = null;

        try {
             response = carritoService.finalizarCarrito(idCarrito);
        } catch (Exception e) {
            response = "No existe carrito activo con esa id";
        }

        return response;
    }


}
