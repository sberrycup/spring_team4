package com.bitc.java505_team4.service;

import com.bitc.java505_team4.dto.UserDto;

public interface UserService {
    public int isUserInfo(String memberEmail, String memberPw) throws Exception;

    public UserDto getUserInfo(String memberEmail) throws Exception;

    public void insertMembership(UserDto userdto) throws Exception;
}
