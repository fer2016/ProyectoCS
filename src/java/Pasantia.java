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

@ManagedBean(name="pasantia")
@RequestScoped
public class Pasantia implements Serializable {
    private MySQL m;
    private String nombreProyecto;
    private String nombreEncargado;
    private String nombreInstitucion;
    private String estudiantes;
    private Date fecha;
    private String cobertura;
    private String temas;
    private String detalle;
    private String imagen;
    
    public Pasantia() {
        m= new MySQL("localhost","root","",3306,"registro");
        m.conectar();
    }

    public Pasantia(String nombreProyecto, String nombreEncargado, String nombreInstitucion, String estudiantes, Date fecha, String cobertura, String temas, String detalle, String imagen) {
        this.nombreProyecto = nombreProyecto;
        this.nombreEncargado = nombreEncargado;
        this.nombreInstitucion = nombreInstitucion;
        this.estudiantes = estudiantes;
        this.fecha = fecha;
        this.cobertura = cobertura;
        this.temas = temas;
        this.detalle = detalle;
        this.imagen = imagen;
    }
    
    public void guardarPasantia(){
        String agregarPasantia = "INSERT into pasantia (nombreProyecto,nombreEncargado,nombreInstitucion,estudiantes,fecha,cobertura,temas,detalle,imagen)"
            + "VALUES ('"+nombreProyecto+"','"+ nombreEncargado+"','"+ nombreInstitucion+"','"+estudiantes+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+cobertura+"','"+temas+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarPasantia);
        } catch (SQLException ex) {
            Logger.getLogger(Pasantia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Trabajo de Campo/Pasantía/Práctica", "¡Se guardó Trabajo de Campo/Pasantía/Práctica!"));
     
    }

    public MySQL getM() {
        return m;
    }

    public void setM(MySQL m) {
        this.m = m;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
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

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public String getTemas() {
        return temas;
    }

    public void setTemas(String temas) {
        this.temas = temas;
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
