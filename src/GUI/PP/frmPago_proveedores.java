package Formularios;

public class frmPago_proveedores extends javax.swing.JFrame {

    public frmPago_proveedores() {
        initComponents();
        //Modificando Width - Tablas.
        jtConsultaPagos.getColumnModel().getColumn(3).setPreferredWidth(120);  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        jbpPrincipal = new javax.swing.JTabbedPane();
        jpPagos = new javax.swing.JPanel();
        jpTituloPagos1 = new javax.swing.JPanel();
        lblTituloPagosProveedores1 = new javax.swing.JLabel();
        jpBusquedas = new javax.swing.JPanel();
        lblBusqueda = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        chkId_orden = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        btnReiniciarBusqueda = new javax.swing.JButton();
        chkId_proveedor = new javax.swing.JCheckBox();
        chkFechaOrden = new javax.swing.JCheckBox();
        lblEstado = new javax.swing.JLabel();
        chkCancelado = new javax.swing.JCheckBox();
        chkPendiente = new javax.swing.JCheckBox();
        jpAcciones = new javax.swing.JPanel();
        btnNuevoPago = new javax.swing.JButton();
        btnGuardarRegistro = new javax.swing.JButton();
        btnLimpiarCampos = new javax.swing.JButton();
        jpIngresoDatos = new javax.swing.JPanel();
        txtIdOrden = new javax.swing.JTextField();
        lblIdOrden = new javax.swing.JLabel();
        lblFechaPago = new javax.swing.JLabel();
        lblAbono = new javax.swing.JLabel();
        txtAbono = new javax.swing.JTextField();
        txtMora = new javax.swing.JTextField();
        lblMora = new javax.swing.JLabel();
        txtNumPago = new javax.swing.JTextField();
        lblNumPago = new javax.swing.JLabel();
        txtTotalAbono = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtIdPago = new javax.swing.JTextField();
        lblIdPago = new javax.swing.JLabel();
        jdFechaPago = new com.toedter.calendar.JDateChooser();
        jpDetallesPago = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtMonto = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtPago = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtOrden = new javax.swing.JTable();
        jpConsultas = new javax.swing.JPanel();
        btnIrAConsultaDePagos = new javax.swing.JButton();
        jpConsultaPagos = new javax.swing.JPanel();
        jpTituloPagos2 = new javax.swing.JPanel();
        lblTituloPagosProveedores2 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtConsultaPagos = new javax.swing.JTable();
        jpBusquedas1 = new javax.swing.JPanel();
        txtBusquedaPagosMax = new javax.swing.JTextField();
        btnBuscarPagos1 = new javax.swing.JButton();
        btnReiniciarBusquedaPagos1 = new javax.swing.JButton();
        lblMax = new javax.swing.JLabel();
        lblMin = new javax.swing.JLabel();
        txtBusquedaPagosMin = new javax.swing.JTextField();
        lblBusqueda2 = new javax.swing.JLabel();
        chkTotal = new javax.swing.JCheckBox();
        chkMontoPendiente = new javax.swing.JCheckBox();
        chkAbonado = new javax.swing.JCheckBox();
        chkAbono = new javax.swing.JCheckBox();
        chkMora = new javax.swing.JCheckBox();
        jpInfoPagos = new javax.swing.JPanel();
        txtxTotalCancelado = new javax.swing.JTextField();
        lblTotalCancelado = new javax.swing.JLabel();
        txtTotalPendiente = new javax.swing.JTextField();
        lblTotalPendiente = new javax.swing.JLabel();
        txtTotalMoraCancelada = new javax.swing.JTextField();
        lblTotalMoraCancelada = new javax.swing.JLabel();
        txtTotalMoraPendiente = new javax.swing.JTextField();
        lblMoraPendiente = new javax.swing.JLabel();
        jpBusquedas2 = new javax.swing.JPanel();
        chkId_proveedor1 = new javax.swing.JCheckBox();
        chkFechaExp1 = new javax.swing.JCheckBox();
        chkFechaOrden1 = new javax.swing.JCheckBox();
        lblBusqueda1 = new javax.swing.JLabel();
        txtBusquedaPagos = new javax.swing.JTextField();
        chkId_orden1 = new javax.swing.JCheckBox();
        btnBuscarPagos = new javax.swing.JButton();
        btnReiniciarBusquedaPagos = new javax.swing.JButton();
        chkId_pago = new javax.swing.JCheckBox();
        chkFechaPago = new javax.swing.JCheckBox();
        jpBusquedas3 = new javax.swing.JPanel();
        lblEstado1 = new javax.swing.JLabel();
        chkCancelado1 = new javax.swing.JCheckBox();
        chkPendiente1 = new javax.swing.JCheckBox();
        btnIrAPagoProveedores1 = new javax.swing.JButton();

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
        lblTituloPagosProveedores1.setText("Pago a proveedores: Consulta de planes de pago  y  registro de pagos");

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

        chkId_orden.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(chkId_orden);
        chkId_orden.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkId_orden.setSelected(true);
        chkId_orden.setText("ID orden");
        chkId_orden.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkId_orden.setFocusable(false);

        btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/PP/search.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusable(false);

        btnReiniciarBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        btnReiniciarBusqueda.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnReiniciarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/PP/reset.png"))); // NOI18N
        btnReiniciarBusqueda.setToolTipText("Reiniciar búsqueda");
        btnReiniciarBusqueda.setBorderPainted(false);
        btnReiniciarBusqueda.setContentAreaFilled(false);
        btnReiniciarBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReiniciarBusqueda.setFocusable(false);
        btnReiniciarBusqueda.setPreferredSize(new java.awt.Dimension(57, 32));

        chkId_proveedor.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(chkId_proveedor);
        chkId_proveedor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkId_proveedor.setText("ID proveedor");
        chkId_proveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkId_proveedor.setFocusable(false);

        chkFechaOrden.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(chkFechaOrden);
        chkFechaOrden.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkFechaOrden.setText("Fecha de orden");
        chkFechaOrden.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkFechaOrden.setFocusable(false);

        lblEstado.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblEstado.setText("Estado:");

        chkCancelado.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(chkCancelado);
        chkCancelado.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkCancelado.setText("Cancelado");
        chkCancelado.setToolTipText("");
        chkCancelado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkCancelado.setFocusable(false);

        chkPendiente.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(chkPendiente);
        chkPendiente.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkPendiente.setSelected(true);
        chkPendiente.setText("Pendiente");
        chkPendiente.setToolTipText("");
        chkPendiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkPendiente.setFocusable(false);

        javax.swing.GroupLayout jpBusquedasLayout = new javax.swing.GroupLayout(jpBusquedas);
        jpBusquedas.setLayout(jpBusquedasLayout);
        jpBusquedasLayout.setHorizontalGroup(
            jpBusquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCancelado)
                .addGap(6, 6, 6)
                .addComponent(chkPendiente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(lblBusqueda)
                .addGap(18, 18, 18)
                .addComponent(chkId_orden)
                .addGap(15, 15, 15)
                .addComponent(chkId_proveedor)
                .addGap(15, 15, 15)
                .addComponent(chkFechaOrden)
                .addGap(146, 146, 146)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReiniciarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpBusquedasLayout.setVerticalGroup(
            jpBusquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpBusquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkId_orden)
                    .addComponent(chkId_proveedor)
                    .addComponent(chkFechaOrden)
                    .addComponent(lblBusqueda)
                    .addComponent(lblEstado)
                    .addComponent(chkCancelado)
                    .addComponent(chkPendiente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnReiniciarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpAcciones.setBackground(new java.awt.Color(226, 226, 226));

        btnNuevoPago.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevoPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnNuevoPago.setText("Nuevo pago");
        btnNuevoPago.setToolTipText("");
        btnNuevoPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoPago.setEnabled(false);
        btnNuevoPago.setFocusPainted(false);

        btnGuardarRegistro.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardarRegistro.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnGuardarRegistro.setText("Guardar registro");
        btnGuardarRegistro.setToolTipText("");
        btnGuardarRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarRegistro.setEnabled(false);
        btnGuardarRegistro.setFocusPainted(false);

        btnLimpiarCampos.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiarCampos.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnLimpiarCampos.setText("Limpiar campos");
        btnLimpiarCampos.setToolTipText("");
        btnLimpiarCampos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarCampos.setEnabled(false);
        btnLimpiarCampos.setFocusPainted(false);

        javax.swing.GroupLayout jpAccionesLayout = new javax.swing.GroupLayout(jpAcciones);
        jpAcciones.setLayout(jpAccionesLayout);
        jpAccionesLayout.setHorizontalGroup(
            jpAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarRegistro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiarCampos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpAccionesLayout.setVerticalGroup(
            jpAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAccionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnNuevoPago)
                    .addComponent(btnGuardarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiarCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpIngresoDatos.setBackground(new java.awt.Color(226, 226, 226));

        txtIdOrden.setEditable(false);
        txtIdOrden.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtIdOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblIdOrden.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblIdOrden.setText("ID de compra:");
        lblIdOrden.setToolTipText("");

        lblFechaPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblFechaPago.setText("Fecha de pago:");
        lblFechaPago.setToolTipText("");

        lblAbono.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblAbono.setText("Monto:");
        lblAbono.setToolTipText("");

        txtAbono.setEditable(false);
        txtAbono.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtAbono.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtMora.setEditable(false);
        txtMora.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtMora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblMora.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMora.setForeground(new java.awt.Color(255, 0, 51));
        lblMora.setText("Mora:");
        lblMora.setToolTipText("");

        txtNumPago.setEditable(false);
        txtNumPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNumPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblNumPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNumPago.setText("Número de pago:");
        lblNumPago.setToolTipText("");

        txtTotalAbono.setEditable(false);
        txtTotalAbono.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtTotalAbono.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblTotal.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblTotal.setText("Total:");
        lblTotal.setToolTipText("");

        txtIdPago.setEditable(false);
        txtIdPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtIdPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblIdPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblIdPago.setText("ID de pago:");
        lblIdPago.setToolTipText("");

        jdFechaPago.setDateFormatString("dd/MM/yyyy");
        jdFechaPago.setEnabled(false);
        jdFechaPago.setFocusCycleRoot(true);
        jdFechaPago.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jdFechaPago.setPreferredSize(new java.awt.Dimension(101, 24));

        javax.swing.GroupLayout jpIngresoDatosLayout = new javax.swing.GroupLayout(jpIngresoDatos);
        jpIngresoDatos.setLayout(jpIngresoDatosLayout);
        jpIngresoDatosLayout.setHorizontalGroup(
            jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                        .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(lblAbono)))
                        .addGap(18, 18, 18)
                        .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMora, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(lblMora)))
                        .addGap(18, 18, 18)
                        .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblFechaPago))
                    .addComponent(jdFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                        .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(lblIdOrden)
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpIngresoDatosLayout.createSequentialGroup()
                                .addComponent(txtIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpIngresoDatosLayout.createSequentialGroup()
                                .addComponent(lblIdPago)
                                .addGap(45, 45, 45)))
                        .addGap(18, 18, 18)
                        .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblNumPago))
                            .addComponent(txtNumPago, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpIngresoDatosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAbono, txtIdOrden, txtIdPago, txtMora, txtNumPago, txtTotalAbono});

        jpIngresoDatosLayout.setVerticalGroup(
            jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpIngresoDatosLayout.createSequentialGroup()
                        .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIdPago, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblNumPago, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMora, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpIngresoDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMora, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jdFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        jpIngresoDatosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtAbono, txtIdOrden, txtIdPago, txtMora, txtNumPago, txtTotalAbono});

        jpDetallesPago.setBackground(new java.awt.Color(226, 226, 226));

        jtMonto.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jtMonto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Total pendiente", "Total abonado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtMonto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtMonto.setFocusable(false);
        jtMonto.setName(""); // NOI18N
        jtMonto.setRowHeight(30);
        jtMonto.setRowSelectionAllowed(false);
        jtMonto.setSelectionBackground(new java.awt.Color(0, 153, 102));
        jtMonto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtMonto.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jtMonto);
        if (jtMonto.getColumnModel().getColumnCount() > 0) {
            jtMonto.getColumnModel().getColumn(0).setResizable(false);
            jtMonto.getColumnModel().getColumn(1).setResizable(false);
        }

        jtPago.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jtPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID de pago", "Estado", "Total", "Abonado", "Fecha exp.", "Mora", "Fecha pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPago.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtPago.setFocusable(false);
        jtPago.setName(""); // NOI18N
        jtPago.setRowHeight(30);
        jtPago.setRowSelectionAllowed(false);
        jtPago.setSelectionBackground(new java.awt.Color(0, 153, 102));
        jtPago.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtPago.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jtPago);
        if (jtPago.getColumnModel().getColumnCount() > 0) {
            jtPago.getColumnModel().getColumn(0).setResizable(false);
            jtPago.getColumnModel().getColumn(1).setResizable(false);
            jtPago.getColumnModel().getColumn(2).setResizable(false);
            jtPago.getColumnModel().getColumn(3).setResizable(false);
            jtPago.getColumnModel().getColumn(4).setResizable(false);
            jtPago.getColumnModel().getColumn(5).setResizable(false);
            jtPago.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jpDetallesPagoLayout = new javax.swing.GroupLayout(jpDetallesPago);
        jpDetallesPago.setLayout(jpDetallesPagoLayout);
        jpDetallesPagoLayout.setHorizontalGroup(
            jpDetallesPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDetallesPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDetallesPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jpDetallesPagoLayout.setVerticalGroup(
            jpDetallesPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetallesPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtOrden.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jtOrden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID de compra", "ID de proveedor", "Fecha de orden", "Estado", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtOrden.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtOrden.setFocusable(false);
        jtOrden.setName(""); // NOI18N
        jtOrden.setRowHeight(30);
        jtOrden.setSelectionBackground(new java.awt.Color(0, 153, 102));
        jtOrden.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtOrden.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jtOrden);
        if (jtOrden.getColumnModel().getColumnCount() > 0) {
            jtOrden.getColumnModel().getColumn(0).setResizable(false);
            jtOrden.getColumnModel().getColumn(1).setResizable(false);
            jtOrden.getColumnModel().getColumn(2).setResizable(false);
            jtOrden.getColumnModel().getColumn(3).setResizable(false);
            jtOrden.getColumnModel().getColumn(4).setResizable(false);
        }

        btnIrAConsultaDePagos.setBackground(new java.awt.Color(255, 255, 255));
        btnIrAConsultaDePagos.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnIrAConsultaDePagos.setText("Consulta de pagos");
        btnIrAConsultaDePagos.setToolTipText("");
        btnIrAConsultaDePagos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIrAConsultaDePagos.setFocusPainted(false);
        btnIrAConsultaDePagos.setPreferredSize(new java.awt.Dimension(168, 26));
        btnIrAConsultaDePagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrAConsultaDePagosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpConsultasLayout = new javax.swing.GroupLayout(jpConsultas);
        jpConsultas.setLayout(jpConsultasLayout);
        jpConsultasLayout.setHorizontalGroup(
            jpConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIrAConsultaDePagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpConsultasLayout.setVerticalGroup(
            jpConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIrAConsultaDePagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpPagosLayout = new javax.swing.GroupLayout(jpPagos);
        jpPagos.setLayout(jpPagosLayout);
        jpPagosLayout.setHorizontalGroup(
            jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpBusquedas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpPagosLayout.createSequentialGroup()
                        .addGroup(jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpPagosLayout.createSequentialGroup()
                                .addComponent(jpConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane4)
                            .addComponent(jpIngresoDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpDetallesPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jpTituloPagos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpPagosLayout.setVerticalGroup(
            jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpTituloPagos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpBusquedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpPagosLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpIngresoDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpDetallesPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 44, Short.MAX_VALUE))
        );

        jbpPrincipal.addTab("Pagos", jpPagos);

        jpConsultaPagos.setBackground(new java.awt.Color(251, 251, 251));

        jpTituloPagos2.setBackground(new java.awt.Color(76, 101, 150));

        lblTituloPagosProveedores2.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        lblTituloPagosProveedores2.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPagosProveedores2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTituloPagosProveedores2.setText("Pago a proveedores: Consulta de pagos");

        javax.swing.GroupLayout jpTituloPagos2Layout = new javax.swing.GroupLayout(jpTituloPagos2);
        jpTituloPagos2.setLayout(jpTituloPagos2Layout);
        jpTituloPagos2Layout.setHorizontalGroup(
            jpTituloPagos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloPagos2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTituloPagosProveedores2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTituloPagos2Layout.setVerticalGroup(
            jpTituloPagos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTituloPagosProveedores2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jtConsultaPagos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jtConsultaPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID de orden", "ID de proveedor", "Fecha de orden", "Fecha de expiración", "Estado", "ID de pago", "Abono", "Mora", "Fecha de pago", "Total abonado", "Total pendiente", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtConsultaPagos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtConsultaPagos.setFocusable(false);
        jtConsultaPagos.setName(""); // NOI18N
        jtConsultaPagos.setRowHeight(30);
        jtConsultaPagos.setSelectionBackground(new java.awt.Color(0, 153, 102));
        jtConsultaPagos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtConsultaPagos.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(jtConsultaPagos);
        if (jtConsultaPagos.getColumnModel().getColumnCount() > 0) {
            jtConsultaPagos.getColumnModel().getColumn(0).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(1).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(2).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(3).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(4).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(5).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(6).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(7).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(8).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(9).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(10).setResizable(false);
            jtConsultaPagos.getColumnModel().getColumn(11).setResizable(false);
        }

        jpBusquedas1.setBackground(new java.awt.Color(226, 226, 226));

        txtBusquedaPagosMax.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtBusquedaPagosMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnBuscarPagos1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarPagos1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnBuscarPagos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/PP/search.png"))); // NOI18N
        btnBuscarPagos1.setToolTipText("Buscar");
        btnBuscarPagos1.setBorderPainted(false);
        btnBuscarPagos1.setContentAreaFilled(false);
        btnBuscarPagos1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPagos1.setFocusable(false);

        btnReiniciarBusquedaPagos1.setBackground(new java.awt.Color(255, 255, 255));
        btnReiniciarBusquedaPagos1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnReiniciarBusquedaPagos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/PP/reset.png"))); // NOI18N
        btnReiniciarBusquedaPagos1.setToolTipText("Reiniciar búsqueda");
        btnReiniciarBusquedaPagos1.setBorderPainted(false);
        btnReiniciarBusquedaPagos1.setContentAreaFilled(false);
        btnReiniciarBusquedaPagos1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReiniciarBusquedaPagos1.setFocusable(false);
        btnReiniciarBusquedaPagos1.setPreferredSize(new java.awt.Dimension(57, 32));

        lblMax.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMax.setText("Max:");

        lblMin.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMin.setText("Min:");

        txtBusquedaPagosMin.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtBusquedaPagosMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblBusqueda2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblBusqueda2.setText("Búsqueda por:");

        chkTotal.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup5.add(chkTotal);
        chkTotal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkTotal.setText("Total");
        chkTotal.setToolTipText("");
        chkTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkTotal.setFocusable(false);

        chkMontoPendiente.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup5.add(chkMontoPendiente);
        chkMontoPendiente.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkMontoPendiente.setText("Tot. Pendiente");
        chkMontoPendiente.setToolTipText("");
        chkMontoPendiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkMontoPendiente.setFocusable(false);

        chkAbonado.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup5.add(chkAbonado);
        chkAbonado.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkAbonado.setText("Tot. abonado");
        chkAbonado.setToolTipText("");
        chkAbonado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkAbonado.setFocusable(false);

        chkAbono.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup5.add(chkAbono);
        chkAbono.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkAbono.setText("Abono");
        chkAbono.setToolTipText("");
        chkAbono.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkAbono.setFocusable(false);

        chkMora.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup5.add(chkMora);
        chkMora.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkMora.setText("Mora");
        chkMora.setToolTipText("");
        chkMora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkMora.setFocusable(false);

        javax.swing.GroupLayout jpBusquedas1Layout = new javax.swing.GroupLayout(jpBusquedas1);
        jpBusquedas1.setLayout(jpBusquedas1Layout);
        jpBusquedas1Layout.setHorizontalGroup(
            jpBusquedas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedas1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblBusqueda2)
                .addGap(18, 18, 18)
                .addComponent(chkTotal)
                .addGap(12, 12, 12)
                .addComponent(chkMontoPendiente)
                .addGap(12, 12, 12)
                .addComponent(chkAbonado)
                .addGap(12, 12, 12)
                .addComponent(chkAbono)
                .addGap(12, 12, 12)
                .addComponent(chkMora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBusquedaPagosMin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusquedaPagosMax, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarPagos1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReiniciarBusquedaPagos1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpBusquedas1Layout.setVerticalGroup(
            jpBusquedas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBuscarPagos1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
            .addComponent(btnReiniciarBusquedaPagos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpBusquedas1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpBusquedas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBusquedas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBusquedaPagosMin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMin)
                        .addComponent(chkMora)
                        .addComponent(chkAbono)
                        .addComponent(chkAbonado)
                        .addComponent(chkMontoPendiente))
                    .addGroup(jpBusquedas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBusquedaPagosMax, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMax)
                        .addComponent(chkTotal)
                        .addComponent(lblBusqueda2)))
                .addGap(10, 10, 10))
        );

        jpInfoPagos.setBackground(new java.awt.Color(226, 226, 226));

        txtxTotalCancelado.setEditable(false);
        txtxTotalCancelado.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtxTotalCancelado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblTotalCancelado.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblTotalCancelado.setText("Total cancelado:");
        lblTotalCancelado.setToolTipText("");

        txtTotalPendiente.setEditable(false);
        txtTotalPendiente.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtTotalPendiente.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblTotalPendiente.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblTotalPendiente.setText("Total pendiente:");
        lblTotalPendiente.setToolTipText("");

        txtTotalMoraCancelada.setEditable(false);
        txtTotalMoraCancelada.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtTotalMoraCancelada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblTotalMoraCancelada.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblTotalMoraCancelada.setText("Total de mora cancelada:");
        lblTotalMoraCancelada.setToolTipText("");

        txtTotalMoraPendiente.setEditable(false);
        txtTotalMoraPendiente.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtTotalMoraPendiente.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblMoraPendiente.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMoraPendiente.setText("Total de mora pendiente:");
        lblMoraPendiente.setToolTipText("");

        javax.swing.GroupLayout jpInfoPagosLayout = new javax.swing.GroupLayout(jpInfoPagos);
        jpInfoPagos.setLayout(jpInfoPagosLayout);
        jpInfoPagosLayout.setHorizontalGroup(
            jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInfoPagosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtxTotalCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpInfoPagosLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lblTotalCancelado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInfoPagosLayout.createSequentialGroup()
                        .addComponent(lblTotalPendiente)
                        .addGap(52, 52, 52)))
                .addGroup(jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpInfoPagosLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblTotalMoraCancelada)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInfoPagosLayout.createSequentialGroup()
                        .addComponent(txtTotalMoraCancelada, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalMoraPendiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInfoPagosLayout.createSequentialGroup()
                        .addComponent(lblMoraPendiente)
                        .addGap(20, 20, 20)))
                .addGap(22, 22, 22))
        );
        jpInfoPagosLayout.setVerticalGroup(
            jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInfoPagosLayout.createSequentialGroup()
                .addGroup(jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpInfoPagosLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(txtTotalMoraCancelada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpInfoPagosLayout.createSequentialGroup()
                                .addGroup(jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMoraPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTotalMoraCancelada, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalMoraPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jpInfoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpInfoPagosLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtTotalPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInfoPagosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTotalCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtxTotalCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpInfoPagosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotalPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jpBusquedas2.setBackground(new java.awt.Color(226, 226, 226));

        chkId_proveedor1.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup4.add(chkId_proveedor1);
        chkId_proveedor1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkId_proveedor1.setText("ID proveedor");
        chkId_proveedor1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkId_proveedor1.setFocusable(false);

        chkFechaExp1.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup4.add(chkFechaExp1);
        chkFechaExp1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkFechaExp1.setText("Fecha de exp.");
        chkFechaExp1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkFechaExp1.setFocusable(false);

        chkFechaOrden1.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup4.add(chkFechaOrden1);
        chkFechaOrden1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkFechaOrden1.setText("Fecha de orden");
        chkFechaOrden1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkFechaOrden1.setFocusable(false);

        lblBusqueda1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblBusqueda1.setText("Búsqueda por:");

        txtBusquedaPagos.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtBusquedaPagos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        chkId_orden1.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup4.add(chkId_orden1);
        chkId_orden1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkId_orden1.setSelected(true);
        chkId_orden1.setText("ID orden");
        chkId_orden1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkId_orden1.setFocusable(false);

        btnBuscarPagos.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarPagos.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnBuscarPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/PP/search.png"))); // NOI18N
        btnBuscarPagos.setToolTipText("Buscar");
        btnBuscarPagos.setBorderPainted(false);
        btnBuscarPagos.setContentAreaFilled(false);
        btnBuscarPagos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPagos.setFocusable(false);

        btnReiniciarBusquedaPagos.setBackground(new java.awt.Color(255, 255, 255));
        btnReiniciarBusquedaPagos.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnReiniciarBusquedaPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/PP/reset.png"))); // NOI18N
        btnReiniciarBusquedaPagos.setToolTipText("Reiniciar búsqueda");
        btnReiniciarBusquedaPagos.setBorderPainted(false);
        btnReiniciarBusquedaPagos.setContentAreaFilled(false);
        btnReiniciarBusquedaPagos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReiniciarBusquedaPagos.setFocusable(false);
        btnReiniciarBusquedaPagos.setPreferredSize(new java.awt.Dimension(57, 32));

        chkId_pago.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup4.add(chkId_pago);
        chkId_pago.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkId_pago.setText("ID de pago");
        chkId_pago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkId_pago.setFocusable(false);

        chkFechaPago.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup4.add(chkFechaPago);
        chkFechaPago.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkFechaPago.setText("Fecha de pago");
        chkFechaPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkFechaPago.setFocusable(false);

        javax.swing.GroupLayout jpBusquedas2Layout = new javax.swing.GroupLayout(jpBusquedas2);
        jpBusquedas2.setLayout(jpBusquedas2Layout);
        jpBusquedas2Layout.setHorizontalGroup(
            jpBusquedas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedas2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblBusqueda1)
                .addGap(18, 18, 18)
                .addComponent(chkId_orden1)
                .addGap(15, 15, 15)
                .addComponent(chkId_proveedor1)
                .addGap(15, 15, 15)
                .addComponent(chkFechaOrden1)
                .addGap(15, 15, 15)
                .addComponent(chkFechaExp1)
                .addGap(15, 15, 15)
                .addComponent(chkId_pago)
                .addGap(15, 15, 15)
                .addComponent(chkFechaPago)
                .addGap(18, 18, 18)
                .addComponent(txtBusquedaPagos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReiniciarBusquedaPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpBusquedas2Layout.setVerticalGroup(
            jpBusquedas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedas2Layout.createSequentialGroup()
                .addGroup(jpBusquedas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBusquedas2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jpBusquedas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkId_orden1)
                            .addComponent(chkId_proveedor1)
                            .addComponent(chkFechaOrden1)
                            .addComponent(chkFechaExp1)
                            .addComponent(lblBusqueda1)
                            .addComponent(chkId_pago)
                            .addComponent(chkFechaPago)))
                    .addGroup(jpBusquedas2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtBusquedaPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnBuscarPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnReiniciarBusquedaPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpBusquedas3.setBackground(new java.awt.Color(226, 226, 226));

        lblEstado1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblEstado1.setText("Estado:");

        chkCancelado1.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup3.add(chkCancelado1);
        chkCancelado1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkCancelado1.setText("Cancelado");
        chkCancelado1.setToolTipText("");
        chkCancelado1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkCancelado1.setFocusable(false);

        chkPendiente1.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup3.add(chkPendiente1);
        chkPendiente1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chkPendiente1.setSelected(true);
        chkPendiente1.setText("Pendiente");
        chkPendiente1.setToolTipText("");
        chkPendiente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkPendiente1.setFocusable(false);

        javax.swing.GroupLayout jpBusquedas3Layout = new javax.swing.GroupLayout(jpBusquedas3);
        jpBusquedas3.setLayout(jpBusquedas3Layout);
        jpBusquedas3Layout.setHorizontalGroup(
            jpBusquedas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedas3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblEstado1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCancelado1)
                .addGap(6, 6, 6)
                .addComponent(chkPendiente1)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jpBusquedas3Layout.setVerticalGroup(
            jpBusquedas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBusquedas3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpBusquedas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado1)
                    .addComponent(chkCancelado1)
                    .addComponent(chkPendiente1))
                .addContainerGap())
        );

        btnIrAPagoProveedores1.setBackground(new java.awt.Color(255, 255, 255));
        btnIrAPagoProveedores1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnIrAPagoProveedores1.setText("Regresar a pago de proveedores");
        btnIrAPagoProveedores1.setToolTipText("");
        btnIrAPagoProveedores1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIrAPagoProveedores1.setFocusPainted(false);
        btnIrAPagoProveedores1.setPreferredSize(new java.awt.Dimension(168, 26));
        btnIrAPagoProveedores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrAPagoProveedores1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpConsultaPagosLayout = new javax.swing.GroupLayout(jpConsultaPagos);
        jpConsultaPagos.setLayout(jpConsultaPagosLayout);
        jpConsultaPagosLayout.setHorizontalGroup(
            jpConsultaPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTituloPagos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpConsultaPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConsultaPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpBusquedas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConsultaPagosLayout.createSequentialGroup()
                        .addComponent(jpBusquedas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpBusquedas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConsultaPagosLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnIrAPagoProveedores1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jpInfoPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpConsultaPagosLayout.setVerticalGroup(
            jpConsultaPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultaPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpTituloPagos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpConsultaPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpBusquedas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpBusquedas3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpBusquedas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpConsultaPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConsultaPagosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpInfoPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(446, 446, 446))
                    .addGroup(jpConsultaPagosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnIrAPagoProveedores1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jbpPrincipal.addTab("Consulta de pagos", jpConsultaPagos);

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
                .addContainerGap()
                .addComponent(jbpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 703, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1296, 665));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIrAPagoProveedores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrAPagoProveedores1ActionPerformed
        this.jbpPrincipal.setSelectedIndex(0);
    }//GEN-LAST:event_btnIrAPagoProveedores1ActionPerformed

    private void btnIrAConsultaDePagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrAConsultaDePagosActionPerformed
        this.jbpPrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_btnIrAConsultaDePagosActionPerformed

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
    private javax.swing.JButton btnBuscarPagos;
    private javax.swing.JButton btnBuscarPagos1;
    private javax.swing.JButton btnGuardarRegistro;
    private javax.swing.JButton btnIrAConsultaDePagos;
    private javax.swing.JButton btnIrAPagoProveedores1;
    private javax.swing.JButton btnLimpiarCampos;
    private javax.swing.JButton btnNuevoPago;
    private javax.swing.JButton btnReiniciarBusqueda;
    private javax.swing.JButton btnReiniciarBusquedaPagos;
    private javax.swing.JButton btnReiniciarBusquedaPagos1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JCheckBox chkAbonado;
    private javax.swing.JCheckBox chkAbono;
    private javax.swing.JCheckBox chkCancelado;
    private javax.swing.JCheckBox chkCancelado1;
    private javax.swing.JCheckBox chkFechaExp1;
    private javax.swing.JCheckBox chkFechaOrden;
    private javax.swing.JCheckBox chkFechaOrden1;
    private javax.swing.JCheckBox chkFechaPago;
    private javax.swing.JCheckBox chkId_orden;
    private javax.swing.JCheckBox chkId_orden1;
    private javax.swing.JCheckBox chkId_pago;
    private javax.swing.JCheckBox chkId_proveedor;
    private javax.swing.JCheckBox chkId_proveedor1;
    private javax.swing.JCheckBox chkMontoPendiente;
    private javax.swing.JCheckBox chkMora;
    private javax.swing.JCheckBox chkPendiente;
    private javax.swing.JCheckBox chkPendiente1;
    private javax.swing.JCheckBox chkTotal;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jbpPrincipal;
    private com.toedter.calendar.JDateChooser jdFechaPago;
    private javax.swing.JPanel jpAcciones;
    private javax.swing.JPanel jpBusquedas;
    private javax.swing.JPanel jpBusquedas1;
    private javax.swing.JPanel jpBusquedas2;
    private javax.swing.JPanel jpBusquedas3;
    private javax.swing.JPanel jpConsultaPagos;
    private javax.swing.JPanel jpConsultas;
    private javax.swing.JPanel jpDetallesPago;
    private javax.swing.JPanel jpInfoPagos;
    private javax.swing.JPanel jpIngresoDatos;
    private javax.swing.JPanel jpPagos;
    private javax.swing.JPanel jpTituloPagos1;
    private javax.swing.JPanel jpTituloPagos2;
    private javax.swing.JTable jtConsultaPagos;
    private javax.swing.JTable jtMonto;
    private javax.swing.JTable jtOrden;
    private javax.swing.JTable jtPago;
    private javax.swing.JLabel lblAbono;
    private javax.swing.JLabel lblBusqueda;
    private javax.swing.JLabel lblBusqueda1;
    private javax.swing.JLabel lblBusqueda2;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblEstado1;
    private javax.swing.JLabel lblFechaPago;
    private javax.swing.JLabel lblIdOrden;
    private javax.swing.JLabel lblIdPago;
    private javax.swing.JLabel lblMax;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblMora;
    private javax.swing.JLabel lblMoraPendiente;
    private javax.swing.JLabel lblNumPago;
    private javax.swing.JLabel lblTituloPagosProveedores1;
    private javax.swing.JLabel lblTituloPagosProveedores2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalCancelado;
    private javax.swing.JLabel lblTotalMoraCancelada;
    private javax.swing.JLabel lblTotalPendiente;
    private javax.swing.JTextField txtAbono;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtBusquedaPagos;
    private javax.swing.JTextField txtBusquedaPagosMax;
    private javax.swing.JTextField txtBusquedaPagosMin;
    private javax.swing.JTextField txtIdOrden;
    private javax.swing.JTextField txtIdPago;
    private javax.swing.JTextField txtMora;
    private javax.swing.JTextField txtNumPago;
    private javax.swing.JTextField txtTotalAbono;
    private javax.swing.JTextField txtTotalMoraCancelada;
    private javax.swing.JTextField txtTotalMoraPendiente;
    private javax.swing.JTextField txtTotalPendiente;
    private javax.swing.JTextField txtxTotalCancelado;
    // End of variables declaration//GEN-END:variables
}
