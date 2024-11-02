package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

// ImageBlur Class (process gaussian blur)
public class ImageBlur extends ImageProcessorDecorator {
	public ImageBlur(ImageProcessor imageProcessor) {
		super(imageProcessor);
	}

	@Override
	public String getName() {
		return imageProcessor.getName() + "Blur";
	}

	@Override
	public BufferedImage process(BufferedImage image) {
		return blur(imageProcessor.process(image));
	}
		
	// blur kernel
	//public static final float[] blurKernel = { 0.1f, 0.1f, 0.1f, 0.1f, 0.2f, 0.1f, 0.1f, 0.1f, 0.1f };
	public static final float[] blur3x3Kernel = { 1/16f, 1/8f, 1/16f, 1/8f, 1/4f, 1/8f, 1/16f, 1/8f, 1/16f };
	public static final float[] blur5x5Kernel = { 1/273f, 4/273f, 7/273f, 4/273f, 1/273f, 
												  4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
												  7/273f, 26/273f, 41/273f, 26/273f, 7/273f,
												  4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
												  1/273f, 4/273f, 7/273f, 4/273f, 1/273f };

	// gaussian blur
	public static BufferedImage blur(BufferedImage image)	{
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		//ConvolveOp op = new ConvolveOp(new Kernel(3, 3, blur3x3Kernel));
		ConvolveOp op = new ConvolveOp(new Kernel(5, 5, blur5x5Kernel));
		newImage = op.filter(image, null);
		return newImage;
	}

	

}
