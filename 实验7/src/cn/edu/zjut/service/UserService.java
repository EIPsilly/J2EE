package cn.edu.zjut.service;

import cn.edu.zjut.dao.AddressDao;
import cn.edu.zjut.dao.CustomerDao;
import cn.edu.zjut.po.Address;
import cn.edu.zjut.po.Customer;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

public class UserService {
    private Map<String,Object> request,session;

    public boolean login(Customer loginUser){
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        request = (Map) ctx.get("request");
        String account = loginUser.getAccount();
        String password = loginUser.getPassword();
        String hql = "from Customer as user where account='" + account + "' and password ='" + password + "'";
        CustomerDao dao = new CustomerDao();
        List list = dao.findByHql(hql);
        dao.getSession().close();
        if (list.isEmpty()) {
            request.put("tip","用户名或密码错误！");
            return false;
        }
        else{
            session.put("user",account);
            request.put("tip","登录成功！");
            loginUser = (Customer)list.get(0);
            request.put("loginUser",loginUser);
            return true;
        }
    }

    public boolean addAddr(Customer loginUser, Address address){
        ActionContext ctx = ActionContext.getContext();
        request = (Map) ctx.get("request");
        CustomerDao c_dao = new CustomerDao();
        loginUser = (Customer)c_dao.findById(loginUser.getCustomerId());
//        address.setCustomer(loginUser);
        loginUser.getAddresses().add(address);
        Transaction tran = null;
        try{
            tran = c_dao.getSession().beginTransaction();
            c_dao.update(loginUser);
            tran.commit();
            request.put("loginUser",loginUser);
            request.put("tip","地址添加成功！");
            return true;
        } catch (Exception e){
            if (tran != null) tran.rollback();
            return false;
        } finally {
            c_dao.getSession().close();
        }
    }

    public boolean delAddr(Customer loginUser,Address address){
        ActionContext ctx = ActionContext.getContext();
        request = (Map) ctx.get("request");
        CustomerDao c_dao = new CustomerDao();
        loginUser = (Customer)c_dao.findById(loginUser.getCustomerId());
        AddressDao a_dao = new AddressDao();
        address = (Address)a_dao.findById(address.getAddressId());
        loginUser.getAddresses().remove(address);
        address.getCustomers().remove(loginUser);
        Transaction tran = null;
        try{
            tran = c_dao.getSession().beginTransaction();
            c_dao.update(loginUser);
            tran.commit();

            tran = a_dao.getSession().beginTransaction();
            a_dao.update(address);
            tran.commit();

            request.put("loginUser",loginUser);
            request.put("tip","地址删除成功！");
            return true;
        } catch (Exception e){
            if (tran != null) tran.rollback();
            return false;
        } finally {
            c_dao.getSession().close();
        }
    }

}
