package com.bitc.java505_team4.mapper;

import com.bitc.java505_team4.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    public int isUserInfo(@Param("memberEmail") String memberEmail, @Param("memberPw") String memberPw) throws Exception;

    public UserDto getUserInfo(@Param("memberEmail") String memberEmail) throws Exception;

    public void insertMembership(UserDto userdto) throws Exception;

    List<UserDto> selectMemberManage() throws Exception;

    void deleteUser(int memberNum) throws Exception;
}
