
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author manue
 */
public class clsPrestamo {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarPrestamo() throws Exception {
        strSQL = "SELECT * FROM prestamo;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar Prestamo");
        }
    }
    
     public ResultSet buscarPrestamo(Integer codigoprestamo) throws Exception {
        strSQL = "select * from  prestamo where codigolibro ="+codigoprestamo;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar prestamo");
        }
    }
    
    
}
