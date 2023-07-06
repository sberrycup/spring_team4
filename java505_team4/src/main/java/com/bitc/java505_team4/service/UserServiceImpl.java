package com.bitc.java505_team4.service;

import com.bitc.java505_team4.common.FileUtils;
import com.bitc.java505_team4.dto.UserDto;
import com.bitc.java505_team4.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public int isUserInfo(String memberEmail, String memberPw) throws Exception {
        return userMapper.isUserInfo(memberEmail, memberPw);
    }

    @Override
    public UserDto getUserInfo(String memberEmail) throws Exception {
        return userMapper.getUserInfo(memberEmail);
    }

    @Override
    public UserDto myUserDetail(String memberEmail) throws Exception {
        return userMapper.myUserDetail(memberEmail);
    }

    @Override
    public void myUserUpdate(UserDto user, MultipartHttpServletRequest uploadFiles) throws Exception {
        userMapper.myUserUpdate(user);
        List<UserDto> fileList = fileUtils.parseFileInfo(user, uploadFiles);
        if (CollectionUtils.isEmpty(fileList) == false) {
//            sql에서 insert처럼 update는 여러개를 한번에 수정하지 않고 한번에 한번만 수정한다
            userMapper.updateUserProfile(fileList.get(0));
        }
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
    public void updateUser(UserDto user) throws Exception {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(int memberNum) throws Exception {
        userMapper.deleteUser(memberNum);
    }
}
