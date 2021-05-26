/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class DownloadBean {

    private StreamedContent file;
    private int codigo;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
    
public void download(){
        ResultSet rs;
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mitocode?user=root&password=admin");
                
                PreparedStatement st = cn.prepareStatement("SELECT img FROM imagen WHERE codigo = (?)");
                st.setInt(1,codigo);
                rs= st.executeQuery();
                while (rs.next()){
                    InputStream stream = rs.getBinaryStream("img");
                    file = new DefaultStreamedContent(stream, "image/jpg", "descarga.jpg");
                }
                cn.close();
                FacesMessage message = new FacesMessage("Exito",  "Fue descargado.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            
        } catch (Exception e){
            FacesMessage message = new FacesMessage("Error de conexion."); 
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
           
    }

    public void ver() {
        ResultSet rs;

        try {
            byte[] bytes = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mitocode?user=root&password=admin");

            PreparedStatement st = cn.prepareStatement("SELECT img FROM imagen WHERE codigo = (?)");
            st.setInt(1, codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                bytes = rs.getBytes("img");
            }
            cn.close();

            FacesMessage message = new FacesMessage("Exito", "Fue descargado.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.getOutputStream().write(bytes);
            response.getOutputStream().close();

            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Error de conexion");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
