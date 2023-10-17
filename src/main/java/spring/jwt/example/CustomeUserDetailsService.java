package spring.jwt.example;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new  User("dilip", passwordEncoder.encode("dilip123"), new HashSet<GrantedAuthority>());
	}

}
