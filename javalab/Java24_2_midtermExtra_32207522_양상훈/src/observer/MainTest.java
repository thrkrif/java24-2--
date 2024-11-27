// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package observer;

public class MainTest {

    public MainTest() {
        String[] imagefiles = {"cat1.jpg", "cat2.jpg"};	

        Subject imageProcessor = new ImageProcessor();

        Observer grayScale = new ImageGrayscale();
        Observer blur = new ImageBlur();
        Observer edgeDetect = new ImageEdgeDetect();
        Observer negative = new ImageNegative();
        Observer rotate = new ImageRotate(45);

        // 모든 옵저버들을 등록한다.
        imageProcessor.attachObserver(grayScale);
        imageProcessor.attachObserver(blur);
        imageProcessor.attachObserver(edgeDetect);
        imageProcessor.attachObserver(negative);
        imageProcessor.attachObserver(rotate);
        for (String imagefile : imagefiles) {
            imageProcessor.notifyObservers(imagefile);
        }

        // rotate 제외하고 옵저버들 해제
        imageProcessor.detachObserver(negative);
        imageProcessor.detachObserver(edgeDetect);
        imageProcessor.detachObserver(grayScale);
        imageProcessor.detachObserver(blur);
        for (String imagefile : imagefiles) {
            imageProcessor.notifyObservers(imagefile);
        }

    }
}
