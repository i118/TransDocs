package com.td.service.crud.dictionary.user;

import com.td.model.repository.dictionary.user.UserRepository;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.service.crud.LazyInitVisiter;
import com.td.service.crud.CRUDService;
import com.td.service.crud.dictionary.DictionaryCRUDService;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 20.11.13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public interface UserCRUDService extends DictionaryCRUDService<UserModel, UserRepository>, CRUDService<UserModel, UserRepository> {


    public IUserModel getUserByName(String userName);

    public UserModel getCurrentUser();


    public IUserModel getUserByName(String userName, LazyInitVisiter<IUserModel> lazyInitVisiter);
}
