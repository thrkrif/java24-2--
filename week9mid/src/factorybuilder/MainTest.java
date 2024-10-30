package factorybuilder;

public class MainTest {
	
    public MainTest() {
		ImageProcessor ip = new ImageProcessor().Builder()
										.add(ImageProcessorFactory.createInstance("ImageGrayscale"))
										.add(ImageProcessorFactory.createInstance("ImageBlur"));
										.add(ImageProcessorFactory.createInstance("ImageEdgeDetect"));
										.add(ImageProcessorFactory.createInstance("ImageNegative"));
										.add(ImageProcessorFactory.createInstance("ImageRotate", 45.0));
										.build();

	String[] imagefiles = {"cat1.jpg", "cat2.jpg"};	
   	for (String filename : imagefiles) {
		ip.process(filename);
	}

}
}