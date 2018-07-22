package com.kk.apollo.biz.service.auth;

import com.kk.apollo.biz.model.auth.Auth;

/**
 * Created by Administrator on 2018/1/3.
 */

public interface AuthService {
    Auth getUserInfo(Integer userId);
}
