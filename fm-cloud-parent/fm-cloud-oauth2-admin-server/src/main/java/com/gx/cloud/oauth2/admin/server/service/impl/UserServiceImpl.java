package com.gx.cloud.oauth2.admin.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gx.cloud.common.exception.ExceptionCode;
import com.gx.cloud.common.util.PasswordEncoderUtil;
import com.gx.cloud.common.util.UtilId;
import com.gx.cloud.oauth2.admin.server.entity.User;
import com.gx.cloud.oauth2.admin.server.mapper.UserMapper;
import com.gx.cloud.oauth2.admin.server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xun.guo
 * @since 2019-12-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 通过username查询登录用户
     * @param username
     * @return
     */
    @Override
    public User getUserByUserName(String username) {
        User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getUsername, username);
        List<User> list = new ArrayList<>();
        list = this.list(queryWrapper);
        if(null == list){
            return null;
        }else if(list.size() == 0){
            return null;
        }else {
                user = list.get(0);
        }
        return user;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        return null;
    }

    @Override
    public String registery(String username, String password){
        boolean flag = false;
        if(null != getUserByUserName(username)){
            throw new RuntimeException(ExceptionCode.USER_ALERDY_EXIST.getMessage());
        }
        User user = new User();
        user.setId(UtilId.UUID());
        user.setUsername(username);
        user.setPassword(PasswordEncoderUtil.passwordEncoder(password));
        user.setStatus("0");
        user.setCreatetime(new Date());
        user.setType("1");
        flag = this.save(user);
        if(flag == true){
            return ExceptionCode.SUCCESS.getMessage();
        }
        return ExceptionCode.FAILURE.getMessage();
    }
}
