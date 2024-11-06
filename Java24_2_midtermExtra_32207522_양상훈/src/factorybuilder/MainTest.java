// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package factorybuilder;

public class MainTest {
	
    public MainTest() {
		ImageProcessor ip = new ImageProcessor.Builder()
								.add(ImageProcessorFactory.createInstance("ImageGrayscale"))
								.add(ImageProcessorFactory.createInstance("ImageBlur"))
								.add(ImageProcessorFactory.createInstance("ImageEdgeDetect"))
								.add(ImageProcessorFactory.createInstance("ImageNegative"))
								.add(ImageProcessorFactory.createInstance("ImageRotate",45.5))
								.build();

		String[] imagefiles = {"cat1.jpg", "cat2.jpg"};	

   		for (String filename: imagefiles) {
			ip.process(filename);
		}
    }

}
