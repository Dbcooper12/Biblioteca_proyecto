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
    
}
