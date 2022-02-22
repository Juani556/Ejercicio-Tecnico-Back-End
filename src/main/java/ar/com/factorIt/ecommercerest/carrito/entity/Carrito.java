package ar.com.factorIt.ecommercerest.carrito.entity;


import ar.com.factorIt.ecommercerest.producto.entity.Producto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Carrito {

    public Carrito() {
        fechaCreacion = LocalDate.now();
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARRITO_SEQ")
    @SequenceGenerator(name = "CARRITO_SEQ")
    private Long idCarrito;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<Producto> productos;

    @Column
    private Boolean finalizado;

    private LocalDate fechaCreacion;

    private Boolean isSpecial;

    private Long usuario;
}
