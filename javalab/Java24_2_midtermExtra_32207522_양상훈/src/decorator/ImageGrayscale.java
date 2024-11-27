// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package decorator;

import java.awt.Color;
import java.awt.image.BufferedImage;

// Concrete Decorator로서 실제로 어떻게 Component의 기능을 확장하고 추가할지를 담당한다.
// ImageGrayscale Class (process grayscaled image)
public class ImageGrayscale extends ImageProcessorDecorator {

	// Decorator가 꾸밀 ImageProcessor 객체를 받아 초기화
	public ImageGrayscale(ImageProcessor imageProcessor) {
		super(imageProcessor);
	}

	// ImageProcessorDecorator클래스의 추상 메서드를 구현
	// 원본 이름에 "ImageGrayscale" 를 추가한다.
	@Override
	public String getName() {
		return imageProcessor.getName() + "ImageGrayscale" ;
	}

	// ImageProcessorDecorator에서 추상 메서드를 오버라이드
	// 원본 imageProcessor의 process 메서드로 이미지를 처리한 후 grayscale 적용
	@Override
	public BufferedImage process(BufferedImage image) {
		return grayscale(imageProcessor.process(image));

	}

	// grayscale image
	public static BufferedImage grayscale(BufferedImage image)	{
		if (image == null) return null;
		// creates output image
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		// set the grayscale color 
	    for (int y = 0; y < image.getHeight(); y++) {
	    	for (int x = 0; x < image.getWidth(); x++) {
	    		Color c = new Color(image.getRGB(x, y)); 
	    		// NTSC relative luminance(or brightness) formula
	    		int brightness = (int) (c.getRed() * 0.299) + (int) (c.getGreen() * 0.587) + (int) (c.getBlue() * 0.114); 
	    		Color grayColor = new Color(brightness, brightness, brightness); 
	    		newImage.setRGB(x, y, grayColor.getRGB()); 
	    	}
	    }
	    return newImage;
	}

	
}
