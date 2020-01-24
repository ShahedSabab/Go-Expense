/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dailyExpense;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Turbo
 */
public class showDetails extends javax.swing.JFrame {

    /**
     * Creates new form showDetails
     */
    private int[] day = new int[100000];
    private int[] month = new int[100000];
    private int[] year = new int[100000];
    private String[] cat = new String[100000];
    private String[] description = new String[100000];
    private int[] price = new int[100000];
    private String[] pay = new String[100000];
    private int total;
    private int i;
    private String[] catName = new String[25];
    private String query;
    DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/YYYY");
    java.util.Date t = new java.util.Date();

    public showDetails() throws SQLException, ClassNotFoundException {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("iconimage.png")).getImage());
        update();
    }

    public Connection SqlConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/goexpense";
        String username = "root";
        String password = "";
        Connection con = (Connection) DriverManager.getConnection(url, username, password);
        return con;
    }

    public void update() throws SQLException, ClassNotFoundException {

        tableshow();
        String[] name_month = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] columnNames = {"Date", "Month", "Year", "Category Name", "Description", "Price", "Payment Mode"};
        Object[][] data = new Object[i][7];

        for (int j = 0; j < i; j++) {

            data[j][0] = day[j];
            data[j][1] = name_month[month[j] - 1];
            data[j][2] = 2000 + year[j];
            data[j][3] = cat[j];
            data[j][4] = description[j];
            data[j][5] = price[j];
            data[j][6] = pay[j];
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
        };

        jTable1.setModel(tableModel);
        JTableHeader th = jTable1.getTableHeader();
        th.setPreferredSize(new Dimension(30, 30));
        jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        TableColumnModel tcm = jTable1.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(5);
        tcm.getColumn(1).setPreferredWidth(5);
        tcm.getColumn(2).setPreferredWidth(5);
        tcm.getColumn(3).setPreferredWidth(100);
        tcm.getColumn(4).setPreferredWidth(180);
        tcm.getColumn(5).setPreferredWidth(60);
        tcm.getColumn(6).setPreferredWidth(100);
        tcm.setColumnMargin(5);
        jTextField1.setText(Integer.toString(total));
        String query2 = "select * from onlycat";
        java.sql.Connection con2 = SqlConnection();
        java.sql.Statement stm2 = con2.createStatement();
        ResultSet rs2 = stm2.executeQuery(query2);
        int j = 0;
        while (rs2.next()) {
            catName[j] = rs2.getString("name");
            j++;
        }

        // jComboBox6.setMaximumRowCount(j);
        Object[] ob = new Object[j];
        for (int n = 0; n < j; n++) {
            ob[n] = catName[n];
        }
        DefaultComboBoxModel com = new DefaultComboBoxModel(ob);
        jComboBox6.setModel(com);

        String[] month_name = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        DefaultComboBoxModel com2 = new DefaultComboBoxModel(month_name);
        jComboBox3.setModel(com2);
        jTextField2.setText(null);
        jComboBox3.setSelectedItem(null);
        jComboBox5.setSelectedItem(null);
        jComboBox6.setSelectedItem(null);
        jTextField3.setText(null);

    }

    public void tableshow() throws SQLException, ClassNotFoundException {
        total = 0;
        i = 0;
        int mon = jComboBox3.getSelectedIndex();
        int yr;
        int primary = t.getYear() - 100;

        mon += 1;
        if (("").equals(jTextField2.getText()) && jComboBox3.getSelectedItem() == null && ("").equals(jTextField3.getText()) && jComboBox6.getSelectedItem() == null && jComboBox5.getSelectedItem() == null) {
            query = "Select * From category where year='" + primary + "'";
        } else if (jComboBox3.getSelectedItem() == null && ("").equals(jTextField3.getText()) && jComboBox6.getSelectedItem() == null && jComboBox5.getSelectedItem() == null) {
            query = "Select * From category where day='" + jTextField2.getText() + "'";
        } else if ((("").equals(jTextField2.getText()) && ("").equals(jTextField3.getText()) && jComboBox6.getSelectedItem() == null && jComboBox5.getSelectedItem() == null)) {
            query = "Select * From category where month='" + mon + "'";
        } else if (("").equals(jTextField2.getText()) && jComboBox3.getSelectedItem() == null && jComboBox6.getSelectedItem() == null && jComboBox5.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where year='" + yr + "'";
        } else if (("").equals(jTextField2.getText()) && jComboBox3.getSelectedItem() == null && ("").equals(jTextField3.getText()) && jComboBox5.getSelectedItem() == null) {
            query = "Select * From category where categoryName='" + jComboBox6.getSelectedItem() + "'";
        } else if (("").equals(jTextField2.getText()) && jComboBox3.getSelectedItem() == null && ("").equals(jTextField3.getText()) && jComboBox6.getSelectedItem() == null) {
            query = "Select * From category where paymentMode='" + jComboBox5.getSelectedItem() + "'";
        } else if (("").equals(jTextField3.getText()) && jComboBox6.getSelectedItem() == null && jComboBox5.getSelectedItem() == null) {
            query = "Select * From category where day='" + jTextField2.getText() + "' and month='" + mon + "'";
        } else if (("").equals(jTextField2.getText()) && jComboBox6.getSelectedItem() == null && jComboBox5.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where year='" + yr + "' and month='" + mon + "'";
        } else if (jComboBox3.getSelectedItem() == null && jComboBox6.getSelectedItem() == null && jComboBox5.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where year='" + yr + "' and day='" + jTextField2.getText() + "'";
        } else if (jComboBox6.getSelectedItem() == null && jComboBox5.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where day='" + jTextField2.getText() + "' and year='" + yr + "' and month='" + mon + "'";
        } else if (jComboBox3.getSelectedItem() == null && ("").equals(jTextField3.getText()) && jComboBox5.getSelectedItem() == null) {
            query = "Select * From category where day='" + jTextField2.getText() + "' and categoryName='" + jComboBox6.getSelectedItem() + "'";
        } else if (("").equals(jTextField2.getText()) && ("").equals(jTextField3.getText()) && jComboBox5.getSelectedItem() == null) {
            query = "Select * From category where month='" + mon + "' and categoryName='" + jComboBox6.getSelectedItem() + "'";
        } else if (jComboBox3.getSelectedItem() == null && ("").equals(jTextField2.getText()) && jComboBox5.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where year='" + yr + "' and categoryName='" + jComboBox6.getSelectedItem() + "'";
        } else if (jComboBox3.getSelectedItem() == null && ("").equals(jTextField3.getText()) && jComboBox6.getSelectedItem() == null) {
            query = "Select * From category where day='" + jTextField2.getText() + "' and paymentMode='" + jComboBox5.getSelectedItem() + "'";
        } else if (("").equals(jTextField2.getText()) && ("").equals(jTextField3.getText()) && jComboBox6.getSelectedItem() == null) {
            query = "Select * From category where month='" + mon + "' and paymentMode='" + jComboBox5.getSelectedItem() + "'";
        } else if (jComboBox3.getSelectedItem() == null && ("").equals(jTextField2.getText()) && jComboBox6.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where year='" + yr + "' and paymentMode='" + jComboBox5.getSelectedItem() + "'";
        } else if (("").equals(jTextField3.getText()) && jComboBox5.getSelectedItem() == null) {
            query = "Select * From category where day='" + jTextField2.getText() + "' and categoryName='" + jComboBox6.getSelectedItem() + "'and month='" + mon + "'";
        } else if (("").equals(jTextField3.getText()) && jComboBox6.getSelectedItem() == null) {
            query = "Select * From category where day='" + jTextField2.getText() + "' and paymentMode='" + jComboBox5.getSelectedItem() + "'and month='" + mon + "'";
        } else if (("").equals(jTextField2.getText()) && jComboBox6.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where year='" + yr + "' and month='" + mon + "' and paymentMode='" + jComboBox5.getSelectedItem() + "'";
        } else if (("").equals(jTextField2.getText()) && jComboBox5.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where year='" + yr + "' and month='" + mon + "' and categoryName='" + jComboBox6.getSelectedItem() + "'";
        } else if (jComboBox3.getSelectedItem() == null && jComboBox5.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where year='" + yr + "' and day='" + jTextField2.getText() + "'and categoryName='" + jComboBox6.getSelectedItem() + "'";
        } else if (jComboBox3.getSelectedItem() == null && jComboBox6.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where year='" + yr + "' and day='" + jTextField2.getText() + "'and paymentMode='" + jComboBox5.getSelectedItem() + "'";
        } else if (jComboBox6.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where day='" + jTextField2.getText() + "' and year='" + yr + "' and month='" + mon + "'and paymentMode='" + jComboBox5.getSelectedItem() + "'";
        } else if (jComboBox5.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where day='" + jTextField2.getText() + "' and year='" + yr + "' and month='" + mon + "'and categoryName='" + jComboBox6.getSelectedItem() + "'";
        } else if (("").equals(jTextField2.getText()) && jComboBox3.getSelectedItem() == null && ("").equals(jTextField3.getText())) {
            query = "Select * From category where   categoryName='" + jComboBox6.getSelectedItem() + "'and paymentMode='" + jComboBox5.getSelectedItem() + "'";
        } else if (jComboBox3.getSelectedItem() == null && ("").equals(jTextField3.getText())) {
            query = "Select * From category where   categoryName='" + jComboBox6.getSelectedItem() + "'and paymentMode='" + jComboBox5.getSelectedItem() + "' and day='" + jTextField2.getText() + "'";
        } else if (("").equals(jTextField2.getText()) && ("").equals(jTextField3.getText())) {
            query = "Select * From category where   categoryName='" + jComboBox6.getSelectedItem() + "'and paymentMode='" + jComboBox5.getSelectedItem() + "'and month='" + mon + "'";
        } else if (("").equals(jTextField2.getText()) && jComboBox3.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where   categoryName='" + jComboBox6.getSelectedItem() + "'and paymentMode='" + jComboBox5.getSelectedItem() + "' and year='" + yr + "'";
        } else if (("").equals(jTextField3.getText())) {
            query = "Select * From category where   categoryName='" + jComboBox6.getSelectedItem() + "'and paymentMode='" + jComboBox5.getSelectedItem() + "' and day='" + jTextField2.getText() + "'and month='" + mon + "'";
        } else if (jComboBox3.getSelectedItem() == null) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where   categoryName='" + jComboBox6.getSelectedItem() + "'and paymentMode='" + jComboBox5.getSelectedItem() + "' and year='" + yr + "'and day='" + jTextField2.getText() + "'";
        } else if (("").equals(jTextField2.getText())) {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where   categoryName='" + jComboBox6.getSelectedItem() + "'and paymentMode='" + jComboBox5.getSelectedItem() + "' and year='" + yr + "' and month= '" + mon + "' ";
        } else {
            yr = Integer.parseInt(jTextField3.getText());
            yr -= 2000;
            query = "Select * From category where  day='" + jTextField2.getText() + "' and categoryName='" + jComboBox6.getSelectedItem() + "'and paymentMode='" + jComboBox5.getSelectedItem() + "' and year='" + yr + "' and month= '" + mon + "' ";
        }

        Connection con = SqlConnection();
        Statement stm = (Statement) con.createStatement();
        ResultSet rs = stm.executeQuery(query);


        while (rs.next()) {
            //user_Id[i] = rs.getString("UserId");
            day[i] = rs.getInt("day");
            month[i] = rs.getInt("month");
            year[i] = rs.getInt("year");
            description[i] = rs.getString("description");
            cat[i] = rs.getString("categoryName");
            price[i] = rs.getInt("price");
            pay[i] = rs.getString("paymentMode");
            total = total + price[i];
            i++;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(null);

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 102, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/delete.jpg"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(690, 40, 70, 69);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/images.jpg"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(610, 40, 66, 69);

        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(25);
        jTable1.setRowMargin(2);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 194, 845, 276);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));

        jComboBox3.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(51, 102, 255));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jComboBox6.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(51, 102, 255));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jComboBox5.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(51, 102, 255));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cash", "Card ", "Check" }));
        jComboBox5.setSelectedIndex(-1);
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel5.setText("Category:");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel4.setText("Payment:");

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel1.setText("Date:");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel2.setText("Month:");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel3.setText("Year:");

        jTextField2.setBackground(new java.awt.Color(102, 102, 102));
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField3.setBackground(new java.awt.Color(102, 102, 102));
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addGap(79, 79, 79)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(20, 20, 563, 157);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Reset");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(790, 110, 40, 17);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Search");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(620, 110, 50, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tk");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(810, 150, 60, 25);

        jTextField1.setBackground(new java.awt.Color(51, 51, 51));
        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(jTextField1);
        jTextField1.setBounds(660, 145, 150, 35);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("BDT");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(620, 150, 60, 25);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/reset.jpg"))); // NOI18N
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(775, 40, 70, 70);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Delete");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(708, 110, 40, 17);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dailyExpense/show.jpg"))); // NOI18N
        jPanel2.add(jLabel6);
        jLabel6.setBounds(0, 0, 860, 490);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 865, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-901)/2, (screenSize.height-545)/2, 901, 545);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jComboBox3.setSelectedItem(null);
        jComboBox5.setSelectedItem(null);
        jComboBox6.setSelectedItem(null);
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        String[] columnNames = {"Date", "Month", "Year", "Category Name", "Description", "Price", "Payment Mode"};
        Object[][] data = new Object[50][7];


        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        jTable1.setModel(tableModel);
        JTableHeader th = jTable1.getTableHeader();
        th.setPreferredSize(new Dimension(30, 30));
        jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        TableColumnModel tcm = jTable1.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(5);
        tcm.getColumn(1).setPreferredWidth(5);
        tcm.getColumn(2).setPreferredWidth(5);
        tcm.getColumn(3).setPreferredWidth(100);
        tcm.getColumn(4).setPreferredWidth(180);
        tcm.getColumn(5).setPreferredWidth(60);
        tcm.getColumn(6).setPreferredWidth(100);
        tcm.setColumnMargin(5);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            update();

        } catch (SQLException ex) {
            Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String[] name_month = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int selectday;
        String selectmonth;
        int selectyear;
        int selectprice;
        String selectcatname;
        String selectdes;
        String selectpay;
        int save = 0;
        int row = -1;
        row = (int) jTable1.getSelectedRow();

        if (row != -1) {
            selectday = Integer.parseInt(jTable1.getModel().getValueAt(row, 0).toString());
            selectmonth = jTable1.getModel().getValueAt(row, 1).toString();
            selectyear =  Integer.parseInt(jTable1.getModel().getValueAt(row, 2).toString());
            selectcatname = jTable1.getModel().getValueAt(row, 3).toString();
            selectdes = jTable1.getModel().getValueAt(row, 4).toString();
            selectprice =  Integer.parseInt(jTable1.getModel().getValueAt(row, 5).toString());
            selectpay = jTable1.getModel().getValueAt(row, 6).toString();
            System.out.println(row);
            System.out.println(row);
            for (int k = 0; k < 12; k++) {
                if (selectmonth == name_month[k]) {
                    save = k + 1;
                }
            }
            selectyear -= 2000;
            String Query;
            Query = "Delete from category WHERE day = '" + selectday + "' and month = '" + save + "' and year = '" + selectyear + "' and categoryName = '" + selectcatname + "' and description = '" + selectdes + "' and price = '" + selectprice + "' and paymentMode = '" + selectpay + "'";

            Connection con1 = null;
            try {
                con1 = SqlConnection();
            } catch (SQLException ex) {
                Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement stm1 = null;
            try {
                stm1 = (Statement) con1.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stm1.executeUpdate(Query);
            } catch (SQLException ex) {
                Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                update();
            } catch (SQLException ex) {
                Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Selected record is deleted", "Successfull", 1);
        } else {
            JOptionPane.showMessageDialog(this, "Please select any row to delete", "Error", 0);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(showDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(showDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(showDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(showDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new showDetails().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(showDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
