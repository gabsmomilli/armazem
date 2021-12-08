package br.gov.sp.fatec.frases.service.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.frases.model.Auth;
import br.gov.sp.fatec.frases.model.Usuario;
import br.gov.sp.fatec.frases.repository.AuthRepository;
import br.gov.sp.fatec.frases.repository.UserRepository;
import br.gov.sp.fatec.frases.service.SecurityService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class SecurityServiceImpl implements SecurityService{

    @Autowired 
    UserRepository userRepo;

    @Autowired 
    AuthRepository authRepo;

    @Autowired 
    PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Usuario newUser(String name, String email, String password, String auth){

        Auth aut = authRepo.findByName(auth);
        if(aut == null){
            aut = new Auth();
            aut.setName("autorização");
            authRepo.save(aut);
        }
        

        Usuario user = new Usuario();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setAuths(new HashSet<Auth>());
        user.getAuths().add(aut);
        userRepo.save(user);

        return user;

    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public List<Usuario> findAllUsers(){
        return userRepo.findAll();
    }

    @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = userRepo.findByName(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
    }
    return User.builder().username(username).password(usuario.getPassword())
        .authorities(usuario.getAuths().stream()
            .map(Auth::getName).collect(Collectors.toList())
            .toArray(new String[usuario.getAuths().size()]))
        .build();
  }

}
