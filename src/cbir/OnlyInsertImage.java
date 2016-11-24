package cbir;
import java.sql.*;
import java.io.*;
import javax.swing.JOptionPane;

public class OnlyInsertImage{
 OnlyInsertImage(File input,String fname)
 {
    System.out.println("Insert Image Example!");
    String url = "jdbc:oracle:thin:@localhost:1521:scott";
    //String dbName = "forimg";
    String userName = "scott";
    String password = "tiger";
    Connection con = null;
    int totrow;
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");
       }
    catch(ClassNotFoundException ex)
	{
	 ex.printStackTrace();
	}

	try{
            con = DriverManager.getConnection(url,userName,password);
        Statement st = con.createStatement();
	int i=1;
	
            PreparedStatement stmt = con.prepareStatement("Select * from imgtable", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=stmt.executeQuery();
         if (rs == null) {
                totrow = 0;
            } else {
                rs.last();
                totrow = rs.getRow();
            }
        i=totrow+1;
        rs.close();
	
        FileInputStream fin = new FileInputStream(input);
	PreparedStatement pre = con.prepareStatement("insert into imgtable values(?,?,?)");
       pre.setInt(1,i);
        pre.setString(2,fname);
        pre.setBinaryStream(3,fin,(int)input.length());
     	pre.executeUpdate();
        System.out.println("Inserting Successfully!");
         JOptionPane.showMessageDialog (null,"Inserting Successfully","Inserting Image",JOptionPane.INFORMATION_MESSAGE);
      int a= JOptionPane.showConfirmDialog(null, "Do you want to continue inserting images?","alert",JOptionPane.YES_NO_OPTION);
       if(a==JOptionPane.YES_OPTION)
       {
           try{new insertimg();}
           catch(Exception e)
           {
             e.getMessage();
           }
       }
       else
       {
          try{new CbirOptions();}
          catch(Exception e)
          {
              e.getMessage();
          }
       }
         pre.close();
         con.close();
   }
    catch(Exception e){
     
              JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
              e.printStackTrace();


    }
  }
}
