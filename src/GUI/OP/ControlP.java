/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package op;



import java.util.Date;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hector
 */
public class ControlP extends javax.swing.JFrame {

    Connection conn;
    Object Data;
    DateFormat formato;
    Date date = new Date();
    DefaultTableModel model2, model3, model4, model5;

    public ControlP() {
        initComponents();
        model2= (DefaultTableModel)this.jTproceso.getModel();
        model3 = (DefaultTableModel)this.jTterminadas.getModel();
        model4 = (DefaultTableModel)this.jTdescartes.getModel();
        model5 = (DefaultTableModel)this.jTcanceladas.getModel();
        formato = new SimpleDateFormat("yyyy-MM-dd");
//        conn = con.conectar();
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/neptuno",
                    "postgres", "postgres");
            System.out.println("Yeah!");
        } catch (SQLException e) {
            System.out.print(e);
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(ControlP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try { 
              Statement stmt = conn.createStatement();
              String query = "select first_name from customer" ; 
              ResultSet rs = stmt.executeQuery(query);
              this.jCbclientes.removeAllItems();
             while(rs.next()){
                int i =0;
                     Data = rs.getObject(i+1);
                     this.jCbclientes.addItem(Data.toString());
                 i++;
             }    
        } catch (SQLException ex) {
            System.out.println("nel");
        } 
        
        
        try{
            Statement stmt = conn.createStatement();
            String query2 = "select name_product from finished_product ";
            ResultSet rs2 = stmt.executeQuery(query2);
            this.jCbreceta.removeAllItems();
            while(rs2.next()){
                int i =0;
                     Data = rs2.getObject(i+1);
                     this.jCbreceta.addItem(Data.toString());
                 i++;
             } 
            } catch (SQLException ex) {
            System.out.println("nel");
        }

    }
    private void consultarP()
    {
       try
        {
          Statement stmt = conn.createStatement() ;
          String query = "Select order_production_id, total, order_prod_date, customer_id from order_production where order_state = 0" ;
          ResultSet rs = stmt.executeQuery(query) ;
          
          model2.setRowCount(0);
          Object Datos[]= new Object[5];
          
          while (rs.next())
           {
              for (int i=0;i<4;i++) Datos[i]=rs.getObject(i+1);
              model2.addRow(Datos);
           }
           this.jTproceso.setModel(model2);
          
        }
        catch(SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+e.toString(),
                "Error", JOptionPane.ERROR_MESSAGE);          
        }
     
    }
    
    private void consultarT()
    {    
        try
        {
          Statement stmt = conn.createStatement() ;
          String query3 = "Select order_production_id, total, order_prod_date, customer_id from order_production where order_state = 1" ;
          ResultSet rs3 = stmt.executeQuery(query3) ;
          
          model3.setRowCount(0);
          Object Datos[]= new Object[5];
          
          while (rs3.next())
           {
              for (int i=0;i<4;i++) Datos[i]=rs3.getObject(i+1);
              model3.addRow(Datos);
           }
           this.jTproceso.setModel(model3);
          
        }
        catch(SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+e.toString(),
                "Error", JOptionPane.ERROR_MESSAGE);          
        }
    }
    
    private void consultarPD(){
        try
        {
          Statement stmt = conn.createStatement() ;
          String query3 = "SELECT pf.name_product, pd.quantity, pd.order_production_id from product_discarded pd\n"
                  + "join recipe rp on pd.recipe_id = pd.recipe_id\n"
                  + "join finished_product pf on rp.id_product = pf.id_finished_product";
          ResultSet rs3 = stmt.executeQuery(query3) ;
          
          model4.setRowCount(0);
          Object Datos[]= new Object[4];
          
          while (rs3.next())
           {
              for (int i=0;i<3;i++) Datos[i]=rs3.getObject(i+1);
              model4.addRow(Datos);
           }
           this.jTdescartes.setModel(model4);
          
        }
        catch(SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+e.toString(),
                "Error", JOptionPane.ERROR_MESSAGE);          
        }
    }
    
    private void consultarC(){
        try
        {
          Statement stmt = conn.createStatement() ;
          String query3 = "Select cop.order_production_id, op.customer_id, op.order_prod_date, cop.canceled_order_date, cop.reason from cancel_order_production cop\n"
                  + " join order_production op on cop.order_production_id = op.order_production_id";
          ResultSet rs3 = stmt.executeQuery(query3) ;
          
          model5.setRowCount(0);
          Object Datos[]= new Object[6];
          
          while (rs3.next())
           {
              for (int i=0;i<5;i++) Datos[i]=rs3.getObject(i+1);
              model5.addRow(Datos);
           }
           this.jTproceso.setModel(model2);
          
        }
        catch(SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+e.toString(),
                "Error", JOptionPane.ERROR_MESSAGE);          
        }
        
    }
    
    private int getReceta(int producto_id){
        int valor = 0;
        try{
           Statement stm = conn.createStatement();
           String query = "SELECT id_recipe from recipe where id_product = "+ producto_id;
           ResultSet rs = stm.executeQuery(query);
           while(rs.next()){
               valor = rs.getInt("id_recipe");
           }
        }catch(SQLException e){
            System.out.print(e);
        }
        return valor;
    }
    
    private float getCosto(int producto_id){
        float valor = 0;
        try{
           Statement stm = conn.createStatement();
           String query = "SELECT unit_cost from recipe where id_product = "+ producto_id;
           ResultSet rs = stm.executeQuery(query);
           while(rs.next()){
               valor = rs.getFloat("unit_cost");
           }
        }catch(SQLException e){
            System.out.print(e);
        }
        return valor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton9 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jCbreceta = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jTfcantidad = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jCbclientes = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jSfecha = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTproceso = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTterminadas = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTdescartes = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTcanceladas = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();

        jButton9.setText("Buscar");

        jLabel29.setText("Cantidad:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Costo", "Cantidad"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton5.setText("Enviar Orden");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel19.setText("PRESUPUESTO");

        jLabel17.setText("Receta:");

        jCbreceta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCbreceta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbrecetaActionPerformed(evt);
            }
        });

        jLabel30.setText("Cantidad:");

        jTfcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfcantidadActionPerformed(evt);
            }
        });

        jButton4.setText("Finalizar orden");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel18.setText("Total Unitario:");

        jLabel28.setText("$0");

        jLabel24.setText("Nombre Cliente:");

        jCbclientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCbclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbclientesActionPerformed(evt);
            }
        });

        jButton7.setText("Terminar Presupuesto");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jSfecha.setModel(new javax.swing.SpinnerDateModel());
        jSfecha.setEditor(new javax.swing.JSpinner.DateEditor(jSfecha, "yyyy-MM-dd"));

        jLabel1.setText("fecha");

        jTproceso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Orden", "Total", "Fecha", "Cliente"
            }
        ));
        jScrollPane8.setViewportView(jTproceso);

        jButton1.setText("Cancelar Orden ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton8.setText("Recargar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jCbreceta, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCbclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(92, 358, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addGap(360, 360, 360))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1)
                        .addGap(42, 42, 42)
                        .addComponent(jButton8)
                        .addGap(82, 82, 82))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jCbclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jCbreceta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jTfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(29, 29, 29)
                        .addComponent(jButton7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1)
                    .addComponent(jButton8))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Presupuesto", jPanel2);

        jLabel31.setText("ORDENES TERMINADAS");

        jTterminadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Orden", "total", "fecha de creacion", "id_cliente"
            }
        ));
        jScrollPane5.setViewportView(jTterminadas);

        jButton3.setText("Recarcar tabla");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(388, 388, 388)
                        .addComponent(jLabel31))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jButton3)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ordenes Terminadas", jPanel4);

        jLabel26.setText("PRODUCTOS DESCARTADOS");

        jTdescartes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "recipe_id", "quantity", "order_id"
            }
        ));
        jScrollPane6.setViewportView(jTdescartes);

        jButton14.setText("Recargar Tabla");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(373, 373, 373))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jButton14))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos Descartados", jPanel5);

        jLabel22.setText("ORDENES CANCELADAS");

        jTcanceladas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Orden", "Id Cliente", "Fecha de Creación ", "Fecha de Cancelación", "Motivo de Cancelación"
            }
        ));
        jScrollPane7.setViewportView(jTcanceladas);

        jButton17.setText("Recargar tabla");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(jLabel22))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jButton17))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ordenes Canceladas", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        float unit_cost = 0;
        String fecha ;
        int product_id = this.jCbreceta.getSelectedIndex() + 1;
        int cantidad = Integer.parseInt(this.jTfcantidad.getText());
        int receta = getReceta(product_id);
        System.out.print(this.jCbreceta.getSelectedIndex());
        fecha =  formato.format(jSfecha.getValue());
        float total= 0;
         if(validacion(receta, cantidad)){
             try
            {
                Statement stmt = conn.createStatement() ;
                String query4 = "Select unit_cost from recipe where id_product ="+this.jCbreceta.getSelectedIndex();
                ResultSet rs4 = stmt.executeQuery(query4) ;
                while(rs4.next())
                unit_cost = rs4.getFloat("unit_cost");
            
                total = Integer.parseInt(jTfcantidad.getText()) * unit_cost;
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+e.toString(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            try
            {
                
                String query = " insert into order_production (order_production_id, customer_id, order_prod_date, total, order_state)"
                        + " values (default, ?, ?, ?, ?)";
                
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, this.jCbclientes.getSelectedIndex()+1);
                pst.setDate(2, java.sql.Date.valueOf(fecha));
                pst.setFloat(3, total);
                pst.setInt(4, 0);
                
                // execute the preparedstatement
                pst.execute();
                
                //limpiar controles visuales
                this.jTfcantidad.setText("");
                
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+e.toString(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            float costo = getCosto(this.jCbreceta.getSelectedIndex());
            try
            {
                
                String query = " insert into order_production_detail values (default, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, receta);
                pst.setInt(2, cantidad);
                pst.setFloat(3, costo);
                pst.setInt(4, getOrder());
                
                // execute the preparedstatement
                pst.execute();
                pst.close();
                updateMateriales(product_id, cantidad);
                consultarP();
                 if(updateM(receta, cantidad)){
                  System.out.println("actualizacion hecha");
                 }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+e.toString(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
         }else{
             JOptionPane.showMessageDialog(null, " no cuenta con suficiente material");
         }   
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int valor = this.jTproceso.getSelectedRow();
        int valorID = Integer.parseInt(String.valueOf(this.model2.getValueAt(valor, 0)));
        int opcion = JOptionPane.showConfirmDialog(null, "¿Las ordenes se completaron en su totalidad?",
            "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opcion==JOptionPane.NO_OPTION)
        {
             int order_id = 0,quantity = 0, recipe_id = 0;
            //JOptionPane.showInputDialog(null, "¿Cuantas unidades seran descartadas?", "Ingrese Cantidad");
//            descartar abrird = new descartar();
//            abrird.setVisible(true);
            int descartes= Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad a descartar"));
            try{
         String query = "Insert INTO product_discarded values(default, ?, ?, ?)";
         String queryOrder = "SELECT order_production_id, recipe_id from order_production_detail\n"
                 + "where order_production_id = ?";
         PreparedStatement stm = conn.prepareCall(queryOrder);
         stm.setInt(1, valorID);
         ResultSet rs = stm.executeQuery();
         while(rs.next()){
             order_id = rs.getInt("order_production_id");
             recipe_id = rs.getInt("recipe_id");
         }
         stm.close();
         PreparedStatement pst = conn.prepareCall(query);
         pst.setInt(1, recipe_id);
         pst.setInt(2, descartes);
         pst.setInt(3, order_id);
         pst.execute();
         pst.close();
          if(updateM(recipe_id, descartes)){
             System.out.println("actualizacion hecha");
         }
         consultarPD();
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+e.toString(),
                        "Error", JOptionPane.ERROR_MESSAGE);
  
      }
        } else{
            try {
            String query = "Update order_production set order_state = 1 where order_production_id = ?" ;
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, valorID);
            int resultado = pst.executeUpdate();
            consultarT();
        } catch (SQLException ex) {
            Logger.getLogger(ControlP.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCbrecetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbrecetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbrecetaActionPerformed

    private void jTfcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfcantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTfcantidadActionPerformed

    private void jCbclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbclientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbclientesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int valor = this.jTproceso.getSelectedRow();
       int valorID = Integer.parseInt(String.valueOf(this.model2.getValueAt(valor, 0)));
       int order_id = 0,quantity = 0, recipe_id = 0;
       int opcion = JOptionPane.showConfirmDialog(null, "Desea cancelar dicha orden?",
            "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opcion==JOptionPane.YES_NO_OPTION){
            String reason = JOptionPane.showInputDialog(null, "Ingrese la razon  a la cual decide cancelar dicha orde");
            try {
            String query = "Update order_production set order_state = 2 where order_production_id = ?" ;
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, valorID);
            int resultado = pst.executeUpdate();
            pst.close();
            consultarT();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+ex.toString(),
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
       
      try{
         String query = "INSERT INTO product_discarded values(default, ?, ?, ?)";
         String queryOrder = "SELECT order_production_id, recipe_id, quantity from order_production_detail\n"
                 + "where order_production_id = ?";
         PreparedStatement stm = conn.prepareCall(queryOrder);
         stm.setInt(1, valorID);
         ResultSet rs = stm.executeQuery();
         while(rs.next()){
             order_id = rs.getInt("order_production_id");
             recipe_id = rs.getInt("recipe_id");
             quantity = rs.getInt("quantity");
         }
         stm.close();
         PreparedStatement pst = conn.prepareCall(query);
         pst.setInt(1, recipe_id);
         pst.setInt(2, quantity);
         pst.setInt(3, order_id);
         pst.execute();
         pst.close();
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+e.toString(),
                        "Error", JOptionPane.ERROR_MESSAGE);
      }
      
      try{
          String query = "INSERT INTO cancel_order_production values(?,?,?)";
          String queryOrder = "SELECT order_production_id, recipe_id, quantity from order_production_detail\n"
                  + "where order_production_id = ?";
         PreparedStatement stm = conn.prepareCall(queryOrder);
         stm.setInt(1, valorID);
         ResultSet rs = stm.executeQuery();
         while(rs.next()){
             order_id = rs.getInt("order_production_id");
         }
         stm.close();
         String fecha = formato.format(date);
         PreparedStatement pst = conn.prepareCall(query);
         pst.setInt(1, order_id);
         pst.setString(2, reason);
         pst.setDate(3, (java.sql.Date.valueOf(fecha)));
         pst.execute();
         pst.close();
      }catch(SQLException e){
          
      }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        consultarP();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        consultarT();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        consultarPD();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        consultarC();
    }//GEN-LAST:event_jButton17ActionPerformed

    public void updateMateriales(int product_id, int quantity){
        try {
            String query = "UPDATE finished_product set units_in_stock = units_in_stock + ? where id_finished_product = ?";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, quantity);
            pst.setInt(2, product_id);
            pst.execute();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR: "+ex.toString(),
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean validacion(int recipe_id, int quantity){
        boolean valor= false;
        int valor1 = 0;
        try {
            String query = "SELECT validacion(?,?)";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1,recipe_id);
            pst.setInt(2, quantity);
            ResultSet rs = pst.executeQuery();  
            while(rs.next()){
                valor1 = rs.getInt(1);
                System.out.print(valor1);
                if (valor1 == 0) {
                    valor = true;
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex);
            return false;
        }
        return valor;
    }
    
    public boolean updateM(int recipe_id, int quantity){
        boolean valor = false;
        String b = null;
        try {
            String query = "SELECT updateM(?,?)";
            PreparedStatement pst  = conn.prepareCall(query);
                pst.setInt(1, recipe_id);
                pst.setInt(2, quantity);
                ResultSet rs = pst.executeQuery(); 
                while(rs.next()){
                b = rs.getString(1);
                }
                if (!"".equals(b)) {
                    valor = true;
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }
        return valor;
    }
    public int getOrder(){
        int order = 0;
       try{
          Statement stm = conn.createStatement();
          String query = "SELECT count(*) from order_production";
          ResultSet rs = stm.executeQuery(query);
          while(rs.next()){
              order = rs.getInt(1);
          }
      }catch(SQLException ex){
          System.out.print(ex);
      }
       return order;
    }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControlP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ControlP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCbclientes;
    private javax.swing.JComboBox<String> jCbreceta;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSpinner jSfecha;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTcanceladas;
    private javax.swing.JTable jTdescartes;
    private javax.swing.JTextField jTfcantidad;
    private javax.swing.JTable jTproceso;
    private javax.swing.JTable jTterminadas;
    // End of variables declaration//GEN-END:variables
}
