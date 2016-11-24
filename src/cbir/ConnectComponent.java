package cbir;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JProgressBar;

public class ConnectComponent implements ActionListener
{  private static String url = "jdbc:oracle:thin:@localhost:1521:scott";
   private static String username = "scott";    
   private static String password = "tiger";   
  File image = null;  
 BufferedImage bimg = null;   
Color cc = null;     
int totrow;   
JFrame jf,jf1 = null;   
JButton back=null;     
long imagebin[] =null;     
int regions,col,row;    
int x = 0,no_of_bins=24;      
BufferedImage newImage = null;      
Boolean imgfound=false;       
JLabel no_of_img_found=null;       
public void connectcomponentsearch(Color sc) throws Exception 
{        int imagecount=0;        
         int colorRGB[] = new int[3];        
         int colorbin[]=new int[no_of_bins];        
	float colorRGB2HSB[] = new float[3];                
	System.out.println("in color RGB values");        
	colorRGB[0] = sc.getRed();        
	colorRGB[1] = sc.getGreen();        
	colorRGB[2] = sc.getBlue();        
	Color.RGBtoHSB(colorRGB[0], colorRGB[1], colorRGB[2], colorRGB2HSB);
      // color splitting into colorbin        
	if (((colorRGB2HSB[0] * 360) >= 0.00) && ((colorRGB2HSB[0] * 360) <= 19.00)) 
	{  colorbin[0] = colorbin[0] + 1;     } 
	else if (((colorRGB2HSB[0] * 360) > 19.00) && ((colorRGB2HSB[0] * 360) <= 39.00)) 
         {            
		colorbin[1] = colorbin[1] + 1;        
	} 
	else if (((colorRGB2HSB[0] * 360) > 39.00) && ((colorRGB2HSB[0] * 360) <= 59.00)) 
	{            colorbin[2] = colorbin[2] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 59.00) && ((colorRGB2HSB[0] * 360) <= 79.00)) 
       {     colorbin[3] = colorbin[3] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 79.00) && ((colorRGB2HSB[0] * 360) <= 99.00)) 
	{            colorbin[4] = colorbin[4] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 99.00) && ((colorRGB2HSB[0] * 360) <= 119.00))
	 {            colorbin[5] = colorbin[5] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 119.00) && ((colorRGB2HSB[0] * 360) <= 139.00)) 
	{            colorbin[6] = colorbin[6] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 139.00) && ((colorRGB2HSB[0] * 360) <= 159.00)) 
	{            colorbin[7] = colorbin[7] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 159.00) && ((colorRGB2HSB[0] * 360) <= 179.00))
	 {            colorbin[8] = colorbin[8] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 179.00) && ((colorRGB2HSB[0] * 360) <= 199.00)) 
	{            colorbin[9] = colorbin[9] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 199.00) && ((colorRGB2HSB[0] * 360) <= 219.00)) 
	{            colorbin[10] = colorbin[10] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 219.00) && ((colorRGB2HSB[0] * 360) <= 239.00)) 
	{            colorbin[11] = colorbin[11] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 239.00) && ((colorRGB2HSB[0] * 360) <= 259.00)) 
	{            colorbin[12] = colorbin[12] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 259.00) && ((colorRGB2HSB[0] * 360) <= 279.00)) 
	{            colorbin[13] = colorbin[13] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 279.00) && ((colorRGB2HSB[0] * 360) <= 299.00)) 
	{            colorbin[14] = colorbin[14] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 299.00) && ((colorRGB2HSB[0] * 360) <= 319.00)) 
	{            colorbin[15] = colorbin[15] + 1;        } 
	else if (((colorRGB2HSB[0] * 360) > 319.00) && ((colorRGB2HSB[0] * 360) <= 339.00)) 
	{            colorbin[16] = colorbin[16] + 1;        }
	 else if (((colorRGB2HSB[0] * 360) > 339.00) && ((colorRGB2HSB[0] * 360) <= 359.00)) 
	{            colorbin[17] = colorbin[17] + 1;        } //end of hue(h) split        
	if (((colorRGB2HSB[1] * 100) >= 0.00) && ((colorRGB2HSB[1] * 100) <= 20.00)) 
	{            colorbin[18] = colorbin[18] + 1;        }  
	else if (((colorRGB2HSB[1] * 100) > 20.00) && ((colorRGB2HSB[1] * 100) <= 80.00)) 
	{            colorbin[19] = colorbin[19] + 1;        }
	else if (((colorRGB2HSB[1] * 100) > 80.00) && ((colorRGB2HSB[1] * 100) <= 100.00)) 
	{           colorbin[20] = colorbin[20] + 1;        }//end of sturate color split       
	 if (((colorRGB2HSB[2] * 100) >= 0.00) && ((colorRGB2HSB[2] * 100) <= 20.00)) 
	{            colorbin[21] = colorbin[21] + 1;        } 
	else if (((colorRGB2HSB[2] * 100) > 20.00) && ((colorRGB2HSB[2] * 100) <= 80.00))
	 {            colorbin[22] = colorbin[22] + 1;        }        
	else if (((colorRGB2HSB[2] * 100) > 80.00) && ((colorRGB2HSB[2] * 100) <= 100.00)) 
	{            colorbin[23] = colorbin[23] + 1;        }       
	System.out.println("finished color bin split");        
	for (int i = 0; i <no_of_bins; i++) 
 	{           System.out.println(colorbin[i]);        } 
	JProgressBar pbar=new JProgressBar(); JFrame loadingframe = new JFrame("Processing...");  
	pbar.setBorderPainted(true); pbar.setBackground(Color.BLACK); 
	pbar.setForeground(Color.white); pbar.setIndeterminate(true); 
	pbar.setSize(10,10); pbar.setString("Loading..Please wait."); 
	pbar.setStringPainted(true); loadingframe.setSize(1280,1280); 
	loadingframe.getContentPane().setLayout(null); 
	loadingframe.getContentPane().setBackground(Color.cyan);  
	pbar.setBounds(420,330,150,50);  
	loadingframe.getContentPane().add(pbar);  
	loadingframe.setVisible(true);  
	loadingframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     
	Connection conn = null;     
	int check=1; 
	jf =new JFrame("Connect Component Method");       
	 try {            
		Class.forName("oracle.jdbc.driver.OracleDriver");            
		conn = DriverManager.getConnection(url, username, password);           
		String sql = "SELECT image from imgtable ";          
	        PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);            
		ResultSet rs = stmt.executeQuery();            
		if (rs == null) 
		{
                    totrow = 0;   }
		 else {                
			rs.last();                
			totrow = rs.getRow();            
		        }            
		rs.beforeFirst();            
		int ino = 0;                   
		while (rs.next()) 
		{                
			imgfound=false;                
			image = new File("e:\\uploadedimages\\java" + ino + ".jpg");               
		        FileOutputStream fos = new FileOutputStream(image);                
			byte[] buffer = new byte[1];// Get the binary stream of our BLOB data 
			InputStream is = rs.getBinaryStream(1); 
                      while (is.read(buffer) > 0) 
			{                    fos.write(buffer);                }       
		           fos.close();               
			 bimg = ImageIO.read(image);                
			col = bimg.getWidth();                
			System.out.println("col=" + col);               
			 row = bimg.getHeight();               
			System.out.println("row=" + row);                
			int arRGB[][][] = new int[row][col][3];                
			float arRGB2HSB[][][] = new float[row][col][3];                
			int imgarray[][]= new int[row][col];              //  int bin[][]=new int[totrow][no_of_bins];                
			int concomp[][]=new int[row][col];      
		          for (int i = 0; i < row; i++) 
				{  for (int j = 0; j < col; j++) 
				    {   cc = new Color(bimg.getRGB(j, i));
                                        arRGB[i][j][0] = cc.getRed();
                                       arRGB[i][j][1] = cc.getGreen();
                                       arRGB[i][j][2] = cc.getBlue(); 
                                       Color.RGBtoHSB(arRGB[i][j][0], arRGB[i][j][1], arRGB[i][j][2], arRGB2HSB[i][j]);                    
                                   }              
                                }
			int check1,check2;                   
			for (int i = 0; i < row; i++) 
			{                    
				for (int j = 0; j < col; j++)
				 {                        
					check1=0; check2=0;                        // START OF HUE(H) VALUES;     
		                   if (((arRGB2HSB[i][j][0] * 360) >= 0.00) && ((arRGB2HSB[i][j][0] * 360) <= 19.00) &&(colorbin[0]==1))
				 {                          
					check1=1;                        
				} 
			   else if (((arRGB2HSB[i][j][0] * 360) > 19.00) && ((arRGB2HSB[i][j][0] * 360) <= 39.00)&&(colorbin[1]==1)) 
                {                           
                 check1=1;                         
               } 
          else if (((arRGB2HSB[i][j][0] * 360) > 39.00) && ((arRGB2HSB[i][j][0] * 360) <= 59.00)&&(colorbin[2]==1)) 
            {               check1=1;                      } 
        else if (((arRGB2HSB[i][j][0] * 360) > 59.00) && ((arRGB2HSB[i][j][0] * 360) <= 79.00)&&(colorbin[3]==1)) 
     {                            check1=1;                        } 
  else if (((arRGB2HSB[i][j][0] * 360) > 79.00) && ((arRGB2HSB[i][j][0] * 360) <= 99.00)&&(colorbin[4]==1)) 
  {                            check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 99.00) && ((arRGB2HSB[i][j][0] * 360) <= 119.00)&&(colorbin[5]==1)) 
{                             check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 119.00) && ((arRGB2HSB[i][j][0] * 360) <= 139.00)&&(colorbin[6]==1)) 
{                            check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 139.00) && ((arRGB2HSB[i][j][0] * 360) <= 159.00)&&(colorbin[7]==1)) 
{                           check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 159.00) && ((arRGB2HSB[i][j][0] * 360) <= 179.00)&&(colorbin[8]==1))
 {                           check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 179.00) && ((arRGB2HSB[i][j][0] * 360) <= 199.00)&&(colorbin[9]==1)) 
{                            check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 199.00) && ((arRGB2HSB[i][j][0] * 360) <= 219.00)&&(colorbin[10]==1)) 
{                           check1=1;                       } 
else if (((arRGB2HSB[i][j][0] * 360) > 219.00) && ((arRGB2HSB[i][j][0] * 360) <= 239.00)&&(colorbin[11]==1)) 
{                            check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 239.00) && ((arRGB2HSB[i][j][0] * 360) <= 259.00)&&(colorbin[12]==1)) 
{                           check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 259.00) && ((arRGB2HSB[i][j][0] * 360) <= 279.00)&&(colorbin[13]==1)) 
{                          check1=1;                        }
 else if (((arRGB2HSB[i][j][0] * 360) > 279.00) && ((arRGB2HSB[i][j][0] * 360) <= 299.00)&&(colorbin[14]==1)) 
{                              check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 299.00) && ((arRGB2HSB[i][j][0] * 360) <= 319.00)&&(colorbin[15]==1)) 
{                             check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 319.00) && ((arRGB2HSB[i][j][0] * 360) <= 339.00)&&(colorbin[16]==1)) 
{                            check1=1;                        } 
else if (((arRGB2HSB[i][j][0] * 360) > 339.00) && ((arRGB2HSB[i][j][0] * 360) <= 359.00)&&(colorbin[17]==1)) 
{                             check1=1;                        }                       // END OF HUE(H) VALUES AND START OF SATURATE(S) VALUES;                       
 if (((arRGB2HSB[i][j][1] * 100) >= 0.00) && ((arRGB2HSB[i][j][1] * 100) <= 20.00)&&(colorbin[18]==1)&&(check1==1)) 
{                             check2=1;}                       
 else  if (((arRGB2HSB[i][j][1] * 100) >= 20.00) && ((arRGB2HSB[i][j][1] * 100) <= 80.00)&&(colorbin[19]==1)&&(check1==1)) 
{                            check2=1;                        } 
else if (((arRGB2HSB[i][j][1] * 100) > 80.00) && ((arRGB2HSB[i][j][1] * 100) <= 100.00)&&(colorbin[20]==1)&&(check1==1)) 
{                            check2=1;                        }                       
// END OF SATURATE(S) VALUES AND START OF B VALUES;
if (((arRGB2HSB[i][j][2] * 100) >= 0.00) && ((arRGB2HSB[i][j][2] * 100) <= 20.00)&&(colorbin[21]==1)&&(check2==1)) 
{                           imgarray[i][j]= 1;                        }  
else if (((arRGB2HSB[i][j][2] * 100) > 20.00) && ((arRGB2HSB[i][j][2] * 100) <= 80.00)&&(colorbin[22]==1)&&(check2==1)) 
{                           imgarray[i][j]= 1;                        }                       
 else if (((arRGB2HSB[i][j][2] * 100) > 80.00) && ((arRGB2HSB[i][j][2] * 100) <= 100.00)&&(colorbin[23]==1)&&(check2==1)) 
{                           imgarray[i][j]= 1;                        }                    
//END OF B VALUES;                    
}                
}    
LabelImage lblimgobj1= new LabelImage();      
concomp=lblimgobj1.labelImage(imgarray);  
regions=lblimgobj1.lab-1;  
System.out.println("Total regions:"); 
 System.out.println(regions);
