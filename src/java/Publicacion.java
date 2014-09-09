import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.joseflores.db.MySQL;
import org.primefaces.context.RequestContext;

/**
 *
 * @author fer
 */
@ManagedBean(name="publicacion")
@RequestScoped
public class Publicacion implements Serializable{
    private MySQL mySQL;
    private String nombre;
    private String tipo;
    private Date fecha;
    private String personas;  
    private String detalle;
    private String archivo;

    public Publicacion() {
        mySQL= new MySQL("localhost","root","",3306,"registro");
        mySQL.conectar();
    }

    public Publicacion(String nombre, String tipo, Date fecha, String personas, String detalle, String archivo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.personas = personas;
        this.detalle = detalle;
        this.archivo = archivo;
    }

    
    
    public void guardarPublicacion(){
        String agregarPublicacion = "INSERT into publicacion (nombre,tipo,fecha,personas,detalle,archivo) "
            + "VALUES ('"+nombre+"','"+tipo+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+personas+"','"+detalle+"','"+archivo+"')";
        try {
            mySQL.getSentencia().execute(agregarPublicacion);
        } catch (SQLException ex) {
            Logger.getLogger(Publicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
     public void showMessage(){
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, 
        "Ingreso de Publicacion", "¡Se guardó la publicacion!"));
    
    }
    
    

    public MySQL getMySQL() {
        return mySQL;
    }

    public void setMySQL(MySQL mySQL) {
        this.mySQL = mySQL;
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    
}
