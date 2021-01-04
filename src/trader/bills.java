/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trader;

import java.awt.Dimension;
import java.awt.Toolkit;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.Toolkit;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;

/**
 *
 * @author Majid-Pc
 */
public class bills extends javax.swing.JPanel {

    /**
     * Creates new form bills
     */
     java.util.Date date;
    java.sql.Date sqldate;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public bills() {
        initComponents();
       setVisible(true);
        
        conn = db.ConnectDB();
         Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
        Date date = new Date();
        Date.setDate(date);
        setLocation(size.width/2 - getWidth()/2, 
        size.height/2 - getHeight()/2);
        //this.setTitle("Retail");
        //icon();
        
        fillcombo();
        fillcombo1();
    }
    
     private void fillcombo()
{
     try{
      
      conn=db.ConnectDB();
      String sql= "select name from rate";
      pst=conn.prepareStatement(sql);
      rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("name");
          jComboBox1.addItem(add);
         
         }
        }catch(HeadlessException | SQLException ex){
           JOptionPane.showMessageDialog(this,ex); 
        }finally {
            
            try{
                rs.close();
                pst.close();            
            }
            catch(SQLException e)
            {
              JOptionPane.showMessageDialog(null, e);
            }
        }
}
     
         private void save()
{
       date = Date.getDate();
            sqldate = new java.sql.Date(date.getTime());
        String value1 =  jComboBox2.getSelectedItem().toString();
        if( txt_qua.getText().isEmpty() && rate.getText().isEmpty() ){
                 JOptionPane.showMessageDialog(null,"Field Is Empty");
         }else{
                   try{
             String sql1= "insert into Retail(date,cust_name,qua,rate,total)values('" +sqldate+ "','"+ value1 + "','"+ txt_qua.getText() + "',"+ rate.getText() + ",'" + less.getText() + "')";
            pst=conn.prepareStatement(sql1);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Successfully saved","Record",JOptionPane.INFORMATION_MESSAGE);
           // if(no_chi.getText()== "" && kg_chi.getText()== "" && kg_chi.getText()== "" && far_rate.getText()== "" && tot_cst.getText()== "" ){
           // String sql2= "insert into master(date,cust_name,chi_no,chi_kg,rate_as,tot)values('" +sqldate+ "','"+ value1 + "','"+ no_chi.getText() +  "',"+ kg_chi.getText() + ",'" + far_rate.getText()+ "','" + tot_cst.getText() + "')";
           // }else{
            
           // }
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(this,ex);
        }
            
        }
        
        /////////////////////////////////////////////////////////////////////
        
         try{
      
        String val= String.valueOf(txt_qua2.getText());
        
        String value4 =  jComboBox1.getSelectedItem().toString();
       String sql= "update rate set qua='"+ val +"' where name='"+ value4 +"' ";
       pst=conn.prepareStatement(sql);
       pst.execute();
       
      //JOptionPane.showMessageDialog(this,"Successfully updated","Customer",JOptionPane.INFORMATION_MESSAGE); 
      
        }catch(HeadlessException | SQLException ex){
           JOptionPane.showMessageDialog(this,ex); 
                }finally {
            
            try{
                rs.close();
                pst.close();            
            }
            catch(SQLException e)
            {
              JOptionPane.showMessageDialog(null, e);
            }
        }
}
     
       private void fillcombo1()
{
     try{
      
      conn=db.ConnectDB();
      String sql= "select name from customer";
      pst=conn.prepareStatement(sql);
      rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("name");
          jComboBox2.addItem(add);
         
         }
        }catch(HeadlessException | SQLException ex){
           JOptionPane.showMessageDialog(this,ex); 
        }finally {
            
            try{
                rs.close();
                pst.close();            
            }
            catch(SQLException e)
            {
              JOptionPane.showMessageDialog(null, e);
            }
        }
}
     
     
     private void clrss(){
           
      txt_qua.setText("");
      txt_qua1.setText("");    
      txt_qua2.setText("");
      rate.setText("");
      less.setText("0");
      
    }
     
     public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}






public class BillPrintable implements Printable {
    
   
    
    
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
                
        
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;
            Graphics2D g2d1 = (Graphics2D) graphics;   

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

