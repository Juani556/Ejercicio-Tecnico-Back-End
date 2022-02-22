package ar.com.factorIt.ecommercerest.compra.entity;


import ar.com.factorIt.ecommercerest.carrito.entity.Carrito;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Compra {

    public Compra() {
        fecha = LocalDate.now();
    }


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPRA_SEQ")
    @SequenceGenerator(name = "COMPRA_SEQ")
    private Long idCompra;

    @OneToOne
    @JoinColumn()
    private Carrito carrito;

    @Column
    private LocalDate fecha;

    @Column
    private Integer precio;

    @Column
    private Long usuario;
}
