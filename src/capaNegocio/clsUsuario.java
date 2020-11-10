/* 03 Set 2019 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.*;
import javax.swing.JOptionPane;

public class clsUsuario 
{
    //Crear instancia de la clase clsJDBC
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs=null;
    
    public String login(String usu, String con) throws Exception{
        strSQL = "SELECT nombrecompleto, contraseña from bibliotecario where nombrecompleto='" + usu + "' and contraseña='" + con+"'" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getString("nombrecompleto");
            }
        } catch (Exception e) {
            throw new Exception("Error al iniciar sesión..");
        }
        return "";
    }
    
    public Boolean validarVigencia(String usu) throws Exception{
    strSQL = "select vigencia from bibliotecario where nombrecompleto='" + usu + "'" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getBoolean("vigencia");
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
     }

    public void cambiarContraseña(String con, String nuevaCon, String nombre) throws Exception{
        strSQL="update bibliotecario set contraseña='" + nuevaCon + "' where nombrecompleto='" + nombre + "' and contraseña='" + con + "'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al cambiar la contraseña..");
        }
    }
        public ResultSet listarBibliotecario() throws Exception {
        strSQL = "SELECT codigobibliotecario, nombrecompleto, email, contraseña, vigencia FROM bibliotecario;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar bibliotecario");
        }
    }
    public void registrar(String codigobibliotecario, String nombrecompleto, String email, String contraseña, Boolean vigencia) throws Exception {
        strSQL = "insert into bibliotecario values('" + codigobibliotecario + "','" + nombrecompleto + "','" + email + "','" + contraseña  + "'," + vigencia + ")";
        
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar bibliotecario");
        }
    }
    public ResultSet buscarBibliotecario(String cod) throws Exception {
        strSQL = "select * from  bibliotecario where codigobibliotecario ='" + cod + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar bibliotecario");
        }
    }
     public void eliminarBibliotecario(String cod) throws Exception {
        strSQL = "delete from bibliotecario where codigobibliotecario='" + cod+"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar bibliotecario");
        }
    }
    public void modificar(String codigobibliotecario, String nombrecompleto, String email, String contraseña,Boolean vigencia) throws Exception {
        strSQL = "UPDATE bibliotecario SET nombrecompleto='"+ nombrecompleto+"', email='"+ email+"', contraseña='"+ contraseña+"',vigencia="+ vigencia+" WHERE codigobibliotecario='" + codigobibliotecario + "'";
         try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar bibliotecario" + e.getMessage());
        }
    }
    public void darBaja(String cod) throws Exception{
        strSQL="update bibliotecario set vigencia=false where codigobibliotecario='" + cod+"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja al bibliotecario");
        }
    }
}
    
//    
//    public int totalUsuarios() throws Exception
//    {
//        int rpta = 0;
//        
//        objConectar.conectar();
//        Connection con = objConectar.getCon();
//        CallableStatement sentencia = con.prepareCall("select f_usuarios_total()");
//        ResultSet resultado = sentencia.executeQuery();
//        if (resultado.next())
//        {
//            rpta = resultado.getInt("f_usuarios_total");
//        }
//        return rpta;
//    }
    
    
    
    
    
    
    
    

