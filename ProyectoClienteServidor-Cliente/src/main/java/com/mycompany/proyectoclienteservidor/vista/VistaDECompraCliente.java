/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectoclienteservidor.vista;

import com.google.gson.Gson;
import com.mycompany.proyectoclienteservidor.Conexion.ConecxionCliente;
import com.mycompany.proyectoclienteservidor.Modelo.Articulo;
import com.mycompany.proyectoclienteservidor.Modelo.Cliente;
import com.mycompany.proyectoclienteservidor.Modelo.ItemCarrito;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaDECompraCliente extends javax.swing.JFrame {

    private DefaultTableModel modelo;
    private DefaultTableModel modeloCarrito;
    private ArrayList<Articulo> carritoDeCompras = new ArrayList<>();
    private ConecxionCliente conexion = new ConecxionCliente();
    private Articulo articuloSeleccionado;

    public VistaDECompraCliente() {
        initComponents();
        modelo = (DefaultTableModel) tablaArticulos.getModel();
        modeloCarrito = (DefaultTableModel) tablaCarrito.getModel();
        inicializarModelos();
        cargarDatos();
        cargarDatosCarrito();
        tablaArticulos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarArticuloSeleccionado();
            }
        });
    }

    private void inicializarModelos() {
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        tablaArticulos.setModel(modelo);
        modeloCarrito = new DefaultTableModel();
        modeloCarrito.addColumn("Nombre");
        modeloCarrito.addColumn("Cantidad");
        modeloCarrito.addColumn("Precio unitario");
        modeloCarrito.addColumn("Precio total");
        tablaCarrito.setModel(modeloCarrito);
    }

    private void cargarDatosCarrito() {
        modeloCarrito.setRowCount(0); // Limpia la tabla
        for (Articulo articulo : carritoDeCompras) {
            
            String nombre = articulo.getNombre();
            int cantidad = articulo.getCantidad();
            double precioUnitario = articulo.getPrecio();
            double precioTotal = precioUnitario * cantidad;
            modeloCarrito.addRow(new Object[]{nombre, cantidad, precioUnitario, precioTotal});
        }
        calcularTotalCuenta(); // Actualiza el total después de cargar los datos
    }

    private boolean validarCantidades(Articulo articulo, int cantidadPorComprar) {
        if (cantidadPorComprar <= articulo.getCantidad()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No hay cantidad suficiente del artículo " + articulo.getNombre());
            return false;
        }
    }

    public void agregarAlCarrito(Articulo articulo, int cantidad) {
        if (validarCantidades(articulo, cantidad)) {
            int cantidaarticulo = articulo.getCantidad();
            articulo.setCantidad(cantidad);
            carritoDeCompras.add(articulo);
            int nuevaCantidad = cantidaarticulo - cantidad;
            cargarDatosCarrito(); // Actualiza la vista del carrito
            calcularTotalCuenta(); // Calcula y actualiza el total
            actualizarCantidadArticulo(articulo.getId(), nuevaCantidad);
            JOptionPane.showMessageDialog(this, "Artículo agregado al carrito.");
        }
    }

    private String pedirDatos() {
        Articulo newArticulo = new Articulo();
        newArticulo.setIdDeMetodo(4);
        Gson gson = new Gson();
        return gson.toJson(newArticulo);
    }

    private void cargarDatos() {
        Gson gson = new Gson();
        ConecxionCliente conexion = new ConecxionCliente();
        String a = conexion.iniciarConexion(pedirDatos());
        Articulo[] articulos = gson.fromJson(a, Articulo[].class);
        modelo.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos

        try {
            for (Articulo articulo : articulos) {
                modelo.addRow(new Object[]{
                    articulo.getId(),
                    articulo.getNombre(),
                    articulo.getCantidad(),
                    articulo.getPrecio()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage());
        }
    }

    private void actualizarCantidadArticulo(int id, int nuevaCantidad) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int idArticulo = (Integer) modelo.getValueAt(i, 0);
            if (idArticulo == id) {
                modelo.setValueAt(nuevaCantidad, i, 2); // Suponiendo que la cantidad está en la columna 2
                break;
            }
        }
    }

    public void calcularTotalCuenta() {
        double total = 0;
        for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
            double precioTotal = (Double) modeloCarrito.getValueAt(i, 3);
            total += precioTotal;
        }
        JlabelTotalCompra.setText("Total de la compra: $" + String.format("%.2f", total));
    }

   

    private void mostrarArticuloSeleccionado() {
        int filaSeleccionada = tablaArticulos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            try {
                int id = (Integer) modelo.getValueAt(filaSeleccionada, 0);
                String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
                int cantidad = (Integer) modelo.getValueAt(filaSeleccionada, 2);
                double precio = (Double) modelo.getValueAt(filaSeleccionada, 3);

                articuloSeleccionado = new Articulo();
                articuloSeleccionado.setId(id);
                articuloSeleccionado.setNombre(nombre);
                articuloSeleccionado.setCantidad(cantidad);
                articuloSeleccionado.setPrecio(precio);

                JlabelMostrarProductoSeleccionado.setText("Nombre: " + nombre + ", Precio: " + precio + ", Cantidad disponible: " + cantidad);
            } catch (ClassCastException e) {
                JOptionPane.showMessageDialog(this, "Error al seleccionar el artículo: " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArticulos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        CantidadDeArticulos = new javax.swing.JSpinner();
        JlabelMostrarProductoSeleccionado = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        btnRealizarCompra = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        JlabelTotalCompra = new javax.swing.JLabel();
        btnEliminarArticulo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton1.setText("Agreagar al carrito");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        CantidadDeArticulos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        JlabelMostrarProductoSeleccionado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        JlabelMostrarProductoSeleccionado.setText("<html>Nombre del producto seleccionado<html>");

        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaCarrito);

        btnRealizarCompra.setText("Realizar la compra");
        btnRealizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarCompraActionPerformed(evt);
            }
        });

        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Tu carrito");

        JlabelTotalCompra.setText("El total de su compra es");

        btnEliminarArticulo.setText("Eliminar Articulo");
        btnEliminarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarArticuloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(JlabelMostrarProductoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEliminarArticulo)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnAtras)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnRealizarCompra)
                                            .addGap(61, 61, 61)
                                            .addComponent(JlabelTotalCompra))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 47, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(CantidadDeArticulos)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(202, 202, 202)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(JlabelMostrarProductoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(CantidadDeArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminarArticulo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnRealizarCompra)
                    .addComponent(JlabelTotalCompra))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String enviarCarritoDeCompraJson() {
    Gson gson = new Gson();
    int metodoId = 9;
    String carritoJson = gson.toJson(carritoDeCompras); // Serializa solo el carrito de compras
    JOptionPane.showMessageDialog(null, metodoId + "|" + carritoJson);
    return metodoId + "|" + carritoJson; // Combina el ID del método y el JSON del carrito
}

    private void btnRealizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCompraActionPerformed
        if (carritoDeCompras.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
            return;
        }

        try {
            Gson gson = new Gson();
            ConecxionCliente conexionServidor = new ConecxionCliente();
            String carritoJson = conexionServidor.iniciarConexion(enviarCarritoDeCompraJson());
            if (carritoJson != null && carritoJson.equals("Success")) {
                JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
                carritoDeCompras.clear(); // Limpiar carrito después de la compra
                cargarDatosCarrito();
            } else {
                JOptionPane.showMessageDialog(this, "Error al realizar la compra.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al realizar la compra: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRealizarCompraActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        VistaClienteregistrado a = new VistaClienteregistrado();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (articuloSeleccionado != null) {
            int cantidad = (Integer) CantidadDeArticulos.getValue();
            if (cantidad > 0) {
                agregarAlCarrito(articuloSeleccionado, cantidad);
            } else {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un artículo primero.");
        }    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEliminarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarArticuloActionPerformed
        int filaSeleccionada = tablaCarrito.getSelectedRow();

        // Verificar si se ha seleccionado alguna fila
        if (filaSeleccionada >= 0) {
            // Obtener el nombre del artículo seleccionado en la fila
            String nombreArticulo = (String) modeloCarrito.getValueAt(filaSeleccionada, 0);

            // Encontrar y eliminar el artículo del carritoDeCompras
            for (int i = 0; i < carritoDeCompras.size(); i++) {
                Articulo articulo = carritoDeCompras.get(i);
                if (articulo.getNombre().equals(nombreArticulo)) {
                    carritoDeCompras.remove(i);
                    break;
                }
            }

            // Actualizar la tabla del carrito
            cargarDatosCarrito();

            // Actualizar el total de la compra
            calcularTotalCuenta();

            JOptionPane.showMessageDialog(this, "Artículo eliminado del carrito.");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un artículo para eliminar.");
        }    }//GEN-LAST:event_btnEliminarArticuloActionPerformed

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
            java.util.logging.Logger.getLogger(VistaDECompraCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaDECompraCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaDECompraCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaDECompraCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaDECompraCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner CantidadDeArticulos;
    private javax.swing.JLabel JlabelMostrarProductoSeleccionado;
    private javax.swing.JLabel JlabelTotalCompra;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnEliminarArticulo;
    private javax.swing.JButton btnRealizarCompra;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaArticulos;
    private javax.swing.JTable tablaCarrito;
    // End of variables declaration//GEN-END:variables
}