if(regions >0)
{  imagebin = new long[regions];  
	for(int k=0;k<regions;k++)  
	{   for(int i=0;i<row;i++)  
		{      for(int j=0;j<col;j++)      
		{        if(concomp[i][j]==(k+1))        
		{         imagebin[k]++;        }                
}  }     } 
	for(int i=0;i<regions;i++) 
	{     System.out.println(imagebin[i]); }  
	long max=imagebin[0];  for(int i=1;i<regions;i++) 
	{     if(imagebin[i]>max)     
	{         max=imagebin[i];     
	} }
	int pixels=row*col;
       System.out.println(pixels+"ryyr"+(pixels*0.5));           
        if(max>=20000)          
        {              imgfound=true;           }     
	if(imgfound==true)    
	{          System.out.println("image retrieved" + (ino+1));
                    if(check==1)          
                      {            
                        JLabel promptlbl = new JLabel("The Following images were Found with the color specified by you:");
                         promptlbl.setOpaque(true);            
			promptlbl.setFont(new Font("Serif", Font.PLAIN, 24));          
			  promptlbl.setBackground(Color.magenta);           
			promptlbl.setForeground(Color.orange);            
			promptlbl.setBounds(300, 50, 600, 100);            
			jf.getContentPane().add(promptlbl);            
			check=0;          
		        }            
		imagecount++;            
		Image in = ImageIO.read(image);            
		ImageScaler imgsclobj=new ImageScaler();            
		loadingframe.dispose();            
		imgsclobj.im(jf,in, 128, 128, 0.25);                               
	}
     }  
  ino++;         
 }              
 Font font1=new Font(Font.MONOSPACED,Font.BOLD,24);                 
   if(imagecount==0)                        
      {                             
         loadingframe.dispose();                             
         JLabel nomatchfound= new JLabel("Sorry No Image Found with specified Color", JLabel.CENTER);
          nomatchfound.setOpaque(true);                             
          nomatchfound.setBackground(Color.PINK);                            
         nomatchfound.setForeground(Color.DARK_GRAY);                             
         nomatchfound.setFont(font1);                            
         nomatchfound.setBounds(150,150,700,100 );                             
	jf.getContentPane().setLayout(null);                            
	jf.getContentPane().add(nomatchfound);                             
	jf.setSize(1280,1280);                            
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                             
	jf.setVisible(true);                        
      }              
       no_of_img_found= new JLabel("Total images found: "+imagecount,JLabel.CENTER);
       no_of_img_found.setOpaque(true);                            
       no_of_img_found.setBackground(Color.red);                             
       no_of_img_found.setForeground(Color.white);                            
       no_of_img_found.setFont(font1);                             
      jf.getContentPane().add(no_of_img_found);            
        back=new JButton("<<Back");            
	 back.addActionListener(this);
        jf.getContentPane().add(back);               
        back.setBounds(450,350,80,80);                
 	back.addActionListener(this);             
	jf.setVisible(true);        
  }
catch (SQLException e) 
{            e.printStackTrace();        } 
catch (Exception e) 
 {            e.printStackTrace();        } 
finally {           
 if (conn != null && !conn.isClosed()) 
{                conn.close();            }        
}}        
public void actionPerformed(ActionEvent ae)
   {            if(ae.getSource()==back)            
       {            try{new CbirOptions();           
                    jf.dispose();                
       }            catch(Exception e)            
        {                e.getMessage();            
}            }        }       }