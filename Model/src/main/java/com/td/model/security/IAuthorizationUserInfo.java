package com.td.model.security;

import com.td.model.entity.dictionary.user.IUserModel;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 23.11.13
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public interface IAuthorizationUserInfo<T extends IUserModel> {

   public T getCurrentUser();
}
