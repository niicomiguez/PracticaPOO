/**
 * Clase abstracta que define la estructura base de cualquier empleado de la fábrica.
 * Centraliza la información personal, contractual y administrativa común a todos
 * los perfiles profesionales (Operarios, Mecánicos, Administradores y Gestores).
 * * Como clase abstracta, no permite su instanciación directa, obligando a definir
 * el comportamiento específico en sus clases hijas.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public abstract class Trabajador {
    private String nombre;
    private String apellidos;
    private String DNI;
    private String direccion;
    private int numSeguridadSocial;
    private String puesto;
    private double salario;
    private String fechaIngreso;

    /**
     * Constructor para la clase Trabajador.
     * * @param nombre              Nombre del empleado.
     * @param apellidos           Apellidos completos.
     * @param direccion           Residencia habitual.
     * @param DNI                 Identificador fiscal único.
     * @param numSeguridadSocial  Número de afiliación.
     * @param puesto              Rol profesional dentro de la fábrica.
     * @param salario             Sueldo bruto.
     * @param fechaIngreso        Fecha de alta en el sistema.
     */
    public Trabajador(String nombre, String apellidos, String direccion, String DNI,
                      int numSeguridadSocial, String puesto, double salario, String fechaIngreso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.DNI = DNI;
        this.numSeguridadSocial = numSeguridadSocial;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    // region GETTERS Y SETTERS
    public String getPuesto() { return puesto; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }

    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDNI() { return DNI; }

    public void setDNI(String DNI) { this.DNI = DNI; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getNumSeguridadSocial() { return numSeguridadSocial; }

    public void setNumSeguridadSocial(int numSeguridadSocial) { this.numSeguridadSocial = numSeguridadSocial; }

    public double getSalario() { return salario; }

    public void setSalario(double salario) { this.salario = salario; }

    public String getFechaIngreso() { return fechaIngreso; }

    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    // endregion

    /**
     * Representación textual de los datos básicos del trabajador.
     * @return Cadena con la ficha del empleado.
     */
    @Override
    public String toString() {
        return String.format("Trabajador [%-15s] | DNI: %-10s | %s %s | SS: %d | Ingreso: %s",
                puesto, DNI, nombre, apellidos, numSeguridadSocial, fechaIngreso);
    }
}