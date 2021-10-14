package br.univesp.meuconsumo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.univesp.meuconsumo.model.Usuario;
import br.univesp.meuconsumo.repository.UsuarioRepository;

@Service
public class UsuarioAuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioData;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioData.findByUsuario(login);
        if (usuario != null) {
            return User.withUsername(usuario.getUsuario()).password(usuario.getSenha()).roles("CLIENT").build();
        } else {
            throw new UsernameNotFoundException("Não foi possível encontrar o usuário " + login);
        }
    }
}