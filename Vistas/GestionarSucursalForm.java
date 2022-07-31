
package Vistas;

import Controlador.*;
import Modelo.*; 
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class GestionarSucursalForm extends javax.swing.JDialog {
    ComboBoxModel enumTipoCalle, enumDepartamento, enumZona;
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;

    public GestionarSucursalForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        enumTipoCalle = new DefaultComboBoxModel(EnumTipoCalle.values());
        enumDepartamento = new DefaultComboBoxModel(EnumDepartamento.values());
        enumZona = new DefaultComboBoxModel(EnumZona.values());
    } 
    public void recibirDatosSucursal(int idSucursal){
        String queryDireccion = "SELECT nombreSucursal, nombreDepartamento, zona, tipoCalle,, numero1, numero2, numero3, FROM 'sucursal' INNER JOIN 'direccion' WHERE FK _idDireccion= AND idSucursal="+ idSucursal+";" ;
        System.out.println(queryDireccion);
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryDireccion);
            while (rs.next()){
                txtSucursal .setText(rs.getString("nombreSucursal"));
                txtNumero1.setText("numero1");
                txtNumero2.setText("numero2");
                txtNumero3.setText("numero3");
                enumDepartamento.setSelectedItem(rs.getString("nombreDepartamento"));
                enumZona.setSelectedItem(rs.getString("zona"));
                enumTipoCalle.setSelectedItem(rs.getString("tipoCalle"));
                
            }
                    
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
      public void recibeInformacionDireccion(String nombreDepartamento, String nombreSucursal, String zona, String tipoCalle, String numero1, String numero2, String numero3) {
        cbDepartamentos.setSelectedItem(nombreDepartamento);
        txtSucursal.setText(nombreSucursal);
        cbZona.setSelectedItem(zona);
        cbCalle.setSelectedItem(tipoCalle);
        txtNumero1.setText(numero1);
        txtNumero2.setText(numero2);
        txtNumero3.setText(numero3);
        
    }

    
    public void actualizarSucursal(){
        String nombreSucursal = txtSucursal.getText();
        String queryIdSucursal = "SELECT idSucursal, idDireccion FROM `sucursal` INNER JOIN `direccion` WHERE " + "FK_idDireccion = idDireccion AND nombreSucursal = '" + nombreSucursal + "';";
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryIdSucursal);
            while (rs.next()){
                int idSucursal = rs.getInt("idSucursal");
                int idDireccion = rs.getInt("idDireccion");
                String sucursal = txtNuevaSucursal.getText();
                if (sucursal.isEmpty()){
                    sucursal =txtSucursal.getText();
                   
                }else {
                    sucursal = sucursal;
                    String queryActualizarSucursal = "UPDATE sucursal SET nombreSucursal='"+ sucursal+ "'WHERE idSucursal="+ idSucursal+ ";";
                    try {
                        st.executeUpdate(queryActualizarSucursal);
                        
                    } catch (SQLException e) {
                        System.out.println(e);
                    }    
                }
                System.out.println(sucursal);   
                String numero1= txtNumero1.getText();
                String numero2= txtNumero2.getText();
                String numero3= txtNumero3.getText();
                String departamento = cbDepartamentos.getSelectedItem().toString();
                String zona = cbZona.getSelectedItem().toString();
                String tipoCalle = cbCalle.getSelectedItem().toString();
                String queryActualizar = "UPDATE `direccion` SET `zona`='" + zona + "',`tipoCalle`='" + tipoCalle + "',`numero1`='" + numero1 + "',`numero2`='" + numero2 + "',`numero3`='" + numero3 + "',`nombreDepartamento`='" + departamento + "' WHERE idDireccion = " + idDireccion + ";";
                try {
                    st.executeUpdate(queryActualizar);
                    this.dispose();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            
        } catch (SQLException e) {
        }
            
        }
    public void eliminarSucursal() {
        String nombreSucursal = txtSucursal.getText();
        String queryIdSucursal = "SELECT idDireccion, idSucursal FROM sucursal INNER JOIN direccion WHERE sucursal.FK_idDireccion = direccion.idDireccion AND sucursal.nombreSucursal = '" + nombreSucursal + "';";
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryIdSucursal);
            while (rs.next()) {
                int idSucursal = rs.getInt("idSucursal");
                int idDireccion = rs.getInt("idDireccion");
                String queryEliminarS = "DELETE FROM `sucursal` WHERE idSucursal = " + idSucursal + ";";
                try {
                    st.executeUpdate(queryEliminarS);
                    String queryEliminarD = "DELETE FROM `direccion` WHERE idDireccion=" + idDireccion + ";";
                    try {
                        st.executeUpdate(queryEliminarD);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "La sucursal ha sido eliminada.", "", JOptionPane.WARNING_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar la informacion de la sucursal.", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSucursal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNumero1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNumero2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNumero3 = new javax.swing.JTextField();
        cbDepartamentos = new javax.swing.JComboBox<>();
        cbZona = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbCalle = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtNuevaSucursal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setText("Sucusal");

        txtSucursal.setEditable(false);
        txtSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSucursalActionPerformed(evt);
            }
        });

        jLabel2.setText("Departamento");

        jLabel3.setText("Zona");

        txtNumero2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumero2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Numeros");

        jLabel5.setText("Calle");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        jLabel6.setText("Nombre Sucursal");

        txtNuevaSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevaSucursalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(btnActualizar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbDepartamentos, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(txtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnEliminar)
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnCancelar))))
                    .addComponent(txtSucursal)
                    .addComponent(txtNuevaSucursal, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNuevaSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSucursalActionPerformed

    private void txtNumero2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumero2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumero2ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this.actualizarSucursal();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       this.eliminarSucursal();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNuevaSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevaSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuevaSucursalActionPerformed

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
            java.util.logging.Logger.getLogger(GestionarSucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarSucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarSucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarSucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionarSucursalForm dialog = new GestionarSucursalForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cbCalle;
    private javax.swing.JComboBox<String> cbDepartamentos;
    private javax.swing.JComboBox<String> cbZona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNuevaSucursal;
    private javax.swing.JTextField txtNumero1;
    private javax.swing.JTextField txtNumero2;
    private javax.swing.JTextField txtNumero3;
    private javax.swing.JTextField txtSucursal;
    // End of variables declaration//GEN-END:variables
}
