package GUI.PP;

import Entidades.PP.AvancePago;
import Entidades.PP.Cuota;
import Entidades.PP.DBAdmin;
import Entidades.PP.MoraCuota;
import Entidades.PP.PlanDePago;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class frmPago_proveedores extends javax.swing.JFrame {

    DBAdmin dba; 
    
    //Arraylist.
    ArrayList<PlanDePago> lPlanDePago; ArrayList<Cuota> lCuota; 
    ArrayList<MoraCuota> lMoraCuota; ArrayList<AvancePago> lAvancePago; 
  
    //Modelos de tabla.
    DefaultTableModel modeloPlanDePago; DefaultTableModel modeloCuota;
    DefaultTableModel modeloMora; DefaultTableModel modeloAvance;
    DefaultTableCellRenderer centerRenderer;
    
    SimpleDateFormat sdf_timestamp = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    
    public frmPago_proveedores() {
        initComponents();
        
        dba = new DBAdmin();
          
        //Inicializando ArrayList.
        lPlanDePago = new ArrayList<>(); lCuota = new ArrayList<>();
        lMoraCuota = new ArrayList<>(); lAvancePago = new ArrayList<>();
        
        //Modelos de tabla.
        modeloPlanDePago = (DefaultTableModel) this.jtPlanDePago.getModel();
        modeloCuota = (DefaultTableModel) this.jtCuota.getModel();
        modeloMora = (DefaultTableModel) this.jtMora.getModel();
        modeloAvance = (DefaultTableModel) this.jtAvance.getModel();
        
        //Modificando propiedades en tablas.
        jtPlanDePago.getColumnModel().getColumn(0).setPreferredWidth(25); 
        jtPlanDePago.getColumnModel().getColumn(2).setPreferredWidth(45); 
        jtPlanDePago.getColumnModel().getColumn(3).setPreferredWidth(25); 
        jtPlanDePago.getColumnModel().getColumn(7).setPreferredWidth(45); 
        jtPlanDePago.getColumnModel().getColumn(10).setPreferredWidth(80); 
        
        jtCuota.getColumnModel().getColumn(0).setPreferredWidth(5); 
        jtCuota.getColumnModel().getColumn(1).setPreferredWidth(30); 
        jtCuota.getColumnModel().getColumn(2).setPreferredWidth(40); 
        jtCuota.getColumnModel().getColumn(3).setPreferredWidth(40); 
        jtCuota.getColumnModel().getColumn(4).setPreferredWidth(30); 
        jtCuota.getColumnModel().getColumn(5).setPreferredWidth(30); 
        jtCuota.getColumnModel().getColumn(6).setPreferredWidth(90); 
        
        jtAvance.getColumnModel().getColumn(0).setPreferredWidth(35); 
        
        jtMora.getColumnModel().getColumn(0).setPreferredWidth(25); 
        jtMora.getColumnModel().getColumn(1).setPreferredWidth(40);
        jtMora.getColumnModel().getColumn(2).setPreferredWidth(40);
        jtMora.getColumnModel().getColumn(3).setPreferredWidth(40);
        jtMora.getColumnModel().getColumn(4).setPreferredWidth(90);
         
        this.centrarCeldas(jtPlanDePago, 11); this.centrarCeldas(jtCuota, 7);
        this.centrarCeldas(jtAvance, 3); this.centrarCeldas(jtMora, 5);
       
        //Estableciendo conexión de prueba.
        try { dba = new DBAdmin("postgres", "2019mu601"); } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        Logger.getLogger(frmPago_proveedores.class.getName()).log(Level.SEVERE, null, ex); }  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jbpPrincipal = new javax.swing.JTabbedPane();
        jpPagos = new javax.swing.JPanel();
        jpTituloPagos1 = new javax.swing.JPanel();
        lblTituloPagosProveedores1 = new javax.swing.JLabel();
        jpBusquedas = new javax.swing.JPanel();
        lblBusqueda = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        chk_id_compra = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        btnReiniciarBusqueda = new javax.swing.JButton();
        chk_proveedor = new javax.swing.JCheckBox();
        chk_fecha_compra = new javax.swing.JCheckBox();
        lblEstado = new javax.swing.JLabel();
        chkCancelado = new javax.swing.JCheckBox();
        chkPendiente = new javax.swing.JCheckBox();
        chkMora = new javax.swing.JCheckBox();
        chkGeneral = new javax.swing.JCheckBox();
        jpDetallePago = new javax.swing.JPanel();
        txtIdCompra = new javax.swing.JTextField();
        lblIdCompra = new javax.swing.JLabel();
        lblFechaPago = new javax.swing.JLabel();
        lblMontoCuota = new javax.swing.JLabel();
        txtMontoCuota = new javax.swing.JTextField();
        txtMontoMora = new javax.swing.JTextField();
        lblMontoMora = new javax.swing.JLabel();
        txtNumPago = new javax.swing.JTextField();
        lblNumPago = new javax.swing.JLabel();
        txtTotalCuota = new javax.swing.JTextField();
        lblTotalCuota = new javax.swing.JLabel();
        txtIdPago = new javax.swing.JTextField();
        lblIdPago = new javax.swing.JLabel();
        jdFechaPago = new com.toedter.calendar.JDateChooser();
        txtMontoAbono = new javax.swing.JTextField();
        lblMontoAbono = new javax.swing.JLabel();
        btnRealizarPago = new javax.swing.JButton();
        btnNuevoPago = new javax.swing.JButton();
        jpDetallesPago = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtCuota = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtPlanDePago = new javax.swing.JTable();
        jpInfo = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtAvance = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtMora = new javax.swing.JTable();
        lblIdCompra1 = new javax.swing.JLabel();
        lblIdCompra2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pago a proveedores");
        setName("frmPagoProveedores"); // NOI18N
        setResizable(false);

        jbpPrincipal.setBackground(new java.awt.Color(251, 251, 251));
        jbpPrincipal.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jpPagos.setBackground(new java.awt.Color(251, 251, 251));

        jpTituloPagos1.setBackground(new java.awt.Color(76, 101, 150));

        lblTituloPagosProveedores1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        lblTituloPagosProveedores1.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPagosProveedores1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTituloPagosProveedores1.setText("Pago a proveedores: Planes de pago  y  registro de pago a cuotas");

        javax.swing.GroupLayout jpTituloPagos1Layout = new javax.swing.GroupLayout(jpTituloPagos1);
        jpTituloPagos1.setLayout(jpTituloPagos1Layout);
        jpTituloPagos1Layout.setHorizontalGroup(
            jpTituloPagos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloPagos1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTituloPagosProveedores1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTituloPagos1Layout.setVerticalGroup(
            jpTituloPagos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTituloPagosProveedores1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jpBusquedas.setBackground(new java.awt.Color(226, 226, 226));

        lblBusqueda.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblBusqueda.setText("Búsqueda por:");

        txtBusqueda.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtBusqueda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        chk_id_compra.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(chk_id_compra);
        chk_id_compra.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chk_id_compra.setSelected(true);
        chk_id_compra.setText("ID compra");
        chk_id_compra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chk_id_compra.setFocusable(false);

        btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/PP/search.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusable(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnReiniciarBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        btnReiniciarBusqueda.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnReiniciarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/PP/reset.png"))); // NOI18N
        btnReiniciarBusqueda.setToolTipText("Reiniciar búsqueda");
        btnReiniciarBusqueda.setBorderPainted(false);
        btnReiniciarBusqueda.setContentAreaFilled(false);
        btnReiniciarBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReiniciarBusqueda.setFocusable(false);
        btnReiniciarBusqueda.setPreferredSize(new java.awt.Dimension(57, 32));
        btnReiniciarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarBusquedaActionPerformed(evt);
            }
        });

        chk_proveedor.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(chk_proveedor);
        chk_proveedor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chk_proveedor.setText("Proveedor");
        chk_proveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chk_proveedor.setFocusable(false);

        chk_fecha_compra.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(chk_fecha_compra);
        chk_fecha_compra.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chk_fecha_compra.setText("Fecha de orden");
        chk_fecha_compra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chk_fecha_compra.setFocusable(false);

        lblEstado.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblEstado.setText("Estado:");

        chkCancelado.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(chkCancelado);
        chkCancelado.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkCancelado.setText("Cancelado");
        chkCancelado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkCancelado.setFocusable(false);
        chkCancelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCanceladoActionPerformed(evt);
            }
        });

        chkPendiente.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(chkPendiente);
        chkPendiente.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkPendiente.setText("Pendiente");
        chkPendiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkPendiente.setFocusable(false);
        chkPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPendienteActionPerformed(evt);
            }
        });

        chkMora.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(chkMora);
        chkMora.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkMora.setText("En mora");
        chkMora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkMora.setFocusable(false);
        chkMora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMoraActionPerformed(evt);
            }
        });

        chkGeneral.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(chkGeneral);
        chkGeneral.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkGeneral.setText("General");
        chkGeneral.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkGeneral.setFocusable(false);
        chkGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGeneralActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpBusquedasLayout = new javax.swing.GroupLayout(jpBusquedas);
        jpBusquedas.setLayout(jpBusquedasLayout);
        jpBusquedasLayout.setHorizontalGroup(
            jpBusquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblEstado)
                .addGap(15, 15, 15)
                .addComponent(chkGeneral)
                .addGap(10, 10, 10)
                .addComponent(chkPendiente)
                .addGap(10, 10, 10)
                .addComponent(chkCancelado)
                .addGap(10, 10, 10)
                .addComponent(chkMora)
                .addGap(95, 95, 95)
                .addComponent(lblBusqueda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_id_compra)
                .addGap(10, 10, 10)
                .addComponent(chk_proveedor)
                .addGap(10, 10, 10)
                .addComponent(chk_fecha_compra)
                .addGap(35, 35, 35)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReiniciarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpBusquedasLayout.setVerticalGroup(
            jpBusquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedasLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jpBusquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_id_compra)
                    .addComponent(chk_proveedor)
                    .addComponent(chk_fecha_compra)
                    .addComponent(lblBusqueda)
                    .addComponent(lblEstado)
                    .addComponent(chkCancelado)
                    .addComponent(chkPendiente)
                    .addComponent(chkMora)
                    .addComponent(chkGeneral))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnReiniciarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpDetallePago.setBackground(new java.awt.Color(226, 226, 226));

        txtIdCompra.setEditable(false);
        txtIdCompra.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtIdCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdCompra.setFocusable(false);

        lblIdCompra.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblIdCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdCompra.setText("ID compra:");
        lblIdCompra.setToolTipText("");

        lblFechaPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblFechaPago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaPago.setText("Fecha de pago:");
        lblFechaPago.setToolTipText("");

        lblMontoCuota.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMontoCuota.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMontoCuota.setText("Monto restante:");
        lblMontoCuota.setToolTipText("");

        txtMontoCuota.setEditable(false);
        txtMontoCuota.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtMontoCuota.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoCuota.setFocusable(false);

        txtMontoMora.setEditable(false);
        txtMontoMora.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtMontoMora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoMora.setFocusable(false);

        lblMontoMora.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMontoMora.setForeground(new java.awt.Color(255, 0, 51));
        lblMontoMora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMontoMora.setText("Monto de mora:");
        lblMontoMora.setToolTipText("");

        txtNumPago.setEditable(false);
        txtNumPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNumPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumPago.setFocusable(false);

        lblNumPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNumPago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumPago.setText("Num. de pago:");
        lblNumPago.setToolTipText("");

        txtTotalCuota.setEditable(false);
        txtTotalCuota.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtTotalCuota.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalCuota.setFocusable(false);

        lblTotalCuota.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblTotalCuota.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalCuota.setText("Total a cancelar:");
        lblTotalCuota.setToolTipText("");

        txtIdPago.setEditable(false);
        txtIdPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtIdPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdPago.setFocusable(false);

        lblIdPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblIdPago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdPago.setText("ID pago:");
        lblIdPago.setToolTipText("");

        jdFechaPago.setDateFormatString("dd/MM/yyyy");
        jdFechaPago.setEnabled(false);
        jdFechaPago.setFocusCycleRoot(true);
        jdFechaPago.setFocusable(false);
        jdFechaPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jdFechaPago.setPreferredSize(new java.awt.Dimension(101, 24));

        txtMontoAbono.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtMontoAbono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoAbono.setEnabled(false);
        txtMontoAbono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoAbonoKeyReleased(evt);
            }
        });

        lblMontoAbono.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMontoAbono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMontoAbono.setText("Monto de abono:");
        lblMontoAbono.setToolTipText("");

        btnRealizarPago.setBackground(new java.awt.Color(255, 255, 255));
        btnRealizarPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnRealizarPago.setText("Realizar pago");
        btnRealizarPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRealizarPago.setEnabled(false);
        btnRealizarPago.setFocusPainted(false);
        btnRealizarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPagoActionPerformed(evt);
            }
        });

        btnNuevoPago.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevoPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnNuevoPago.setText("Nuevo pago");
        btnNuevoPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoPago.setEnabled(false);
        btnNuevoPago.setFocusPainted(false);
        btnNuevoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDetallePagoLayout = new javax.swing.GroupLayout(jpDetallePago);
        jpDetallePago.setLayout(jpDetallePagoLayout);
        jpDetallePagoLayout.setHorizontalGroup(
            jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetallePagoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jpDetallePagoLayout.createSequentialGroup()
                            .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtIdCompra, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblIdCompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblIdPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdPago, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNumPago)
                                .addComponent(lblNumPago, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jdFechaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jpDetallePagoLayout.createSequentialGroup()
                            .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMontoCuota)
                                .addComponent(lblMontoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblMontoMora, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                .addComponent(txtMontoMora))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTotalCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTotalCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpDetallePagoLayout.createSequentialGroup()
                        .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMontoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMontoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevoPago)
                        .addGap(18, 18, 18)
                        .addComponent(btnRealizarPago)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jpDetallePagoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNuevoPago, btnRealizarPago, lblMontoCuota, lblMontoMora, lblTotalCuota, txtMontoAbono, txtMontoCuota, txtMontoMora, txtTotalCuota});

        jpDetallePagoLayout.setVerticalGroup(
            jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDetallePagoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblIdPago, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNumPago, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpDetallePagoLayout.createSequentialGroup()
                                .addComponent(lblMontoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addComponent(txtMontoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpDetallePagoLayout.createSequentialGroup()
                            .addComponent(lblMontoMora, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMontoMora, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpDetallePagoLayout.createSequentialGroup()
                        .addComponent(lblTotalCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblMontoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMontoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRealizarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jpDetallePagoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnNuevoPago, btnRealizarPago, jdFechaPago, txtIdCompra, txtIdPago, txtMontoAbono, txtMontoCuota, txtMontoMora, txtNumPago, txtTotalCuota});

        jpDetallesPago.setBackground(new java.awt.Color(226, 226, 226));

        jtCuota.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jtCuota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID  pago", "Estado", "Total de cuota", "Total abonado", "Monto de mora", "Fecha de exp.", "Fecha de pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCuota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtCuota.setFocusable(false);
        jtCuota.setName(""); // NOI18N
        jtCuota.setRowHeight(30);
        jtCuota.setSelectionBackground(new java.awt.Color(5, 135, 91));
        jtCuota.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtCuota.getTableHeader().setReorderingAllowed(false);
        jtCuota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCuotaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jtCuota);
        if (jtCuota.getColumnModel().getColumnCount() > 0) {
            jtCuota.getColumnModel().getColumn(0).setResizable(false);
            jtCuota.getColumnModel().getColumn(1).setResizable(false);
            jtCuota.getColumnModel().getColumn(2).setResizable(false);
            jtCuota.getColumnModel().getColumn(3).setResizable(false);
            jtCuota.getColumnModel().getColumn(4).setResizable(false);
            jtCuota.getColumnModel().getColumn(5).setResizable(false);
            jtCuota.getColumnModel().getColumn(5).setHeaderValue("Fecha de exp.");
            jtCuota.getColumnModel().getColumn(6).setResizable(false);
            jtCuota.getColumnModel().getColumn(6).setHeaderValue("Fecha de pago");
        }

        javax.swing.GroupLayout jpDetallesPagoLayout = new javax.swing.GroupLayout(jpDetallesPago);
        jpDetallesPago.setLayout(jpDetallesPagoLayout);
        jpDetallesPagoLayout.setHorizontalGroup(
            jpDetallesPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetallesPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jpDetallesPagoLayout.setVerticalGroup(
            jpDetallesPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetallesPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtPlanDePago.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jtPlanDePago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID compra", "Proveedor", "Fecha de orden", "Estado", "Total planificado", "Total cancelado", "Total restante", "Num. de pagos", "Porcentaje de mora", "Total en mora", "Tot. mora cancelada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPlanDePago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtPlanDePago.setFocusable(false);
        jtPlanDePago.setName(""); // NOI18N
        jtPlanDePago.setRowHeight(30);
        jtPlanDePago.setSelectionBackground(new java.awt.Color(76, 101, 150));
        jtPlanDePago.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtPlanDePago.getTableHeader().setReorderingAllowed(false);
        jtPlanDePago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPlanDePagoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtPlanDePago);
        if (jtPlanDePago.getColumnModel().getColumnCount() > 0) {
            jtPlanDePago.getColumnModel().getColumn(0).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(1).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(2).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(3).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(4).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(5).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(6).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(7).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(8).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(9).setResizable(false);
            jtPlanDePago.getColumnModel().getColumn(10).setResizable(false);
        }

        jpInfo.setBackground(new java.awt.Color(226, 226, 226));

        jtAvance.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jtAvance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID  pago", "Monto de avance", "Fecha de pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtAvance.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtAvance.setFocusable(false);
        jtAvance.setName(""); // NOI18N
        jtAvance.setRowHeight(30);
        jtAvance.setRowSelectionAllowed(false);
        jtAvance.setSelectionBackground(new java.awt.Color(0, 153, 102));
        jtAvance.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtAvance.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(jtAvance);
        if (jtAvance.getColumnModel().getColumnCount() > 0) {
            jtAvance.getColumnModel().getColumn(0).setResizable(false);
            jtAvance.getColumnModel().getColumn(1).setResizable(false);
            jtAvance.getColumnModel().getColumn(2).setResizable(false);
        }

        jtMora.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jtMora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID  pago", "Estado", "Mora", "Cancelado", "Fecha de pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtMora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtMora.setFocusable(false);
        jtMora.setName(""); // NOI18N
        jtMora.setRowHeight(30);
        jtMora.setRowSelectionAllowed(false);
        jtMora.setSelectionBackground(new java.awt.Color(0, 153, 102));
        jtMora.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtMora.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(jtMora);
        if (jtMora.getColumnModel().getColumnCount() > 0) {
            jtMora.getColumnModel().getColumn(0).setResizable(false);
            jtMora.getColumnModel().getColumn(1).setResizable(false);
            jtMora.getColumnModel().getColumn(2).setResizable(false);
            jtMora.getColumnModel().getColumn(3).setResizable(false);
            jtMora.getColumnModel().getColumn(4).setResizable(false);
        }

        lblIdCompra1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblIdCompra1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdCompra1.setText("Avances de pago para plan seleccionado");
        lblIdCompra1.setToolTipText("");

        lblIdCompra2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblIdCompra2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdCompra2.setText("Lista de moras para plan seleccionado");
        lblIdCompra2.setToolTipText("");

        javax.swing.GroupLayout jpInfoLayout = new javax.swing.GroupLayout(jpInfo);
        jpInfo.setLayout(jpInfoLayout);
        jpInfoLayout.setHorizontalGroup(
            jpInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addComponent(lblIdCompra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                    .addComponent(lblIdCompra2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpInfoLayout.setVerticalGroup(
            jpInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIdCompra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIdCompra2))
                .addGap(11, 11, 11)
                .addGroup(jpInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpPagosLayout = new javax.swing.GroupLayout(jpPagos);
        jpPagos.setLayout(jpPagosLayout);
        jpPagosLayout.setHorizontalGroup(
            jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTituloPagos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jpBusquedas, javax.swing.GroupLayout.PREFERRED_SIZE, 1235, Short.MAX_VALUE)
                    .addGroup(jpPagosLayout.createSequentialGroup()
                        .addComponent(jpDetallePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpDetallesPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpPagosLayout.setVerticalGroup(
            jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpTituloPagos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpBusquedas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpDetallePago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpDetallesPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jbpPrincipal.addTab("Pagos", jpPagos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbpPrincipal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(1296, 695));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Habilita el registro de un nuevo pago.
    private void btnNuevoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPagoActionPerformed
        this.txtMontoAbono.setEnabled(true); this.btnRealizarPago.setEnabled(true);
        this.btnNuevoPago.setEnabled(false);
    }//GEN-LAST:event_btnNuevoPagoActionPerformed

    // Realiza un pago a cuota pendiente.
    private void btnRealizarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPagoActionPerformed
        try { if (this.verificarInput()) { return; }
            
            String purchase_payment_id = this.txtIdPago.getText(), paid_amount = this.txtMontoAbono.getText();

            this.dba.conectar();
            this.dba.ejecutarConsulta(
            "SELECT new_purchase_payment('" + purchase_payment_id + "','"+ paid_amount +"');");
            
            //Mostrando datos.
            this.mostrarPlanesDePago(0, "", "");
            this.mostrarCuotas(Integer.parseInt(this.txtIdCompra.getText()));
            this.mostrarMoras(Integer.parseInt(this.txtIdCompra.getText())); 
            this.mostrarAvances(Integer.parseInt(this.txtIdCompra.getText()));
            
            this.jtCuota.setRowSelectionInterval(
            Integer.parseInt(this.txtNumPago.getText())-1, Integer.parseInt(this.txtNumPago.getText())-1);
            
            JOptionPane.showMessageDialog(null, "Transacción realizada con éxito: Nuevo pago registrado", 
                "Pago de cuota", JOptionPane.INFORMATION_MESSAGE);
            
            this.limpiarControles();  
        } catch(SQLException | NumberFormatException ex){}
    }//GEN-LAST:event_btnRealizarPagoActionPerformed

    // Realiza un búsqueda en planes de pago y muestra el resultado.
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        String expression = this.txtBusqueda.getText();
        
        if (this.chk_id_compra.isSelected()) {
            this.mostrarPlanesDePago(SOMEBITS, "purchase_id", expression);
        }
        else if (this.chk_proveedor.isSelected()) {
            this.mostrarPlanesDePago(SOMEBITS, "company_name", expression);
        }
        else if (this.chk_fecha_compra.isSelected()) {
            this.mostrarPlanesDePago(SOMEBITS, "purchase_date", expression);
        }
        
        this.limpiarSeleccion();
    }//GEN-LAST:event_btnBuscarActionPerformed

    // Reinicia una búsqueda de planes de pago.
    private void btnReiniciarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarBusquedaActionPerformed
        this.mostrarPlanesDePago(0, "", ""); this.limpiarSeleccion(); 
    }//GEN-LAST:event_btnReiniciarBusquedaActionPerformed

    // Selección de plan de pago.
    private void jtPlanDePagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPlanDePagoMouseClicked
        try { this.limpiarControles(); this.jtCuota.clearSelection();
            
            int index = jtPlanDePago.getSelectedRow();
            int purchase_id = Integer.parseInt(this.modeloPlanDePago.getValueAt(index, 0).toString());
            
            this.mostrarCuotas(purchase_id); this.mostrarMoras(purchase_id); this.mostrarAvances(purchase_id);
        } catch(Exception ex){}
    }//GEN-LAST:event_jtPlanDePagoMouseClicked

    // Selección de cuota en plan de pago.
    private void jtCuotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCuotaMouseClicked
        try { this.limpiarControles(); this.btnNuevoPago.setEnabled(true);
            
            int index = jtCuota.getSelectedRow();
            int purchase_payment_id = Integer.parseInt(this.modeloCuota.getValueAt(index, 0).toString());
          
            //Mostrando detalles para pago de cuota.
            this.mostrarDetallesCuota(purchase_payment_id);
        } catch(Exception ex){}
    }//GEN-LAST:event_jtCuotaMouseClicked

    // Filtro de planes de pago por estado: general.
    private void chkGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGeneralActionPerformed
        this.mostrarPlanesDePago(0, "", ""); this.limpiarSeleccion(); 
    }//GEN-LAST:event_chkGeneralActionPerformed

    // Filtro de planes de pago por estado: pendiente.
    private void chkPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPendienteActionPerformed
        this.mostrarPlanesDePago(1, "", ""); this.limpiarSeleccion(); 
    }//GEN-LAST:event_chkPendienteActionPerformed

    // Filtro de planes de pago por estado: cancelado
    private void chkCanceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCanceladoActionPerformed
        this.mostrarPlanesDePago(2, "", ""); this.limpiarSeleccion(); 
    }//GEN-LAST:event_chkCanceladoActionPerformed

    // Filtro de planes de pago por estado: en mora
    private void chkMoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMoraActionPerformed
        this.mostrarPlanesDePago(3, "", ""); this.limpiarSeleccion(); 
    }//GEN-LAST:event_chkMoraActionPerformed

    // 1. Mostrar pplanes de pago.
    public final void mostrarPlanesDePago(int status_filter, String filter, String expression) {
        try { this.lPlanDePago = dba.getPlanesDePago(status_filter, filter, expression);

            //Limpiando tabla.
            modeloPlanDePago.getDataVector().removeAllElements();
            modeloPlanDePago.fireTableDataChanged();

            //Mostrando en tabla.
            for(int i = 0; i < this.lPlanDePago.size(); i++)
            {
                String[] registroP = {
                    Integer.toString(this.lPlanDePago.get(i).getPurchase_id()),
                    this.lPlanDePago.get(i).getSupplier(),
                    this.lPlanDePago.get(i).getPurchaseDate().toString(),
                    this.lPlanDePago.get(i).getStatus(),
                    String.format("$ %.2f", this.lPlanDePago.get(i).getTotal()),
                    String.format("$ %.2f", this.lPlanDePago.get(i).getAmount_paid()),
                    String.format("$ %.2f", this.lPlanDePago.get(i).getRemaining_amount()),
                    Integer.toString(this.lPlanDePago.get(i).getNumber_of_payments()),
                    String.format("%.2f", this.lPlanDePago.get(i).getLate_fee_percentage_charge() * 100) + " %",
                    String.format("$ %.2f", this.lPlanDePago.get(i).getLate_fee_total()),
                    String.format("$ %.2f", this.lPlanDePago.get(i).getLate_fee_paid_amount())
                };

                //Agregando a tabla.
                modeloPlanDePago.addRow(registroP);
            }
            
        } catch(Exception ex){}
    }
    
    // 2. Mostrar cuotas para plan de pago seleccionado.
    public void mostrarCuotas(int purchase_id) {
        try { this.lCuota = dba.getCuotas(purchase_id);

            //Limpiando tabla.
            modeloCuota.getDataVector().removeAllElements();
            modeloCuota.fireTableDataChanged();

            //Mostrando en tabla.
            for(int i = 0; i < this.lCuota.size(); i++)
            { String payment_date = "No cancelado";
                
                //Cuota ya ha sido cancelada.
                if (!(this.lCuota.get(i).getPayment_date()== null)) {
                    payment_date = this.sdf_timestamp.format(this.lCuota.get(i).getPayment_date());
                }
                
                String[] registroC = {
                    Integer.toString(this.lCuota.get(i).getPurchase_payment_id()),
                    this.lCuota.get(i).getStatus(),
                    String.format("$ %.2f",this.lCuota.get(i).getTotal()),
                    String.format("$ %.2f",this.lCuota.get(i).getPaid_amount()),
                    String.format("$ %.2f",this.lCuota.get(i).getLateFeeCharge()),
                    this.lCuota.get(i).getDue_date().toString(), payment_date
                };

                //Agregando a tabla.
                modeloCuota.addRow(registroC);
            }
            
            jtCuota.getColumnModel().getColumn(1).setCellRenderer(new StatusColumnCellRenderer());
        } catch(Exception ex){System.out.println(ex);}
    }
    
    // 3. Mostrar detalles de pago para cuota seleccionada.
    public void mostrarDetallesCuota(int purchase_payment_id) {
        try { dba.conectar();

            //Sentencia SQL de la consulta
            String consulta = "SELECT * FROM getPurchasePaymentDetails('"+purchase_payment_id+"');";

            //Obteniendo ResultSet.
            ResultSet rs = dba.ejecutarConsulta(consulta);

            //Mostrando contenido del ResultSet.
            while(rs.next())
            {
                this.txtIdCompra.setText(Integer.toString(rs.getInt("purchase_id"))); 
                this.txtIdPago.setText(Integer.toString(purchase_payment_id)); 
                this.txtMontoCuota.setText(String.format("$ %.2f",rs.getDouble("remaining_amount_to_pay")));
                this.txtMontoMora.setText(String.format("$ %.2f",rs.getDouble("late_fee_charge"))); 
                this.txtTotalCuota.setText(String.format("$ %.2f",rs.getDouble("final_total")));
                this.jdFechaPago.setDate(rs.getDate("new_payment_date"));
                this.txtNumPago.setText(Integer.toString(jtCuota.getSelectedRow()+1)); 
                
                //Cuota cancelada.
                if (rs.getDouble("remaining_amount_to_pay") == 0.0) {
                    this.jdFechaPago.setDate(null); this.bloquearControles();
                }
            }

            dba.cerrarConexion();
            
        } catch(Exception ex){}
    }
    
    // 4. Mostrar lista de moras para plan de pago seleccionado.
    public void mostrarMoras(int purchase_id) {
        try { this.lMoraCuota = dba.getMoraDeCuotas(purchase_id);

            //Limpiando tabla.
            modeloMora.getDataVector().removeAllElements();
            modeloMora.fireTableDataChanged();

            //Mostrando en tabla.
            for(int i = 0; i < this.lMoraCuota.size(); i++)
            { String payment_date = "No cancelado";
                
                //Mora ya ha sido cancelada.
                if (!(this.lMoraCuota.get(i).getPayment_date()==null)) {
                    payment_date = this.sdf_timestamp.format(this.lMoraCuota.get(i).getPayment_date());
                }
                
                String[] registroM = {
                    Integer.toString(this.lMoraCuota.get(i).getPurchase_payment_id()),
                    this.lMoraCuota.get(i).getStatus(),
                    String.format("$ %.2f",this.lMoraCuota.get(i).getTotal()),
                    String.format("$ %.2f",this.lMoraCuota.get(i).getPaid_amount()),
                    payment_date
                };

                //Agregando a tabla.
                modeloMora.addRow(registroM);
            }
            
        } catch(Exception ex){System.out.println(ex);}
    }
    
    // 5. Mostrar lista de avances de pago para plan de pago seleccionado.
    public void mostrarAvances(int purchase_id) {
        try { this.lAvancePago = dba.getAvancesDePago(purchase_id);

            //Limpiando tabla.
            modeloAvance.getDataVector().removeAllElements();
            modeloAvance.fireTableDataChanged();

            //Mostrando en tabla.
            for(int i = 0; i < this.lAvancePago.size(); i++)
            {

                String[] registroA = {
                    Integer.toString(this.lAvancePago.get(i).getPurchase_payment_id()),
                    String.format("$ %.2f",this.lAvancePago.get(i).getPaid_amount()),
                    this.sdf_timestamp.format(this.lAvancePago.get(i).getUpfront_payment_date())
                };

                //Agregando a tabla.
                modeloAvance.addRow(registroA);
            }
            
        } catch(Exception ex){System.out.println(ex);}
    }
    
    //Validaciones de texto.
    public boolean verificarInput() {
        
        double amount = 0;
        for (int i = 0; i < lCuota.size(); i++) {
            if (lCuota.get(i).getPayment_status_id() == 1) { 
                amount = amount + (this.lCuota.get(i).getTotal() - 
                this.lCuota.get(i).getPaid_amount());
            }
        }
            
        if (txtMontoAbono.getText().trim().isEmpty() || 
            !(Pattern.matches("^(0*[1-9][0-9]*(\\.[0-9]*)?|0*\\.[0-9]*[1-9][0-9]*)$", txtMontoAbono.getText()))) { 
            JOptionPane.showMessageDialog(null, "Transacción cancelada: Ingrese un monto de abono válido",
            "Registro de pago a cuotas", JOptionPane.WARNING_MESSAGE);
            return true;
        }
            
        else if (Double.parseDouble(txtMontoAbono.getText()) < 
            Double.parseDouble(this.txtTotalCuota.getText().substring(2))) {
            JOptionPane.showMessageDialog(null, "Transacción cancelada: El monto ingresado no es "
            + "suficiente para realizar esta transacción",
            "Registro de pago a cuotas", JOptionPane.WARNING_MESSAGE);
            return true;
        }

        for (int i = 0; i < Integer.parseInt(txtNumPago.getText())-1; i++) {
            if (this.lCuota.get(i).getPayment_status_id() == 1) {
            JOptionPane.showMessageDialog(null, "Transacción cancelada: Existen pagos pendientes "
            + "disponibles anteriores a esta cuota",
            "Registro de pago a cuotas", JOptionPane.WARNING_MESSAGE);
            return true;
            }
        }

        if ((lCuota.get(Integer.parseInt(this.txtNumPago.getText())-1).getPayment_status_id() == 1)
            && (Double.parseDouble(txtMontoAbono.getText()) > amount)) {
            JOptionPane.showMessageDialog(null, "Transacción cancelada: El monto ingresado supera "
            + "el total planificado para cuotas pendientes",
            "Registro de pago a cuotas", JOptionPane.WARNING_MESSAGE);
            this.txtBusqueda.setText(" " + amount);
            return true;
        }

        else if ((lCuota.get(Integer.parseInt(this.txtNumPago.getText())-1).getPayment_status_id() == 3)
            && (Double.parseDouble(txtMontoAbono.getText()) > (amount +
            Double.parseDouble(this.txtTotalCuota.getText().substring(2))))) {
            JOptionPane.showMessageDialog(null, "Transacción cancelada: El monto ingresado supera "
            + "supera el total planificado para este plan de pago",
            "Registro de pago a cuotas", JOptionPane.WARNING_MESSAGE);
            this.txtBusqueda.setText(" " + amount);
            return true;
        }
        
        return false;
    }
     
    //Monto de abono - txt Max length.
    private void txtMontoAbonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAbonoKeyReleased
        if (txtMontoAbono.getText().length() > 10) {
            int res = txtMontoAbono.getText().length() - 10;
            txtMontoAbono.setText(txtMontoAbono.getText().substring(0, txtMontoAbono.getText().length()-res));
        }
    }//GEN-LAST:event_txtMontoAbonoKeyReleased

    //Expresión de búsqueda - txt Max length.
    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        if (txtBusqueda.getText().length() > 15) {
            int res = txtBusqueda.getText().length() - 15;
            txtBusqueda.setText(txtBusqueda.getText().substring(0, txtBusqueda.getText().length()-res));
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    // Método para bloquear controles en formulario.
    public void bloquearControles() { 
        this.txtMontoAbono.setEnabled(false); this.btnNuevoPago.setEnabled(false); 
        this.btnRealizarPago.setEnabled(false);
    }
    
    //Método para limpiar datos de controles en formulario.
    public void limpiarControles() {
        this.bloquearControles();
        this.txtIdCompra.setText(""); this.txtIdPago.setText("");
        this.txtNumPago.setText(""); this.txtMontoAbono.setText("");
        this.txtMontoCuota.setText(""); this.txtMontoMora.setText("");
        this.txtTotalCuota.setText(""); this.jdFechaPago.setDate(null);
        this.txtBusqueda.setText("");
    }
    
    //Método para limpiar selección de controles en formulario.
    public void limpiarSeleccion() {
        this.limpiarControles(); this.bloquearControles();
        this.jtPlanDePago.clearSelection(); this.jtCuota.clearSelection();
        modeloAvance.getDataVector().removeAllElements(); modeloAvance.fireTableDataChanged();
        modeloMora.getDataVector().removeAllElements(); modeloMora.fireTableDataChanged();
        modeloCuota.getDataVector().removeAllElements(); modeloCuota.fireTableDataChanged();
    }
    
    //Método para centrar celdas y titulos de tabla.
    public final void centrarCeldas(JTable jt, int columns) {
        
        //Centrando headers.
        TableCellRenderer rendererFromHeader = jt.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader; headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //Centrando celdas.
        centerRenderer = new DefaultTableCellRenderer(); centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columns; i++) { jt.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); }     
    }
    
    //Cell renderer tabla cuotas.
    public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            
            //Cells are by default rendered as a JLabel.
            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

            //Get the status for the current row.
            if (value.equals("En mora")) {
                l.setHorizontalAlignment(JLabel.CENTER); l.setForeground(new Color(169,15,25));
            } 
            else if (value.equals("Cancelado")) {
                l.setHorizontalAlignment(JLabel.CENTER); l.setForeground(new Color(12,90,63));
            } 
            else { l.setHorizontalAlignment(JLabel.CENTER); l.setForeground(new Color(0,0,0)); }

            if (isSelected) { l.setForeground(new Color(255,255,255));}
            return l; //Return the JLabel which renders the cell.
        }
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPago_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPago_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPago_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPago_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPago_proveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevoPago;
    private javax.swing.JButton btnRealizarPago;
    private javax.swing.JButton btnReiniciarBusqueda;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox chkCancelado;
    private javax.swing.JCheckBox chkGeneral;
    private javax.swing.JCheckBox chkMora;
    private javax.swing.JCheckBox chkPendiente;
    private javax.swing.JCheckBox chk_fecha_compra;
    private javax.swing.JCheckBox chk_id_compra;
    private javax.swing.JCheckBox chk_proveedor;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jbpPrincipal;
    private com.toedter.calendar.JDateChooser jdFechaPago;
    private javax.swing.JPanel jpBusquedas;
    private javax.swing.JPanel jpDetallePago;
    private javax.swing.JPanel jpDetallesPago;
    private javax.swing.JPanel jpInfo;
    private javax.swing.JPanel jpPagos;
    private javax.swing.JPanel jpTituloPagos1;
    private javax.swing.JTable jtAvance;
    private javax.swing.JTable jtCuota;
    private javax.swing.JTable jtMora;
    private javax.swing.JTable jtPlanDePago;
    private javax.swing.JLabel lblBusqueda;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaPago;
    private javax.swing.JLabel lblIdCompra;
    private javax.swing.JLabel lblIdCompra1;
    private javax.swing.JLabel lblIdCompra2;
    private javax.swing.JLabel lblIdPago;
    private javax.swing.JLabel lblMontoAbono;
    private javax.swing.JLabel lblMontoCuota;
    private javax.swing.JLabel lblMontoMora;
    private javax.swing.JLabel lblNumPago;
    private javax.swing.JLabel lblTituloPagosProveedores1;
    private javax.swing.JLabel lblTotalCuota;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtIdCompra;
    private javax.swing.JTextField txtIdPago;
    private javax.swing.JTextField txtMontoAbono;
    private javax.swing.JTextField txtMontoCuota;
    private javax.swing.JTextField txtMontoMora;
    private javax.swing.JTextField txtNumPago;
    private javax.swing.JTextField txtTotalCuota;
    // End of variables declaration//GEN-END:variables
}
