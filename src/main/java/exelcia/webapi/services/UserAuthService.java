package exelcia.webapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exelcia.webapi.exceptions.ResourceNotFoundException;
import com.exelcia.webapi.model.User;
import com.exelcia.webapi.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	UserRepository repo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repo.findUserByUsername(username).orElseThrow(
		() -> new ResourceNotFoundException("user not found", "", username));
		return user;
		
	}

}
