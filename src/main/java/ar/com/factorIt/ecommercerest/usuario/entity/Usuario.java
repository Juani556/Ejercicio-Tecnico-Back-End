package ar.com.factorIt.ecommercerest.usuario.entity;

import ar.com.factorIt.ecommercerest.compra.entity.Compra;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(name = "USUARIO_SEQ")
    private Long idUsuario;

    @Column
    private String documento;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;

}
