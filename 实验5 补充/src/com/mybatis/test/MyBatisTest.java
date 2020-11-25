package com.mybatis.test;

import com.mybatis.po.MyUser;
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
//            MyUser mu = ss.selectOne("com.mybatis.mapper.UserMapper.selectUserById",1);
//            System.out.println(mu.getUname() + " " + mu.getUsex());
//            MyUser addmu = new MyUser();
//            addmu.setUname("张三");
//            addmu.setUsex("男");
//            ss.insert("com.mybatis.mapper.UserMapper.addUser",addmu);
            Map<String,Object> map = new HashMap<>();
            map.put("u_name","a");
            map.put("u_sex","男");
            List<MyUser> list = ss.selectList("com.mybatis.mapper.UserMapper.selectAllUser",map);
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
