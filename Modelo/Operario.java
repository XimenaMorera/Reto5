
package Modelo;


public class Operario extends Empleado {
    private boolean manejaMaquinaPesada;

    public Operario(boolean manejaMaquinaPesada, float salario, String puestoTrabajo, int idPersona, String nombrePersona, String apellidos, String tipoDocumento, String documento, String correo) {
        super(salario, puestoTrabajo, idPersona, nombrePersona, apellidos, tipoDocumento, documento, correo);
        this.manejaMaquinaPesada = manejaMaquinaPesada;
    }

    public Operario() {
    }

    public boolean isMaquinaPesada(){
        return manejaMaquinaPesada;
    }
    public void setManejaMaquinaPesada(boolean manejaMaquinaPesada){
        this.manejaMaquinaPesada = manejaMaquinaPesada;
    }

    @Override
    public String toString() {
        return "Operario{" + "manejaMaquinaPesada=" + manejaMaquinaPesada + '}';
    }
    
    
}
