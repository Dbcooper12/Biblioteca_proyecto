package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author manue
 */
public class clsDevolucion {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public Integer generarCodigoDevolucion() throws Exception {
        strSQL = "SELECT COALESCE(max(codigodevolucion),0)+1 as codigodevolucion from devolucion";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigodevolucion");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de Devolucion");
        }
        return 0;
    }

    public ResultSet listarDevolucion() throws Exception {
        strSQL = "SELECT * FROM devolucion";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar devolucion");
        }
    }

    public void registrar(Integer codigodevolucion, Integer numeroPrestamo, String nombreLibro, String fechaDevolucion, String dnialumno, String estado) throws Exception {
        strSQL = "insert into devolucion values("+codigodevolucion+"," +  numeroPrestamo +",'" + nombreLibro +"','" + dnialumno + "','"+estado+"','"+fechaDevolucion+"')";
      
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar devolucion" + e.getMessage());
        }

    }

}
