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
    
    
    
    public void Registrar_Libro(Integer cod,String p_nombre, String p_autor, String p_pais, java.util.Date p_fecha,int p_codigoEditorial,int p_codigoEspecialidad) throws Exception {
        strSQL = "insert into libro values (" + cod + ", '" + p_nombre + "', '" + p_autor + "', '" + p_pais + "', '" + p_fecha + "','DISPONIBLE'," + p_codigoEditorial+ ", " + p_codigoEspecialidad   + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
           throw new Exception("Error al registrar libro");
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
            throw new Exception(e.getMessage() + ": Error al consultar libro.") ;
        }
    }
        public ResultSet Listar() throws Exception {
        strSQL = "select l.*,e.nombreeditorial,es.nombreespecialidad from libro l inner join editorial e on e.codigoeditorial = l.codigoeditorial inner join especialidad es on es.codigoespecialidad = l.codigoespecialidad order by codigolibro" ;
                  
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + "error al consultar lista" + e.getLocalizedMessage());
        }
    }
        public ResultSet ListarLibro() throws Exception {
        strSQL = "select l.codigolibro, l.nombre,l.autor, l.estado,e.nombreeditorial,es.nombreespecialidad from libro l inner join editorial e on l.codigoeditorial = e.codigoeditorial inner join especialidad es on l.codigoespecialidad = es.codigoespecialidad" ;
                  
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + "error al consultar lista" + e.getLocalizedMessage());
        }
    }
    public void Modificar(int codLibro, String p_nombre, String p_autor, String p_pais, java.util.Date p_fecha,int p_codigoEditorial,int p_codigoEspecialidad) throws Exception {
        strSQL = "update libro set nombre = '" + p_nombre + "', autor = '" + p_autor + "',pais = '" + p_pais + "',fecha = '" + p_fecha + "',codigoeditorial = " + p_codigoEditorial + ", codigoespecialidad = " + p_codigoEspecialidad + " where codigolibro = " + codLibro;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar " + e.getMessage());
        }

    }
    
    public ResultSet buscarlibropornombre(String nombre) throws Exception{
        strSQL="select l.codigolibro, l.nombre,l.autor,l.estado,e.nombreeditorial,es.nombreespecialidad from libro l inner join editorial e on l.codigoeditorial = e.codigoeditorial inner join especialidad es on l.codigoespecialidad = es.codigoespecialidad where nombre like '" + nombre + "%' order by codigolibro "; 
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar libro.") ;
        }
    
}
     public ResultSet buscarlibroporDNI (String dni) throws Exception{
        strSQL="select al.dniestudiante,al.ap_paterno,al.ap_materno,al.nombre,r.codigoreserva, r.fechareserva,l.nombre,l.autor,r.estado from reserva r inner join libro l on r.codigolibro = l.codigolibro inner join alumno al on r.dnialumno = al.dniestudiante where r.dnialumno='" + dni + "'"; 
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar libro!.") ;
        }
    
}
      public ResultSet buscarlibroporEstado (String estado) throws Exception{
        strSQL="select l.codigolibro, l.nombre,l.autor,e.nombreeditorial,es.nombreespecialidad, l.estado from libro l inner join editorial e on l.codigoeditorial = e.codigoeditorial inner join especialidad es on l.codigoespecialidad = es.codigoespecialidad where l.estado='" + estado + "' order by codigolibro"; 
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar libro.!") ;
        }
    
}
}

