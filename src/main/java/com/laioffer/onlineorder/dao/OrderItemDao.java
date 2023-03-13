package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(OrderItem orderItem) { //关于传进来的parameter, for data tier只需要考虑把orderitem的这个对象保存到数据库就可以了
        //至于里面的数字怎么set 交给business处理
        Session session = null;
        try {
            session = sessionFactory.openSession();//用transaction的话不能用try with res, 否则的话只能作用于try block, catch access 不到
            session.beginTransaction();
            session.save(orderItem);//因为要insert 所以最好还是要保证一致性
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}


