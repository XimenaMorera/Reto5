    package Principal;
import Vistas.*;
import Modelo.*;


public class Main {


    public static void main(String[] args) {
        //1.  Creamos la isntancia de la clase conexion
        Conexion conexion = new Conexion();
        conexion.getConnection();   
        //DatosModeloDB datosModeloDB= new DatosModeloDB();
        //datosModeloDB.getDepartamentoEmpresa("999999999");
        //datosModeloDB.pruebaFunciones();
        //2. creamos unsa instancia de la vista login
        Login login = new Login();
        //3. Mediante la instacia de la vista llamamos al metodo que la hace visible  
        login.setVisible(true);
    }
    
}
