package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

// ImageNegative Class (process negative image)
public class ImageNegative extends ImageProcessorDecorator {
	
	public ImageNegative(ImageProcessor imageProcessor) {
		super(imageProcessor);
	}

	@Override
	public String getName(){
		return imageProcessor.getName() + " Negative";
	}

	@Override
	public BufferedImage process(BufferedImage image){
		return negative(imageProcessor.process(image));
	}


    // Image negative. Multiply each color value by -1.0 and add 255
	public static BufferedImage negative(BufferedImage image) {
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		RescaleOp op = new RescaleOp(-1.0f, 255f, null);
		newImage = op.filter(image, null);
		return newImage;
	}
}