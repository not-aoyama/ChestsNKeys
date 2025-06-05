package gg.archipelago.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
    public LoginPanel() {
        // Create a GroupLayout object and associate it with this panel
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        // Specify automatic gap insertion
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Create components
        JLabel urlLabel = new JLabel("Server URL");
        JLabel portLabel = new JLabel("Server Port");
        JLabel slotLabel = new JLabel("Slot Name");
        JLabel passwordLabel = new JLabel("Server Password");

        JTextField urlField = new JTextField();
        JTextField portField = new JTextField();
        JTextField slotField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton submitButton = new JButton("Login");

        // Set up the layout with the components
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(urlLabel)
                    .addComponent(portLabel)
                    .addComponent(slotLabel)
                    .addComponent(passwordLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(urlField)
                    .addComponent(portField)
                    .addComponent(slotField)
                    .addComponent(passwordField)
                    .addComponent(submitButton))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(urlLabel)
                    .addComponent(urlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(portLabel)
                    .addComponent(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(slotLabel)
                    .addComponent(slotField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addComponent(submitButton)
        );
    }
}
