package com.td.service.auth;

import com.td.model.context.qualifier.UserQualifier;
import com.td.model.repository.dictionary.user.UserRepository;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.multitenant.SchemaProviderImpl;
import com.td.model.security.UserDetailsImpl;
import com.td.service.context.qualifier.UserManagerQualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 20.11.13
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
@Service
@UserManagerQualifier
public class UserManagerImpl implements UserManager{

    private UserRepository userDao;


    @Inject
    public UserManagerImpl(@UserQualifier UserRepository userDao) {
        this.userDao= userDao;
    }

    @Override
    @Transactional
    public UserDetails getUserByName(String userName) throws UsernameNotFoundException {
        if(userName==null)  throw new UsernameNotFoundException("userName: "+userName+"not found");
        String schema;
        int schemaIndex = userName.indexOf(".");
        if(schemaIndex>0){
            schema = "td_"+userName.substring(0, schemaIndex);
            userName = userName.substring(schemaIndex+1, userName.length());
        }else{
            throw new UsernameNotFoundException("userName: "+userName+"not found");
        }

        SchemaProviderImpl.setCurrentSchema(schema);
        UserModel userModel = userDao.getUserByName(userName);
        if(userModel.getRoleModels()!=null){
            for(IRoleModel roleModel : userModel.getRoleModels()){
                roleModel.getDescription();
                roleModel.getRoleName();
            }
        }
       UserDetails userDetails = null;
       if(userModel!=null){
          userDetails = new UserDetailsImpl(userModel, schema);
       }else{
           throw new UsernameNotFoundException("userName: "+userName+"not found");
       }
      return userDetails;
    }
}
