package lectuescritura;

import java.io.*;
import javax.swing.JFileChooser;

/**
 *
 * @author {Sergio Correa, Nicolas Martinez, Cristian Aragon, Carlos Ortigoza}
 */
public class LectuEscritura {

    /*
     * 
     *metodo publico que verificara
     *
     */
    public void opciones() {
        int opciones = Utilitarios.leerEntero("Presiona 1 para leer o 2 para escribir archivo");

        switch (opciones) {
            case 1:
                leerArchivo();
                break;
            case 2:
                escribirArchivo();
                break;
            default:
                Utilitarios.mostrar("No se ha seleccionado ningúna opción", Boolean.FALSE);
                break;
        }

    }

    /*
    *
    *metodo leer contenido de archivo
    **/
    private void leerArchivo() {
        String cadena;
        String ordenado = "";
        String ubicacion = buscarArchivo();

        //valido que selecciono archivo
        if (ubicacion != "") {
            try {
                FileReader archivo = new FileReader(ubicacion);
                BufferedReader lector = new BufferedReader(archivo);
                while ((cadena = lector.readLine()) != null) {
                    ordenado += cadena + "\n";
                }
                //ordenado += "___________________________\n";
                Utilitarios.mostrar(ordenado, Boolean.TRUE);
                lector.close();
            } catch (IOException e) {
                Utilitarios.mostrar("Ha sucedido un error al abrir archivo", Boolean.FALSE);
            }
        } else {
            Utilitarios.mostrar("No se ha seleccionado ningún fichero", Boolean.FALSE);
        }

    }

    /*
    *
    *metodo para escribir archivo texto.
    **/
    private void escribirArchivo() {
        String ubicacion = "";
        String[] formato = {"Cedula: ", "Nombre: ", "Apellido: ", "Saldo: ", "Fecha: ", "Perfil: "};
        BufferedWriter escritor = null;
        FileWriter archivo_escribir = null;
        int respuesta = Utilitarios.leerEntero("Presiona 1 para crear archivo, 2 para editar archivo existente");

        if (respuesta == 1) {
            ubicacion = System.getProperty("user.home") + "/documento.txt";
        } else {
            ubicacion = buscarArchivo();
        }

        if (ubicacion != "") {
            try {
                File file = new File(ubicacion);
                String[] usuario = Utilitarios.formularioUsuario();
                archivo_escribir = new FileWriter(file.getAbsoluteFile(), true);
                escritor = new BufferedWriter(archivo_escribir);
                for (int i = 0; i < usuario.length; i++) {
                    escritor.write(formato[i] + usuario[i] + "\n");
                }
                escritor.close();
                Utilitarios.mostrar("Se ha guardado en archivo", Boolean.TRUE);
            } catch (IOException e) {
                Utilitarios.mostrar("Ha sucedido un error al escribir archivo", Boolean.FALSE);
            }

        } else {
            Utilitarios.mostrar("No se ha seleccionado ningún fichero", Boolean.FALSE);
        }
    }

    /*
    *
    *metodo para buscar archivo que se usara retorna ubicacion de archivo
     */
    private String buscarArchivo() {
        String respuesta = "";
        JFileChooser fileChooser = new JFileChooser();
        int valor = fileChooser.showOpenDialog(fileChooser);

        if (valor == JFileChooser.APPROVE_OPTION) {
            respuesta = fileChooser.getSelectedFile().getAbsolutePath();
        }
        return respuesta;
    }
}
