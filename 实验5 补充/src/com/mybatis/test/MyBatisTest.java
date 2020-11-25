package com.mybatis.test;

import com.mybatis.po.MyUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {
    public static void main(String[] args){
        try{
            InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(config);
            SqlSession ss = ssf.openSession();
            MyUser mu = ss.selectOne("com.mybatis.mapper.UserMapper.selectUserById",1);
            System.out.println(mu.getUname() + " " + mu.getUsex());
            MyUser addmu = new MyUser();
            addmu.setUname("张三");
            addmu.setUsex("男");
            ss.insert("com.mybatis.mapper.UserMapper.addUser",addmu);
            ss.commit();
            ss.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
