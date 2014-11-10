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

@ManagedBean(name="comision")
@RequestScoped
public class Comision implements Serializable {
    private MySQL m;
    private String persona;
    private Date fecha;
    private String nombre;
    private String temas;
    private String organizaciones;
    private String detalle;
    private String imagen;
    

    public Comision() {
        m= new MySQL("localhost","root","",3306,"registro");
        m.conectar();
    }

    public Comision(String persona, Date fecha, String nombre, String temas, String organizaciones, String detalle, String imagen) {
        this.persona = persona;
        this.fecha = fecha;
        this.nombre = nombre;
        this.temas = temas;
        this.organizaciones = organizaciones;
        this.detalle = detalle;
        this.imagen = imagen;
    }
    
    public void guardarComision(){
        String agregarComision = "INSERT into comision (persona,fecha,nombre,temas,organizaciones,detalle,imagen) "
            + "VALUES ('"+persona+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+nombre+"','"+temas+"','"+organizaciones+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarComision);
        } catch (SQLException ex) {
            Logger.getLogger(Comision.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Comision/representacion", "¡Se guardó la comisión/representacion!"));
     
    }

    public MySQL getM() {
        return m;
    }

    public void setM(MySQL m) {
        this.m = m;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemas() {
        return temas;
    }

    public void setTemas(String temas) {
        this.temas = temas;
    }

    public String getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(String organizaciones) {
        this.organizaciones = organizaciones;
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
