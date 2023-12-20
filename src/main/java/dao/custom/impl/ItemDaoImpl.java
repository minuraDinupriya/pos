package dao.custom.impl;

import db.DBConnection;
import dto.ItemDto;
import dao.custom.ItemDao;
import entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    @Override
    public Item getItem(String code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item WHERE code=?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,code);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }


    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        String updateQuery="INSERT INTO item VALUES('"+entity.getCode()+"','"+entity.getDescription()+"','"+entity.getUnitPrice()+"','"+entity.getQtyOnHand()+"')";
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        return statement.executeUpdate(updateQuery)>0;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM item";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return list;
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        String updateQuery="UPDATE item SET code='"+entity.getCode()+"',description='"+entity.getDescription()+"',unitPrice="+entity.getUnitPrice()+", qtyOnHand="+entity.getQtyOnHand()+" WHERE code='"+entity.getCode()+"'";
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        return statement.executeUpdate(updateQuery)>0;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        String deleteQuery="DELETE FROM item WHERE code='"+value+"'";
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        return statement.executeUpdate(deleteQuery)>0;
    }
}
