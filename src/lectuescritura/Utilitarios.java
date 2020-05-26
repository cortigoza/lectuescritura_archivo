package lectuescritura;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author {Sergio Correa, Nicolas Martinez, Cristian Aragon, Carlos Ortigoza}
 */
public class Utilitarios {

    /*
     * metodo para mostrar un mensaje editado:agregado opciones personalizadas
     * estado_mensaje definido por defecto error
     * 
     * @params mensaje
     * 
     * @params estado
     */
    public static void mostrar(String mensaje, Boolean estado) {
        int estado_mensaje = JOptionPane.ERROR_MESSAGE;
        if (estado) {
            estado_mensaje = JOptionPane.PLAIN_MESSAGE;
        }
        JOptionPane.showMessageDialog(null, mensaje, "Mostrar respuesta", estado_mensaje);
    }

    /*
     *
     * Menu con dos selecciones por defecto se la primera posicion
     */
    public static int menuMostrar(String mensaje) {
        String[] opciones = {"Si", "No"};
        int n = JOptionPane.showOptionDialog(null, mensaje, "Archivo planos", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return n;
    }

    // metodo para leer un dato de tipo string y convertirlo a entero
    public static int leerEntero(String mensaje) {
        String N = JOptionPane.showInputDialog(null, mensaje, "Banco Progreso", 1);
        int n = Integer.parseInt(N);
        return n;
    }
    
     /*
     *
     *
     * metodo pedira la informacion relacionado 
     */
    public static String[] formularioUsuario() {

        JTextField identificacion = new JTextField(15);
        JTextField nombre = new JTextField(15);
        JTextField apellido = new JTextField(15);
        JTextField saldo = new JTextField(15);
        JTextField fecha = new JTextField(15);
        JTextField nivel = new JTextField(15);

        JPanel Formulario = new JPanel();

        Formulario.setLayout(new BoxLayout(Formulario, BoxLayout.Y_AXIS));// modo vertical
        Formulario.add(new JLabel("Identificacion:"));
        Formulario.add(identificacion);
        Formulario.add(new JLabel("Nombre:"));
        Formulario.add(nombre);
        Formulario.add(new JLabel("Apellido:"));
        Formulario.add(apellido);
        Formulario.add(new JLabel("Saldo:"));
        Formulario.add(saldo);
        Formulario.add(new JLabel("Fecha:"));
        Formulario.add(fecha);
        Formulario.add(new JLabel("Perfil:"));
        Formulario.add(nivel);
        Formulario.setSize(1024, 768);// resolucion seteada

        JOptionPane.showConfirmDialog(null, Formulario, "Ingrese informacion cliente", JOptionPane.DEFAULT_OPTION);

        String[] usuario = { nombre.getText(), apellido.getText(), identificacion.getText(), saldo.getText(),
                fecha.getText(), nivel.getText() };
        return usuario;
    }
}
