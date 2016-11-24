package cbir;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.border.*;
import  javax.swing.filechooser.FileNameExtensionFilter;

 class CbirOptions extends JFrame implements ActionListener
{
        // static count=0;
	JButton imgmchbtn=new JButton("Image Matching");
	JButton cbsbtn=new JButton("Colour based search");
	JButton instbtn=new JButton("Insert an image");
	JButton viewallbtn=new JButton("View all images");
        JButton exitbtn=new JButton("Exit");
	ImageIcon icon1=new ImageIcon("C:\\cbir.gif");
	JLabel lbl1 =new JLabel();
        Container cp=getContentPane();
	public CbirOptions() throws Exception
	{
	super("Main Screen");
	//calling jframe const.
        //count++;
        lbl1.setIcon(icon1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	cp.setLayout(null);
        cp.add(lbl1);
	cp.add(imgmchbtn);
	cp.add(cbsbtn);
	cp.add(instbtn);
	cp.add(viewallbtn);
        cp.add(exitbtn);
	//ButtonGroup bg=new ButtonGroup();
	//bg.add(b1);
	//bg.add(b2);
	//bg.add(b3);
       // bg.add(b4);
	imgmchbtn.addActionListener(this);
	cbsbtn.addActionListener(this);
	instbtn.addActionListener(this);
	viewallbtn.addActionListener(this);
        exitbtn.addActionListener(this);
	setSize(1280,1280);
	setVisible(true);
        cp.setBackground(Color.pink);
	lbl1.setBounds(300,50,700,100);
        imgmchbtn.setBounds(400,150,200,100);
	cbsbtn.setBounds(500,250,200,100);
	instbtn.setBounds(600,350,200,100);
	viewallbtn.setBounds(700,450,200,100);
        exitbtn.setBounds(800,550,200,100);

	}
	


    public void actionPerformed(ActionEvent ae) 
   {
	
	if((ae.getSource()==cbsbtn))
      {
      //  Color initialBackground = ok.getBackground();
        
       
	 try{ dispose();
             new colorretrieve();}
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
       }
		if((ae.getSource()==viewallbtn))
                  {
			try{
                                (new ViewAllImages()).View();
                                dispose();
                        }
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
  		  }	    
   }
        if(ae.getSource()==instbtn)
        {
                dispose();
                  new insertimg();
        }
        if(ae.getSource()==imgmchbtn)
        {
            dispose();
            new ImageMatch();

        }
        if(ae.getSource()==exitbtn)
        {
            int choice=JOptionPane.showConfirmDialog(this,"Are you sure you want to exit?");
            if(choice==JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            

        }
    }
 }


class colorretrieve extends JFrame implements ActionListener

 {
  		
	        JLabel promptlbl = new JLabel("Select one of the following methods  for color based retrieval:");
                JButton fstbtn =new JButton("Histogram Method");
		JButton scndbtn =new JButton("Connect Component Method");
                ImageIcon icon = new ImageIcon( "c:\\cbirimage2.jpg" ) ;
		JLabel l = new JLabel( "", icon, SwingConstants.LEFT );
		Container c=getContentPane();
                JButton back = new JButton("<<Back");

		 colorretrieve()
  			{
				super("Color Search Method Selection Screen");
				c.setLayout(null);
                                promptlbl.setFont(new Font("Serif", Font.PLAIN, 24));
                                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                promptlbl.setOpaque(true);
 				promptlbl.setBackground(Color.magenta);
                                promptlbl.setForeground(Color.orange);
                                promptlbl.setBounds(300, 50, 600, 100);
                                c.add(promptlbl);
                                l.setBounds(100,300 , 300, 220);
                                c.add(l);
                                c.setBackground(Color.pink);
                                fstbtn.setBounds(600, 400, 200, 50);
                                c.add(fstbtn);
				fstbtn.addActionListener(this);
                                scndbtn.setBounds(600, 300, 200, 50);
				c.add(scndbtn);
                                scndbtn.addActionListener(this);
                                back.setBounds(600,500,100,50);
                                c.add(back);
                                back.addActionListener(this);
				//l.setUI( new MultiLineLabelUI() );
		                promptlbl.setBorder( new EtchedBorder() );
				setSize(1280,1280);
				setVisible(true);
				
			}
	   
