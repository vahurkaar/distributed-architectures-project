package ee.ttu

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import org.springframework.dao.DataAccessException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomerUserDetailsService extends GormUserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException, DataAccessException {
        User user = findUserByUsername(username)
        if (user == null) {
            log.warn "User not found: $username"
            throw new NoStackUsernameNotFoundException()
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>()
        createUserDetails user, authorities
    }

    private User findUserByUsername(String username) {
        // TODO Load user from SOAP service
        return new User(username: 'casuser', password: 'Mellon');
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        loadUserByUsername username, true
    }
}
