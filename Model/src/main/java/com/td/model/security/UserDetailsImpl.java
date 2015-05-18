package com.td.model.security;

import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 20.11.13
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsImpl implements UserDetails,IAuthorizationUserInfo<IUserModel> {
    public static final long serialVersionUID = 8266525488057072269L;

    private  final UserModel currentUser;

    private final String schema;

    public UserDetailsImpl(UserModel currentUser, String schema) {
        this.currentUser = currentUser;
        this.schema = schema;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (IRoleModel roleModel : currentUser.getRoleModels()){
            authorities.add(new GrantedAuthorityImpl(roleModel));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return currentUser.getPassword().toString();
    }

    @Override
    public String getUsername() {
        return currentUser.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public UserModel getCurrentUser() {
        return currentUser;
    }

    protected class GrantedAuthorityImpl implements GrantedAuthority {
        private IRoleModel roleModel;

        public GrantedAuthorityImpl(IRoleModel roleModel) {
            this.roleModel = roleModel;
        }

        @Override
        public String getAuthority() {
            return roleModel.getRoleName();
        }
    }

    public String getSchema() {
        return schema;
    }
}