		public void actionPerformed(ActionEvent a) 
                    {
	
	                 if((a.getSource()==fstbtn))
 			  {
				 Color inputcolor = JColorChooser.showDialog(null,"Choose a Color", null);
                               if(inputcolor==null)
                               {}
                               else
                               {
		          try{
                               dispose();
                              (new HistogramMethod()).histogramsearch(inputcolor);
                              
                               }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
                         }}
                          if((a.getSource()==scndbtn))
 			  {
				 Color inputcolor = JColorChooser.showDialog(null,"Choose a Color", null);
		        if(inputcolor==null)
                        {}
                        else{
                                 try{
                               dispose();
                          (new ConnectComponent()).connectcomponentsearch(inputcolor);
                          
                          }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
       }}
                          
                         if(a.getSource()==back)
                         {
                             try{
                                 dispose();

                             new CbirOptions();
                             }catch(Exception e)
                             {
                                 e.getMessage();
                             }
                         }
  }

}
class insertimg extends JFrame implements ActionListener
  {  
     
       JLabel heading2=new JLabel("         Click on Choose Image Button to choose the image.");
      JLabel heading3=new JLabel("  Click on Insert Image Button to insert image into database.");
      JButton imgchoose = new JButton("Choose Image");
     ImageIcon icon = new ImageIcon( "c:\\cbirimage4.jpg" ) ;
		JLabel l = new JLabel( "", icon, SwingConstants.LEFT );
	Container cn=getContentPane();
        JButton instimg=new JButton("Insert Image");
         JButton back=new JButton("<<Back");
        File image=null;
        String imagename=null;
         BufferedImage newImage=null;
        
        insertimg()
		{
                  super("Inserting Images into Database");
                  l.setBounds(10,10,420,550);
                                cn.add(l);
                  back.addActionListener(this);
                  imgchoose.addActionListener(this);
                  instimg.addActionListener(this);
                  cn.setLayout(null);
                  cn.setBackground(Color.pink);
                  heading2.setFont(new Font("Serif", Font.PLAIN, 24));
                  heading2.setOpaque(true);
                  heading2.setBounds(500,50 ,600, 50);
		  heading2.setBackground(Color.magenta);
                  heading2.setForeground(Color.orange);
                  cn.add(heading2);
                   imgchoose.setBounds(800, 200, 200, 50);
                  cn.add(imgchoose);
                  heading3.setOpaque(true);
                  heading3.setBackground(Color.magenta);
                  heading3.setForeground(Color.orange);
                  heading3.setFont(new Font("Serif", Font.PLAIN, 24));
                   heading3.setBounds(500, 300, 600, 50);
                  cn.add(heading3);
                   instimg.setBounds(800, 400, 200, 50);
                   instimg.setEnabled(false);
                  cn.add(instimg);
                   back.setBounds(800, 500, 200, 50);
                  cn.add(back);
                  setSize(1280,1280);
		  setVisible(true);
		}
			
	 public void actionPerformed(ActionEvent ae)
                    {
			if(ae.getSource()==imgchoose)
                        {
                            JFileChooser chooser =new JFileChooser("E:");
                            chooser.setBackground(Color.MAGENTA);


                             FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(null);
    if(returnVal == JFileChooser.APPROVE_OPTION)
    {
         image=chooser.getSelectedFile();
         imagename=chooser.getSelectedFile().getAbsolutePath();

    
    instimg.setEnabled(true);
    try{
                              BufferedImage img=ImageIO.read(image);
                              int width=img.getWidth();
                              int height=img.getHeight();
                              if((width!=512)||(height!=512))
                              {
                            image=  imgscale(img,512,512,(double)512/width);
                              }
    }catch(Exception e)
    {
      JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
    }
    }   
    else
    {}
                        }

                        if(ae.getSource()==instimg)
                        {
                            instimg.setEnabled(false);

                            new OnlyInsertImage(image,imagename);
                             dispose();

                        }
                        if(ae.getSource()==back)
                        {
                           try
                           {
                               dispose();
                               new CbirOptions();
                           }catch(Exception ex)
                           {
                               JOptionPane.showMessageDialog(null,ex.getMessage(),"error",JOptionPane.ERROR);
                           }
                        }
         }
          public File imgscale(Image img, int newWidth, int newHeight, double sc) {
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
        File scaledfile=new File("c:\\temp_images\\image1.jpg");
        try{
      javax.imageio.ImageIO.write(newImage, "JPG",scaledfile );
   }catch(Exception e)
    {
       e.getMessage();
    }
    return scaledfile;
    }


   }

class firstframe extends JFrame implements ActionListener
 {
  ImageIcon icon1 = new ImageIcon( "c:\\cbirimage1.jpg" ) ;
	JLabel l1 = new JLabel( "", icon1, SwingConstants.LEFT );
      
		
	JLabel l = new JLabel( "    CBIR- COLOUR BASED IMAGE READER" );
      
	JButton next1 =new JButton("Next>>>>..."); 
	Container c1=getContentPane();
		
