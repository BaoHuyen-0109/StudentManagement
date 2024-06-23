package controller;

import view.Homepage;
import model.StudentModel;
import utils.DatabaseUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentController {
    private Homepage view;
    private StudentModel model;

    public StudentController(Homepage view, StudentModel model) {
        this.view = view;
        this.model = model;
    }

    public void handleAddStudent(ActionEvent e) {
        String id = view.getStudentIDField().getText();
        String firstname = view.getFirstnameField().getText();
        String surname = view.getSurnameField().getText();
        String address = view.getAddressField().getText();
        String gender = view.getGenderComboBox().getSelectedItem().toString();
        String birthplace = view.getBirthplaceField().getText();
        String mobile = view.getMobileField().getText();
        String email = view.getEmailField().getText();
        String khoa = view.getKhoaComboBox().getSelectedItem().toString();
        String nganh = view.getNganhComboBox().getSelectedItem().toString();

        DefaultTableModel tableModel = view.getTableModel();
        tableModel.addRow(new Object[]{id, firstname, surname, address, gender, birthplace, mobile, email, khoa, nganh});

        // Add code to insert into the database
        try {
            Connection connection = DatabaseUtils.getConnection();
            String sql = "INSERT INTO sys.student (`Mã sinh viên`, `Họ`, `Tên`, `Địa chỉ`, `Giới tính`, `Nơi sinh`, `Số điện thoại`, `Email`, `Khoa`, `Ngành`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, birthplace);
            preparedStatement.setString(7, mobile);
            preparedStatement.setString(8, email);
            preparedStatement.setString(9, khoa);
            preparedStatement.setString(10, nganh);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding student to the database: " + ex.getMessage());
        }
    }

    public void handleResetFields(ActionEvent e) {
        view.getStudentIDField().setText("");
        view.getFirstnameField().setText("");
        view.getSurnameField().setText("");
        view.getAddressField().setText("");
        view.getGenderComboBox().setSelectedIndex(0);
        view.getBirthplaceField().setText("");
        view.getMobileField().setText("");
        view.getEmailField().setText("");
        view.getKhoaComboBox().setSelectedIndex(0);
        view.getNganhComboBox().removeAllItems();
    }

    public void handleDeleteStudent(ActionEvent e) {
        int selectedRow = view.getStudentTable().getSelectedRow();
        if (selectedRow >= 0) {
            String studentID = view.getTableModel().getValueAt(selectedRow, 0).toString();
            view.getTableModel().removeRow(selectedRow);

            try {
                Connection connection = DatabaseUtils.getConnection();
                String sql = "DELETE FROM sys.student WHERE `Mã sinh viên` = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, studentID);

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);

                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting student from the database: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete");
        }
    }

    public void handleEditStudent(ActionEvent e) {
        int selectedRow = view.getStudentTable().getSelectedRow();
        if (selectedRow >= 0) {
            view.getStudentIDField().setText(view.getTableModel().getValueAt(selectedRow, 0).toString());
            view.getFirstnameField().setText(view.getTableModel().getValueAt(selectedRow, 1).toString());
            view.getSurnameField().setText(view.getTableModel().getValueAt(selectedRow, 2).toString());
            view.getAddressField().setText(view.getTableModel().getValueAt(selectedRow, 3).toString());
            view.getGenderComboBox().setSelectedItem(view.getTableModel().getValueAt(selectedRow, 4).toString());
            view.getBirthplaceField().setText(view.getTableModel().getValueAt(selectedRow, 5).toString());
            view.getMobileField().setText(view.getTableModel().getValueAt(selectedRow, 6).toString());
            view.getEmailField().setText(view.getTableModel().getValueAt(selectedRow, 7).toString());
            view.getKhoaComboBox().setSelectedItem(view.getTableModel().getValueAt(selectedRow, 8).toString());
            view.getNganhComboBox().setSelectedItem(view.getTableModel().getValueAt(selectedRow, 9).toString());

            view.getEditButton().setVisible(false);
            view.getConfirmButton().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to edit");
        }
    }

    public void handleConfirmEdit(ActionEvent e) {
        int selectedRow = view.getStudentTable().getSelectedRow();
        if (selectedRow >= 0) {
            String id = view.getStudentIDField().getText();
            String firstname = view.getFirstnameField().getText();
            String surname = view.getSurnameField().getText();
            String address = view.getAddressField().getText();
            String gender = view.getGenderComboBox().getSelectedItem().toString();
            String birthplace = view.getBirthplaceField().getText();
            String mobile = view.getMobileField().getText();
            String email = view.getEmailField().getText();
            String khoa = view.getKhoaComboBox().getSelectedItem().toString();
            String nganh = view.getNganhComboBox().getSelectedItem().toString();

            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(firstname, selectedRow, 1);
            tableModel.setValueAt(surname, selectedRow, 2);
            tableModel.setValueAt(address, selectedRow, 3);
            tableModel.setValueAt(gender, selectedRow, 4);
            tableModel.setValueAt(birthplace, selectedRow, 5);
            tableModel.setValueAt(mobile, selectedRow, 6);
            tableModel.setValueAt(email, selectedRow, 7);
            tableModel.setValueAt(khoa, selectedRow, 8);
            tableModel.setValueAt(nganh, selectedRow, 9);

            try {
                Connection connection = DatabaseUtils.getConnection();
                String sql = "UPDATE sys.student SET `Họ` = ?, `Tên` = ?, `Địa chỉ` = ?, `Giới tính` = ?, `Nơi sinh` = ?, `Số điện thoại` = ?, `Email` = ?, `Khoa` = ?, `Ngành` = ? WHERE `Mã sinh viên` = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, surname);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, birthplace);
                preparedStatement.setString(6, mobile);
                preparedStatement.setString(7, email);
                preparedStatement.setString(8, khoa);
                preparedStatement.setString(9, nganh);
                preparedStatement.setString(10, id);

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);

                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating student in the database: " + ex.getMessage());
            }

            view.getEditButton().setVisible(true);
            view.getConfirmButton().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to edit");
        }
    }
}
