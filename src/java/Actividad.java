import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.joseflores.db.MySQL;
import org.primefaces.context.RequestContext;
/**
 *
 * @author fer
 */
@ManagedBean(name="actividad")
@RequestScoped
@SessionScoped
public class Actividad implements Serializable{
   
    private MySQL m;
    private String nombre;
    private String tipo;
    private Date fecha;
    private String personas;  
    private String detalle;
    private String imagen;
    
    public Actividad() {
        m= new MySQL("localhost","root","",3306,"registro");
        m.conectar();
    }

    public Actividad(String nombre, String tipo, Date fecha, String personas, String detalle, String imagen ){
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.personas = personas;
        this.imagen = imagen;
        this.detalle = detalle;
    }
    
    
    public void guardarActividad(){
        String agregarActividad = "INSERT into actividad (nombre,tipo,fecha,personas,detalle,imagen) "
            + "VALUES ('"+nombre+"','"+tipo+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+personas+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarActividad);
        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Actividad", "¡Se guardó la actividad!"));
     
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPersonas() {
        return personas;
    }

    public void setPersonas(String personas) {
        this.personas = personas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    
    
}
