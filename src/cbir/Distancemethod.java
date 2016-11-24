package cbir;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Distancemethod implements ActionListener {
    
    int i,j;
    int imagecount=0;
     private static String url = "jdbc:oracle:thin:@localhost:1521:scott";
    private static String username = "scott";
    private static String password = "tiger";
    File image = null;
    JButton back=null;
   Color cc=null;
   float inputimgbin[]=new float[24];
    float imgbin[][] = new float[200][24];
   
    int totrow;
    
JFrame jf=null;
    Container cp=null;
    BufferedImage bimg=null;
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
           int inputimgRGB[][][] = new int[rowsinimg][colsinimg][3];
                   float inputimgRGB2HSB[][][] = new float[rowsinimg][colsinimg][3];

                   for(i=0;i<rowsinimg;i++)
                {
                    for(j=0;j<colsinimg;j++)
                    {
                          cc = new Color(inimg.getRGB(j, i));

                        inputimgRGB[i][j][0] = cc.getRed();

                        inputimgRGB[i][j][1] = cc.getGreen();

                       inputimgRGB[i][j][2] = cc.getBlue();
                           Color.RGBtoHSB(inputimgRGB[i][j][0], inputimgRGB[i][j][1], inputimgRGB[i][j][2], inputimgRGB2HSB[i][j]);
                    }
                }
                        for(i=0;i<rowsinimg;i++)
                {
                    for(j=0;j<colsinimg;j++)
                    {
                     if ((( inputimgRGB2HSB[i][j][0] * 360) >= 0.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 19.00)) {
            inputimgbin[0] = inputimgbin[0] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 19.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 39.00)) {
            inputimgbin[1] = inputimgbin[1] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 39.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 59.00)) {
            inputimgbin[2] = inputimgbin[2] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 59.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 79.00)) {
            inputimgbin[3] = inputimgbin[3] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 79.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 99.00)) {
            inputimgbin[4] = inputimgbin[4] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 99.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 119.00)) {
            inputimgbin[5] = inputimgbin[5] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 119.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 139.00)) {
            inputimgbin[6] = inputimgbin[6] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 139.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 159.00)) {
            inputimgbin[7] = inputimgbin[7] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 159.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 179.00)) {
            inputimgbin[8] = inputimgbin[8] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 179.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 199.00)) {
            inputimgbin[9] = inputimgbin[9] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 199.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 219.00)) {
            inputimgbin[10] = inputimgbin[10] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 219.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 239.00)) {
            inputimgbin[11] = inputimgbin[11] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 239.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 259.00)) {
            inputimgbin[12] = inputimgbin[12] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 259.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 279.00)) {
            inputimgbin[13] = inputimgbin[13] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 279.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 299.00)) {
            inputimgbin[14] = inputimgbin[14] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 299.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 319.00)) {
            inputimgbin[15] = inputimgbin[15] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 319.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 339.00)) {
            inputimgbin[16] = inputimgbin[16] + 1;
        } else if ((( inputimgRGB2HSB[i][j][0] * 360) > 339.00) && (( inputimgRGB2HSB[i][j][0] * 360) <= 359.00)) {
            inputimgbin[17] = inputimgbin[17] + 1;
        }


//end of hue(h) split



        if ((( inputimgRGB2HSB[i][j][1] * 100) >= 0.00) && (( inputimgRGB2HSB[i][j][1] * 100) <= 30.00)) {
            inputimgbin[18] = inputimgbin[18] + 1;
        } else if ((( inputimgRGB2HSB[i][j][1] * 100) > 30.00) && (( inputimgRGB2HSB[i][j][1] * 100) <= 60.00)) {
            inputimgbin[19] = inputimgbin[19] + 1;
        } else if ((( inputimgRGB2HSB[i][j][1] * 100) > 60.00) && (( inputimgRGB2HSB[i][j][1] * 100) <= 100.00)) {
            inputimgbin[20] = inputimgbin[20] + 1;
        }//else if ((( inputimgRGB2HSB[i][j][1] * 100) > 75.00) && (( inputimgRGB2HSB[i][j][1] * 100) <= 100.00)) {
          //  inputimgbin[21] = inputimgbin[21] + 1;
       // }



