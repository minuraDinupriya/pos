package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBo {
    boolean saveItem(CustomerDto dto) throws SQLException, ClassNotFoundException;
    List<CustomerDto> allItems() throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String value) throws SQLException, ClassNotFoundException;
}
