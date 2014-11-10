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

/**
 *
 * @author fer
 */
@ManagedBean(name="convenio")
@RequestScoped
public class Convenio implements Serializable{
    private MySQL m;
    private String nombre;
    private Date fecha;
    private String organizacion;
    private String objetivos;
    private String rdepartamento;
    private String rcontraparte;
    private String detalle;
    private String imagen;
    
    
    public Convenio() {
        m= new MySQL("localhost","root","",3306,"registro");
        m.conectar();
    }

    public Convenio(String nombre, Date fecha, String organizacion, String objetivos, String rdepartamento, String rcontraparte, String detalle, String imagen) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.organizacion = organizacion;
        this.objetivos = objetivos;
        this.rdepartamento = rdepartamento;
        this.rcontraparte = rcontraparte;
        this.detalle = detalle;
        this.imagen = imagen;
    }
    
    public void guardarConvenio(){
        String agregarConvenio = "INSERT into convenio (nombre,fecha,organizacion,objetivos,rDepartamento,rContraparte,detalle,imagen)"
            + "VALUES ('"+nombre+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+organizacion+"','"+objetivos+"','"+rdepartamento+"','"+rcontraparte+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarConvenio);
        } catch (SQLException ex) {
            Logger.getLogger(Convenio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Convenio", "¡Se guardó el convenio!"));
     
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public MySQL getM() {
        return m;
    }

    public void setM(MySQL m) {
        this.m = m;
    }

    public String getRdepartamento() {
        return rdepartamento;
    }

    public void setRdepartamento(String rdepartamento) {
        this.rdepartamento = rdepartamento;
    }

    public String getRcontraparte() {
        return rcontraparte;
    }

    public void setRcontraparte(String rcontraparte) {
        this.rcontraparte = rcontraparte;
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
