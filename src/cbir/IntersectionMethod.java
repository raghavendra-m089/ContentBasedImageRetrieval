package cbir;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
public class IntersectionMethod implements ActionListener{
 int i,j;
     private static String url = "jdbc:oracle:thin:@localhost:1521:scott";
    private static String username = "scott";
    private static String password = "tiger";
    File image = null;
JFrame jf=null;
   Color cc=null;
   float inputimgbin[]=new float[24];
    float imgbin[][] = new float[200][24];
     int pixels[]=new int[200];;
    int totrow;
    BufferedImage bimg=null;
    Container cp=null;
JButton back=null;
void imagematch(File imgf) throws Exception
{

    JProgressBar pbar=new JProgressBar();

 JFrame loadingframe = new JFrame("Processing.. Please Wait.");
  pbar.setBorderPainted(true);
 pbar.setBackground(Color.BLACK);
 pbar.setForeground(Color.white);
 pbar.setIndeterminate(true);
 pbar.setSize(10,10);
 pbar.setString("Loading..Please wait.");
 pbar.setStringPainted(true);
 loadingframe.setSize(1280,1280);
 loadingframe.getContentPane().setLayout(null);
 loadingframe.getContentPane().setBackground(Color.cyan);
 pbar.setBounds(420,330,150,50);
  loadingframe.getContentPane().add(pbar);
  loadingframe.setVisible(true);
  loadingframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    BufferedImage inimg=ImageIO.read(imgf);
     int colsinimg = inimg.getWidth();
                System.out.println("col=" + colsinimg);
                int rowsinimg = inimg.getHeight();
                System.out.println("row=" + rowsinimg);
   float inputimgRGB[][][] = new float[rowsinimg][colsinimg][3];

                for(i=0;i<rowsinimg;i++)
                {
                    for(j=0;j<colsinimg;j++)
                    {
                          cc = new Color(inimg.getRGB(j, i));

                        inputimgRGB[i][j][0] = cc.getRed();

                        inputimgRGB[i][j][1] = cc.getGreen();

                       inputimgRGB[i][j][2] = cc.getBlue();
                    }
                }
for ( i = 0; i < rowsinimg; i++) {
                    for ( j = 0; j < colsinimg; j++) { 	// START OF HUE(H) VALUES;
                        if (((inputimgRGB[i][j][0] ) >= 0.00) && ((inputimgRGB[i][j][0] ) <= 31.00)) {
                           inputimgbin[0] =inputimgbin[0] + 1;
                        } else if (((inputimgRGB[i][j][0] ) >= 32.00) && ((inputimgRGB[i][j][0] ) <= 63.00)) {
                           inputimgbin[1] =inputimgbin[1] + 1;
                        } else if (((inputimgRGB[i][j][0] ) >=64.00) && ((inputimgRGB[i][j][0] ) <= 95.00)) {
                           inputimgbin[2] =inputimgbin[2] + 1;
                        } else if (((inputimgRGB[i][j][0] ) >=96.00) && ((inputimgRGB[i][j][0] ) <= 127.00)) {
                           inputimgbin[3] =inputimgbin[3] + 1;
                        } else if (((inputimgRGB[i][j][0] ) >=128.00) && ((inputimgRGB[i][j][0] ) <= 159.00)) {
                           inputimgbin[4] =inputimgbin[4] + 1;
                        } else if (((inputimgRGB[i][j][0] ) >=160.00) && ((inputimgRGB[i][j][0] ) <= 191.00)) {
                           inputimgbin[5] =inputimgbin[5] + 1;
                        } else if (((inputimgRGB[i][j][0] ) >=192.00) && ((inputimgRGB[i][j][0] ) <= 223.00)) {
                           inputimgbin[6] =inputimgbin[6] + 1;
                        } else if (((inputimgRGB[i][j][0] ) >= 224.00) && ((inputimgRGB[i][j][0] ) <= 255.00)) {
                           inputimgbin[7] =inputimgbin[7] + 1;
                        }
                           //end of R values
                           if (((inputimgRGB[i][j][1] ) >= 0.00) && ((inputimgRGB[i][j][1] ) <= 31.00)) {
                           inputimgbin[8] =inputimgbin[8] + 1;
                        } else if (((inputimgRGB[i][j][1] ) >= 32.00) && ((inputimgRGB[i][j][1] ) <= 63.00)) {
                           inputimgbin[9] =inputimgbin[9] + 1;
                        } else if (((inputimgRGB[i][j][1] ) >=64.00) && ((inputimgRGB[i][j][1] ) <= 95.00)) {
                           inputimgbin[10] =inputimgbin[10] + 1;
                        } else if (((inputimgRGB[i][j][1] ) >=96.00) && ((inputimgRGB[i][j][1] ) <= 127.00)) {
                           inputimgbin[11] =inputimgbin[11] + 1;
                        } else if (((inputimgRGB[i][j][1] ) >=128.00) && ((inputimgRGB[i][j][1] ) <= 159.00)) {
                           inputimgbin[12] =inputimgbin[12] + 1;
                        } else if (((inputimgRGB[i][j][1] ) >=160.00) && ((inputimgRGB[i][j][1] ) <= 191.00)) {
                           inputimgbin[13] =inputimgbin[13] + 1;
                        } else if (((inputimgRGB[i][j][1] ) >=192.00) && ((inputimgRGB[i][j][1] ) <= 223.00)) {
                           inputimgbin[14] =inputimgbin[14] + 1;
                        } else if (((inputimgRGB[i][j][1] ) >= 224.00) && ((inputimgRGB[i][j][1] ) <= 255.00)) {
                           inputimgbin[15] =inputimgbin[15] + 1;
                        }
                           //end of G values
                        if (((inputimgRGB[i][j][2] ) >= 0.00) && ((inputimgRGB[i][j][2] ) <= 31.00)) {
                           inputimgbin[16] =inputimgbin[16] + 1;
                        } else if (((inputimgRGB[i][j][2] ) >= 32.00) && ((inputimgRGB[i][j][2] ) <= 63.00)) {
                           inputimgbin[17] =inputimgbin[17] + 1;
                        } else if (((inputimgRGB[i][j][2] ) >=64.00) && ((inputimgRGB[i][j][2] ) <= 95.00)) {
                           inputimgbin[18] =inputimgbin[18] + 1;
                        } else if (((inputimgRGB[i][j][2] ) >=96.00) && ((inputimgRGB[i][j][2] ) <= 127.00)) {
                           inputimgbin[19] =inputimgbin[19] + 1;
                        } else if (((inputimgRGB[i][j][2] ) >=128.00) && ((inputimgRGB[i][j][2] ) <= 159.00)) {
                           inputimgbin[20] =inputimgbin[20] + 1;
                        } else if (((inputimgRGB[i][j][2] ) >=160.00) && ((inputimgRGB[i][j][2] ) <= 191.00)) {
                           inputimgbin[21] =inputimgbin[21] + 1;
                        } else if (((inputimgRGB[i][j][2] ) >=192.00) && ((inputimgRGB[i][j][2] ) <= 223.00)) {
                           inputimgbin[22] =inputimgbin[22] + 1;
                        } else if (((inputimgRGB[i][j][2] ) >= 224.00) && ((inputimgRGB[i][j][2] ) <= 255.00)) {
                           inputimgbin[23] =inputimgbin[23] + 1;


                    //END OF B values
                    }

                }
}
      System.out.println("finished input img bin split");

                for ( i = 0; i <= 23; i++) {
                    System.out.println(inputimgbin[i]);
                }
 Connection conn = null;

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(url, username, password);



            String sql = "SELECT image from imgtable ";



            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery();

            if (rs == null) {
                totrow = 0;
            } else {
                rs.last();
                totrow = rs.getRow();
            }
            rs.beforeFirst();
            int ino = 0;
            while (rs.next()) {

                image = new File("c:\\temp_images\\java" + ino + ".jpg");

                FileOutputStream fos = new FileOutputStream(image);



                byte[] buffer = new byte[1];





// Get the binary stream of our BLOB data



                InputStream is = rs.getBinaryStream(1);

                while (is.read(buffer) > 0) {
                    fos.write(buffer);

                }



                fos.close();
                bimg = ImageIO.read(image);
                int col = bimg.getWidth();
                System.out.println("col=" + col);
                int row = bimg.getHeight();
                System.out.println("row=" + row);

                
                pixels[ino]=row*col;
                int arRGB[][][] = new int[row][col][3];
                 for ( i = 0; i < row; i++) {
                    for ( j = 0; j < col; j++) {

                        cc = new Color(bimg.getRGB(j, i));

                        arRGB[i][j][0] = cc.getRed();

                        arRGB[i][j][1] = cc.getGreen();

                        arRGB[i][j][2] = cc.getBlue();
                    }
                 }

            for ( i = 0; i < row; i++) {
                    for ( j = 0; j < col; j++) { 	// START OF HUE(H) VALUES;
                        if (((arRGB[i][j][0] ) >= 0.00) && ((arRGB[i][j][0] ) <= 31.00)) {
                           imgbin[ino][0] =imgbin[ino][0] + 1;
                        } else if (((arRGB[i][j][0] ) >= 32.00) && ((arRGB[i][j][0] ) <= 63.00)) {
                           imgbin[ino][1] =imgbin[ino][1] + 1;
                        } else if (((arRGB[i][j][0] ) >=64.00) && ((arRGB[i][j][0] ) <= 95.00)) {
                           imgbin[ino][2] =imgbin[ino][2] + 1;
                        } else if (((arRGB[i][j][0] ) >=96.00) && ((arRGB[i][j][0] ) <= 127.00)) {
                           imgbin[ino][3] =imgbin[ino][3] + 1;
                        } else if (((arRGB[i][j][0] ) >=128.00) && ((arRGB[i][j][0] ) <= 159.00)) {
                           imgbin[ino][4] =imgbin[ino][4] + 1;
                        } else if (((arRGB[i][j][0] ) >=160.00) && ((arRGB[i][j][0] ) <= 191.00)) {
                           imgbin[ino][5] =imgbin[ino][5] + 1;
                        } else if (((arRGB[i][j][0] ) >=192.00) && ((arRGB[i][j][0] ) <= 223.00)) {
                           imgbin[ino][6] =imgbin[ino][6] + 1;
                        } else if (((arRGB[i][j][0] ) >= 224.00) && ((arRGB[i][j][0] ) <= 255.00)) {
                           imgbin[ino][7] =imgbin[ino][7] + 1;
                        }
                           //end of R values
                           if (((arRGB[i][j][1] ) >= 0.00) && ((arRGB[i][j][1] ) <= 31.00)) {
                           imgbin[ino][8] =imgbin[ino][8] + 1;
                        } else if (((arRGB[i][j][1] ) >= 32.00) && ((arRGB[i][j][1] ) <= 63.00)) {
                           imgbin[ino][9] =imgbin[ino][9] + 1;
                        } else if (((arRGB[i][j][1] ) >=64.00) && ((arRGB[i][j][1] ) <= 95.00)) {
                           imgbin[ino][10] =imgbin[ino][10] + 1;
                        } else if (((arRGB[i][j][1] ) >=96.00) && ((arRGB[i][j][1] ) <= 127.00)) {
                           imgbin[ino][11] =imgbin[ino][11] + 1;
                        } else if (((arRGB[i][j][1] ) >=128.00) && ((arRGB[i][j][1] ) <= 159.00)) {
                           imgbin[ino][12] =imgbin[ino][12] + 1;
                        } else if (((arRGB[i][j][1] ) >=160.00) && ((arRGB[i][j][1] ) <= 191.00)) {
                           imgbin[ino][13] =imgbin[ino][13] + 1;
                        } else if (((arRGB[i][j][1] ) >=192.00) && ((arRGB[i][j][1] ) <= 223.00)) {
                           imgbin[ino][14] =imgbin[ino][14] + 1;
                        } else if (((arRGB[i][j][1] ) >= 224.00) && ((arRGB[i][j][1] ) <= 255.00)) {
                           imgbin[ino][15] =imgbin[ino][15] + 1;
                        }
                           //end of G values
                        if (((arRGB[i][j][2] ) >= 0.00) && ((arRGB[i][j][2] ) <= 31.00)) {
                           imgbin[ino][16] =imgbin[ino][16] + 1;
                        } else if (((arRGB[i][j][2] ) >= 32.00) && ((arRGB[i][j][2] ) <= 63.00)) {
                           imgbin[ino][17] =imgbin[ino][17] + 1;
                        } else if (((arRGB[i][j][2] ) >=64.00) && ((arRGB[i][j][2] ) <= 95.00)) {
                           imgbin[ino][18] =imgbin[ino][18] + 1;
                        } else if (((arRGB[i][j][2] ) >=96.00) && ((arRGB[i][j][2] ) <= 127.00)) {
                           imgbin[ino][19] =imgbin[ino][19] + 1;
                        } else if (((arRGB[i][j][2] ) >=128.00) && ((arRGB[i][j][2] ) <= 159.00)) {
                           imgbin[ino][20] =imgbin[ino][20] + 1;
                        } else if (((arRGB[i][j][2] ) >=160.00) && ((arRGB[i][j][2] ) <= 191.00)) {
                           imgbin[ino][21] =imgbin[ino][21] + 1;
                        } else if (((arRGB[i][j][2] ) >=192.00) && ((arRGB[i][j][2] ) <= 223.00)) {
                           imgbin[ino][22] =imgbin[ino][22] + 1;
                        } else if (((arRGB[i][j][2] ) >= 224.00) && ((arRGB[i][j][2] ) <= 255.00)) {
                           imgbin[ino][23] =imgbin[ino][23] + 1;


                    //END OF B values
                    }

                }
}
                ino++;

            } System.out.println(ino);
            float minval[][]=new float[200][24];
            float summinval[]=new float[200];
            double finalval[][]=new double[200][2];
               for(i=0;i<totrow;i++)
            {
                summinval[i]=0;
            }
            for(i=0;i<totrow;i++)
            {
                for(j=0;j<24;j++)
                {
                    minval[i][j]=Math.min(imgbin[i][j],inputimgbin[j]);
                    summinval[i]=summinval[i]+minval[i][j];
                }
          }

            
        for(i=0;i<totrow;i++)
        {
		System.out.println(pixels[i]);
           finalval[i][0]=summinval[i]/(pixels[i]*3);
           finalval[i][1]=i;
		System.out.println(finalval[i][0]);
        }
            double temp,t;
            for(i=0;i<totrow;i++)
            {
                for(j=0;j<totrow-1;j++)
                {
                    if(finalval[j][0]<finalval[j+1][0])
                    {
                       temp=finalval[j][0];
                       finalval[j][0]=finalval[j+1][0];
                       finalval[j+1][0]=temp;
                       t=finalval[j][1];
                       finalval[j][1]=finalval[j+1][1];
                       finalval[j+1][1]=t;
                    }
                }
            }
            for(i=0;i<totrow;i++)
            {
                System.out.println(finalval[i][0]+" imageno "+finalval[i][1]);
            }
             jf=new JFrame("Intersection Method");
              jf.setBackground(Color.MAGENTA);
              cp=jf.getContentPane();
            cp.setLayout(new BorderLayout());
            jf.setSize(1280,1280);
             JPanel jp=new JPanel();
              jp.setLayout(new GridLayout(10,1));
           JLabel input= new JLabel("Input Image is:", JLabel.CENTER);
            Font font=new Font(Font.SANS_SERIF, Font.BOLD,14);
            input.setFont(font);
           jp.add(input);
             ImageScaler imgsclobj=new ImageScaler();

                        imgsclobj.im(jf,jp,inimg, 128, 128, 0.25);
             for(i=0;i<totrow;i++)
             {
             File imgfile = new File("C:\\temp_images\\java"+(int)finalval[i][1]+".jpg");
                      Image in = ImageIO.read(imgfile);
                      String percent=String.valueOf(finalval[i][0]*100);
                      percent=percent.substring(0, 4);
                       loadingframe.dispose();
                      JButton imgbtn=new JButton(percent+" percent match");
            
                        imgsclobj.im(jf,jp,in,imgbtn ,128, 128, 0.25);
             }
                          back=new JButton("<<BACK");
            back.setBackground(Color.orange);
            back.setFont(font);
            back.setSize(100,100);
             back.addActionListener(this);
                        jp.add(back);
                         JScrollPane jsp = new JScrollPane(jp,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
cp.add(jsp,BorderLayout.CENTER);
jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
jf.setVisible(true);
        }catch(Exception e)
{
    e.getMessage();
}
}
 public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource()==back)
            {
            try{jf.dispose();
                new CbirOptions();}
            catch(Exception e)
            {
                e.getMessage();
            }
            }
        }

}
