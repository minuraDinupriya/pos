package dao.custom.impl;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import dao.DaoFactory;
import dao.util.CrudUtil;
import dao.util.DaoType;
import dao.util.HibernateUtil;
import db.DBConnection;
import dto.OrderDetailsDto;
import dto.OrderDto;
import dao.custom.OrderDetailsDao;
import dao.custom.OrderDao;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAIL);

    @Override
    public OrderDto lastOrder() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<Orders> resultList = session.createQuery("FROM Orders o ORDER BY o.orderId DESC", Orders.class)
                .setMaxResults(1)
                .getResultList();

        transaction.commit();
        session.close();

        if (!resultList.isEmpty()) {
            Orders latestOrder = resultList.get(0);
            return new OrderDto(
                    latestOrder.getOrderId(),
                    latestOrder.getDate(),
                    latestOrder.getCustomer().getId(),
                    null
            );

        } else {
            return null;
        }
    }

    @Override
    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Orders order = new Orders(
                dto.getOrderId(),
                dto.getDate()
        );
        order.setCustomer(session.find(Customer.class,dto.getCustId()));
        session.save(order);

        List<OrderDetailsDto> list = dto.getList(); //dto type

        for (OrderDetailsDto detailDto:list) {
            OrderDetail orderDetail = new OrderDetail(
                    new OrderDetailsKey(
                            detailDto.getOrderId(),
                            detailDto.getItemCode()
                    ),
                    session.find(Item.class, detailDto.getItemCode()),
                    order,
                    detailDto.getQty(),
                    detailDto.getUnitPrice()
            );
            session.save(orderDetail);
        }

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }
}
