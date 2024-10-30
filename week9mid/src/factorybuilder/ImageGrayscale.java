package factorybuilder;

import java.awt.Color;
import java.awt.image.BufferedImage;

// ImageGrayscale Class (process grayscaled image)
public class ImageGrayscale implements IProcessor{

	public ImageGrayscale(){
		System.out.println(this);
	}

	@Override
	public String toString(){
		return " ImageGrayscale";
	}

	@Override
	public BufferedImage process(BufferedImage image){
		return grayscale(image);
	}

	// grayscale image
	public static BufferedImage grayscale(BufferedImage image)	{
		if (image == null) return null;
		// creates output image
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		// set the grayscale color 
	    for (int y = 0; y < image.getHeight(); y++) {
	    	for (int x = 0; x < image.getWidth(); x++) {
	    		Color c = new Color(image.getRGB(x, y)); 
	    		// NTSC relative luminance(or brightness) formula
	    		int brightness = (int) (c.getRed() * 0.299) + (int) (c.getGreen() * 0.587) + (int) (c.getBlue() * 0.114); 
	    		Color grayColor = new Color(brightness, brightness, brightness); 
	    		newImage.setRGB(x, y, grayColor.getRGB()); 
	    	}
	    }
	    return newImage;
	}
}
