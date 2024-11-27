// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package decorator;

import java.awt.image.BufferedImage;

// Concrete Component
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
