package presentation.logic;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Alejo
 */
public class ConfigTables 
{
    /**
     * Tipo de letra y tamaño para las tablas del sistema.
     */
    private Font fontTables;
    
    /**
     * Tipo de letra y tamaño para los encabezados de las tablas del sistema.
     */
    private Font fontHeaders;
    
    /**
     * Color de la letra de los titulos de tablas del sistema.
     */
    private Color headersColor;
    /**
     * Color de las celdas de los titulos de tablas en el sistema.
     */
    private Color headersRowsColor;
    
    //*********************************************************************//
    
    //Modelos de las tablas.
    private DefaultTableModel fuelModel = new DefaultTableModel();
    private DefaultTableModel vehicleModel = new DefaultTableModel();
    private DefaultTableModel expenseModel = new DefaultTableModel();
    private DefaultTableModel detailVehicleModel = new DefaultTableModel();

    public ConfigTables() 
    {
        this.fontTables = new Font("Cambria", 0, 16);
        this.fontHeaders = new Font("Cambria", 0, 18);
        this.headersColor = new Color(255,255,255);
        this.headersRowsColor = new Color(0,0,41);
    }
    
    
    public ConfigTables(JTable fuelTable, JTable vehicleTable, 
                            JTable expenseTable) 
    {
        this.fontTables = new Font("Cambria", 0, 16);
        this.fontHeaders = new Font("Cambria", 0, 18);
        this.headersColor = new Color(255,255,255);
        this.headersRowsColor = new Color(0,0,41);
        this.initConfig(fuelTable, vehicleTable, expenseTable);
    }
    
    public void initConfig(JTable fuelTable, JTable vehicleTable, 
                                    JTable expenseTable)
    {
        JTableHeader header;
        
        //Tabla para el combustible.
        this.fuelModel.addColumn("ID");
        this.fuelModel.addColumn("Nombre");
        this.fuelModel.addColumn("Valor X Galón");
        fuelTable.setModel(fuelModel);
        fuelTable.setFont(this.fontTables);    
        header = fuelTable.getTableHeader();
        header.setFont(this.fontHeaders);
        header.setForeground(this.headersColor);
        header.setBackground(this.headersRowsColor);
        
        // Tabla para los vehículos.
        this.vehicleModel.addColumn("ID");
        this.vehicleModel.addColumn("Descripción");
        this.vehicleModel.addColumn("Combustible");
        vehicleTable.setModel(vehicleModel);
        vehicleTable.setFont(this.fontTables);    
        header = vehicleTable.getTableHeader();
        header.setFont(this.fontHeaders);
        header.setForeground(this.headersColor);
        header.setBackground(this.headersRowsColor);
        
        // Tabla para los gastos.
        this.expenseModel.addColumn("ID");
        this.expenseModel.addColumn("Vehículo");
        this.expenseModel.addColumn("Fecha (A-M-D)");
        this.expenseModel.addColumn("Cod. Factura");
        this.expenseModel.addColumn("Valor");        
        expenseTable.setModel(expenseModel);
        expenseTable.setFont(this.fontTables);    
        header = expenseTable.getTableHeader();
        header.setFont(this.fontHeaders);
        header.setForeground(this.headersColor);
        header.setBackground(this.headersRowsColor);                  
    }
    
    public void startDetailVehicle(JTable detailVehicleTable)
    {
        JTableHeader header;
        //Tabla para el detalle de gastos de un vehículo.
        this.detailVehicleModel.addColumn("ID");
        this.detailVehicleModel.addColumn("Fecha (A-M-D)");
        this.detailVehicleModel.addColumn("Cod. Factura");
        this.detailVehicleModel.addColumn("Valor");        
        detailVehicleTable.setModel(this.detailVehicleModel);
        detailVehicleTable.setFont(this.fontTables);    
        header = detailVehicleTable.getTableHeader();
        header.setFont(this.fontHeaders);
        header.setForeground(this.headersColor);
        header.setBackground(this.headersRowsColor);
    }

    public DefaultTableModel getFuelModel() {
        return fuelModel;
    }

    public DefaultTableModel getVehicleModel() {
        return vehicleModel;
    }

    public DefaultTableModel getExpenseModel() {
        return expenseModel;
    }

    public DefaultTableModel getDetailVehicleModel() {
        return detailVehicleModel;
    }
    
    
       
}
