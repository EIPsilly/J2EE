package cn.edu.zjut.dao;

import cn.edu.zjut.po.Customer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao extends  BaseHibernateDao{
    private Log log = LogFactory.getLog(CustomerDao.class);

    public List findByHql(String hql) {
        log.debug("finding Customer instance by hql");
        try{
            String queryString = hql;
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re){
            log.error("find by hql failed",re);
            throw re;
        }
    }

    public Object findById(int CustomerId){
        log.debug("finding Customer instance by Id");
        try{
            String queryString = "from Customer Where customerId = " + CustomerId;
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list().get(0);
        } catch (RuntimeException re){
            log.error("find by hql failed",re);
            throw re;
        }
    }

    public void save(Customer instance){
        log.debug("saving Customer instance");
        try{
            getSession().save(instance);
            log.debug("save successful");
        } catch (RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void update(Customer instance){
        log.debug("updating Customer instance");
        try{
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re){
            log.error("update failed",re);
            throw re;
        }
    }

    public void delete(Customer instance){
        log.debug("deleting Customer instance");
        try{
            getSession().delete(instance);
            log.debug("delete successful");
        } catch (RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }
}
