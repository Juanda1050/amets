package modelo;

public class Empleado {
    private int IDempleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String contraseña;
    private int turno;
    private int puesto;

    public Empleado(int IDempleado, String nombreEmpleado, String apellidoEmpelado, String contraseña, int turno, int puesto) {
        this.IDempleado = IDempleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpelado;
        this.contraseña = contraseña;
        this.turno = turno;
        this.puesto = puesto;
    }
    public Empleado(){

    }
    public int getIDempleado() {
        return IDempleado;
    }

    public void setIDempleado(int IDempleado) {
        this.IDempleado = IDempleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }


}
