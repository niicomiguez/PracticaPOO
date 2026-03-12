
/**
 * Write a description of class Trabajador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Trabajador {
    private String nombre;
    private String apellidos;
    private String DNI;
    private String direccion;
    private int numSeguridadSocial;
    private Puesto puesto;
    private int salario;
    private String fechaIngreso;
    
    public enum Puesto {
    OPERARIO,GESTOR,ADMINISTRADOR,MECANICO
    }

    public Trabajador(String nombre, String apellidos, String direccion, String DNI, int numSeguridadSocial, Puesto puesto, int salario, String fechaIngreso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.DNI = DNI;
        this.numSeguridadSocial = numSeguridadSocial;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public int getNumSeguridadSocial() {
        return numSeguridadSocial;
    }

    public void setNumSeguridadSocial(int numSeguridadSocial) {
        this.numSeguridadSocial = numSeguridadSocial;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", DNI='" + DNI + '\'' +
                ", direccion='" + direccion + '\'' +
                ", numSeguridadSocial=" + numSeguridadSocial +
                ", puesto=" + puesto +
                ", salario=" + salario +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                '}';
    }
}
