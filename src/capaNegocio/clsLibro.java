/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author manue
 */
public class clsLibro {
    
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    
    
    
    public void Registrar_Libro(Integer cod,String p_nombre, String p_autor, String p_pais, java.util.Date p_fecha, int p_codigoEditorial,int p_codigoEspecialidad, Boolean estado) throws Exception {
        strSQL = "insert into libro values (" + cod + ", '" + p_nombre + "', '" + p_autor + "', "
                + "'" + p_pais + "', '" + p_fecha + "'," + p_codigoEditorial+ ", " + p_codigoEspecialidad   + ", " + estado +")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
        public Integer generarCodigoLibro() throws Exception{
        strSQL = "SELECT COALESCE(max(codigolibro),0)+1 as codigo from libro" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código del libro");
        }
        return 0;
    }
         public int obtenerIDLibro() throws Exception {
        int id = 0;
        strSQL = "select COALESCE(MAX(codigolibro), 0) + 1  from libro";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                id = rs.getInt(1);
                return id;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return -1;
    }
    public ResultSet buscarlibro(Integer cod) throws Exception{
        strSQL="select l.codigolibro,l.nombre,l.autor,l.pais,l.fecha,e.nombreeditorial,es.nombreespecialidad,l.estado \n" 
                  + "from libro l \n" 
                  + "inner join editorial e on e.codigoeditorial = l.codigoeditorial\n" 
                  + "inner join especialidad es on es.codigoespecialidad = l.codigoespecialidad where codigolibro=" + cod;
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar productos.") ;
        }
    }
        public ResultSet Listar() throws Exception {
        strSQL = "select l.*,e.nombreeditorial,es.nombreespecialidad from libro l inner join editorial e on e.codigoeditorial = l.codigoeditorial inner join especialidad es on es.codigoespecialidad = l.codigoespecialidad" ;
                  
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + "error al consultar lista" + e.getLocalizedMessage());
        }
    }
    public void Modificar(int codLibro, String p_nombre, String p_autor, String p_pais, java.util.Date p_fecha,int p_codigoEditorial,int p_codigoEspecialidad, Boolean estado) throws Exception {
        strSQL = "update libro set nombre = '" + p_nombre + "', autor = '" + p_autor + "',pais = '" + p_pais + "',fecha = '" + p_fecha + "',codigoeditorial = " + p_codigoEditorial + ", codigoespecialidad = " + p_codigoEspecialidad + ", estado=" + estado +" where codigolibro = " + codLibro;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar " + e.getMessage());
        }

    }
    
        public void DarBaja(int cod) throws Exception {
        strSQL = "update libro set estado=false where codigoLibro=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja libro" + e.getMessage());
        }
    }

    public void Eliminar(int cod) throws Exception {

        strSQL = "delete from libro where codigoLibro = " + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar Libro" + e.getMessage());
        }
    }  
}

