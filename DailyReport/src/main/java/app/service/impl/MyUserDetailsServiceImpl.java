package app.service.impl;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import app.model.User;

public class MyUserDetailsServiceImpl extends BaseServiceImpl implements UserDetailsService {

	private static final Logger logger = Logger.getLogger(MyUserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		try {
			logger.info("loadUserByUsername");
			User user1 = getUserDAO().findByUsername(username);
			if (user1 != null) {
				return createUserDetails(user1);
			}
			return null;
		} catch (Exception e) {
			logger.error("error in loadUserByUsername");
			e.printStackTrace();
			return null;
		}

	}

	private UserDetails createUserDetails(User user) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, user.getAuthorities());
	}

}