          firstframe()
	   {
		super("Welcome");
                c1.setLayout(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		next1.addActionListener(this);
		l.setBounds(170,20,1000,150);
                c1.add(l);
                l1.setBounds(500, 200, 350, 220);
                c1.add(l1);
                next1.setBounds(630,500,80,80);
                c1.add(next1);
                l.setFont(new Font("Serif", Font.PLAIN, 48));
		next1.setBackground(Color.ORANGE);
		l.setBackground(Color.magenta);
		l.setOpaque(true);
		l.setForeground(Color.yellow);
		c1.setBackground(Color.pink);
		l.setBorder( new EtchedBorder() );
		setSize(1280,1280);
		setVisible(true);
            }


		public void actionPerformed(ActionEvent an) 
                    {
	
	                 if((an.getSource()==next1))
 			  {
				try{ dispose();
                                    new CbirOptions();}
                                catch(Exception e)
                                {
                                    e.getMessage();
                         }
		           }
		    }
  }
class ImageMatch extends JFrame implements ActionListener
{
                JFrame frame=new JFrame("Image Matching Selection Screen");
	        JLabel promptlbl = new JLabel("  Select one of the following methods for image matching:");
                JButton fstbtn =new JButton("Eucledean Distance Method");
		JButton scndbtn =new JButton("Intersection Method");
                ImageIcon icon = new ImageIcon( "c:\\cbirimag3.jpg" ) ;
                JButton back=new JButton("<<Back");
		JLabel l = new JLabel( "", icon, SwingConstants.LEFT );
		Container c=getContentPane();
                File image=null;
                String imagename=null;
                BufferedImage newImage=null;
                Image input=null;
   ImageMatch()
   {
                               super("Image Matching Selection Screen");
                                c.setLayout(null);
                                c.setBackground(Color.pink);
                                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 				   promptlbl.setOpaque(true);
                                    promptlbl.setFont(new Font("Serif", Font.PLAIN, 24));
 				promptlbl.setBackground(Color.magenta);
                                promptlbl.setForeground(Color.orange);
                                 l.setBounds(100,300,300, 200);
                                 c.add(l);
                                promptlbl.setBounds(300, 50, 600, 100);
                                c.add(promptlbl);
                                fstbtn.setBounds(600, 300, 200, 50);
                                c.add(fstbtn);
				fstbtn.addActionListener(this);
                                scndbtn.setBounds(600, 400, 200, 50);
				c.add(scndbtn);
                                 back.setBounds(600,500,100,50);
                                c.add(back);
                                scndbtn.addActionListener(this);
                                back.addActionListener(this);
				//l.setUI( new MultiLineLabelUI() );
		                promptlbl.setBorder( new EtchedBorder() );
				setSize(1280,1280);
				setVisible(true);

   }
   public void actionPerformed(ActionEvent a)
                    {
if(a.getSource()==back)
{
    try{
         dispose();
         new CbirOptions();
                             }catch(Exception e)
                             {
                                 e.getMessage();
                             }
}
	                 if((a.getSource()==fstbtn))
 			  {
                           JFileChooser chooser =new JFileChooser("E:");


                             FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(null);
    if(returnVal == JFileChooser.APPROVE_OPTION)
    {
         image=chooser.getSelectedFile();
         imagename=chooser.getSelectedFile().getAbsolutePath();
        

   
		          try{
                              BufferedImage img=ImageIO.read(image);
                              int width=img.getWidth();
                              int height=img.getHeight();
                              if((width!=512)||(height!=512))
                              {
                            image=  imgscale(img,512,512,(double)512/width);
                              }
                               dispose();
                              Distancemethod dm=new Distancemethod();
                              dm.imagematch(image);
                            
                               }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
                    }
    }
                          if((a.getSource()==scndbtn))
 			  {
			            JFileChooser chooser =new JFileChooser("E:");


                             FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(null);
    if(returnVal == JFileChooser.APPROVE_OPTION)
    {
         image=chooser.getSelectedFile();
         imagename=chooser.getSelectedFile().getAbsolutePath();


    
		          try{

                              BufferedImage img=ImageIO.read(image);
                              int width=img.getWidth();
                              int height=img.getHeight();
                             if((width!=512)||(height!=512))
                              {
                            image=  imgscale(img,512,512,(double)512/width);
                              }
                               dispose();
                                IntersectionMethod intersectionmethod = new IntersectionMethod();
                         intersectionmethod.imagematch(image);
                               }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
       }
}
    public File imgscale(Image img, int newWidth, int newHeight, double sc) {
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
        File scaledfile=new File("c:\\temp_images\\image1.jpg");
        try{
      javax.imageio.ImageIO.write(newImage, "JPG",scaledfile );
   }catch(Exception e)
    {
       e.getMessage();
    }
    return scaledfile;
    }

}
class Screenmain
{
  public static void main(String args[])
    {
     new firstframe();
    }
}