            ////////// code by alqama//////////////

            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
            
            
        //    int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

        //    int idPosition=0;
        //    int productPosition=idPosition + idLength + 2;
        //    int pricePosition=productPosition + prodLength +10;
        //    int qtyPosition=pricePosition + priceLength + 2;
        //    int amtPosition=qtyPosition + qtyLength + 2;
            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=10;
            int yShift = 12;
            int headerRectHeight=2;
            int headerRectHeighta=2;
            
            ///////////////// Product names Get ///////////
              //  String  pn1a=pn1.getText();
              //  String pn2a=pn2.getText();
             //   String pn3a=pn3.getText();
             //   String pn4a=pn4.getText();
            ///////////////// Product names Get ///////////
                date = Date.getDate();
        sqldate = new java.sql.Date(date.getTime());
        String sqldate2 = String.valueOf(sqldate);

            
            ///////////////// Product price Get ///////////
                String pp1a= txt_qua.getText();
                //String pp2a=txt_kg.getText();
                String pp3a=jComboBox1.getSelectedItem().toString();
                String pp3a1=jComboBox2.getSelectedItem().toString();
                String pp4a=rate.getText();
                //String pp5a=txt_tkn.getText();
                String pp6a=less.getText();
               // String pp7a=txt_amt.getText();
                 Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = formatter.format(date);
                
            ///////////////// Product price Get ///////////
                
            g2d.setFont(new Font("Monospaced",Font.BOLD,12));
            //g2d1.setFont(new Font("Monospaced",Font.BOLD,15));
            g2d.drawString("----------------------",2,y);y+=yShift;
            g2d.drawString("       POSIM          ",2,y);y+=yShift;
            g2d.drawString("----------------------",2,y);y+=yShift;
            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
            g2d.drawString("  DATE.   "+strDate+" ",2,y);y+=yShift;
            g2d.drawString("----------------------",2,y);y+=yShift;
            g2d.drawString("Customer  "+pp3a1+"   ",2,y);y+=yShift;
            g2d.drawString("Item Name "+pp3a+"    ",2,y);y+=yShift;
            g2d.drawString("Rate      "+pp4a+"    ",2,y);y+=yShift;
            g2d.drawString("Quantity  "+pp1a+"    ",2,y);y+=yShift;
            g2d.drawString("Total     "+pp6a+"    ",2,y);y+=yShift;
            g2d.drawString("**********************",2,y);y+=yShift;
            g2d.drawString(" ThankYou Visit Again ",2,y);y+=yShift;
            g2d.drawString("**********************",2,y);y+=yShift;
                   
           
             
           
            
//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
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

        btn_calc = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_qua1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_qua = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rate = new javax.swing.JTextField();
        txt_qua2 = new javax.swing.JTextField();
        Date = new com.toedter.calendar.JDateChooser();
        less = new javax.swing.JTextField();
        btn_save = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        btn_clear = new javax.swing.JButton();
        btn_clear2 = new javax.swing.JButton();

