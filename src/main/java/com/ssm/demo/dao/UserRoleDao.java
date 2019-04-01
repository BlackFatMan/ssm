package com.ssm.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface UserRoleDao {

    Set<String> getRolesByUserName(@Param("userName")String userName);

}
