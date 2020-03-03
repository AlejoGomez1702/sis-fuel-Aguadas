/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.logic;

import javax.swing.JTable;

/**
 *
 * @author Alejo
 */
public class TableManager 
{
    
    public int getFuelCode(String item)
    {
        if(item.equals(""))
            return -1;
        
        int fuelCode = -1;
        String[] info = item.split("#");
        try {
            fuelCode = Integer.parseInt(info[0]);
        } catch (NumberFormatException e) {
        }
        
        
        return fuelCode;        
    }    
    
    /**
     * Obtiene el código del elemento seleccionado en la tabla.
     * @param tabla Tabla del inventario de donde se obtendra el elemento.
     * @return null-->No se seleccionó elemento, else-->código del elemento.
     */
    public int getIdElementFromTable(JTable tabla)
    {
        int id = -1;
        int row1 = tabla.getSelectedRow();
        //int row = convertRowIndexToModel(tabla);
        int row = tabla.convertRowIndexToModel(row1);
        if(row != -1)
        {
            try 
            {
                String code = tabla.getModel().getValueAt(row, 0).toString();
                id = Integer.parseInt(code);
            } catch (Exception e) 
            {
                return -1;                
            }            
        }
        
        return id;        
    }
    
}
