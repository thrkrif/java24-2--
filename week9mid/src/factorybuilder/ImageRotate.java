package factorybuilder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageRotate implements IProcessor {
	private double angle = 45.0;

	public ImageRotate(double angle) {
		this.angle = angle;
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "ImageRotate" + this.angle;
	}

	@Override
	public BufferedImage process(BufferedImage image) {
		return rotate(image, angle);
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
