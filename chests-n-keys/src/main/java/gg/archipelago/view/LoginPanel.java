package gg.archipelago.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dev.koifysh.archipelago.Client;
import gg.archipelago.App;

import java.awt.event.ActionEvent;
import java.net.URISyntaxException;

/**
 * This is the menu where the user logs in to connect to the server.
 */
public class LoginPanel extends JPanel {
    // Make the text fields class variables so they can be accessed in multiple methods.
    private final JTextField urlField = new JTextField();
    private final JTextField portField = new JTextField();
    private final JTextField slotField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

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

        JButton submitButton = new JButton("Login");
        submitButton.addActionListener(this::login);

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

    private void login(ActionEvent e) {
        Client client = App.getClient();

        // Set the slot name and password of the client
        client.setName(slotField.getText());
        client.setPassword(new String(passwordField.getPassword()));

        // Try to connect to the given URL and port
        String url = urlField.getText() + ":" + portField.getText();
        try {
            client.connect(url);
        } catch (URISyntaxException ex) {
            System.out.println("\nFrom LoginPanel.login()");
            ex.printStackTrace();
        }
    }
}
