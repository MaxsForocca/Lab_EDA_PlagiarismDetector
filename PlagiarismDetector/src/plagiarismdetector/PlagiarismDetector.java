
package plagiarismdetector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Trie.Trie;
import java.text.Normalizer;

public class PlagiarismDetector {
    static String folderPath = "./textos"; //ruta de la carpeta
    static File folder = new File(folderPath); // indicamos la carpeta de archivos
    static File[] files = folder.listFiles(); // obtenemos los archivos de la carpeta
    static ArrayList<Trie> tries= new ArrayList<>(); //array de tries
    public static void main(String[] args) {
        loadFiles(files);
        System.out.println(tries.get(0).search("constante"));
        Scanner sc = new Scanner(System.in);
        String textoIngresado = sc.nextLine();
        ArrayList<Double> plagioDB = detectarPlagioPalabras(textoIngresado);
        for(Double d : plagioDB){
            System.out.println(d);
        }
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
                            String[] words = procesarTexto(line);
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
    
    public static String[] procesarTexto(String str){
        String normalizedText = Normalizer.normalize(str, Normalizer.Form.NFD);
        // Eliminar caracteres diacríticos
        normalizedText = normalizedText.replaceAll("\\p{M}", "");
        // Reemplazar caracteres especiales con sus equivalentes
        normalizedText = normalizedText.replaceAll("[á]", "a")
                                       .replaceAll("[é]", "e")
                                       .replaceAll("[í]", "i")
                                       .replaceAll("[ó]", "o")
                                       .replaceAll("[ú]", "u")
                                       .replaceAll("ñ", "n");
        // filtamos los caracteres especiales y lo volvemos en minusculas
        // cada palabra se queda guardado en un arreglo
        String[] words = normalizedText.toLowerCase().split("\\W+");
        return words;
    }
    // Devuelve un arreglo del porcentaje de similitud de cada texto de la DB
    public static ArrayList<Double> detectarPlagioPalabras(String texto){
        String[] palabrasText = procesarTexto(texto);
        int palabrasTotal = palabrasText.length;
        ArrayList<Double> porcentPlagio = new ArrayList<>();
        for(Trie t: tries){
            double porcent = 0.0;
            int cantPalabrasPlagio = 0;
            for(String p: palabrasText){
                if(t.search(p)){
                    System.out.println(p);
                    cantPalabrasPlagio++;
                }
            }
            porcent = cantPalabrasPlagio * 100.0 / palabrasTotal;
            porcentPlagio.add(porcent);
        }
        return porcentPlagio;
    }
    
    public ResultChecker verifyPlagiarism(String path){
        ResultChecker result = null;

        return result;
    }
}
