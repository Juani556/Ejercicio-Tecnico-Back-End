package ar.com.factorIt.ecommercerest.carrito.service;

import ar.com.factorIt.ecommercerest.carrito.entity.Carrito;
import ar.com.factorIt.ecommercerest.carrito.mapper.CarritoMapper;
import ar.com.factorIt.ecommercerest.carrito.repository.CarritoRepository;
import ar.com.factorIt.ecommercerest.compra.repository.CompraRepository;
import ar.com.factorIt.ecommercerest.producto.entity.Producto;
import ar.com.factorIt.ecommercerest.producto.repository.ProductoRepository;
import ar.com.factorIt.ecommercerest.usuario.entity.Usuario;
import ar.com.factorIt.ecommercerest.usuario.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
class CarritoServiceTest {

    @TestConfiguration
    static class CarritoServiceTestContextConfiguration {

        @Bean
        public CarritoService carritoService() {
            return new CarritoService();
        }
    }


    @MockBean
    private CarritoRepository carritoRepository;

    @MockBean
    private ProductoRepository productoRepository;

    @MockBean
    private CompraRepository compraRepository;

    @MockBean
    private CarritoMapper carritoMapper;

    @MockBean
    private UserService userService;

    @Autowired
    private CarritoService carritoService;

    @BeforeEach
    void setUp() {

        Carrito carrito = new Carrito();
        carrito.setIdCarrito(1L);
        carrito.setIsSpecial(true);
        carrito.setFinalizado(false);

        Usuario usuario = new Usuario();
        usuario.setDocumento("40564785");
        usuario.setIdUsuario(1L);

        carrito.setUsuario(1L);

        Producto producto = new Producto();
        producto.setCantidad(6);
        producto.setPrecio(500);
        producto.setIdProducto(1L);

        List<Producto> list = new ArrayList<>();
        list.add(producto);

        carrito.setProductos(list);

        carrito.setFechaCreacion(LocalDate.now());

        Mockito.when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));
        Mockito.when(userService.isVip(1L)).thenReturn(true);

    }

    @Test
    void calcularPrecio() throws Exception {

        String response = null;
        response = carritoService.finalizarCarrito(1L);

        assertTrue(response.contains("$1850"));

    }
}