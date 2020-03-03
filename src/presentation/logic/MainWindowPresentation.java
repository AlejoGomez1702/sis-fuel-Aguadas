package presentation.logic;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alejo
 */
public class MainWindowPresentation 
{
    public void runInteractiveButton(JLabel button, boolean visibility)
    {
        if(visibility)
        {
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
        else
        {
            button.setBorder(null);
        } 
    }
    
    public void runInteractiveButton(JPanel boton, JLabel etiqueta, boolean indicador)
    {
        if(indicador)
        {
            boton.setBackground(new Color(0,153,153));
            etiqueta.setBackground(new Color(0,153,153));
        }
        else
        {
            boton.setBackground(new Color(255,255,255));
            etiqueta.setBackground(new Color(255,255,255));
        }        
    }
    
    /**
     * Modifica el contenido principal que visualiza el usuario.
     * @param contenedor Panel principal donde se pintara la información.
     * @param contenido Panel con la información que se quiere pintar.
     */
    public void refreshPanel(JPanel contenedor, JPanel contenido)
    {
        contenedor.removeAll();
        contenedor.repaint();
        contenedor.revalidate();
        
        //Pintando
        contenedor.add(contenido);      
        contenedor.repaint();
        contenedor.revalidate();        
    }
    
    
}
