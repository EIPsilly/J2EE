package com.mybatis.test;

import com.dao.UserDao;
import com.mybatis.po.MyUser;
import com.pojo.SelectUserParam;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    public static void main(String[] args){
        try{
            InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(config);
            SqlSession ss = ssf.openSession();
            SelectUserParam su = new SelectUserParam();
            su.setU_name("陈");
            su.setU_sex("男");
            UserDao userDao = ss.getMapper(UserDao.class);
            List<MyUser> list = userDao.selectAllUser(su);
            for (MyUser myUser:list){
                System.out.println("编号:"+myUser.getUid()+"\n姓名:"+myUser.getUname()+"\n性别:"+myUser.getUsex());
            }
            ss.commit();
            ss.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
