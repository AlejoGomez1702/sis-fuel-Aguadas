package bd;

import bd.cruds.*;

/**
 * FECHA ==> 2019-06-01.
 * Posee todos los CRUD con la base de datos.
 * @author Luis Alejandro Gómez C.
 * @version 1.0 .
 */
public class BaseDatos 
{
    /**
     * Conexión con la base de datos.
     */
    private Conexion conexion;
    
    private CrudFuel crudFuel;  
    private CrudVehicle crudVehicle;
    private CrudExpense crudExpense;
        
    /**
     * Prepara todos los cruds con la base de datos.
     * @param con Conexión con la base de datos.
     */
    public BaseDatos(Conexion con)
    {
        this.conexion = con;
        this.crudFuel = new CrudFuel(conexion);
        this.crudVehicle = new CrudVehicle(conexion, crudFuel);
        this.crudExpense = new CrudExpense(conexion, crudVehicle);
        //this.crudProveedores = new CrudProveedor(con);
        //this.crudCategorias = new CrudCategoria(con);
        
    }

    public CrudFuel getCrudFuel() 
    {
        return crudFuel;
    }

    public CrudVehicle getCrudVehicle() {
        return crudVehicle;
    }

    public CrudExpense getCrudExpense() {
        return crudExpense;
    }
               
    /**
     * Obtiene la conexión con la base de datos del sistema.
     * @return Conexión con la base de datos.
     */
    public Conexion getConexion() 
    {
        return conexion;
    }   
       
        
}
