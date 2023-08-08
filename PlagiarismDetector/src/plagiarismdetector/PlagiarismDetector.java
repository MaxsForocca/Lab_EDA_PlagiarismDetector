
package plagiarismdetector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Trie.Trie;

public class PlagiarismDetector {
    static String folderPath = "../textos"; //ruta de la carpeta

    static File folder = new File(folderPath); // indicamos la carpeta de archivos
    static File[] files = folder.listFiles(); // obtenemos los archivos de la carpeta
    static ArrayList<Trie> tries= new ArrayList<>(); //array de tries
    public static void main(String[] args) {
        
    }
    public static boolean loadFiles(File[] files){
        if (files != null) {
            // iterador para el arraylist de Trie
            int idxTries = 0;
            // recorrer los archivos del arreglo
            for (File file : files) {
                if (file.isFile()) {
                    // leer archivo y crear un nuevo Trie para ese archivo
                    System.out.println("Leyendo archivo: " + file.getName());
                    tries.add(new Trie());
                    try {
                        Scanner scanner = new Scanner(file);
                        // recorrer cada linea del archivo 
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            // filtamos los caracteres especiales y lo volvemos en minusculas
                            // cada palabra se queda guardado en un arreglo
                            String[] words = line.toLowerCase().split("\\W+");
                            // introducimos cada palabra al Trie correspondiente
                            for(String word:words)
                                tries.get(idxTries).insert(word);
                            System.out.println(line);
                        }
                        scanner.close();
                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                    }
                    System.out.println("--------------------");
                }
                // aumentamos el iterador
                idxTries++;
            }
            return true;
        } else {
            System.out.println("La carpeta no existe o no contiene archivos.");
            return false;
        }
    }
    
    public ResultChecker verifyPlagiarism(String path){
        ResultChecker result = null;

        return result;
    }
}
