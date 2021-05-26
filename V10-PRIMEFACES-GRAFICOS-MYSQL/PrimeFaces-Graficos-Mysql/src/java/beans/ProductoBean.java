/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProductoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import model.Producto;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@SessionScoped
public class ProductoBean {
    private PieChartModel pieModel;

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
    public void listar(){
        ProductoDAO dao;
        List<Producto> lista;
            System.out.println("00-productoBean-listar()");
        
        try {
            dao = new ProductoDAO();
            System.out.println("00-01-productoBean-listar()");
            lista = dao.listar();
            System.out.println("01-productoBean-listar()-tamanio:"+lista.size());
            graficar(lista);
        } catch (Exception e){
            System.out.println("02-productoBean-listar()");
            
        } finally {
            
        }
            System.out.println("03-productoBean-listar()");
    }
    
    
    
    
    
    
    
    
    public void graficar(List<Producto> lista){
        pieModel = new PieChartModel();
            System.out.println("00-productoBean-graficar()");
        
        for (Producto pro: lista){
            System.out.println("01-productoBean-graficar()");
            pieModel.set(pro.getNombre(), pro.getPrecio());
        }
        
            System.out.println("02-productoBean-graficar()");
        pieModel.setTitle("Precios");
        pieModel.setLegendPosition("e");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
            System.out.println("03-productoBean-graficar()");
    }
}
