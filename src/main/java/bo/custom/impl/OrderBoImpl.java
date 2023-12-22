package bo.custom.impl;

import bo.custom.OrderBo;
import dao.DaoFactory;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dao.util.DaoType;
import dto.OrderDto;

import java.sql.SQLException;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao= DaoFactory.getInstance().getDao(DaoType.ORDER);
    @Override
    public boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return orderDao.save(orderDto);
    }

    @Override
    public OrderDto lastOrder() throws SQLException, ClassNotFoundException {
        return orderDao.lastOrder();
    }

}
