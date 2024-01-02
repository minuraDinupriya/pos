package dao.custom.impl;

import dao.util.CrudUtil;
import dao.util.HibernateUtil;
import db.DBConnection;
import dto.ItemDto;
import dao.custom.ItemDao;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    @Override
    public Item getItem(String code) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {

        Session session= HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        System.out.println(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {

        Session session=HibernateUtil.getSession();
        Query query = session.createQuery("FROM Item");
        List<Item> list = query.list();
        session.close();
        return list;
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.find(Item.class,entity.getCode());
        System.out.println(item);
        item.setDescription(entity.getDescription());
        item.setUnitPrice(entity.getUnitPrice());
        item.setQtyOnHand(entity.getQtyOnHand());
        session.save(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {

        Session session= HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Item.class,value));
        transaction.commit();
        session.close();
        return true;
    }
}
