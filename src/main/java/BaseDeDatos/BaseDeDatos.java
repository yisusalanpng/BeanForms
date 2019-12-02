package BaseDeDatos;

import Modelos.Form;
import Modelos.Opcion;
import Modelos.Pregunta;
import Modelos.Respuesta;
import Modelos.Usuario;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
            PreparedStatement sql = con.prepareStatement("select Nickname,Nombre,Apellido, Administrador from Usuario where Nickname=? && AES_DECRYPT(Password,\"frijolero\")=?&&Activo=1");
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

    public boolean registro(Usuario usuario, String pass, Boolean activo) {
        try {
            String query = activo ? "insert into Usuario values(?,?,?, AES_ENCRYPT(?,\"frijolero\"),0,1)" : "insert into Usuario values(?,?,?, AES_ENCRYPT(?,\"frijolero\"),0,0)";

            ResultSet res;

            PreparedStatement sql = con.prepareStatement(query);
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
        int form_id = 0;
        int ultima_pregunta = 0;
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
                while (res.next()) {
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

    public boolean editarEncuesta(Form nuevoForm, int id, String nickname) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int form_id = 0;
        int ultima_pregunta = 0;
        try {
            ResultSet res;

            PreparedStatement sql = con.prepareStatement("DELETE FROM Forms where Forms_ID=?");
            sql.setInt(1, id);

            sql.executeUpdate();

            sql = con.prepareStatement("insert into Forms(Titulo,Expiracion,Privado,Codigo,Creador_FK) values(?,?,?,?,?)");
            sql.setString(1, nuevoForm.getTitulo());
            sql.setString(2, dateFormat.format(nuevoForm.getExpiracion()));
            sql.setBoolean(3, nuevoForm.getPrivado());
            sql.setString(4, nuevoForm.getCodigo());
            sql.setString(5, nickname);
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
                while (res.next()) {
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

    public Form obtenerForm(int id) {
        Form nuevo = new Form();
        List<Pregunta> preguntas = new ArrayList<>();
        List<Opcion> opciones = new ArrayList<>();

        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("SELECT Preguntas_ID,Pregunta from Preguntas where Forms_FK=?");
            sql.setInt(1, id);
            res = sql.executeQuery();
            while (res.next()) {
                Pregunta pregunta = new Pregunta(res.getString("Pregunta"), res.getInt("Preguntas_ID"));
                opciones = new ArrayList<>();

                ResultSet resOpciones;
                PreparedStatement sqlOpciones = con.prepareStatement("SELECT Opcion_ID,Opcion from Opcion where Pregunta_FK=?");
                sqlOpciones.setInt(1, res.getInt("Preguntas_ID"));
                resOpciones = sqlOpciones.executeQuery();

                while (resOpciones.next()) {
                    opciones.add(new Opcion(resOpciones.getInt("Opcion_ID"), resOpciones.getString("Opcion")));
                }
                pregunta.setOpciones(opciones);
                preguntas.add(pregunta);
            }
            nuevo.setPreguntas(preguntas);
            return nuevo;
        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return null;
        }

    }

    public boolean enviarRespuesta(Form nuevoForm, String nickname) {

        try {
            for (int i = 0; i < nuevoForm.getPreguntas().size(); i++) {

                PreparedStatement sql = con.prepareStatement("insert into Respuesta (Opcion_FK,Nickname_FK) Values(?,?)");
                sql.setInt(1, nuevoForm.getPreguntas().get(i).getOpcion_elegida_id());
                sql.setString(2, nickname);
                sql.executeUpdate();

            }
            return true;
        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return false;
        }
    }

    public List<Form> obtenerMisForms(Boolean admin) {
        String query = admin ? "select * from Forms" : "select * from Forms WHERE Creador_FK=?";

        List<Form> listaForms = new ArrayList<Form>();
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement(query);
            if (!admin) {
                sql.setString(1, usuario.getNickname());
            }
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

    public List<Respuesta> obtenerRespuestas(int id) {
        List<Respuesta> respuestas = new ArrayList<>();
        System.out.println("ID" + id);
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("SELECT Opcion.Pregunta_FK, Respuesta.Opcion_FK, Preguntas.Pregunta, Opcion.Opcion, CONCAT(Usuario.Nombre,\" \",Usuario.Apellido) as persona FROM Respuesta INNER JOIN Opcion ON Respuesta.Opcion_FK = Opcion.Opcion_ID INNER JOIN Preguntas ON Opcion.Pregunta_FK = Preguntas.Preguntas_ID INNER JOIN Forms ON Preguntas.Forms_FK = Forms.Forms_ID INNER JOIN Usuario ON Respuesta.Nickname_FK = Usuario.Nickname WHERE Forms.Forms_ID = ?");
            sql.setInt(1, id);
            res = sql.executeQuery();
            while (res.next()) {
                respuestas.add(
                        new Respuesta(res.getString("persona"), res.getInt("Pregunta_FK"), res.getInt("Opcion_FK"),
                                res.getString("Pregunta"), res.getString("Opcion")));
            }
            return respuestas;
        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return null;
        }

    }

    public boolean borrarForm(int id) {
        try {
            PreparedStatement sql = con.prepareStatement("DELETE FROM Forms where Forms_ID=?");
            sql.setInt(1, id);

            sql.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return false;
        }
    }

    public List<Pregunta> obtenerPreguntas(int id) {
        List<Pregunta> preguntas = new ArrayList<>();
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("SELECT * from Preguntas where Forms_FK=?");
            sql.setInt(1, id);
            res = sql.executeQuery();
            while (res.next()) {
                Pregunta temp = new Pregunta(res.getString("Pregunta"));
                List<Opcion> opcionTemp = new ArrayList<>();
                ResultSet resOpciones;
                PreparedStatement sqlOpciones = con.prepareStatement("SELECT * from Opcion where Pregunta_FK=?");
                sqlOpciones.setInt(1, res.getInt("Preguntas_ID"));
                resOpciones = sqlOpciones.executeQuery();
                while (resOpciones.next()) {
                    opcionTemp.add(new Opcion(resOpciones.getString("Opcion")));
                }
                temp.setOpciones(opcionTemp);
                preguntas.add(temp);
            }
            return preguntas;

        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return null;
        }
    }

    public Form canjearCodigo(String codigo) {
        int form_id = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Form temp = new Form();
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("SELECT Forms_ID,Titulo from Forms where Codigo=? &&Expiracion>?");
            sql.setString(1, codigo);
            sql.setString(2, formatter.format(date));
            res = sql.executeQuery();
            while (res.next()) {
                temp.setForms_id(res.getInt("Forms_ID"));
                temp.setTitulo(res.getString("Titulo"));
            }
            return temp;

        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return null;
        }
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("SELECT * from Usuario");
            res = sql.executeQuery();
            while (res.next()) {
                usuarios.add(new Usuario(res.getString("Nickname"), res.getString("Nombre"),
                        res.getString("Apellido"), res.getBoolean("Administrador"), res.getBoolean("Activo")));

            }
            return usuarios;

        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return null;
        }
    }

    public Boolean eliminarUsuario(String nickname) {
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("UPDATE Usuario set activo=0 where nickname=?");
            sql.setString(1, nickname);
            sql.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return false;
        }
    }

    public Boolean activarUsuario(String nickname) {
        try {
            ResultSet res;
            PreparedStatement sql = con.prepareStatement("UPDATE Usuario set Activo=1 where nickname=?");
            sql.setString(1, nickname);
            sql.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println(" -> " + ex);
            return false;
        }
    }
}
