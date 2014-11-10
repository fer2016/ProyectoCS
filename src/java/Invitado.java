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

@ManagedBean(name="invitado")
@RequestScoped
public class Invitado implements Serializable {
    private MySQL m;
    private String nombre;
    private Date fecha;
    private String tema;
    private String pais;
    private String universidad;
    private String fulbright;
    private String patrocinador;
    private String trabajoCampo;
    private String estudiantes;
    private String imparteCurso;
    private String cursos;
    private String curriculum;
    private String detalle;
    private String imagen;
    
    public Invitado() {
        m= new MySQL("localhost","root","",3306,"registro");
        m.conectar();
    }

    public Invitado(String nombre, Date fecha, String tema, String pais, String universidad, String fulbright, String patrocinador, String trabajoCampo, String estudiantes, String imparteCurso, String cursos, String curriculum, String detalle, String imagen) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tema = tema;
        this.pais = pais;
        this.universidad = universidad;
        this.fulbright = fulbright;
        this.patrocinador = patrocinador;
        this.trabajoCampo = trabajoCampo;
        this.estudiantes = estudiantes;
        this.imparteCurso = imparteCurso;
        this.cursos = cursos;
        this.curriculum = curriculum;
        this.detalle = detalle;
        this.imagen = imagen;
    }
    
    
    
    public void guardarInvitado(){
        String agregarInvitado = "INSERT into invitado (nombre,fecha,tema,pais,universidad,fulbright,patrocinador,trabajoCampo,estudiantes,imparteCurso,cursos,curriculum,detalle,imagen)"
            + "VALUES ('"+nombre+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+tema+"','"+pais+"','"+universidad+"','"+fulbright+"','"+patrocinador
                +"','"+trabajoCampo+"','"+estudiantes+"','"+imparteCurso+"','"+cursos+"','"+curriculum+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarInvitado);
        } catch (SQLException ex) {
            Logger.getLogger(Invitado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Invitado", "¡Se guardó el invitado!"));
     
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getFulbright() {
        return fulbright;
    }

    public void setFulbright(String fulbright) {
        this.fulbright = fulbright;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String getTrabajoCampo() {
        return trabajoCampo;
    }

    public void setTrabajoCampo(String trabajoCampo) {
        this.trabajoCampo = trabajoCampo;
    }

    public String getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(String estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getImparteCurso() {
        return imparteCurso;
    }

    public void setImparteCurso(String imparteCurso) {
        this.imparteCurso = imparteCurso;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
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
