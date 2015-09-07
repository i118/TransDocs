package com.td.model.repository.dictionary.user;

import com.td.model.repository.IRepository;
import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 20.11.13
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends DictionaryRepository<UserModel>, IRepository<UserModel> {

    public UserModel getUserByName(String userName);

    public boolean hasUser(IUserModel userModel);

    List<UserModel> findByCompanyId(UUID companyId);

}
