package com.dao;

import com.mybatis.po.MyUser;
import com.pojo.SelectUserParam;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    public MyUser SelectUserById(Integer id);
    public List<MyUser> selectAllUser(SelectUserParam su);
    public int addUser(MyUser user);
    public int updateUser(MyUser user);
    public int deleteUser(MyUser user);
}
