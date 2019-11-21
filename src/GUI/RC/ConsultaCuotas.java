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
public class ConsultaCuotas extends Conexion {

    PreparedStatement ps = null;
    Connection con = null;
    PlanPago oPlanP;
    ResultSet results = null;

    public int planPagos(String pnum_voice) throws ClassNotFoundException, SQLException {
        int id_pending = 0;
        con = getConnection();
        try {
            String SQL = "select search_payment_fee(?) limit 1";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, pnum_voice);
            results = pstmt.executeQuery();
            while (results.next()) {
                id_pending = Integer.parseInt(results.getObject(1).toString());
            }

            return id_pending;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error:" + e);
            return 0;
        }

    }

    public PlanPago consulta_plan_pago(int pnum_pending) throws ClassNotFoundException {
        con = getConnection();
        try {
            String SQL = "select * from search_payment_plan_by_cod_pending(?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, pnum_pending);
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

                return oPlanP;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error:" + e);
            return null;
        }
        return null;
    }
}
