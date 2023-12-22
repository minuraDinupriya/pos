package dao.custom;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import dao.CrudDao;
import dao.SuperDao;
import dto.OrderDto;
import entity.Orders;

import java.sql.SQLException;

public interface OrderDao {
    boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException;
    OrderDto lastOrder() throws SQLException, ClassNotFoundException;
}
