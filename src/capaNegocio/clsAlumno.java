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
        strSQL = "SELECT dniestudiante, carnetuniversitario, nombre, ap_paterno, ap_materno, nacionalidad, direccion, telefono, correo, cicloacademico, carrerauniversitaria, escuela, vigencia FROM alumno;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar alumnos");
        }
    }
 
    public void registrar(String dniestudiante, String carnetuniversitario, String nombre, String ap_paterno, String ap_materno, String nacionalidad, String direccion, String telefono, String correo, String cicloacademico, String carrerauniversitaria, String escuela, Boolean vigencia) throws Exception {
        strSQL = "insert into alumno values('" + dniestudiante + "','" + carnetuniversitario + "','" + nombre + "','" + ap_paterno + "','" + ap_materno + "','" + nacionalidad + "','" + direccion + "','" + telefono + "','" + correo + "','" + cicloacademico + "','" + carrerauniversitaria + "','" + escuela + "'," + vigencia + ")";
        
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

    public void eliminarAlumno(String cod) throws Exception {
        strSQL = "delete from alumno where dniestudiante='" + cod+"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar Alumno");
        }
    }
 
    public void modificar(String dniestudiante, String carnetuniversitario, String nombre, String ap_paterno, String ap_materno, String nacionalidad, String direccion, String telefono, String correo, String cicloacademico, String carrerauniversitaria, String escuela, Boolean vigencia) throws Exception {
        strSQL = "UPDATE alumno SET carnetuniversitario='"+ carnetuniversitario+"', nombre='"+ nombre+"', ap_paterno='"+ ap_paterno+"', ap_materno='"+ ap_materno+"', nacionalidad='"+ nacionalidad+"', direccion='"+ direccion+"', telefono='"+ telefono+"', correo='"+ correo+"', cicloacademico='"+ cicloacademico+"', carrerauniversitaria='"+ carrerauniversitaria+"', escuela='"+ escuela+"',vigencia="+ vigencia+" WHERE dniestudiante='" + dniestudiante + "'";
         try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar alumno" + e.getMessage());
        }
    }
    
    public void darBaja(String cod) throws Exception{
        strSQL="update alumno set vigencia=false where dniestudiante='" + cod+"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja al alumno");
        }
    } 
    
}

