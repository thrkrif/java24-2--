// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package decorator;


public class MainTest {
	
    public MainTest() {
		// PlaneImage + Grayscale + Blur + Negative
		ImageProcessor imageProcessor = new PlaneImage("cat1.jpg");
		imageProcessor = new ImageGrayscale(imageProcessor);
		imageProcessor = new ImageBlur(imageProcessor);
		imageProcessor = new ImageNegative(imageProcessor);
		imageProcessor.process();

		// PlaneImage + Rotate45.5 + EdgeDetect
		ImageProcessor imageProcessor2 = new PlaneImage("cat2.jpg");
		imageProcessor2 = new ImageRotate(imageProcessor2, 45.5);
		imageProcessor2 = new ImageEdgeDetect(imageProcessor2);
		imageProcessor2.process();

    }

}
