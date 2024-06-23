package model;

import utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    private String[] columnNames = {
        "Mã sinh viên", "Họ", "Tên", "Địa chỉ", "Giới tính", "Nơi sinh", "Số điện thoại", "Email", "Khoa", "Ngành"
    };

    private List<Object[]> data;

    public StudentModel() {
        data = new ArrayList<>();
        loadData();
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public Object[][] getData() {
        return data.toArray(new Object[0][]);
    }

    public void loadData() {
        try (Connection connection = DatabaseUtils.getConnection()) {
            String sql = "SELECT * FROM student";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            data.clear(); // Xóa dữ liệu cũ

            while (resultSet.next()) {
                data.add(new Object[]{
                    resultSet.getString("Mã sinh viên"),
                    resultSet.getString("Họ"),
                    resultSet.getString("Tên"),
                    resultSet.getString("Địa chỉ"),
                    resultSet.getString("Giới tính"),
                    resultSet.getString("Nơi sinh"),
                    resultSet.getString("Số điện thoại"),
                    resultSet.getString("Email"),
                    resultSet.getString("Khoa"),
                    resultSet.getString("Ngành")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
