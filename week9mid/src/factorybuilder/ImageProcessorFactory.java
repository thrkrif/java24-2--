// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package factorybuilder;

// Simple Factory는 객체 생성 부분을 담당하는 클래스이다.
public class ImageProcessorFactory {

    // params: 이미지 처리 객체에 필요한 추가 파라미터 (예: 회전 각도)
    public static IProcessor createInstance(String type, double... params){
        switch (type) {
            case "ImageGrayscale":
                return new ImageGrayscale();
            case "ImageBlur":
                return new ImageBlur();
            case "ImageEdgeDetect":
                return new ImageEdgeDetect();
            case "ImageNegative":
                return new ImageNegative();
            case "ImageRotate":
                return new ImageRotate(params[0]);
            default:
                throw new IllegalArgumentException("UNKNOWN Image Processing");
        }

    }

}
