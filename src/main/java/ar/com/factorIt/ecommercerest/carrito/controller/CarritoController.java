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
    public void crearCarrito(@RequestBody CrearCarritoRequest request) {


        carritoService.crearCarrito(request.getDocumento(), request.getIsSpecial());
    }

    @DeleteMapping("/{idCarrito}")
    public void borrarCarrito(@PathVariable Long idCarrito) {

        carritoService.borrarCarrito(idCarrito);
    }

    @PostMapping("/add")
    public void agregarProducto(@Valid @RequestBody ProductoRequest request) {

        carritoService.agregarProducto(request.getIdCarrito(), request.getNombre(), request.getPrecio());

    }

    @PostMapping("/delete")
    public void borrarProducto(@RequestBody ProductoRequest request) {

        carritoService.borrarProducto(request.getIdCarrito(), request.getNombre());
    }

    @GetMapping
    public CarritoDto getCarrito(@RequestParam Long idUsuario) {

        return carritoService.getCarrito(idUsuario);
    }

    @PostMapping("/finalizar/{idCarrito}")
    public void finalizarCarrito(@PathVariable Long idCarrito) {

        try {
            carritoService.finalizarCarrito(idCarrito);
        } catch (Exception e) {
            return;
        }

    }


}
