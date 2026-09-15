package com.ecommerce.auth.infraestructure.driver_adapters;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")

public class UsuarioData {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private String idUsuario;
    private String nombre;
    @Column (length = 20, nullable = false)
    private String correo;
    @Column (length = 12, nullable = false, unique = true)
    private String password;
    private String rol;
    private Integer edad;
    private String numeroTelefonico;
}
