import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.diegorivas.db.MySQL;

@ManagedBean(name="premio")
@RequestScoped
public class Premio implements Serializable {
    private MySQL m;
    private String tipo;
    private String recipiendario;
    private Date fecha;
    private String nombrePremio;
    private String organizacion;
    private String detalle;
    private String imagen;
    private final String[] tipos= new String[] {"Premios y reconocimientos dados a docentes e investigadores",
                                                "Premios y reconocimientos dados a estudiantes (ajenos a UVG)",
                                                "Ingreso a Asociaciones Gremiales y Sociedades de Honor"}; 
    private ArrayList<String> b = new ArrayList();
  
    public Premio() {
        m= new MySQL("localhost","root","",3306,"registro");
        m.conectar();
        Collections.addAll(b, tipos);
    }

    public Premio(String tipo, String recipiendario, Date fecha, String nombrePremio, String organizacion, String detalle, String imagen) {
        this.tipo = tipo;
        this.recipiendario = recipiendario;
        this.fecha = fecha;
        this.nombrePremio = nombrePremio;
        this.organizacion = organizacion;
        this.detalle = detalle;
        this.imagen = imagen;
    }
    
      public void guardarPremio(){
        String agregarPremio = "INSERT into premio (tipo,recipiendario,fecha,nombrePremio,organizacion,detalle,imagen) "
            + "VALUES ('"+tipo+"','"+recipiendario+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+nombrePremio+"','"+organizacion+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarPremio);
        } catch (SQLException ex) {
            Logger.getLogger(Premio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Premio", "¡Se guardó el premio!"));
     
    }

    public MySQL getM() {
        return m;
    }

    public ArrayList<String> getB() {
        return b;
    }

    public void setB(ArrayList<String> b) {
        this.b = b;
    }

    
    public void setM(MySQL m) {
        this.m = m;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRecipiendario() {
        return recipiendario;
    }

    public void setRecipiendario(String recipiendario) {
        this.recipiendario = recipiendario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombrePremio() {
        return nombrePremio;
    }

    public void setNombrePremio(String nombrePremio) {
        this.nombrePremio = nombrePremio;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
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
