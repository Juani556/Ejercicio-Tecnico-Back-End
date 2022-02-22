package ar.com.factorIt.ecommercerest.carrito.service;

import ar.com.factorIt.ecommercerest.carrito.dto.CarritoDto;
import ar.com.factorIt.ecommercerest.carrito.entity.Carrito;
import ar.com.factorIt.ecommercerest.carrito.mapper.CarritoMapper;
import ar.com.factorIt.ecommercerest.carrito.repository.CarritoRepository;
import ar.com.factorIt.ecommercerest.compra.entity.Compra;
import ar.com.factorIt.ecommercerest.compra.repository.CompraRepository;
import ar.com.factorIt.ecommercerest.producto.entity.Producto;
import ar.com.factorIt.ecommercerest.producto.repository.ProductoRepository;
import ar.com.factorIt.ecommercerest.usuario.service.UserService;
import ar.com.factorIt.ecommercerest.usuario.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class CarritoService {

    public static final Integer LIMITE_MIN_COMPRA_DESC_USUARIO_VIP = 2000;
    public static final Integer MONTO_DESC_USUARIO_VIP = 500;
    public static final Integer LIMITE_MIN_CANT_PRODUCTOS_DESC = 3;
    public static final Integer MONTO_DESC_CANT_PRODUCTOS_NO_ESPECIAL = 100;
    public static final Integer MONTO_DESC_CANT_PRODUCTOS_ESPECIAL = 150;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarritoMapper carritoMapper;

    public String crearCarrito(String documento, Boolean isSpecial) {

        Usuario usuario = userService.getUsuario(documento);

        if (usuario == null) {
            usuario = userService.registrarUsuario(documento);
        }

        Carrito carrito = carritoRepository.findByUsuarioAndFinalizadoIsFalse(usuario.getIdUsuario());

        StringBuilder sb = new StringBuilder("Carrito con id: ");

        if (carrito == null) {
            carrito = new Carrito();
            carrito.setFinalizado(false);
            carrito.setIsSpecial(isSpecial);
            carrito.setUsuario(usuario.getIdUsuario());
            carritoRepository.save(carrito);
            sb.append(carrito.getIdCarrito()).append(" creado con éxito.");
        } else {
            sb.append(carrito.getIdCarrito()).append(" esta en uso.");
        }

        return sb.toString();
    }

    public String borrarCarrito(Long idCarrito) {

        StringBuilder sb = new StringBuilder();

        try {
            carritoRepository.deleteByIdCarritoAndFinalizadoIsFalse(idCarrito);
            sb.append("Carrito con id: ").append(idCarrito).append(" eliminado con éxito.");
        } catch (EmptyResultDataAccessException e) {
            sb.append("No existe carrito no finalizado con Id: ").append(idCarrito);
        }

        return sb.toString();

    }

    public String agregarProducto(Long idCarrito, String nombre, Integer precio) {

        StringBuilder sb = new StringBuilder();

        Optional<Carrito> carrito = carritoRepository.findById(idCarrito);

        if (carrito.isEmpty()) {
            sb.append("No existe carrito activo con id: ").append(idCarrito);
            return sb.toString();
        }

        Producto producto = productoRepository.findByNombreAndCarrito(nombre, idCarrito);

        if (producto == null) {
            producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCarrito(idCarrito);
            productoRepository.save(producto);
        } else {
            producto.setCantidad(producto.getCantidad() + 1);
        }

        sb.append("Producto agregado con éxito al carrito");

        return sb.toString();

    }

    public String borrarProducto(Long idCarrito, String nombre) {

        StringBuilder sb = new StringBuilder();

        Producto producto = productoRepository.findByNombreAndCarrito(nombre, idCarrito);



        if (producto != null) {
            if (producto.getCantidad() == 1) {
                productoRepository.delete(producto);
            } else {
                producto.setCantidad(producto.getCantidad() - 1);
            }

            sb.append("Producto borrado con éxito");
        } else {
            sb.append("No existe ese producto en ese carrito");
        }

        return sb.toString();
    }

    public CarritoDto getCarrito(Long idUsuario) {

        Carrito carrito = carritoRepository.findByUsuarioAndFinalizadoIsFalse(idUsuario);

        if (carrito == null) {
            return new CarritoDto();
        }

        return carritoMapper.toDto(carrito);

    }

    public String finalizarCarrito(Long idCarrito) throws Exception {

        StringBuilder sb = new StringBuilder();

        Optional<Carrito> carritoOptional = carritoRepository.findById(idCarrito);

        Carrito carrito = carritoOptional.orElseThrow(Exception::new);

        if (carrito.getFinalizado()) {
            sb.append("Carrito ya estaba cerrado.");
        } else {
            carrito.setFinalizado(true);
            Compra compra = new Compra();
            compra.setPrecio(calcularPrecio(carrito));
            compra.setCarrito(carrito);
            compra.setUsuario(carrito.getUsuario());
            compraRepository.save(compra);

            sb.append("Carrito cerrado con un total de: $").append(compra.getPrecio());
        }

        return sb.toString();

    }

    private Integer calcularPrecio(Carrito carrito) {

        Integer precio = carrito.getProductos().stream()
                .map(x -> x.getPrecio() * x.getCantidad())
                .reduce(0, Integer::sum);

        Integer cantProductos = carrito.getProductos().stream()
                .map(x -> x.getCantidad())
                .reduce(0, Integer::sum);

        if (userService.isVip(carrito.getUsuario()) && precio > LIMITE_MIN_COMPRA_DESC_USUARIO_VIP) {
            precio -= MONTO_DESC_USUARIO_VIP;
        }

        if (cantProductos > LIMITE_MIN_CANT_PRODUCTOS_DESC) {
            if (carrito.getIsSpecial()) {
                precio -= MONTO_DESC_CANT_PRODUCTOS_ESPECIAL;
            } else {
                precio -= MONTO_DESC_CANT_PRODUCTOS_NO_ESPECIAL;
            }
        }



        Integer descuentoProdIguales = carrito.getProductos().stream()
                .map(x -> x.getPrecio() * (x.getCantidad() / 4))
                .reduce(0, Integer::sum);

        return precio - descuentoProdIguales;




    }
}
