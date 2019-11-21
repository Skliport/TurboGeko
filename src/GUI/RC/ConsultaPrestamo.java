/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.RC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Eli
 */
public class ConsultaPrestamo extends Conexion {

    PreparedStatement ps = null;
    ResultSet results = null;
    Connection con = null;
    Prestamo oPres;
    
     public void agregar_moras() throws SQLException {
        try {
            con = getConnection();
            String SQL = "Select add_late_fee_function()";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            results = pstmt.executeQuery();
           
        } catch (ClassNotFoundException ex) {
            System.out.println("Error " + ex);
        }

    }

    public ArrayList<Prestamo> todosPrestamos() throws ClassNotFoundException {
        con = getConnection();
        try {
            ArrayList<Prestamo> lPres = new ArrayList<>();
            String SQL = "Select * from search_loan()";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            results = pstmt.executeQuery();
            while (results.next()) {

                oPres = new Prestamo();
                oPres.loan_number = results.getObject(1).toString();
                oPres.cus_name = results.getObject(2).toString();
                oPres.i_date = Date.valueOf(results.getObject(3).toString());
                oPres.f_date = Date.valueOf(results.getObject(4).toString());
                oPres.fee = Float.parseFloat(results.getObject(5).toString());
                oPres.pinterest = Float.parseFloat(results.getObject(6).toString());
                oPres.plate_fee = Float.parseFloat(results.getObject(7).toString());
                oPres.amount = Float.parseFloat(results.getObject(8).toString());
                oPres.id_cus = Integer.parseInt(results.getObject(9).toString());
                if (Integer.parseInt(results.getObject(9).toString()) == 0) {
                    oPres.state_name = "Pendiente";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 1) {
                    oPres.state_name = "Activo";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 2) {
                    oPres.state_name = "Mora";
                } else {
                    oPres.state_name = "Finalizado";
                }
                lPres.add(oPres);
            }
            return lPres;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error: " + e);
            return null;
        }
    }

    public ArrayList<Prestamo> prestamoXNum(String number_loan) throws ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet results = null;
        Connection con = getConnection();
        Prestamo oPres;

