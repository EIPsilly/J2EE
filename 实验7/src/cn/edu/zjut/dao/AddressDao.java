package cn.edu.zjut.dao;

import cn.edu.zjut.po.Address;
import cn.edu.zjut.po.Customer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

public class AddressDao extends BaseHibernateDao {
    private Log log = LogFactory.getLog(AddressDao.class);

    public Object findById(int AddressId){
        log.debug("finding Address instance by Id");
        try{
            String queryString = "from Address Where AddressId = " + AddressId;
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list().get(0);
        } catch (RuntimeException re){
            log.error("find by hql failed",re);
            throw re;
        }
    }

    public void update(Address instance){
        log.debug("updating Address instance");
        try{
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re){
            log.error("update failed",re);
            throw re;
        }
    }

}
