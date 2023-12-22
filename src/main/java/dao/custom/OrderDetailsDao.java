package dao.custom;

import dao.CrudDao;
import dao.SuperDao;
import dto.OrderDetailsDto;
import entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDao  {
    boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException;
}
