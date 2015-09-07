package com.td.service.crud.dictionary.user;

import com.td.model.repository.dictionary.user.UserRepository;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.service.crud.LazyInitVisiter;
import com.td.service.crud.CRUDService;
import com.td.service.crud.dictionary.DictionaryCRUDService;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 20.11.13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public interface UserCRUDService extends CRUDService<UserModel> {


    UserModel getUserByName(String userName);

    UserModel getCurrentUser();

    List<UserModel> findByCompanyId(UUID companyId);
}
