package com.td.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 20.11.13
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public interface UserManager {

    public UserDetails getUserByName(String userName) throws UsernameNotFoundException;

}
