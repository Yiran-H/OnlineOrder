package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.MenuItem;
import com.laioffer.onlineorder.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();//session没办法做全表搜索 没办法获得集合的记录 session帮我们增删改查
            CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);//搜索的表在哪
            criteria.from(Restaurant.class);//搜索的条件
            return session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        try (Session session = sessionFactory.openSession()) {
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null) {
                return restaurant.getMenuItemList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public MenuItem getMenuItem(int menuItemId) { //添加menu到order的时候需要用到
        try (Session session = sessionFactory.openSession()) {
            return session.get(MenuItem.class, menuItemId); //没有用transaction可以 因为目前单线程 没有多个用户对数据进行修改 不会读到脏数据
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

