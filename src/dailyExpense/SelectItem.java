/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dailyExpense;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Turbo
 */
public class SelectItem extends javax.swing.JFrame {

    private String[] cat = new String[100];
    private int[] price = new int[100];
    private int tempprice;
    private String[] catName = new String[25];
    private int total;
    public static int day;
    public static int month;
    public static int year;
    private int database_date;
    private int res;
    private int cost;
    private int temp;
    private int temptotal;
       // DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/YYYY");
         java.util.Date t = new java.util.Date();
    private showDetails show;


    /**
     * Creates new form SelectItem
     */
    public SelectItem() throws SQLException, ClassNotFoundException {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("iconimage.png")).getImage());
        jSpinner7.setValue(t.getDate());
        jSpinner8.setValue(t.getMonth() + 1);
        jSpinner6.setValue(t.getYear() - 100);
        update();
    }

    public Connection SqlConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/goexpense";
        String username = "root";
        String password = "";
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }

    public void update() throws SQLException, ClassNotFoundException {
     
     
        day = Integer.parseInt(jSpinner7.getValue().toString());
        month = Integer.parseInt(jSpinner8.getValue().toString());
        year =  Integer.parseInt(jSpinner6.getValue().toString());
        
        jTextField2.setEditable(false);
      
        
        String query = "Select * From onlycat";
        Connection con = SqlConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(query);
        total = 0;
        int i = 0;
     
        while (rs.next()) {
            //user_Id[i] = rs.getString("UserId");
            cat[i] = rs.getString("name");
            String nestedquery = "select * from category where categoryName = '"+cat[i]+"' and day='"+day+"' and month = '"+month+"' and year='"+year+"'"; 
            Connection nestedcon = SqlConnection();
            Statement nestedstm = nestedcon.createStatement();
            ResultSet nestedrs = nestedstm.executeQuery(nestedquery);
            price[i]=0;
            while(nestedrs.next())
            {
                tempprice=nestedrs.getInt("price");
                price[i]+=tempprice; 
            }
            total+=price[i];

            i++;
        }
        jTextField2.setText(Integer.toString(total));
        String[] columnNames = {"Category", "Amount"};
        Object[][] data = new Object[i][9];

        for (int j = 0; j < i; j++) {
            data[j][0] = cat[j];
            data[j][1] = price[j];

        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
            
        };
      
      
        jTable1.setModel(tableModel);
        JTableHeader th = jTable1.getTableHeader();
        th.setPreferredSize(new Dimension(40, 40));
        jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
        TableColumnModel tcm = jTable1.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(180);
        tcm.setColumnMargin(10);

        String query2 = "select * from onlycat";
        Connection con2 = SqlConnection();
        Statement stm2 = con2.createStatement();
        ResultSet rs2 = stm.executeQuery(query2);
        int j = 0;
        while (rs2.next()) {
            catName[j] = rs2.getString("name");
            j++;
        }
          Object[] ob = new Object[j];
        for(int n=0;n<j;n++)
        {
            ob[n]=catName[n];
        }
        
        DefaultComboBoxModel com = new DefaultComboBoxModel(ob);

        jComboBox1.setModel(com);
        jComboBox1.setSelectedItem(null);
        jComboBox2.setSelectedItem(null);
        jTextField2.setText(Integer.toString(total));
        //jTextField3.setText(day+" /"+month+" /"+"20"+year);
        jLabel5.setText("Date: " + day + " /" + month + " /" + "20" + year);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jSpinner6 = new javax.swing.JSpinner();
        jSpinner7 = new javax.swing.JSpinner();
        jSpinner8 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        jPanel1.setLayout(null);

        jComboBox1.setBackground(new java.awt.Color(153, 153, 153));
        jComboBox1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(150, 145, 170, 30);

        jComboBox2.setBackground(new java.awt.Color(153, 153, 153));
        jComboBox2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cash", "Card ", "Check" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(150, 350, 170, 30);

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 0, 51));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(jTextField1);
        jTextField1.setBounds(150, 100, 170, 30);

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane1.setHorizontalScrollBar(null);

        jTextPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(0, 153, 102));
        jScrollPane1.setViewportView(jTextPane1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(150, 240, 170, 90);

        jSpinner6.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(13), Integer.valueOf(13), null, Integer.valueOf(1)));
        jPanel1.add(jSpinner6);
        jSpinner6.setBounds(270, 193, 50, 30);

        jSpinner7.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jSpinner7.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));
        jPanel1.add(jSpinner7);
        jSpinner7.setBounds(150, 193, 50, 30);

        jSpinner8.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jSpinner8.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        jPanel1.add(jSpinner8);
        jSpinner8.setBounds(210, 193, 50, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("(dd/mm/yy)");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(70, 200, 90, 17);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setForeground(new java.awt.Color(204, 204, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/folder_add_256.png"))); // NOI18N
        jButton1.setToolTipText("Add New Record");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(210, 400, 80, 90);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/reset.png"))); // NOI18N
        jButton3.setToolTipText("Reset");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(50, 420, 60, 60);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/sdaf.png"))); // NOI18N
        jButton5.setToolTipText("Show Details");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(130, 420, 60, 60);

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/url.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 360, 530);

        jPanel2.setLayout(null);

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel4.setText("History");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel4)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(50, 10, 250, 40);

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(51, 51, 51)));
        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 22)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Category", "Cost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jTable1.setOpaque(false);
        jTable1.setRowHeight(50);
        jTable1.setRowMargin(2);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setSelectionBackground(new java.awt.Color(51, 51, 255));
        jTable1.setShowVerticalLines(false);
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setResizable(false);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 358, 378);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(10, 80, 360, 380);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/settings.png"))); // NOI18N
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jButton6);
        jButton6.setBounds(320, 20, 50, 50);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 0, 0));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(jTextField2);
        jTextField2.setBounds(260, 490, 100, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Total");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(290, 470, 60, 22);

        jLabel5.setFont(new java.awt.Font("Tunga", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setText("jLabel5");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(120, 60, 130, 20);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/8.jpg"))); // NOI18N
        jPanel2.add(jLabel3);
        jLabel3.setBounds(0, 0, 380, 530);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-762)/2, (screenSize.height-569)/2, 762, 569);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText(null);
        jComboBox1.setSelectedItem(null);
        jComboBox2.setSelectedItem(null);
        jTextPane1.setText(null);
        jSpinner7.setValue(t.getDate());
        jSpinner8.setValue(t.getMonth() + 1);
        jSpinner6.setValue(t.getYear() - 100);
         
        try {
            update();
        } catch (SQLException ex) {
            Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if ("".equals(jTextField1.getText())) {
            JOptionPane.showMessageDialog(this, "Enter price", "Warning", 0);
        } else if (jComboBox1.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Select any category ", "Warning", 0);
        } else if (jComboBox2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Select any payment mode ", "Warning", 0);
        } else {
            cost = Integer.parseInt(jTextField1.getText());

            String query3 = "insert into category values('" + jSpinner7.getValue() + "','" + jSpinner8.getValue() + "','" + jSpinner6.getValue() + "','" + jComboBox1.getSelectedItem() + "','" + jTextPane1.getText() + "','" + cost + "','" + jComboBox2.getSelectedItem() + "')";
            Connection con3 = null;
            try {
                con3 = SqlConnection();
            } catch (SQLException ex) {
                Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement stm3 = null;
            try {
                stm3 = con3.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stm3.executeUpdate(query3);
            } catch (SQLException ex) {
                Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                update();
            } catch (SQLException ex) {
                Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTextField1.setText(null);
            jComboBox1.setSelectedItem(null);
            jComboBox2.setSelectedItem(null);
            jTextPane1.setText(null);
            jLabel5.setText("Date: " + jSpinner7.getValue() + " /" + jSpinner8.getValue() + " /" + "20" + jSpinner6.getValue());
            //jSpinner7.setValue(t.getDay() + 1);
           // jSpinner8.setValue(t.getMonth() + 1);
           // jSpinner6.setValue(t.getYear() - 100);
            JOptionPane.showMessageDialog(this, "Successfully added", "Successfull", 1);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            show = new showDetails();
        } catch (SQLException ex) {
            Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        show.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SelectItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new SelectItem().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SelectItem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    private RowSorter getRowSorter() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
