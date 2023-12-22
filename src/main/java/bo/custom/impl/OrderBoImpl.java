package bo.custom.impl;

import bo.custom.OrderBo;
import dao.DaoFactory;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dao.util.DaoType;
import dto.OrderDto;

import java.sql.SQLException;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao= new OrderDaoImpl();
    @Override
    public boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return orderDao.saveOrder(orderDto);
    }

}
