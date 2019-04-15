package com.emps.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.emps.model.User;
import com.emps.repository.UserRepository;

@Repository
public class UserDetailsServiceImplements implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User usuario = repository.findUserByLogin(login);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		return usuario;
	}

}
