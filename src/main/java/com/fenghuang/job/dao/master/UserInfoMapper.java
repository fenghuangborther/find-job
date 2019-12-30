package com.fenghuang.job.dao.master;

import com.fenghuang.job.entity.UserInfo;
import com.fenghuang.job.request.ReqUserInfo;
import com.fenghuang.job.request.ReqUserInfoQuery;
import com.fenghuang.job.request.ReqUserInfoUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> findUserInfoByUserName(String userName);

    UserInfo findUserInfo(ReqUserInfoQuery reqUserInfoQuery);

    List<UserInfo> findUserInfoPage(ReqUserInfoQuery reqUserInfoQuery);

    UserInfo findUserInfoByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    int changePassword(ReqUserInfoUpdate reqUserInfoUpdate);

    UserInfo loginQueryUserInfo(ReqUserInfo reqUserInfo);

    UserInfo findUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    UserInfo findUserByUserNicknameAndPassword(@Param("userNickname") String userNickname, @Param("password") String password);

    UserInfo findMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    UserInfo findIdCardAndPassword(@Param("idCard") String idCard, @Param("password") String password);
}