package com.nostalgia.service.impl;

import com.nostalgia.dao.UserDAO;
import com.nostalgia.entity.User;
import com.nostalgia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public User login(User user) {
        User user1 = userDAO.login(user);
        if(user1==null) throw new RuntimeException("login fail");
        return user1;
    }
}
