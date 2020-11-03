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
public class clsAlumno {

    //
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
 
    public ResultSet listarAlumnos() throws Exception {
        strSQL = "SELECT dniestudiante, carnetuniversitario, nombre, ap_paterno, ap_materno, nacionalidad, direccion, telefono, correo, cicloacademico, planestudios, carrerauniversitaria, escuela, vigencia FROM alumno;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar categoría");
        }
    }
 
    public void registrar(String dniestudiante, String carnetuniversitario, String nombre, String ap_paterno, String ap_materno, String nacionalidad, String direccion, String telefono, String correo, String cicloacademico, String planestudios, String carrerauniversitaria, String escuela, Boolean vigencia) throws Exception {
        strSQL = "insert into alumno values('" + dniestudiante + "','" + carnetuniversitario + "','" + nombre + "','" + ap_paterno + "','" + ap_materno + "','" + nacionalidad + "','" + direccion + "','" + telefono + "','" + correo + "','" + cicloacademico + "','" + planestudios + "','" + carrerauniversitaria + "','" + escuela + "'," + vigencia + ")";
        
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar alumno");
        }
    }

    public ResultSet buscarAlumno(String cod) throws Exception {
        strSQL = "select * from  alumno where dniestudiante ='" + cod + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar alumno");
        }
    }

    public void eliminarCliente(Integer cod) throws Exception {
        strSQL = "delete from CLIENTE where codCliente=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar cliente");
        }
    }

    public ResultSet buscarClienteDniRuc(String cod, Boolean tipo) throws Exception {
        if (tipo) {
            strSQL = "select * from CLIENTE C inner join TIPO_CLIENTE T on C.codTipo=T.codTipo where dni='" + cod + "'";
        } else {
            strSQL = "select * from CLIENTE C inner join TIPO_CLIENTE T on C.codTipo=T.codTipo where ruc='" + cod + "'";
        }
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar cliente");
        }
    }

    public void modificar(String dniestudiante, String carnetuniversitario, String nombre, String ap_paterno, String ap_materno, String nacionalidad, String direccion, String telefono, String correo, String cicloacademico, String planestudios, String carrerauniversitaria, String escuela, Boolean vigencia) throws Exception {
        strSQL = "UPDATE alumno SET carnetuniversitario='"+ carnetuniversitario+"', nombre='"+ nombre+"', ap_paterno='"+ ap_paterno+"', ap_materno='"+ ap_materno+"', nacionalidad='"+ nacionalidad+"', direccion='"+ direccion+"', telefono='"+ telefono+"', correo='"+ correo+"', cicloacademico='"+ cicloacademico+"', planestudios='"+ planestudios+"', carrerauniversitaria='"+ carrerauniversitaria+"', escuela='"+ escuela+"',vigencia="+ vigencia+" WHERE dniestudiante='" + dniestudiante + "'";
         try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar alumno" + e.getMessage());
        }
    }

}
