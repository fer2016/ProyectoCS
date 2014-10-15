import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.diegorivas.db.MySQL;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author fer
 */
@SessionScoped
@ManagedBean(name= "tablebean2")

public class TableBean2 implements Serializable {
    private List<Publicacion> publicaciones;
    private List<Publicacion> droppedPublicaciones = new ArrayList<>();
    private Publicacion selectedPublicacion;
    private MySQL conexionMySQL;
    private JasperPrint jasperPrint;

   @PostConstruct
    public void init(){      
        publicaciones= new ArrayList<>();     
       
        añadirPublicaciones();
  
        JRBeanCollectionDataSource bean = new JRBeanCollectionDataSource(droppedPublicaciones,false);
     
        try {
            String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporte.jasper");
            jasperPrint = JasperFillManager.fillReport(report, new HashMap(),bean);
        } catch (JRException ex) {
            Logger.getLogger(TableBean2.class.getName()).log(Level.SEVERE, null, ex);
        }  
      
    }
        
    public void añadirPublicaciones(){
        conexionMySQL = new MySQL("localhost", "root", "", 3306, "registro");
        conexionMySQL.conectar();
        ResultSet resultado = conexionMySQL.consulta("SELECT * FROM publicacion");  
        try{    
            while(resultado.next()){
                publicaciones.add(new Publicacion(
                        resultado.getString("nombre"),
                        resultado.getString("tipo"),
                        resultado.getDate("fecha"),
                        resultado.getString("personas"),
                        resultado.getString("detalle"),
                        resultado.getString("archivo")));      
            }           
        }catch(SQLException e){
            e.printStackTrace();
        }    
    }
      
    public void onActivityDropped(DragDropEvent ddEvent){
        Publicacion publicacion= ((Publicacion)ddEvent.getData());
        droppedPublicaciones.add(publicacion);
        publicaciones.remove(publicacion);             
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public List<Publicacion> getDroppedPublicaciones() {
        return droppedPublicaciones;
    }

    public Publicacion getSelectedPublicacion() {
        return selectedPublicacion;
    }

    public void setSelectedPublicacion(Publicacion selectedPublicacion) {
        this.selectedPublicacion = selectedPublicacion;
    }
    
     public void generarPDF(ActionEvent actionEvent) throws JRException, IOException{
        init();  
        HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
        //httpServletResponse.addHeader("Content-disposition", "attachment; filename=file.pdf"); 
        FacesContext.getCurrentInstance().responseComplete();
        ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();  
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream); 
        servletOutputStream.flush();
        servletOutputStream.close(); 
        FacesContext.getCurrentInstance().responseComplete();        
    }

    
}
