package com.bitc.java505_team4.service;

import com.bitc.java505_team4.dto.UserDto;
import com.bitc.java505_team4.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int isUserInfo(String memberEmail, String memberPw) throws Exception {
        return userMapper.isUserInfo(memberEmail, memberPw);
    }

    @Override
    public UserDto getUserInfo(String memberEmail) throws Exception {
        return userMapper.getUserInfo(memberEmail);
    }

    @Override
    public void insertMembership(UserDto userdto) throws Exception {
        userMapper.insertMembership(userdto);
    }

    @Override
    public List<UserDto> selectMemberManage() throws Exception {
        return userMapper.selectMemberManage();
    }

    @Override
    public void deleteUser(int memberNum) throws Exception {
        userMapper.deleteUser(memberNum);
    }
}
