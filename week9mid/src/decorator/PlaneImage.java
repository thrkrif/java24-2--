package decorator;

import java.awt.image.BufferedImage;

public class PlaneImage extends ImageProcessor {
    public PlaneImage(String filename){
        load(filename);
    }

    public String getName(){
        return name;
    }

    @Override
    public BufferedImage process(BufferedImage image) {
        return image;
    }

}
