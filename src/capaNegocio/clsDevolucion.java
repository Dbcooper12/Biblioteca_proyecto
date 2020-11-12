package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author manue
 */
public class clsDevolucion {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

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

    public void registrar(Integer codigodevolucion, Integer numeroPrestamo, String nombreLibro, String fechaDevolucion, String dnialumno) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);
            sent = con.createStatement();
            strSQL = "insert into devolucion values(" + codigodevolucion + "," + numeroPrestamo + ",'" + nombreLibro + "','" + dnialumno + "','Devuelto','" + fechaDevolucion + "')";
            sent.executeUpdate(strSQL);
          //elimno prestamo
            strSQL = "DELETE FROM prestamo WHERE numeroprestamo="+numeroPrestamo+";";
            sent.executeUpdate(strSQL);
          //actualizo estado de libro
            strSQL = "UPDATE libro SET estado='Disponible' WHERE  codigolibro="+nombreLibro+";";
            sent.executeUpdate(strSQL);
            con.commit();

        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al guardar Devolucion: " + e.getMessage());
        } finally {
            objConectar.desconectar();
        }

    }
//    

}

