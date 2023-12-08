package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;

import java.util.List;
import java.sql.SQLException;

public interface CustomerBo<T> extends SuperBo {
    boolean saveCustomer(T dto) throws SQLException, ClassNotFoundException;
    List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException;
    boolean updateCustomer(T dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String value) throws SQLException, ClassNotFoundException;
}
