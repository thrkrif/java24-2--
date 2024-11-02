package factorybuilder;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

// ImageEdgeDetect Class (process image edge detection)
public class ImageEdgeDetect implements IProcessor {

	public ImageEdgeDetect() {
		System.out.println(this);
	}


	@Override
	public String toString() {
		return "ImageEdgeDetect";
	}
	    
	@Override
	public BufferedImage process(BufferedImage image) {
		return edgeDetect(image);
	}

	// edge detection kernel
	public static final float[] edge3x3Kernel = { 0.0f, -0.75f, 0.0f, -0.75f, 3.0f, -0.75f, 0.0f, -0.75f, 0.0f };

	// edge detect
	public static BufferedImage edgeDetect(BufferedImage image)	{
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		ConvolveOp op = new ConvolveOp(new Kernel(3, 3, edge3x3Kernel));
		newImage = op.filter(image, null);
		return newImage;
	}
}
