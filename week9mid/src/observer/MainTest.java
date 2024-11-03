// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package observer;

public class MainTest {

    public MainTest() {
        Subject imageProcessor = new ImageProcessor();

        Observer grayScale = new ImageGrayscale();
        Observer blur = new ImageBlur();
        Observer edgeDetect = new ImageEdgeDetect();
        Observer negative = new ImageNegative();
        Observer rotate = new ImageRotate(45);

        imageProcessor.attachObserver(grayScale);
        imageProcessor.attachObserver(blur);
        imageProcessor.attachObserver(edgeDetect);
        imageProcessor.attachObserver(negative);
        imageProcessor.attachObserver(rotate);
        imageProcessor.notifyObservers();

        imageProcessor.detachObserver(negative);
        imageProcessor.detachObserver(edgeDetect);
        imageProcessor.detachObserver(grayScale);
        imageProcessor.detachObserver(blur);
        imageProcessor.notifyObservers();
    }
}