        btn_calc.setText("Calculate");
        btn_calc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calcActionPerformed(evt);
            }
        });
        btn_calc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_calcKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Customer Name");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Date");

        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Quantity");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Rate");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Items");

        txt_qua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_qua1ActionPerformed(evt);
            }
        });
        txt_qua1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_qua1KeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Total");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Quantity");

        txt_qua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quaActionPerformed(evt);
            }
        });
        txt_qua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_quaKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Quantity N");

        rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateActionPerformed(evt);
            }
        });
        rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rateKeyTyped(evt);
            }
        });

        txt_qua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_qua2ActionPerformed(evt);
            }
        });
        txt_qua2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_qua2KeyTyped(evt);
            }
        });

        Date.setDateFormatString("dd-MM-yyyy");

        less.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessActionPerformed(evt);
            }
        });
        less.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lessKeyTyped(evt);
            }
        });

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employee Payroll/Icons/Save-icon.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        btn_save.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_saveKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btn_saveKeyTyped(evt);
            }
        });

        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employee Payroll/Icons/erase-128.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_clear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employee Payroll/Icons/printer.png"))); // NOI18N
        btn_clear2.setText("Print");
        btn_clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear2ActionPerformed(evt);
            }
        });
        btn_clear2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_clear2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_qua, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel10))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(less, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_save, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_calc, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt_qua1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txt_qua2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_qua1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_qua2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_qua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_calc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(less, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_clear2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_calcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calcActionPerformed
        // TODO add your handling code here:
        try
        {
            int add1 = Integer.parseInt(txt_qua.getText());
            int add = Integer.parseInt(rate.getText());
            int add2 = (int) ((int) add * add1);
            String add3= Integer.toString(add2);
            less.setText(add3);

            int add34 = Integer.parseInt(txt_qua1.getText());
            int add23 = Integer.parseInt(txt_qua.getText());
            int add33 = (int) ((int) add34 - add23);
            String add38= Integer.toString(add33);
            txt_qua2.setText(add38);

        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_calcActionPerformed

    private void btn_calcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_calcKeyPressed
        // TODO add your handling code here:
        try
        {
            int add1 = Integer.parseInt(txt_qua.getText());
            int add = Integer.parseInt(rate.getText());
            int add2 = (int) ((int) add * add1);
            String add3= Integer.toString(add2);
            less.setText(add3);

        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_calcKeyPressed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void txt_qua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_qua1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_qua1ActionPerformed

    private void txt_qua1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qua1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_qua1KeyTyped

    private void txt_quaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quaActionPerformed

    private void txt_quaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_quaKeyTyped
        // TODO add your handling code here:
        char c= evt.getKeyChar();
        if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ){
            evt.consume();
        }
    }//GEN-LAST:event_txt_quaKeyTyped

    private void rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rateActionPerformed

    private void rateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rateKeyTyped
        // TODO add your handling code here:
        char c= evt.getKeyChar();
        if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ){
            evt.consume();
        }
    }//GEN-LAST:event_rateKeyTyped

    private void txt_qua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_qua2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_qua2ActionPerformed

    private void txt_qua2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qua2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_qua2KeyTyped

    private void lessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessActionPerformed

    private void lessKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lessKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lessKeyTyped

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        date = Date.getDate();
        sqldate = new java.sql.Date(date.getTime());
        String value1 =  jComboBox2.getSelectedItem().toString();
        if( txt_qua.getText().isEmpty() && rate.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null,"Field Is Empty");
        }else{
            try{
                String sql1= "insert into Retail(date,cust_name,qua,rate,total)values('" +sqldate+ "','"+ value1 + "','"+ txt_qua.getText() + "',"+ rate.getText() + ",'" + less.getText() + "')";
                pst=conn.prepareStatement(sql1);
                pst.execute();
                JOptionPane.showMessageDialog(this,"Successfully saved","Record",JOptionPane.INFORMATION_MESSAGE);
                // if(no_chi.getText()== "" && kg_chi.getText()== "" && kg_chi.getText()== "" && far_rate.getText()== "" && tot_cst.getText()== "" ){
                    // String sql2= "insert into master(date,cust_name,chi_no,chi_kg,rate_as,tot)values('" +sqldate+ "','"+ value1 + "','"+ no_chi.getText() +  "',"+ kg_chi.getText() + ",'" + far_rate.getText()+ "','" + tot_cst.getText() + "')";
                    // }else{

                    // }
            }catch(HeadlessException | SQLException ex){
                JOptionPane.showMessageDialog(this,ex);
            }

        }

        /////////////////////////////////////////////////////////////////////

        try{

            String val= String.valueOf(txt_qua2.getText());

            String value4 =  jComboBox1.getSelectedItem().toString();
            String sql= "update rate set qua='" + val + "'where name = '"+ value4 +"'";
            pst=conn.prepareStatement(sql);
            pst.execute();

            //JOptionPane.showMessageDialog(this,"Successfully updated","Customer",JOptionPane.INFORMATION_MESSAGE);

        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(this,ex);
        }finally {

            try{
                rs.close();
                pst.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_saveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_saveKeyPressed
        // TODO add your handling code here:
        date = Date.getDate();
        sqldate = new java.sql.Date(date.getTime());
        String value1 =  jComboBox2.getSelectedItem().toString();
        if( txt_qua.getText().isEmpty() && rate.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null,"Field Is Empty");
        }else{
            try{
                String sql1= "insert into Retail(date,cust_name,qua,rate,total)values('" +sqldate+ "','"+ value1 + "','"+ txt_qua.getText() + "',"+ rate.getText() + ",'" + rate.getText() + "','" + less.getText() + "')";
                pst=conn.prepareStatement(sql1);
                pst.execute();
                JOptionPane.showMessageDialog(this,"Successfully saved","Record",JOptionPane.INFORMATION_MESSAGE);
                // if(no_chi.getText()== "" && kg_chi.getText()== "" && kg_chi.getText()== "" && far_rate.getText()== "" && tot_cst.getText()== "" ){
                    // String sql2= "insert into master(date,cust_name,chi_no,chi_kg,rate_as,tot)values('" +sqldate+ "','"+ value1 + "','"+ no_chi.getText() +  "',"+ kg_chi.getText() + ",'" + far_rate.getText()+ "','" + tot_cst.getText() + "')";
                    // }else{

                    // }
            }catch(HeadlessException | SQLException ex){
                JOptionPane.showMessageDialog(this,ex);
            }

        }
    }//GEN-LAST:event_btn_saveKeyPressed

    private void btn_saveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_saveKeyTyped
        // TODO add your handling code here:
        date = Date.getDate();
        sqldate = new java.sql.Date(date.getTime());
        String value1 =  jComboBox2.getSelectedItem().toString();
        if( txt_qua.getText().isEmpty() && rate.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null,"Field Is Empty");
        }else{
            try{
                String sql1= "insert into Retail(date,cust_name,qua,rate,total)values('" +sqldate+ "','"+ value1 + "','"+ txt_qua.getText() + "',"+ rate.getText() + ",'" + rate.getText() + "','" + less.getText() + "')";
                pst=conn.prepareStatement(sql1);
                pst.execute();
                JOptionPane.showMessageDialog(this,"Successfully saved","Record",JOptionPane.INFORMATION_MESSAGE);
                // if(no_chi.getText()== "" && kg_chi.getText()== "" && kg_chi.getText()== "" && far_rate.getText()== "" && tot_cst.getText()== "" ){
                    // String sql2= "insert into master(date,cust_name,chi_no,chi_kg,rate_as,tot)values('" +sqldate+ "','"+ value1 + "','"+ no_chi.getText() +  "',"+ kg_chi.getText() + ",'" + far_rate.getText()+ "','" + tot_cst.getText() + "')";
                    // }else{

                    // }
            }catch(HeadlessException | SQLException ex){
                JOptionPane.showMessageDialog(this,ex);
            }

        }
    }//GEN-LAST:event_btn_saveKeyTyped

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
        try{

            conn=db.ConnectDB();
            String value1 =  jComboBox1.getSelectedItem().toString();
            String sql= "select * from rate where name = '"+value1+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){

                String add3=rs.getString("rate");
                rate.setText(add3);
                String add4=rs.getString("qua");
                txt_qua1.setText(add4);

            }
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(this,ex);
        }finally {

            try{
                rs.close();
                pst.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:

        txt_qua.setText("");
        txt_qua1.setText("");
        txt_qua2.setText("");
        rate.setText("");
        less.setText("0");
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_clear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear2ActionPerformed
        // TODO add your handling code here:

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        if (pj.printDialog()) {
            try {pj.print();

            }
            catch (PrinterException exc) {
                System.out.println(exc);
            }
             }

        save();
        clrss();
    }//GEN-LAST:event_btn_clear2ActionPerformed

    private void btn_clear2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_clear2KeyPressed
        // TODO add your handling code here:
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        //  if (pj.printDialog()) {
            try {pj.print();

            }
            catch (PrinterException exc) {
                System.out.println(exc);
            }
            //  }

        txt_qua.requestFocus();
        clrss();
    }//GEN-LAST:event_btn_clear2KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date;
    private javax.swing.JButton btn_calc;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_clear2;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField less;
    private javax.swing.JTextField rate;
    private javax.swing.JTextField txt_qua;
    private javax.swing.JTextField txt_qua1;
    private javax.swing.JTextField txt_qua2;
    // End of variables declaration//GEN-END:variables
}
