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
//        String updateQuery="INSERT INTO item VALUES(?,?,?,?)";
//        return CrudUtil.execute(
//                updateQuery,
//                entity.getCode(),
//                entity.getDescription(),
//                entity.getUnitPrice(),
//                entity.getQtyOnHand()
//        );
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
//        List<Item> list = new ArrayList<>();//its better to pass dto to bo
//        String sql = "SELECT * FROM item";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        while (resultSet.next()){
//            list.add(new Item(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getDouble(3),
//                    resultSet.getInt(4)
//            ));
//        }
//
//        return list;

        Session session=HibernateUtil.getSession();
        Query query = session.createQuery("FROM Item");
        List<Item> list = query.list();
        session.close();
        return list;
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        String updateQuery="UPDATE item SET description= ?, unitPrice=?, qtyOnHand=? WHERE code=?";
        System.out.println(entity);
        return CrudUtil.execute(
                updateQuery,
                entity.getDescription(),
                entity.getUnitPrice(),
                entity.getQtyOnHand(),
                entity.getCode()
        );


//        Session session = HibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();
//        Item item = session.find(Item.class,entity.getCode());
//        System.out.println(item);
//        item.setDescription(entity.getDescription());
//        item.setUnitPrice(entity.getUnitPrice());
//        item.setQtyOnHand(entity.getQtyOnHand());
//        session.save(item);
//        transaction.commit();
//        session.close();
//        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
//        String deleteQuery="DELETE FROM item WHERE code=?";
//        return CrudUtil.execute(
//                deleteQuery,
//                value
//        );

        Session session= HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Item.class,value));
        transaction.commit();
        session.close();
        return true;
    }
}
