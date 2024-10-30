package decorator;

import java.awt.image.BufferedImage;

public abstract class ImageProcessorDecorator extends ImageProcessor {

    protected ImageProcessor imageProcessor;

    protected ImageProcessorDecorator(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    public BufferedImage getImage(){
        return imageProcessor.getImage();
    }

    public String getExt(){
        return imageProcessor.getExt();
    }

    public String getPath(){
        return imageProcessor.getPath();
    }
}