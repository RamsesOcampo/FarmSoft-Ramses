package BusinessModelLayer;

import DataAccessLayer.DataAccess;
import javax.swing.table.DefaultTableModel;


public class Farmacia {
    private DataAccess dataAccess = DataAccess.Instance();
    private int idFarmacia;
    private String nombre;
    private String domicilio;
    private String telefono;
    private int activo;

    public Farmacia() {}

    public Farmacia(int idFarmacia, String nombre, String domicilio, String telefono, int activo) {
        this.idFarmacia = idFarmacia;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.activo = activo;
    }

    public int getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(int idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int isActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM farmacias";
        return dataAccess.Query(query);
    }
    
    public void GetById() {
        String query = "SELECT * FROM farmacias WHERE idFarmacia = " + idFarmacia;
        DefaultTableModel res = dataAccess.Query(query);
        idFarmacia = (int) res.getValueAt(0, 0);
        nombre = (String) res.getValueAt(0, 1);
        domicilio = (String) res.getValueAt(0, 2);
        telefono = (String) res.getValueAt(0, 3);
        activo = (int) res.getValueAt(0, 4);
    }
    
    public boolean Add() {
        String query = "INSERT INTO farmacias(nombre, domicilio, telefono, activo)"
                + "VALUES ('" + nombre + "', '" + domicilio + "', '" + telefono + "', " + activo + ");";
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Delete(){
        String query = "DELETE productos, farmacias FROM productos JOIN farmacias ON productos.idFarmacia=farmacias.idFarmacia WHERE farmacias.idFarmacia = " + idFarmacia;
        if(dataAccess.Execute(query) >= 1){
            return true;
        }else{
            query = "DELETE FROM farmacias WHERE idFarmacia = " + idFarmacia;
            return dataAccess.Execute(query) >= 1;
        }
    }
    
    public boolean Update(){
        String query = "UPDATE farmacias SET " +
                 "nombre = '" + nombre + "', " + 
                 "domicilio = '" + domicilio + "', " +
                 "telefono = '" + telefono + "', " + 
                 "activo = " + activo +  " " + 
                 "WHERE idFarmacia = " + idFarmacia;
        return dataAccess.Execute(query) >= 1;
    }
    
}
