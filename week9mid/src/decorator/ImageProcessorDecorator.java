package decorator;

import java.awt.image.BufferedImage;

// Decorator 클래스는 Component를 확장하고 추가 기능을 제공한다.
public abstract class ImageProcessorDecorator extends ImageProcessor {
    
    // Component를 참조한다.
    protected ImageProcessor imageProcessor;

    // Decoroator는 Component 없이 단독으로 객체를 생성할 수 없다.
    protected ImageProcessorDecorator(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    // 현재 이미지를 반환한다.
    public BufferedImage getImage(){
        return imageProcessor.getImage();
    }

    // 파일 확장자를 반환한다.
    public String getExt(){
        return imageProcessor.getExt();
    }

    // 파일 경로를 반환한다.
    public String getPath(){
        return imageProcessor.getPath();
    }
}