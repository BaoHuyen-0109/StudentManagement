package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("Đăng nhập");
        setSize(400, 250); // Đặt kích thước khung đăng nhập
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(titleLabel, constraints);

        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(usernameLabel, constraints);

        usernameField = new JTextField(15); // Tăng kích thước của usernameField
        usernameField.setPreferredSize(new Dimension(200, 25)); // Đặt kích thước cố định
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(15); // Tăng kích thước của passwordField
        passwordField.setPreferredSize(new Dimension(200, 25)); // Đặt kích thước cố định
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, constraints);

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(loginButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = String.valueOf(passwordField.getPassword());

                // Kiểm tra tên đăng nhập và mật khẩu
                if (username.equals("HThello") && password.equals("12345")) {
                    // Đăng nhập thành công, chuyển sang trang Homepage
                    Homepage homepage = new Homepage();
                    homepage.setVisible(true);
                    dispose(); // Đóng cửa sổ đăng nhập sau khi đăng nhập thành công
                } else {
                    // Hiển thị thông báo lỗi đăng nhập
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Tên đăng nhập hoặc mật khẩu không đúng",
                            "Lỗi đăng nhập",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        // Set look and feel to Nimbus for a modern UI (optional)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}
