
import Trie.Trie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static plagiarismdetector.PlagiarismDetector.procesarTexto;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

import java.text.Normalizer;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author JAVIER
 */

public class Vista extends javax.swing.JFrame {
    static String folderPath = "./textos"; //ruta de la carpeta
    static File folder = new File(folderPath); // indicamos la carpeta de archivos
    static File[] files = folder.listFiles(); // obtenemos los archivos de la carpeta
    static ArrayList<Trie> tries= new ArrayList<>(); //array de tries
    
    
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
        // Reemplazar caracteres especiales
        normalizedText = normalizedText.replaceAll("ñ", "n");
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
    public void Resultado (String texto)
    {
        lbPT.setText("-----");
        lbpt2.setText("-----");
        lbpt3.setText("-----");
        ArrayList<Double> plagioDB = detectarPlagioPalabras(texto);
        if (plagioDB.get(1)==100){
            lbPT.setText("SI");
            lbpt3.setText("100");
        }
        else if (plagioDB.get(1)>0)
        {
            DecimalFormat df = new DecimalFormat("#.00");
            Double numEntero =(double)Math.round(plagioDB.get(1) * 100d) / 100d ;    
            String numCadena=numEntero+"";
            lbpt2.setText("SI");
            lbpt3.setText(numCadena);
        }
        
            
    }
    
    

    /**
     * Creates new form Vista
     */
    public Vista() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaArchivo = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaArchivo2 = new javax.swing.JTextArea();
        btnSel2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lbPT = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbpt2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbpt3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto Final");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ingrese el archivo original");

        btnSel.setText("Subir");
        btnSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelActionPerformed(evt);
            }
        });

        txaArchivo.setColumns(20);
        txaArchivo.setRows(5);
        jScrollPane1.setViewportView(txaArchivo);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ingrese la oracion para comparar");

        txaArchivo2.setColumns(20);
        txaArchivo2.setRows(5);
        jScrollPane2.setViewportView(txaArchivo2);

        btnSel2.setText("Comparar");
        btnSel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSel2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Plagio Total");

        lbPT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbPT.setText("-----");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Plagio Parcial");

        lbpt2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbpt2.setText("-----");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Porcentaje de plagio");

        lbpt3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbpt3.setText("-----");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnSel)
                .addContainerGap(250, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(btnSel2)
                        .addGap(39, 39, 39)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lbpt3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(lbPT))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(lbpt2)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbPT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(lbpt2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(lbpt3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void btnSelActionPerformed(java.awt.event.ActionEvent evt) {                                       
        JFileChooser fc=new JFileChooser();
        fc.showOpenDialog(null);
        File archivo = fc.getSelectedFile();
        loadFiles(files);
        try{
            FileReader fr = new FileReader(archivo);
            BufferedReader br=new BufferedReader(fr);
            
            String texto="";
            String linea="";
            while(((linea=br.readLine())!=null)){
                texto+=linea+"\n";
            }
            txaArchivo.setText(texto);
            JOptionPane.showMessageDialog(null,"Archivo leido correctamente"); 
            
        }
        catch(Exception e){
        }
        
    }                                      

    private void btnSel2ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        String texto=txaArchivo2.getText();
        Resultado(texto);
    }                                       

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSel;
    private javax.swing.JButton btnSel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbPT;
    private javax.swing.JLabel lbpt2;
    private javax.swing.JLabel lbpt3;
    private javax.swing.JTextArea txaArchivo;
    private javax.swing.JTextArea txaArchivo2;
    // End of variables declaration                   
}
