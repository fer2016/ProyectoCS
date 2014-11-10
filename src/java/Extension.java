import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.diegorivas.db.MySQL;

@ManagedBean(name="extension")
@RequestScoped
public class Extension implements Serializable{
    private MySQL m;
    private String nombre;
    private String encargado;
    private String institucion;
    private String estudiantes;
    private Date fecha;
    private String comunidades;
    private String descripcion;
    private String detalle;
    private String imagen;
    
    public Extension() {
         m= new MySQL("localhost","root","",3306,"registro");
         m.conectar();
    }

    public Extension(String nombre, String encargado, String institucion, String estudiantes, Date fecha, String comunidades, String descripcion, String detalle, String imagen) {
        this.nombre = nombre;
        this.encargado = encargado;
        this.institucion = institucion;
        this.estudiantes = estudiantes;
        this.fecha = fecha;
        this.comunidades = comunidades;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.imagen = imagen;
    }
    
     
     public void guardarExtension(){
        String agregarExtension = "INSERT into extension (nombre,encargado,institucion,estudiantes,fecha,comunidades,descripcion,detalle,imagen)"
            + "VALUES ('"+nombre+"','"+encargado+"','"+institucion+"','"+estudiantes+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+comunidades+"','"+descripcion+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarExtension);
        } catch (SQLException ex) {
            Logger.getLogger(Extension.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Extension", "¡Se guardó: Extension!"));
     
    }

    public MySQL getM() {
        return m;
    }

    public void setM(MySQL m) {
        this.m = m;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(String estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComunidades() {
        return comunidades;
    }

    public void setComunidades(String comunidades) {
        this.comunidades = comunidades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
    
    
}
