/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Banco.IOperacionesEmpresa;
import Factura.Factura;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlos
 */
public class Interfaz_Cliente extends javax.swing.JFrame {
    public float monto_total;
    public int contador;
    public Factura [] pendientes;
    public Factura [] factura_pagar;
    public Interfaz_Cliente() {
        initComponents();
    }
    
    public String capturarid(){
        String id = tfidcliente.getText();
        return id;
    }
    
    public void limpiar_tabla(){
        DefaultTableModel dftable = (DefaultTableModel) tabla_factura_pendiente.getModel();
        for (int i = 0; i < tabla_factura_pendiente.getRowCount(); i++) {
        dftable.removeRow(i);
        i-=1;
        }
    }
    
    public void conexion_banco(int idcliente){
        ///////////////////////////////////////////////////////////////////////////////////
      IOperacionesEmpresa operaciones;
      String pago="";
	try {
	    operaciones=(IOperacionesEmpresa)Naming.lookup("rmi://localhost/Operaciones");

	    this.pendientes=operaciones.calcular(idcliente);
            llenar_tabla(this.pendientes);
            /*if (pendientes.length!=0)
            {
               // pago=operaciones.pagar(pendientes);
                //lblpagoresultado.setText("Se llamo a pagar con exito "+pago);
            }*/
	}
	catch (Exception e){
	    e.printStackTrace();
	}
    }
        public String pagar(){
            IOperacionesEmpresa operaciones;
            String pago="no hay respuesta";
            try{
                operaciones=(IOperacionesEmpresa)Naming.lookup("rmi://localhost/Operaciones");
                String respuesta = operaciones.pagar(this.factura_pagar);
                return respuesta;
            } catch(Exception e){
                e.printStackTrace();
            }
            return pago;
        }
///////////////////////////////////////////////////////////////////////////////////
    

    public void llenar_tabla(Factura[] pendientes){
        
        limpiar_tabla();
        this.contador=0;
        this.monto_total=0;
        for(Factura f:pendientes){
        String Empresa = f.getEmpresa()+"";
        String IdFactura = f.getIdFactura()+"";
        String Monto = f.getMonto()+"";
        DefaultTableModel dftable = (DefaultTableModel) tabla_factura_pendiente.getModel();
        //Object obj[] = {Empresa,IdFactura,Monto,"Boton no:"+this.cont};
        Object obj[] = {Empresa,IdFactura,Monto};
        dftable.addRow(obj);
        this.monto_total += Float.parseFloat(Monto);
        this.contador++;
        }
        System.out.print(this.contador);
    }
    
    public void buscar_factura(int id_factu){
        //DefaultTableModel modelo = new DefaultTableModel();
        //tabla_factura_pendiente.setModel(modelo);
        int rows = tabla_factura_pendiente.getRowCount();
        System.out.print(rows);
        Factura[] aux=new Factura[2];
        for(int i=0;i<rows;i++){
            String factu = tabla_factura_pendiente.getValueAt(i, 1).toString();
            if(Integer.parseInt(factu) == id_factu){
                aux[0]=new Factura(tabla_factura_pendiente.getValueAt(i, 0).toString(), Integer.parseInt(tabla_factura_pendiente.getValueAt(i, 1).toString()), Double.parseDouble(tabla_factura_pendiente.getValueAt(i, 2).toString()));
            }
        }
        //aux[0]=new Factura("Cotes",590,220.0);
        this.factura_pagar = aux;
    }
    
    public float monto_total(){
        return this.monto_total;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfpagoresultado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfidcliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_factura_pendiente = new javax.swing.JTable();
        lblpagoresultado = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lblmonto = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        lbldolar = new javax.swing.JLabel();
        tffactura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblrespagar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tfpagoresultado.setBackground(new java.awt.Color(214, 216, 249));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Banco Central");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Id de Cliente : ");

        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton1.setText("Facturas Pendientes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabla_factura_pendiente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Empresa", "Id Factura", "Monto(Bs)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_factura_pendiente);
        if (tabla_factura_pendiente.getColumnModel().getColumnCount() > 0) {
            tabla_factura_pendiente.getColumnModel().getColumn(0).setResizable(false);
            tabla_factura_pendiente.getColumnModel().getColumn(1).setResizable(false);
            tabla_factura_pendiente.getColumnModel().getColumn(2).setResizable(false);
        }

        lblpagoresultado.setText("                        ");

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton2.setText("Pagar Factura");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblmonto.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblmonto.setText("Monto");

        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton3.setText("Cambio");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lbldolar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbldolar.setText("Dolar");

        tffactura.setPreferredSize(new java.awt.Dimension(100, 27));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Id_Factura : ");

        lblrespagar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblrespagar.setText("respuesta pagar");

        javax.swing.GroupLayout tfpagoresultadoLayout = new javax.swing.GroupLayout(tfpagoresultado);
        tfpagoresultado.setLayout(tfpagoresultadoLayout);
        tfpagoresultadoLayout.setHorizontalGroup(
            tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                .addGroup(tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                        .addComponent(lblpagoresultado)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                        .addGroup(tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(30, 30, 30)
                                .addComponent(tffactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblmonto)
                            .addComponent(jButton3)
                            .addComponent(lbldolar))
                        .addGap(80, 80, 80))
                    .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                        .addComponent(lblrespagar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        tfpagoresultadoLayout.setVerticalGroup(
            tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblpagoresultado)
                    .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                        .addGroup(tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                                .addGroup(tfpagoresultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tffactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addComponent(jButton2))
                            .addGroup(tfpagoresultadoLayout.createSequentialGroup()
                                .addComponent(lblmonto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbldolar)))))
                .addGap(27, 27, 27)
                .addComponent(lblrespagar)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfpagoresultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfpagoresultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void Boton_tabla(java.awt.event.ActionEvent evt) { 
        
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int id = Integer.parseInt(capturarid());
        //probar_conexion();
        conexion_banco(id);
        float monto = monto_total();
        lblmonto.setText("Monto total : "+String.valueOf(monto)+" Bs");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String id_factura = tffactura.getText();
        String res= "nada";
        lblrespagar.setText(res);
        buscar_factura(Integer.parseInt(id_factura));
        res = pagar();
        lblrespagar.setText(res);
        //lblrespagar.setText(res);
        //conexion_banco(id);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String cotizacion = String.valueOf(getCotizacion("2019/24/06"));
        float monto_dolar = monto_total/Float.parseFloat(cotizacion);
        lbldolar.setText("Monto Total : "+String.valueOf(monto_dolar)+" $");
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Interfaz_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz_Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldolar;
    private javax.swing.JLabel lblmonto;
    private javax.swing.JLabel lblpagoresultado;
    private javax.swing.JLabel lblrespagar;
    private javax.swing.JTable tabla_factura_pendiente;
    private javax.swing.JTextField tffactura;
    private javax.swing.JTextField tfidcliente;
    private javax.swing.JPanel tfpagoresultado;
    // End of variables declaration//GEN-END:variables

    public Double getCotizacion(java.lang.String fecha) {
        Tasa_Cambio.Wsbanco_Service service = new Tasa_Cambio.Wsbanco_Service();
        Tasa_Cambio.Wsbanco port = service.getWsbancoPort();
        return port.getCotizacion(fecha);
    }

}
