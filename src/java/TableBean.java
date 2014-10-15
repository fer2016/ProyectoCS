
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
@ManagedBean(name = "tablebean")

public class TableBean implements Serializable {
    private List<Actividad> activities;
    private List<Actividad> droppedActivities = new ArrayList<>();
    private Actividad selectedActivity;
    private MySQL conexionMySQL;
    private JasperPrint jasperPrint;

    @PostConstruct
    public void init() {
        activities = new ArrayList<>();
        añadirActividades();
        JRBeanCollectionDataSource bean = new JRBeanCollectionDataSource(droppedActivities, false);

        try {
            String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporte2.jasper");
            jasperPrint = JasperFillManager.fillReport(report, new HashMap(), bean);
        } catch (JRException ex) {
            Logger.getLogger(TableBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void añadirActividades() {
        conexionMySQL = new MySQL("localhost", "root", "", 3306, "registro");
        conexionMySQL.conectar();
        ResultSet resultado = conexionMySQL.consulta("SELECT * FROM actividad");
        try {
            while (resultado.next()) {
                activities.add(new Actividad(
                        resultado.getString("nombre"),
                        resultado.getString("tipo"),
                        resultado.getDate("fecha"),
                        resultado.getString("personas"),
                        resultado.getString("detalle"),
                        resultado.getString("imagen")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onActivityDropped(DragDropEvent ddEvent) {
        Actividad actividad = ((Actividad) ddEvent.getData());
        droppedActivities.add(actividad);
        activities.remove(actividad);
    }

    public List<Actividad> getActivities() {
        return activities;
    }

    public List<Actividad> getDroppedActivities() {
        return droppedActivities;
    }

    public Actividad getSelectedActivity() {
        return selectedActivity;
    }

    public void setSelectedActivity(Actividad selectedActivity) {
        this.selectedActivity = selectedActivity;
    }

    public void generarPDF(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=file.pdf"); 
        FacesContext.getCurrentInstance().responseComplete();
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        servletOutputStream.flush();
        servletOutputStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
}
