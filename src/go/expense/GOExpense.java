/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go.expense;

import dailyExpense.SelectItem;
import java.sql.SQLException;

/**
 *
 * @author Turbo
 */
public class GOExpense {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SelectItem select = new SelectItem();
        select.setVisible(true);
    }
}
