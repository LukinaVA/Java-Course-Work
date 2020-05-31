package com.javamaster.spring_crud.service;

import com.javamaster.spring_crud.controller.AuthRequest;
import com.javamaster.spring_crud.entity.User;

import java.util.Map;

public interface AuthService {
    Map<Object, Object> signIn(AuthRequest authRequest);
    User signUp(AuthRequest authRequest);
}