package com.mycompany.proyectoclienteservidor.vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.proyectoclienteservidor.Conexion.ConecxionCliente;
import com.mycompany.proyectoclienteservidor.Modelo.Articulo;
import com.mycompany.proyectoclienteservidor.Modelo.Cliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroArticulosVista extends javax.swing.JFrame {

    private DefaultTableModel modelo;

    public RegistroArticulosVista() throws IOException {
        initComponents();
        modelo = new DefaultTableModel(); // se nombra la tabla
        tablaArticulos.setModel(modelo);// aca se setea el nombre de la tabal que se encuentra en la vista
        modelo.addColumn("id");//aca se escriben la cabezas de las columnas
        modelo.addColumn("Tipo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        //cargarDatos();
        populateTipoComboBox(); // se carga el combobox para mostrar los tipo
        tablaArticulos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//esta opcion es para cunado seleccionamos algo de la tabal de articulos
                int fila = tablaArticulos.getSelectedRow();// aca se almacena en fila la seleccionada de manera numerica
                if (fila >= 0) {// si es mayor a 0
                    jComboBoxTipos.setSelectedItem(modelo.getValueAt(fila, 1).toString()); // aca se setea el tipo mediante el combo box
                    txtArticulo.setText(modelo.getValueAt(fila, 2).toString());//
                    txtCantidad.setText(modelo.getValueAt(fila, 3).toString());
                    txtPrecio.setText(modelo.getValueAt(fila, 4).toString());
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPrecio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtArticulo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArticulos = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        labelTipo = new javax.swing.JLabel();
        jComboBoxTipos = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Nombre");

        txtArticulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArticuloActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("cantidad");

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("precio");

        tablaArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaArticulos);

        btnActualizar.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        labelTipo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelTipo.setText("Tipo");

        jComboBoxTipos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jComboBoxTipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        jButton2.setText("Atras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(btnActualizar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxTipos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrecio)
                            .addComponent(txtCantidad)
                            .addComponent(labelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(labelTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar)
                        .addGap(15, 15, 15)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
    }//GEN-LAST:event_txtPrecioActionPerformed
    private void jComboBoxTiposActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private Articulo RegitrarBoton() {
        Articulo newArticulo = new Articulo();
        newArticulo.setNombre(txtArticulo.getText());
        newArticulo.setCantidad(Integer.parseInt(txtCantidad.getText()));
        newArticulo.setPrecio(Double.parseDouble(txtPrecio.getText()));
        newArticulo.setTipo(jComboBoxTipos.getSelectedItem().toString());
        newArticulo.setIdDeMetodo(1);
        return newArticulo;
    }

    public boolean verificacionDeCamposVacios() {
        if (txtArticulo.getText().isEmpty() || txtCantidad.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
        }
        return true;
    }

    private void populateTipoComboBox() { // aca se setea lo que va a dentro del ComboBox
        jComboBoxTipos.addItem("consolas");
        jComboBoxTipos.addItem("controles");
        jComboBoxTipos.addItem("juegos");
        jComboBoxTipos.addItem("accesorios");
    }
    private void txtArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArticuloActionPerformed
    }//GEN-LAST:event_txtArticuloActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificacionDeCamposVacios()) {
            Articulo articulo = RegitrarBoton();
            articulo.setIdDeMetodo(3);
            Gson gson = new Gson();
            String json = gson.toJson(articulo);
            ConecxionCliente conexion = new ConecxionCliente();
            try {
                conexion.enviarInformacion(json);
            } catch (IOException ex) {
                Logger.getLogger(RegistroArticulosVista.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            limpiarTabla();
            try {
                cargarDatos();
            } catch (IOException ex) {
                Logger.getLogger(RegistroArticulosVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
    }//GEN-LAST:event_txtCantidadActionPerformed
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        int fila = tablaArticulos.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            if (verificacionDeCamposVacios()) {
                Articulo articulo = RegitrarBoton();
                articulo.setIdDeMetodo(2);
                articulo.setId(id);  // Seteamos el ID del artículo a actualizar

                ConecxionCliente conexion = new ConecxionCliente();
                try {
                    cargarDatos();

                } catch (IOException ex) {
                    Logger.getLogger(InicioSesion.class.getName()).log(Level.SEVERE, null, ex);
                }
                limpiarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un artículo para actualizar.");
        }

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Adminitrar a = new Adminitrar();
        a.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            cargarDatos();
        } catch (IOException ex) {
            Logger.getLogger(RegistroArticulosVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

   
    public String enviarRequest() {
        Articulo newArticulo = new Articulo();
        newArticulo.setIdDeMetodo(5);
        Gson gson = new Gson();
        String json = gson.toJson(newArticulo);
        return json;
    }

    private void cargarDatos() throws IOException {
        try {
            limpiarTabla();
            ConecxionCliente conexion = new ConecxionCliente();
            String a = conexion.iniciarConexion(enviarRequest());
            Gson gson = new Gson();
            Articulo[] articulos = gson.fromJson(a, Articulo[].class);
            for (Articulo articulo : articulos) {
                modelo.addRow(new Object[]{
                    articulo.getId(),
                    articulo.getTipo(),
                    articulo.getNombre(),
                    articulo.getCantidad(),
                    articulo.getPrecio()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
        }
    }

    private void limpiarTabla() {// aca se limpia la tabla 
        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(RegistroArticulosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroArticulosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroArticulosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroArticulosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RegistroArticulosVista().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(RegistroArticulosVista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxTipos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JTable tablaArticulos;
    private javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
