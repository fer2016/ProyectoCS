import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.diegorivas.db.MySQL;

@ManagedBean(name="innovacion")
@RequestScoped
public class Innovacion implements Serializable{
    private MySQL m;
    private String nombreProfesor;
    private String nombreInnovacion;
    private String detalle;
    private String imagen;
    
    public Innovacion() {
         m= new MySQL("localhost","root","",3306,"registro");
         m.conectar();
    }

    public Innovacion(String nombreProfesor, String nombreInnovacion, String detalle, String imagen) {
        this.nombreProfesor = nombreProfesor;
        this.nombreInnovacion = nombreInnovacion;
        this.detalle = detalle;
        this.imagen = imagen;
    }
    
     public void guardarInnovacion(){
        String agregarInnovacion = "INSERT into innovacion (nombreProfesor,nombreInnovacion,detalle,imagen)"
            + "VALUES ('"+nombreProfesor+"','"+nombreInnovacion+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarInnovacion);
        } catch (SQLException ex) {
            Logger.getLogger(Innovacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Innovacion", "¡Se guardó Innovacion!"));
     
    }

    public MySQL getM() {
        return m;
    }

    public void setM(MySQL m) {
        this.m = m;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getNombreInnovacion() {
        return nombreInnovacion;
    }

    public void setNombreInnovacion(String nombreInnovacion) {
        this.nombreInnovacion = nombreInnovacion;
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
