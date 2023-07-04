package com.bitc.java505_team4.service;

import com.bitc.java505_team4.dto.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public int isUserInfo(String memberEmail, String memberPw) throws Exception;

    public UserDto getUserInfo(String memberEmail) throws Exception;

    UserDto myUserDetail(@Param("memberEmail")String memberEmail) throws Exception;

    void myUserUpdate(UserDto user) throws Exception;

    public void insertMembership(UserDto userdto) throws Exception;

    List<UserDto> selectMemberManage() throws Exception;

    void updateUser(UserDto user) throws Exception;

    void deleteUser(int memberNum) throws Exception;
}
