package template;

import java.awt.image.BufferedImage;

public class MainTest {
	
    public MainTest() {

		String[] imagefiles = {"cat1.jpg", "cat2.jpg"};	
   		for (String filename: imagefiles) {
			String format = ImageUtil.getExtension(filename);
			BufferedImage image = ImageUtil.load(filename);

			BufferedImage outputImage = ImageGrayscale.grayscale(image);
			String outputFile = ImageUtil.getFullpathWithoutExt(filename) + "Grayscale" + "." + ImageUtil.getExtension(filename);
			ImageUtil.save(outputImage, format, outputFile);

			outputImage = ImageBlur.blur(image);
			outputFile = ImageUtil.getFullpathWithoutExt(filename) + "ImageBlur" + "." + ImageUtil.getExtension(filename);
			ImageUtil.save(outputImage, format, outputFile);

			outputImage = ImageNegative.negative(image);
			outputFile = ImageUtil.getFullpathWithoutExt(filename) + "ImageNegative" + "." + ImageUtil.getExtension(filename);
			ImageUtil.save(outputImage, format, outputFile);

			outputImage = ImageEdgeDetect.edgeDetect(image);
			outputFile = ImageUtil.getFullpathWithoutExt(filename) + "ImageEdgeDetect" + "." + ImageUtil.getExtension(filename);
			ImageUtil.save(outputImage, format, outputFile);

			outputImage = ImageRotate.rotate(image, 45.0);
			outputFile = ImageUtil.getFullpathWithoutExt(filename) + "ImageRotate" + "." + ImageUtil.getExtension(filename);
			ImageUtil.save(outputImage, format, outputFile);
		}
    }

}
