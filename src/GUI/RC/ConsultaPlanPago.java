/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.RC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Eli
 */
public class ConsultaPlanPago extends Conexion {
    PreparedStatement ps = null;
    Connection con = null;
    PlanPago oPlanP;
    ResultSet results = null;
    

    public ArrayList<PlanPago> planPagos(String pnum_voice) throws ClassNotFoundException, SQLException {

        con = getConnection();
        try {
            ArrayList<PlanPago> lPlan = new ArrayList<>();
            String SQL = "Select * from search_payment_plan(?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, pnum_voice);
            results = pstmt.executeQuery();
            while (results.next()) {
                oPlanP = new PlanPago();
                oPlanP.i_balance = Float.parseFloat(results.getObject(1).toString());
                oPlanP.fee = Float.parseFloat(results.getObject(2).toString());
                oPlanP.r_date = Date.valueOf(results.getObject(3).toString());
                try {
                    oPlanP.late_fee = Float.parseFloat(results.getObject(4).toString());
                } catch (Exception e) {
                    oPlanP.late_fee = 0.0f;
                }
                oPlanP.f_balance = Float.parseFloat(results.getObject(5).toString());
                oPlanP.num_sale = results.getObject(6).toString();
                oPlanP.cod_cus = Integer.parseInt(results.getObject(8).toString());
                oPlanP.state = Integer.parseInt(results.getObject(9).toString());
                if (Integer.parseInt(results.getObject(9).toString()) == 0) {
                    oPlanP.name_state = "Pendiente";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 1) {
                    oPlanP.name_state = "Mora";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 2) {
                    oPlanP.name_state = "Pagado";
                }
                oPlanP.nameCus = results.getObject(10).toString();
                oPlanP.id_plan_pago = Integer.parseInt(results.getObject(11).toString());
                lPlan.add(oPlanP);
            }

            return lPlan;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error:" + e);
            return null;
        }

    }

    public ArrayList<PlanPago> planPagosX(int estado) throws ClassNotFoundException, SQLException {
        int state = 0;
        if ("Pendiente".equals(estado)) {
            state = 0;
        } else if ("Mora".equals(estado)) {
            state = 1;
        } else if ("Pagado".equals(estado)) {
            state = 2;
        }
        PreparedStatement ps = null;
        Connection con = getConnection();
        PlanPago oPlanP;
        ResultSet results = null;

        try {
            ArrayList<PlanPago> lPlan = new ArrayList<>();
            String SQL = "Select * from filter_number_pending_fee(?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, state);
            results = pstmt.executeQuery();
            while (results.next()) {
                oPlanP = new PlanPago();
                oPlanP.i_balance = Float.parseFloat(results.getObject(1).toString());
                oPlanP.fee = Float.parseFloat(results.getObject(2).toString());
                oPlanP.r_date = Date.valueOf(results.getObject(3).toString());
                try {
                    oPlanP.late_fee = Float.parseFloat(results.getObject(4).toString());
                } catch (Exception e) {
                    oPlanP.late_fee = 0.0f;
                }
                oPlanP.f_balance = Float.parseFloat(results.getObject(5).toString());
                oPlanP.num_sale = results.getObject(6).toString();
                oPlanP.cod_cus = Integer.parseInt(results.getObject(8).toString());
                oPlanP.state = Integer.parseInt(results.getObject(9).toString());
                if (Integer.parseInt(results.getObject(9).toString()) == 0) {
                    oPlanP.name_state = "Pendiente";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 1) {
                    oPlanP.name_state = "Mora";
                } else if (Integer.parseInt(results.getObject(9).toString()) == 2) {
                    oPlanP.name_state = "Pagado";
                }
                oPlanP.nameCus = results.getObject(10).toString();
                oPlanP.id_plan_pago = Integer.parseInt(results.getObject(11).toString());
                lPlan.add(oPlanP);
            }

            return lPlan;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error:" + e);
            return null;
        }

    }
}
