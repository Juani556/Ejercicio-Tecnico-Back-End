package ar.com.factorIt.ecommercerest.producto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
public class Producto {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTO_SEQ")
    @SequenceGenerator(name = "PRODUCTO_SEQ")
    private Long idProducto;

    @Column
    private String nombre;

    private Integer cantidad = 1;

    @Min(100)
    private Integer precio;

    @Column
    private Long carrito;



}
