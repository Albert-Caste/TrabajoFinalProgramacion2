package co.edu.uniquindio.cineuq.controller;


import co.edu.uniquindio.cineuq.model.RolUsuario;
import co.edu.uniquindio.cineuq.model.Usuario;
import co.edu.uniquindio.cineuq.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class LoginController {

    private List<Usuario> listaUsuarios;

    public LoginController() {
        listaUsuarios = new ArrayList<>();

        // Usuarios iniciales de prueba
        listaUsuarios.add(
                new Usuario("admin@cinemauq.com", "12345",
                        RolUsuario.ADMINISTRADOR)
        );

        listaUsuarios.add(
                new Usuario("cliente@cinemauq.com", "12345",
                        RolUsuario.CLIENTE)
        );
    }

    public Usuario iniciarSesion(String correo, String contrasena) {

        for (Usuario usuario : listaUsuarios) {

            if (usuario.getCorreo().equalsIgnoreCase(correo)
                    && usuario.getContrasena().equals(contrasena)) {

                return usuario;
            }
        }

        return null;
    }

    public void registrarUsuario(Usuario usuario) {

        listaUsuarios.add(
                new Cliente(
                        "cliente@cinemauq.com",
                        "12345",
                        "Juan Pérez",
                        "123456789"
                )
        );
    }

    private boolean isAdd(Usuario usuario) {
        return listaUsuarios.add(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return listaUsuarios;
    }
}
