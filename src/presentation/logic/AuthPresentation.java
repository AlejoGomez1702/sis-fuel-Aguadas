package presentation.logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Alejo
 */
public class AuthPresentation 
{
    private JFrame window;
    private JTextField username;
    private JTextField password;

    public AuthPresentation(JFrame window, JTextField username, JTextField password) 
    {
        this.window = window;
        this.username = username;
        this.password = password;
        this.startStyles();
    }

    public void startStyles()
    {        
        this.username.setHorizontalAlignment(JTextField.CENTER);
        this.password.setHorizontalAlignment(JTextField.CENTER);
        this.window.setLocationRelativeTo(null);
    }
    
    // id: 1==>username incorrect, 2==>password incorrect.
    public boolean checkInformation(int id)
    {
        boolean flag = true;
        
        if(id == 1)
        {
            JOptionPane.showMessageDialog(null, "Nombre De Usuario Incorrecto");
            this.username.setText("");
            this.password.setText("");
            flag = false;
        }
        else if(id == 2)
        {
            JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
            this.password.setText("");
            flag = false;
        }
        
        return flag;
    }

    public JFrame getWindow() {
        return window;
    }

    public JTextField getUsername() {
        return username;
    }

    public JTextField getPassword() {
        return password;
    }
        
}
