package com.ssm.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserPermessionDao {

    Set<String> getPermessionByUserName(@Param("userName")String userName);
}
