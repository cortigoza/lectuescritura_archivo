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
     *metodo publico que verificara el tipo seleccion el usuario
     *1 ejecutara el metodo leer archivo
     *2 ejecutara el metodo escribir archivo
     *default si no selecciono nada se llamara el metodo utilitarios 
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
    *@params cadena
    *@params ordenado String
    *@params ubicacion String @return buscararchivo
    **/
    private void leerArchivo() {
        String cadena;
        String ordenado = "";
        String ubicacion = buscarArchivo();

        /*
        *
        *valido que retorne la ubicacion del archivo en caso contrario
        *mostrara por pantalla un enunciado diciendole que "no selecciono archivo"
        */
        if (ubicacion != "") {
            try {
                //instancio FileReader que se encarga de leer el archivo
                FileReader archivo = new FileReader(ubicacion);
                /*
                *instancio BufferedReader valida tamaño del archivo
                *y ademas almacena los caracteres en buffer (espacio de memoria)
                **/
                BufferedReader lector = new BufferedReader(archivo);
                while ((cadena = lector.readLine()) != null) {
                    ordenado += cadena + "\n";
                }
                Utilitarios.mostrar(ordenado, Boolean.TRUE);
                //luego que hizo la lectura y la mostro en pantalla
                //se llama el metodo close el cual libera los recursos
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
    *@params ubicacion String
    *@params formato String[] template para concatenar con la info del archivo
    **/
    private void escribirArchivo() {
        String ubicacion = "";
        String[] formato = {"Nombre: ", "Apellido: ", "Cedula: ", "Saldo: ", "Fecha: ", "Perfil: "};
        BufferedWriter escritor = null;
        FileWriter archivo_escribir = null;
        int respuesta = Utilitarios.leerEntero("Presiona 1 para crear archivo, 2 para editar archivo existente");
        /*
        *valido antes la opcion que selecciono el usuario
        *1 crear el cual creara un archivo en la carpeta home de cualquier S.O
        *2 editara archivo existente el cual le da la opcion de buscar el archivo
        */
        if (respuesta == 1) {
            ubicacion = System.getProperty("user.home") + "/documento.txt";
        } else if(respuesta == 2) {
            ubicacion = buscarArchivo();
        }
        
        /*
        *
        *valido que retorne la ubicacion del archivo en caso contrario
        *mostrara por pantalla un enunciado diciendole que "no selecciono fichero"
        */
        if (ubicacion != "") {
            try {
                File file = new File(ubicacion);
                //despliega formulario el cual se guarda en arreglo usuario
                String[] usuario = Utilitarios.formularioUsuario();
                archivo_escribir = new FileWriter(file.getAbsoluteFile(), true);
                //almaceno en buffer (espacio de memoria)
                escritor = new BufferedWriter(archivo_escribir);
                for (int i = 0; i < usuario.length; i++) {
                    //aqui escribo concatenando con la posicion del template
                    escritor.write(formato[i] + usuario[i] + "\n");
                }
                
                // luego que se escribio se llama el metodo close el cual libera los recursos
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
    *metodo para buscar archivo este retorna ubicacion de archivo
    *filechooser clase de java.swing se debe instanciar
    *el metodo que tiene filechooser showOpenDialog el cual desplegara un menu por defecto
    *@return respuesta String ubicacion del archivo
    *
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
