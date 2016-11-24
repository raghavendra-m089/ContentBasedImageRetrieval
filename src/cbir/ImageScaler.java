package cbir;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class ImageScaler {
    int i;
    public BufferedImage newImage;
    /**
     * Scale an image while preserving aspect ratio
     *
     * @param args Name of image file to be scaled (testing only)
     *
     */
   /*public static void main(String[] args) throws Exception {
   //  BufferedImage in = javax.imageio.ImageIO.read(new java.io.File(args[0]));
     File f=new File("C:\\18.jpg");
    Image in = ImageIO.read(f);
    ImageScaler i=new ImageScaler();
    JFrame jf=new JFrame();
      i.im(jf,in,100,100,0.5);

   }*/

   public void im(JFrame jf,Image img,int newWidth,int newHeight,double sc)
    {
  //      BufferedImage out = ImageScaler.scaleImage(in,
//		BufferedImage.TYPE_BYTE_GRAY, 789*2, 218*2);

        double thumbRatio = (double) newWidth / (double) newHeight;
  //      int imageWidth = inimage.getWidth(null);
     //   int imageHeight = inimage.getHeight(null);
      int imageWidth=(int)(newWidth/sc);
      int imageHeight =(int)(newHeight/sc);
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
        ImageIcon ic=new ImageIcon(newImage);
    jf.setLayout(new FlowLayout());
      JLabel jl=new JLabel();
               jl.setIcon(ic);
               jf.getContentPane().setBackground(Color.pink);
             jf.getContentPane().add(jl);
          jf.setSize(1280,1280);
           jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           jf.setVisible(true);

    //     rwimage cg2 = new rwimage(newImage);
    //    System.out.println(cg2.arGRAY.length);
/* try{
      javax.imageio.ImageIO.write(newImage, "JPG", new java.io.File("C:\\lo_scaledn.jpg"));
   }
    catch(Exception e)
    {
    }*/

    }


    public void im(JFrame jf,JPanel jp,Image img,JButton jb,int newWidth,int newHeight,double sc)
    {
  //      BufferedImage out = ImageScaler.scaleImage(in,
//		BufferedImage.TYPE_BYTE_GRAY, 789*2, 218*2);

        double thumbRatio = (double) newWidth / (double) newHeight;
  //      int imageWidth = inimage.getWidth(null);
     //   int imageHeight = inimage.getHeight(null);
      int imageWidth=(int)(newWidth/sc);
      int imageHeight =(int)(newHeight/sc);
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
        ImageIcon ic=new ImageIcon(newImage);
               jb.setIcon(ic);
    //         jf.getContentPane().add(jl);

       jp.add(jb);

    //     rwimage cg2 = new rwimage(newImage);
    //    System.out.println(cg2.arGRAY.length);
/* try{
      javax.imageio.ImageIO.write(newImage, "JPG", new java.io.File("C:\\lo_scaledn.jpg"));
   }
    catch(Exception e)
    {
    }*/

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


















    /**
     *
     * @param image The image to be scaled
     * @param imageType Target image type, e.g. TYPE_INT_RGB
     * @param newWidth The required width
     * @param newHeight The required width
     *
     * @return The scaled image
     */
    /*
    public static BufferedImage scaleImage(BufferedImage image, int imageType,
            int newWidth, int newHeight) {
        // Make sure the aspect ratio is maintained, so the image is not distorted
        double thumbRatio = (double) newWidth / (double) newHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double aspectRatio = (double) imageWidth / (double) imageHeight;

        if (thumbRatio < aspectRatio) {
            newHeight = (int) (newWidth / aspectRatio);
        } else {
            newWidth = (int) (newHeight * aspectRatio);
        }

        // Draw the scaled image
        BufferedImage newImage = new BufferedImage(newWidth, newHeight,
                imageType);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);
//	javax.imageio.ImageIO.write(newImage, "JPG", new java.io.File("E:\\downwith\\from 256\\mat_testing\\exz.jpg"));
        //return newImage;*/



