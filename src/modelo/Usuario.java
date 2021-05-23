package modelo;

public class Usuario
{
    String Nombre;
    String Apellido;
    String Nacimiento;
    String Direccion;
    String Email;
    String Telefono;
    int ID;

    public Usuario(String nombre, String apellido, String nacimiento, String direccion, String email, String telefono, int ID) {
        Nombre = nombre;
        Apellido = apellido;
        Nacimiento = nacimiento;
        Direccion = direccion;
        Email = email;
        Telefono = telefono;
        this.ID = ID;
    }

    public Usuario() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getNacimiento() {
        return Nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        Nacimiento = nacimiento;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}