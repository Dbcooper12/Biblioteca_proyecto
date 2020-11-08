/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author manue
 */
public class clsEspecialidad {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public void registrar(String codigoespecialidad, String nombreespecialidad, Boolean estado) throws Exception {
        strSQL = "insert into especialidad values('" + codigoespecialidad + "','" + nombreespecialidad + "'," + estado + ")";

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar especialidad");
        }
    }
     public void darBaja(String cod) throws Exception{
        strSQL="update especialidad set estado=false where codigoespecialidad='" + cod+"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja especialidad");
        }
    }
     public ResultSet listarEspecialidad() throws Exception {
        strSQL = "SELECT * FROM especialidad;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar especialidad");
        }
    }
  
    public ResultSet buscarEspecialidad(String codigoespecialidad) throws Exception {
        strSQL = "select * from  especialidad where codigoespecialidad ='" + codigoespecialidad + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar especialidad");
        }
    }

    
     public void modificar(String codigoespecialidad, String nombreespecialidad, Boolean estado) throws Exception {
       strSQL = "UPDATE especialidad SET nombreespecialidad='"+ nombreespecialidad +"',estado="+ estado+" WHERE codigoespecialidad='" + codigoespecialidad + "'";
         try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar alumno" + e.getMessage());
        }
    }

     

}
