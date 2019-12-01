package BaseDeDatos;

import Modelos.Form;
import Modelos.Usuario;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class BaseDeDatos {

    @EJB
    private Usuario usuario;

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

    public Usuario iniciarSesion(String nickname, String pass) {
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("select Nickname,Nombre,Apellido, Administrador from Usuario where Nickname=? && AES_DECRYPT(Password,\"frijolero\")=?");
            sql.setString(1, nickname);
            sql.setString(2, pass);
            res = sql.executeQuery();
            if (!res.next()) {
                return null;
            } else {
                do {

                    usuario.setNickname(res.getString("Nickname"));
                    usuario.setNombre(res.getString("Nombre"));
                    usuario.setApellido(res.getString("Apellido"));
                    usuario.setAdministrador(res.getBoolean("Administrador"));

                    return usuario;
                } while (res.next());
            }
        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return null;
        }
    }

    public List<Form> obtenerForms() {
        String fecha = java.time.LocalDate.now() + "";

        List<Form> listaForms = new ArrayList<Form>();
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("select * from Forms WHERE Privado=0&&Expiracion>?");
            sql.setString(1, fecha);
            res = sql.executeQuery();
            if (!res.next()) {
                return null;
            } else {
                do {
                    listaForms.add(new Form(res.getInt("Forms_ID"), res.getString("Titulo"),
                            res.getString("Expiracion"), res.getString("Codigo"), res.getString("Creador_FK"), res.getBoolean("Privado")));
                } while (res.next());
                return listaForms;

            }
        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return null;
        }
    }

    public boolean registro(Usuario usuario, String pass) {
        try {
            ResultSet res;

            PreparedStatement sql = con.prepareStatement("insert into Usuario values(?,?,?, AES_ENCRYPT(?,\"frijolero\"),0)");
            sql.setString(1, usuario.getNickname());
            sql.setString(2, usuario.getNombre());
            sql.setString(3, usuario.getApellido());
            sql.setString(4, pass);

            sql.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return false;
        }
    }

    public boolean crearEncuesta(Form nuevoForm) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int form_id=0;
        int ultima_pregunta=0;
        try {
            ResultSet res;

            PreparedStatement sql = con.prepareStatement("insert into Forms(Titulo,Expiracion,Privado,Codigo,Creador_FK) values(?,?,?,?,?)");
            sql.setString(1, nuevoForm.getTitulo());
            sql.setString(2, dateFormat.format(nuevoForm.getExpiracion()));
            sql.setBoolean(3, nuevoForm.getPrivado());
            sql.setString(4, nuevoForm.getCodigo());
            sql.setString(5, usuario.getNickname());
            sql.executeUpdate();

            sql = con.prepareStatement("SELECT LAST_INSERT_ID() as id");
            res = sql.executeQuery();
            while (res.next()) {
                form_id = res.getInt("id");
            }
            for (int i = 0; i < nuevoForm.getPreguntas().size(); i++) {
                sql = con.prepareStatement("INSERT INTO Preguntas (Pregunta,Forms_FK) VALUES(?,?)");
                sql.setString(1, nuevoForm.getPreguntas().get(i).getPregunta());
                sql.setInt(2, form_id);
                sql.executeUpdate();

                sql = con.prepareStatement("SELECT LAST_INSERT_ID() as id");
                res = sql.executeQuery();
                while(res.next()){
                ultima_pregunta = res.getInt("id");
                }
                for (int j = 0; j < nuevoForm.getPreguntas().get(i).getOpciones().size(); j++) {
                    sql = con.prepareStatement("INSERT INTO Opcion (Opcion,Pregunta_FK) VALUES(?,?)");
                    sql.setString(1, nuevoForm.getPreguntas().get(i).getOpciones().get(j).getOpcion());
                    sql.setInt(2, ultima_pregunta);
                    sql.executeUpdate();
                }

            }

            return true;

        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return false;
        }
    }

}
