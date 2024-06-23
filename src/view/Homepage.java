package view;

import controller.StudentController;
import model.StudentModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Homepage extends JFrame {
    private JTextField studentIDField, firstnameField, surnameField, addressField, mobileField, emailField, birthplaceField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> khoaComboBox, nganhComboBox;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentController studentController;
    private StudentModel studentModel;
    private JButton editButton, confirmButton;

    public Homepage() {
        studentModel = new StudentModel();
        studentController = new StudentController(this, studentModel);

        setTitle("Phần mềm quản lý sinh viên");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        add(panel);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel titleLabel = new JLabel("Phần mềm quản lý sinh viên");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 29));

        JLabel studentIDLabel = new JLabel("Mã sinh viên");
        studentIDField = new JTextField();

        JLabel firstnameLabel = new JLabel("Họ");
        firstnameField = new JTextField();

        JLabel surnameLabel = new JLabel("Tên");
        surnameField = new JTextField();
 
        JLabel addressLabel = new JLabel("Địa chỉ");
        addressField = new JTextField();

        JLabel genderLabel = new JLabel("Giới tính");
        genderComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"});

        JLabel birthplaceLabel = new JLabel("Nơi sinh");
        birthplaceField = new JTextField();

        JLabel mobileLabel = new JLabel("Số điện thoại");
        mobileField = new JTextField();

        JLabel emailLabel = new JLabel("Email");
        emailField = new JTextField();

        JLabel khoaLabel = new JLabel("Khoa");
        khoaComboBox = new JComboBox<>(new String[]{"Khoa công nghệ thông tin", "Khoa kinh tế"});

        JLabel nganhLabel = new JLabel("Ngành");
        nganhComboBox = new JComboBox<>();

        khoaComboBox.addActionListener(e -> {
            String selectedKhoa = (String) khoaComboBox.getSelectedItem();
            nganhComboBox.removeAllItems();
            if (selectedKhoa.equals("")) {
            	
            }
            else if (selectedKhoa.equals("Khoa công nghệ thông tin")) {
                nganhComboBox.addItem("Ngành an toàn thông tin");
                nganhComboBox.addItem("Ngành thiết kế mỹ thuật số");
                nganhComboBox.addItem("Ngành thiết kế vi mạch");
            } else if (selectedKhoa.equals("Khoa kinh tế")) {
                nganhComboBox.addItem("Marketing");
                nganhComboBox.addItem("Quản trị kinh doanh");
                nganhComboBox.addItem("Quản trị nhân lực");
            }
        });

        tableModel = new DefaultTableModel(studentModel.getData(), studentModel.getColumnNames());
        studentTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);

        JButton addButton = new JButton("Thêm mới");
        editButton = new JButton("Sửa");
        confirmButton = new JButton("Xong");
        confirmButton.setVisible(false);
        JButton resetButton = new JButton("Làm mới");
        JButton deleteButton = new JButton("Xóa");
        JButton exitButton = new JButton("Thoát");

        addButton.addActionListener(studentController::handleAddStudent);
        editButton.addActionListener(studentController::handleEditStudent);
        confirmButton.addActionListener(studentController::handleConfirmEdit);
        resetButton.addActionListener(studentController::handleResetFields);
        deleteButton.addActionListener(studentController::handleDeleteStudent);
        exitButton.addActionListener(e -> System.exit(0));

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titleLabel, GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(studentIDLabel)
                        .addComponent(firstnameLabel)
                        .addComponent(surnameLabel)
                        .addComponent(addressLabel)
                        .addComponent(genderLabel)
                        .addComponent(birthplaceLabel)
                        .addComponent(mobileLabel)
                        .addComponent(emailLabel))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(studentIDField)
                        .addComponent(firstnameField)
                        .addComponent(surnameField)
                        .addComponent(addressField)
                        .addComponent(genderComboBox)
                        .addComponent(birthplaceField)
                        .addComponent(mobileField)
                        .addComponent(emailField))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(khoaLabel)
                        .addComponent(nganhLabel))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(khoaComboBox)
                        .addComponent(nganhComboBox)))
                .addComponent(tableScrollPane)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(addButton)
                    .addComponent(editButton)
                    .addComponent(confirmButton)
                    .addComponent(resetButton)
                    .addComponent(deleteButton)
                    .addComponent(exitButton))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(studentIDLabel)
                    .addComponent(studentIDField)
                    .addComponent(khoaLabel)
                    .addComponent(khoaComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstnameLabel)
                    .addComponent(firstnameField)
                    .addComponent(nganhLabel)
                    .addComponent(nganhComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(surnameLabel)
                    .addComponent(surnameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(genderLabel)
                    .addComponent(genderComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(birthplaceLabel)
                    .addComponent(birthplaceField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mobileLabel)
                        .addComponent(mobileField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(emailField))
                    .addComponent(tableScrollPane)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(editButton)
                    .addComponent(confirmButton)
                    .addComponent(resetButton)
                    .addComponent(deleteButton)
                    .addComponent(exitButton))
        );
    }

    public void refreshTable() {
        studentModel.loadData(); // Tải lại dữ liệu từ cơ sở dữ liệu
        tableModel.setDataVector(studentModel.getData(), studentModel.getColumnNames()); // Cập nhật mô hình bảng
    }

    public JTextField getStudentIDField() {
        return studentIDField;
    }
    
    public JTable getStudentTable() {
        return studentTable;
    }

    public JTextField getFirstnameField() {
        return firstnameField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JComboBox<String> getGenderComboBox() {
        return genderComboBox;
    }

    public JTextField getBirthplaceField() {
        return birthplaceField;
    }

    public JTextField getMobileField() {
        return mobileField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JComboBox<String> getKhoaComboBox() {
        return khoaComboBox;
    }

    public JComboBox<String> getNganhComboBox() {
        return nganhComboBox;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    
    public JButton getEditButton() {
        return editButton;
    }
    
    public JButton getConfirmButton() {
        return confirmButton;
    }
}
