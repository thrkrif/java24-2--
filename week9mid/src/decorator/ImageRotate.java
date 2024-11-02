package decorator;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageRotate extends ImageProcessorDecorator {
	
	private double angle = 45.0;
	public ImageRotate(ImageProcessor imageProcessor, double angle) {
		super(imageProcessor);
		this.angle = angle;
	}

	@Override
	public String getName() {
		return imageProcessor.getName() + "Rotate" + angle;
	}

	@Override
	public BufferedImage process(BufferedImage image) {
		return rotate(imageProcessor.process(image), angle);
	}
	
	public static BufferedImage rotate(BufferedImage image, double rotateAngle) {
		if (image == null) return null;
		// creates output image
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		// rotate the input image by angle to the output image
		Graphics2D g2d = newImage.createGraphics();
		g2d.rotate(Math.toRadians(rotateAngle), image.getWidth()/2, image.getHeight()/2);  
		g2d.drawImage(image, null, 0, 0);
		return newImage;  
	}

	

}
