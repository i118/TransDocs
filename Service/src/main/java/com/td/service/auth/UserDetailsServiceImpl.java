package com.td.service.auth;

import com.td.service.context.qualifier.UserDetailsQualifier;
import com.td.service.context.qualifier.UserManagerQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 20.11.13
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
@Service
@UserDetailsQualifier
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserManager userManager;

    @Inject
    public UserDetailsServiceImpl(@UserManagerQualifier UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       return userManager.getUserByName(userName);
    }
}
