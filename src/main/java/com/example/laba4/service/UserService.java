package com.example.laba4.service;

import com.example.laba4.dao.UserDao;
import com.example.laba4.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(int userId) {

        return userDao.findById(userId);
    }
}
