package cbir;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Color;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class ViewAllImages implements ActionListener{

private static String url = "jdbc:oracle:thin:@localhost:1521:scott";//jdbc:oracle:thin:@localhost:1521:scott jdbc:odbc:forimg

private static String username = "scott";

private static String password = "tiger";
 JButton back=null;
  JFrame jf = null;
   BufferedImage newImage = null;
Container cp=null;
void View()throws Exception
{
    
jf = new JFrame("Selected Images");
jf.setVisible(true);
   cp=jf.getContentPane();
   jf.setSize(1280,1280);

    cp.setLayout(new BorderLayout());
    JPanel jp=new JPanel();
    jp.setLayout(new GridLayout(10,1));
   
       
    
Connection conn = null;


try {

Class.forName("oracle.jdbc.driver.OracleDriver");//oracle.jdbc.driver.OracleDriver  sun.jdbc.odbc.JdbcOdbcDriver

conn = DriverManager.getConnection(url, username, password);



String sql = "SELECT IMAGE from imgtable ";

PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

ResultSet rs = stmt.executeQuery();

int i=1;


while (rs.next()) {

File image = new File("e:\\uploadedimages\\java"+ i +".jpg");

FileOutputStream fos = new FileOutputStream(image);



byte[] buffer = new byte[1];





// Get the binary stream of our BLOB data



InputStream is = rs.getBinaryStream(1);

while (is.read(buffer) > 0) {
fos.write(buffer);

}


i++;

  fos.close();


BufferedImage in = ImageIO.read(image);
 System.out.println("Row="+in.getWidth());
  System.out.println("Col="+in.getHeight());
//ImageScaler imgscaleobj=new ImageScaler();
//imgscaleobj.im(jf,in, 128, 128, 0.25);

                        im(jf,jp,in, 128, 128, 0.25);


}
  back=new JButton("<<Back");
                     back.addActionListener(this);
                     back.setOpaque(true);
                        back.setBackground(Color.YELLOW);
                       jp.add(back);
                        JScrollPane jsp = new JScrollPane(jp,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
cp.add(jsp,BorderLayout.CENTER);
jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
jf.setVisible(true);

} catch (SQLException e) {

e.printStackTrace();

}catch(Exception e)
{
    e.printStackTrace();
}

finally {

if (conn != null && !conn.isClosed()) {

conn.close();

}

}

}
 public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource()==back)
            {
            try{
                new CbirOptions();
            jf.dispose();}
            catch(Exception e)
            {
                e.getMessage();
            }
            }
        }

public void im(JFrame jf,JPanel jp,Image img, int newWidth, int newHeight, double sc) {
        //      BufferedImage out = ImageScaler.scaleImage(in,
//		BufferedImage.TYPE_BYTE_GRAY, 789*2, 218*2);

        double thumbRatio = (double) newWidth / (double) newHeight;
        //      int imageWidth = inimage.getWidth(null);
        //   int imageHeight = inimage.getHeight(null);
        int imageWidth = (int) (newWidth / sc);
        int imageHeight = (int) (newHeight / sc);
        double aspectRatio = (double) imageWidth / (double) imageHeight;

        if (thumbRatio < aspectRatio) {
            newHeight = (int) (newWidth / aspectRatio);
        } else {
            newWidth = (int) (newHeight * aspectRatio);
        }

        // Draw the scaled image
        newImage = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(img, 0, 0, newWidth, newHeight, null);
        ImageIcon ic = new ImageIcon(newImage);

        JLabel jl = new JLabel("");
        jl.setIcon(ic);
         jp.add(jl);



    }
}