package factorybuilder;
// single facatory
// 파라미터가 여러개 있을 수 있다.  : double... 
public class ImageProcessorFactory {
    public static IProcessor createInstance(String type, double...params){
        switch (type) {
            case "ImageGrayScale":
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
                throw new IllegalArgumentException("UNKNOWN");
        }
    }
}