//end of sturate color split


        if ((( inputimgRGB2HSB[i][j][2] * 100) >= 0.00) && (( inputimgRGB2HSB[i][j][2] * 100) <= 30.00)) {
            inputimgbin[21] = inputimgbin[21] + 1;
        } else if ((( inputimgRGB2HSB[i][j][2] * 100) > 30.00) && (( inputimgRGB2HSB[i][j][2] * 100) <= 60.00)) {
            inputimgbin[22] = inputimgbin[22] + 1;
        }else if ((( inputimgRGB2HSB[i][j][2] * 100) > 60.00) && (( inputimgRGB2HSB[i][j][2] * 100) <= 100.00)) {
            inputimgbin[23] = inputimgbin[23] + 1;
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
                 image = new File("c:\\temp_images\\java"+ino+".jpg");

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
                int arRGB[][][] = new int[row][col][3];
                     float arRGB2HSB[][][] = new float[row][col][3];
                 for ( i = 0; i < row; i++) {
                    for ( j = 0; j < col; j++) {
                       cc = new Color(bimg.getRGB(j, i));
                        arRGB[i][j][0] = cc.getRed();
                        arRGB[i][j][1] = cc.getGreen();
                        arRGB[i][j][2] = cc.getBlue();
                            Color.RGBtoHSB(arRGB[i][j][0], arRGB[i][j][1], arRGB[i][j][2], arRGB2HSB[i][j]);
                    }
                 }
                             for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) { 	// START OF HUE(H) VALUES;
                        if (((arRGB2HSB[i][j][0] * 360) >= 0.00) && ((arRGB2HSB[i][j][0] * 360) <= 19.00)) {
                            imgbin[ino][0] = imgbin[ino][0] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 19.00) && ((arRGB2HSB[i][j][0] * 360) <= 39.00)) {
                            imgbin[ino][1] = imgbin[ino][1] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 39.00) && ((arRGB2HSB[i][j][0] * 360) <= 59.00)) {
                            imgbin[ino][2] = imgbin[ino][2] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 59.00) && ((arRGB2HSB[i][j][0] * 360) <= 79.00)) {
                            imgbin[ino][3] = imgbin[ino][3] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 79.00) && ((arRGB2HSB[i][j][0] * 360) <= 99.00)) {
                            imgbin[ino][4] = imgbin[ino][4] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 99.00) && ((arRGB2HSB[i][j][0] * 360) <= 119.00)) {
                            imgbin[ino][5] = imgbin[ino][5] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 119.00) && ((arRGB2HSB[i][j][0] * 360) <= 139.00)) {
                            imgbin[ino][6] = imgbin[ino][6] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 139.00) && ((arRGB2HSB[i][j][0] * 360) <= 159.00)) {
                            imgbin[ino][7] = imgbin[ino][7] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 159.00) && ((arRGB2HSB[i][j][0] * 360) <= 179.00)) {
                            imgbin[ino][8] = imgbin[ino][8] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 179.00) && ((arRGB2HSB[i][j][0] * 360) <= 199.00)) {
                            imgbin[ino][9] = imgbin[ino][9] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 199.00) && ((arRGB2HSB[i][j][0] * 360) <= 219.00)) {
                            imgbin[ino][10] = imgbin[ino][10] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 219.00) && ((arRGB2HSB[i][j][0] * 360) <= 239.00)) {
                            imgbin[ino][11] = imgbin[ino][11] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 239.00) && ((arRGB2HSB[i][j][0] * 360) <= 259.00)) {
                            imgbin[ino][12] = imgbin[ino][12] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 259.00) && ((arRGB2HSB[i][j][0] * 360) <= 279.00)) {
                            imgbin[ino][13] = imgbin[ino][13] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 279.00) && ((arRGB2HSB[i][j][0] * 360) <= 299.00)) {
                            imgbin[ino][14] = imgbin[ino][14] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 299.00) && ((arRGB2HSB[i][j][0] * 360) <= 319.00)) {
                            imgbin[ino][15] = imgbin[ino][15] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 319.00) && ((arRGB2HSB[i][j][0] * 360) <= 339.00)) {
                            imgbin[ino][16] = imgbin[ino][16] + 1;
                        } else if (((arRGB2HSB[i][j][0] * 360) > 339.00) && ((arRGB2HSB[i][j][0] * 360) <= 359.00)) {
                            imgbin[ino][17] = imgbin[ino][17] + 1;
                        }

                        // END OF HUE(H) VALUES AND START OF SATURATE(S) VALUES;


                        if (((arRGB2HSB[i][j][1] * 100) >= 0.00) && ((arRGB2HSB[i][j][1] * 100) <= 30.00)) {
                            imgbin[ino][18] = imgbin[ino][18] + 1;
                        } else if (((arRGB2HSB[i][j][1] * 100) > 30.00) && ((arRGB2HSB[i][j][1] * 100) <= 60.00)) {
                            imgbin[ino][19] = imgbin[ino][19] + 1;
                        } else if (((arRGB2HSB[i][j][1] * 100) > 60.00) && ((arRGB2HSB[i][j][1] * 100) <= 100.00)) {
                            imgbin[ino][20] = imgbin[ino][20] + 1;
                        }//else if (((arRGB2HSB[i][j][1] * 100) > 75.00) && ((arRGB2HSB[i][j][1] * 100) <= 100.00)) {
                           // imgbin[ino][21] = imgbin[ino][21] + 1;
                        //}

                        // END OF SATURATE(S) VALUES AND START OF B VALUES;

                        if (((arRGB2HSB[i][j][2] * 100) >= 0.00) && ((arRGB2HSB[i][j][2] * 100) <= 30.00)) {
                            imgbin[ino][21] = imgbin[ino][21] + 1;
                        } else if (((arRGB2HSB[i][j][2] * 100) > 30.00) && ((arRGB2HSB[i][j][2] * 100) <= 60.00)) {
                            imgbin[ino][22] = imgbin[ino][22] + 1;
                        }else if (((arRGB2HSB[i][j][2] * 100) > 60.00) && ((arRGB2HSB[i][j][2] * 100) <= 100.00)) {
                            imgbin[ino][23] = imgbin[ino][23] + 1;
                        }

                    //END OF B VALUES;
                    }

                }
            
                 ino++;

            } System.out.println(ino);
                    float diffbin[][] = new float[200][24];

                    for(i=0;i<totrow;i++)
                    {
                        for(j=0;j<24;j++)
                        {
                            diffbin[i][j]= (float)Math.pow(Math.abs(inputimgbin[j]-imgbin[i][j]),2);
                        }
                    }

                   float imgsum[][]=new float[totrow][2];
                   for(i=0;i<totrow;i++)
                   {
                       imgsum[i][0]=0;
                   }
                   for(i=0;i<totrow;i++)
                   {
                       for(j=0;j<24;j++)
                       {
                           imgsum[i][0]=imgsum[i][0]+diffbin[i][j];
                       }
                       imgsum[i][1]=i;
                   }
                   for(i=0;i<totrow;i++)
                   {
                       imgsum[i][0]=(float)Math.sqrt(imgsum[i][0]);
                   }
                    for(i=0;i<totrow;i++)
                    {
                        System.out.println("image "+i+"="+imgsum[i][0]/3);
                    }
            jf=new JFrame("Euclidean Distance Method");
            jf.setBackground(Color.MAGENTA);
            cp=jf.getContentPane();
            jf.setSize(1280,1280);
            cp.setLayout(new BorderLayout());
             JPanel jp=new JPanel();
              jp.setLayout(new GridLayout(10,1));
           JLabel input= new JLabel("Input Image is:", JLabel.CENTER);
            Font font=new Font(Font.SANS_SERIF, Font.BOLD,14);
            input.setFont(font);
           jp.add(input);
             ImageScaler imgsclobj=new ImageScaler();

                        imgsclobj.im(jf,jp,inimg, 128, 128, 0.25);
                   float temp,t;
                        for(i=0;i<totrow;i++)
            {
                for(j=0;j<totrow-1;j++)
                {
                    if(imgsum[j][0]>imgsum[j+1][0])
                    {
                       temp=imgsum[j][0];
                       imgsum[j][0]=imgsum[j+1][0];
                       imgsum[j+1][0]=temp;
                       t=imgsum[j][1];
                       imgsum[j][1]=imgsum[j+1][1];
                       imgsum[j+1][1]=t;
                    }
                }
            }
              for(i=0;i<totrow;i++)
                    {
                        System.out.println("image "+(int)imgsum[i][1]+"="+imgsum[i][0]/3);
                    }

                        for(i=0;i<totrow;i++)
              {

                      // if((imgsum[i]>=0.0)&&(imgsum[i]<=2000))
                //  {
                      imagecount++;
                      String percent=String.valueOf(100-(imgsum[i][0]/(3*(262144))*100));
                      percent=percent.substring(0,3);
                     // if(percent<20.0)
                     // {
                         File imgfile = new File("C:\\temp_images\\java"+(int)imgsum[i][1]+".jpg");
                      Image in = ImageIO.read(imgfile);

                      loadingframe.dispose();
                       JButton imgbtn=new JButton(percent+"%");
                        imgsclobj.im(jf,jp,in,imgbtn, 128, 128, 0.25);
                     // }

                //  }
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
   } catch (SQLException e) {

            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (conn != null && !conn.isClosed()) {

                conn.close();

            }

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
