package decorator;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// ImageUtil Class
public class ImageUtil {
	// load an image    
    public static BufferedImage load(String fullPath) {
    	try {
			//this.img = ImageIO.read(new File(fullPath));
			return toCompatibleImage(ImageIO.read(new File(fullPath)));
		} catch (IOException e) {
			System.out.println(fullPath  + " could not be loaded. It's not an image!");
			e.printStackTrace();
		}
		return null;
    }

    // convert an image read in with the image io api to a BufferedImage that has a Image Data Model compatible with the default screen device 
    public static BufferedImage toCompatibleImage(BufferedImage image) {
        BufferedImage compatibleImage = null;
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	GraphicsConfiguration gc = gd.getDefaultConfiguration();
    	compatibleImage = gc.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
    	Graphics2D g2d = compatibleImage.createGraphics();
    	g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    	g2d.dispose();
        return compatibleImage;
    }

    // save an image
    public static void save(BufferedImage image, String format, String fullPath) {
    	try {
			boolean result = ImageIO.write(image, format, new File(fullPath));
			if (result) {
				System.out.println("save to " + fullPath + " in format " + format + " successfully.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
    // get path from fullPath (e.g. C:/JAVA/IMG.jpg => C:/JAVA/) 
    public static String getPath(String fullPath) {
    	return fullPath.lastIndexOf('/') == -1 ? null : fullPath.substring(0, fullPath.lastIndexOf('/'));
    }
    
    // get filenameWithoutExt from fullPath (e.g. C:/JAVA/IMG.jpg => IMG) 
    public static String getFilenameWithoutExt(String fullPath) {
    	return fullPath.substring(fullPath.lastIndexOf('/') + 1, fullPath.lastIndexOf('.'));
    }    
    
    // get fullPathWithoutExt from fullPath (e.g. C:/JAVA/IMG.jpg => C:/JAVA/IMG) 
    public static String getFullpathWithoutExt(String fullPath) {
    	return fullPath.substring(0, fullPath.lastIndexOf('.'));
    }
    
    // get extension from fullPath (e.g. C:/JAVA/IMG.jpg => jpg) 
    public static String getExtension(String fullPath) {
    	return fullPath.substring(fullPath.lastIndexOf('.') + 1);
    }
}
