package factorybuilder;

public class ImageProcessorFactory {
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
