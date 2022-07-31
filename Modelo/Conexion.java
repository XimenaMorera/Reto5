package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    //atributo tipo Conexion permite la conexion entre el netbean y el xaamp
    Connection connection;
    //innicializamos una varible tipo String Driver
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String user= "root"; //por defecto siempre que se decarga el xamp se trae el usuario root
    private final String password= "";
    private final String urlConexion = "jdbc:mysql://localhost:3306/reto1_xm"; //direccion de conexion al proyecto    
    //añadimos el cosntructor sin argumentos osea no se selecciona nada:
    public Conexion() {
        //inicializamos la variable conexion, intentamos hacer conexion mediante el bloque try catch
        try {
            Class.forName(driver); // busqueda del nombre de  la clase
            //necesitamos establecer conexion
            connection = (Connection)DriverManager.getConnection(urlConexion, user,password);
            if(connection != null){
                System.out.println("Conexion exitosa a la base de datos");

            }
        } catch(ClassNotFoundException | SQLException e)  {
            System.out.println("Error en la conexion" + e);
        }
    }
    //4 Crear funcion getConnection
    public Connection getConnection(){
        // Puede retornar  el mensaje de que si se conecto o que no se pudo conectar
        return connection;
    }
}