        try {
            ArrayList<Prestamo> lPres = new ArrayList<>();
            String SQL = "select * from filter_number_fee(?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, number_loan);
            results = pstmt.executeQuery();

            while (results.next()) {

                oPres = new Prestamo();
                oPres.loan_number = results.getObject(1).toString();
                oPres.cus_name = results.getObject(2).toString();
                oPres.i_date = Date.valueOf(results.getObject(3).toString());
                oPres.f_date = Date.valueOf(results.getObject(4).toString());
                oPres.fee = Float.parseFloat(results.getObject(5).toString());
                oPres.pinterest = Float.parseFloat(results.getObject(6).toString());
                oPres.plate_fee = Float.parseFloat(results.getObject(7).toString());
                oPres.amount = Float.parseFloat(results.getObject(8).toString());
                oPres.id_cus = Integer.parseInt(results.getObject(9).toString());
                if (Integer.parseInt(results.getObject(9).toString()) == 0) {
                    oPres.state_name = "Pendiente";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 1) {
                    oPres.state_name = "Activo";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 2) {
                    oPres.state_name = "Mora";
                } else {
                    oPres.state_name = "Finalizado";
                }
                lPres.add(oPres);
            }
            return lPres;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error: " + e);
            return null;
        }
    }

    public ArrayList<Prestamo> prestamoXCliente(String nom_cliente) throws ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet results = null;
        Connection con = getConnection();
        PlanPago oPlanP;
        Prestamo oPres;

        try {
            ArrayList<Prestamo> lPres = new ArrayList<>();
            String SQL = "select * from filter_customer(?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, nom_cliente);
            results = pstmt.executeQuery();

            while (results.next()) {

                oPres = new Prestamo();
                oPres.loan_number = results.getObject(1).toString();
                oPres.cus_name = results.getObject(2).toString();
                oPres.i_date = Date.valueOf(results.getObject(3).toString());
                oPres.f_date = Date.valueOf(results.getObject(4).toString());
                oPres.fee = Float.parseFloat(results.getObject(5).toString());
                oPres.pinterest = Float.parseFloat(results.getObject(6).toString());
                oPres.plate_fee = Float.parseFloat(results.getObject(7).toString());
                oPres.amount = Float.parseFloat(results.getObject(8).toString());
                oPres.id_cus = Integer.parseInt(results.getObject(9).toString());
                if (Integer.parseInt(results.getObject(9).toString()) == 0) {
                    oPres.state_name = "Pendiente";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 1) {
                    oPres.state_name = "Activo";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 2) {
                    oPres.state_name = "Mora";
                } else {
                    oPres.state_name = "Finalizado";
                }
                lPres.add(oPres);
            }
            return lPres;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error: " + e);
            return null;
        }
    }

    public ArrayList<Prestamo> prestamoXMonto(Float monto) throws ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet results = null;
        Connection con = getConnection();
        PlanPago oPlanP;
        Prestamo oPres;

        try {
            ArrayList<Prestamo> lPres = new ArrayList<>();
            String SQL = "select * from filter_amount(?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setFloat(1, monto);
            results = pstmt.executeQuery();

            while (results.next()) {

                oPres = new Prestamo();
                oPres.loan_number = results.getObject(1).toString();
                oPres.cus_name = results.getObject(2).toString();
                oPres.i_date = Date.valueOf(results.getObject(3).toString());
                oPres.f_date = Date.valueOf(results.getObject(4).toString());
                oPres.fee = Float.parseFloat(results.getObject(5).toString());
                oPres.pinterest = Float.parseFloat(results.getObject(6).toString());
                oPres.plate_fee = Float.parseFloat(results.getObject(7).toString());
                oPres.amount = Float.parseFloat(results.getObject(8).toString());
                oPres.id_cus = Integer.parseInt(results.getObject(9).toString());
                if (Integer.parseInt(results.getObject(9).toString()) == 0) {
                    oPres.state_name = "Pendiente";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 1) {
                    oPres.state_name = "Activo";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 2) {
                    oPres.state_name = "Mora";
                } else {
                    oPres.state_name = "Finalizado";
                }
                lPres.add(oPres);
            }
            return lPres;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error: " + e);
            return null;
        }
    }

    public ArrayList<Prestamo> prestamoXEstado(String estado) throws ClassNotFoundException {
        int state = 0;
        if ("Pendiente".equals(estado)) {
            state = 0;
        } else if ("Activo".equals(estado)) {
            state = 1;
        } else if ("Mora".equals(estado)) {
            state = 2;
        } else {
            state = 3;
        }

        PreparedStatement ps = null;
        ResultSet results = null;
        Connection con = getConnection();
        Prestamo oPres;

        try {
            ArrayList<Prestamo> lPres = new ArrayList<>();
            String SQL = "select * from filter_status_fee(?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, state);
            results = pstmt.executeQuery();

            while (results.next()) {

                oPres = new Prestamo();
                oPres.loan_number = results.getObject(1).toString();
                oPres.cus_name = results.getObject(2).toString();
                oPres.i_date = Date.valueOf(results.getObject(3).toString());
                oPres.f_date = Date.valueOf(results.getObject(4).toString());
                oPres.fee = Float.parseFloat(results.getObject(5).toString());
                oPres.pinterest = Float.parseFloat(results.getObject(6).toString());
                oPres.plate_fee = Float.parseFloat(results.getObject(7).toString());
                oPres.amount = Float.parseFloat(results.getObject(8).toString());
                oPres.id_cus = Integer.parseInt(results.getObject(9).toString());
                if (Integer.parseInt(results.getObject(9).toString()) == 0) {
                    oPres.state_name = "Pendiente";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 1) {
                    oPres.state_name = "Activo";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 2) {
                    oPres.state_name = "Mora";
                } else {
                    oPres.state_name = "Finalizado";
                }
                lPres.add(oPres);
            }
            return lPres;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error: " + e);
            return null;
        }
    }
}
