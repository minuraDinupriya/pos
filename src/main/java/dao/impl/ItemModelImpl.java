package dao.impl;

import db.DBConnection;
import dto.ItemDto;
import dao.ItemModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemModelImpl implements ItemModel {
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        String updateQuery="INSERT INTO item VALUES('"+dto.getCode()+"','"+dto.getDesc()+"','"+dto.getUnitPrice()+"','"+dto.getQty()+"')";
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        return statement.executeUpdate(updateQuery)>0;
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        String updateQuery="UPDATE item SET code='"+dto.getCode()+"',description='"+dto.getDesc()+"',unitPrice="+dto.getUnitPrice()+", qtyOnHand="+dto.getQty()+" WHERE code='"+dto.getCode()+"'";
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        return statement.executeUpdate(updateQuery)>0;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        String deleteQuery="DELETE FROM item WHERE code='"+code+"'";
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        return statement.executeUpdate(deleteQuery)>0;
    }

    @Override
    public ItemDto getItem(String code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item WHERE code=?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,code);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<ItemDto> list = new ArrayList<>();
        String sql = "SELECT * FROM item";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return list;
    }
}
