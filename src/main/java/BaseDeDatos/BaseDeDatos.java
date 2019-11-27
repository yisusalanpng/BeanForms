package BaseDeDatos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class BaseDeDatos {
  protected Connection con;

    public BaseDeDatos() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/beanformsbd", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("-> Excepcion de tipo " + ex);
            System.out.println("-> ¿Sí tienes mysql corriendo, maldito incompetente?");
        }
    }

    /*
     public void registro(long Celular, String Nombre, String Apellido, String Passwd, String Respuesta) {
        respuesta = new Respuesta();
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("SELECT Celular FROM Usuario where Celular = ?");
            sql.setLong(1, Celular);
            res = sql.executeQuery();

            if (res.next()) {       //Si existe la fila
                respuesta.setSuccess(false);
            } else {
                do {
                    sql = con.prepareStatement("insert into Usuario values(?,?,?,? , AES_ENCRYPT(?,\"chetis\"))");

                    sql.setLong(1, Celular);
                    sql.setString(2, Nombre);
                    sql.setString(3, Apellido);
                    sql.setString(5, Passwd);
                    sql.setString(4, Respuesta);
                    sql.executeUpdate();
                    Vector<String> datos = new Vector<>();
                    datos.add(Celular + "");
                    datos.add(Nombre);
                    datos.add(Apellido);
                    datos.add(Passwd);
                    respuesta.setDatos(datos);
                    respuesta.setSuccess(true);
                    return respuesta;
                } while (!res.next());
            }

        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
        }
    }
*/

}
