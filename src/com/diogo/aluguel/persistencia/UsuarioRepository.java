package com.diogo.aluguel.persistencia;

import java.util.ArrayList;
import java.time.LocalDateTime;

import com.diogo.aluguel.model.Usuario;


public class UsuarioRepository {
    private ArrayList<Usuario> usuarios;

    Usuario master = new Usuario(0, "master", "master", "master", LocalDateTime.now());

    public UsuarioRepository() {
        usuarios = new ArrayList<>();
        usuarios.add(master);
    }

    private Usuario usuariologado;

    // métodos necessários:
    // - login
    // - logout
    // - cadastro

    public Boolean addUsuario(String nome, String email, String senha) {
        // coletar data e hora atuais
        LocalDateTime horario_cadastro = LocalDateTime.now();
        // coletar id do usuário
        int id = Funcoes.criador_de_ids(usuarios);

        Usuario novoUsuario = new Usuario(id, nome, email, senha, horario_cadastro);
        usuarios.add(novoUsuario);
        return true;
    }

    public Boolean login(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                usuariologado = usuario;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        usuariologado = null;
    }

    public Usuario getUsuariologado() {
        return usuariologado;
    }

    public void setUsuariologado(Usuario usuariologado) {
        this.usuariologado = usuariologado;
    }

}
