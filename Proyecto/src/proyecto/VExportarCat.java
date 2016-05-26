/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author AitorBM
 */
public class VExportarCat extends javax.swing.JFrame {

    private Connection conn;
    private List<Pregunta> pre = new ArrayList<>();
    private List<Categoria> cat = new ArrayList<>();
    private List<Respuesta> res = new ArrayList<>();
    private VP padre;

    private void ActualizarPreguntas(Categoria c) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("select * from preguntas where categorias_id_cat = " + c.getId());
        while (rset.next()) {
            Pregunta p = new Pregunta();
            p.setId(Integer.parseInt(rset.getString(1)));
            p.setTexto(rset.getString(2));
            p.setCategoria(c);
            pre.add(p);

            c.getPreguntas().add(p);
            cat.add(c);
            actualizarRespuestas(p);
        }
        stmt.close();
    }

    private void actualizarRespuestas(Pregunta p) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("select * from respuestas where preguntas_id_pre = " + p.getId());
        while (rset.next()) {
            Respuesta r = new Respuesta();
            r.setId(rset.getInt(1));
            r.setTexto(rset.getString(2));
            r.setCorrecta(rset.getInt(3));
            r.setPregunta(p);
            res.add(r);

            p.getRespuestas().add(r);
        }
        stmt.close();
    }

    /**
     * Creates new form VExportar
     * @throws java.sql.SQLException
     */
    public VExportarCat(VP vp) throws SQLException {
        padre = vp;
        
        initComponents();

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        //Conexion maquina vagrant
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102", "system", "oracle");
        /*
        Conexion server egibide -> conn = DriverManager.getConnection("jdbc:oracle:thin:@SrvOracle:1521:orcl", "noc08", "noc08");
        Conexion en mi casa -> conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.2:1521:orcl", "SYSTEM", "root");
        */
        System.out.println("INFO: Conexión abierta");

        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("select * from categorias");
        while (rset.next()) {
            Categoria c = new Categoria();
            c.setId(rset.getInt(1));
            c.setNombre(rset.getString(2));

            ActualizarPreguntas(c);
        }
        stmt.close();

        jcbCategoria.removeAllItems();
        for (Categoria cate : cat) {
            jcbCategoria.addItem(cate.getNombre());
        }

        jtaPreguntas.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbCategoria = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jbAceptar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaPreguntas = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jcbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCategoriaActionPerformed(evt);
            }
        });

        jLabel1.setText("Elige la categoria");

        jbAceptar.setText("Exportar");
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAceptarActionPerformed(evt);
            }
        });

        jtaPreguntas.setColumns(20);
        jtaPreguntas.setRows(5);
        jScrollPane1.setViewportView(jtaPreguntas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbAceptar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbAceptar)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jcbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCategoriaActionPerformed

        String texto = "";

        int i = jcbCategoria.getSelectedIndex();
        if (i >= 0) {
            Categoria c = cat.get(i);
            for (Pregunta p : c.getPreguntas()) {
                String res = "";
                for (int j = 0; j < p.getRespuestas().size(); j++) {
                    if (p.getRespuestas().get(j).getCorrecta() == 0) {
                        res += "~" + p.getRespuestas().get(j).getTexto() + "\n";
                    } else {
                        res += "=" + p.getRespuestas().get(j).getTexto() + "\n";
                    }

                }
                texto += p.getTexto() + "\n{\n" + res + "}\n\n";

            }
            jtaPreguntas.setText(texto);
        }
    }//GEN-LAST:event_jcbCategoriaActionPerformed

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
        // TODO add your handling code here:
        JFileChooser explorador = new JFileChooser();

        byte buffer[] = jtaPreguntas.getText().getBytes();
        FileOutputStream f0 = null;
        explorador.setVisible(true);
        try {
            f0 = new FileOutputStream("../PreguntasCategoria" + padre.getExportadosCat() + ".gift");
            f0.write(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VExportarCat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VExportarCat.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (f0 != null) {
                f0.close();
                padre.setExportadosCat(padre.getExportadosCat()+1);
            }
            JOptionPane.showMessageDialog(this, "Categoria exportada correctamente.\nen la carpeta que contiene el proyecto");
        } catch (IOException e) {
            System.out.println("ERROR: No se puede cerrar f1.txt");
            JOptionPane.showMessageDialog(this, "No se a podido exportar correctamente la categoria.");
        }
        this.dispose();
    }//GEN-LAST:event_jbAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JComboBox jcbCategoria;
    private javax.swing.JTextArea jtaPreguntas;
    // End of variables declaration//GEN-END:variables
}
