// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package observer;

import java.awt.Color;
import java.awt.image.BufferedImage;

// ImageGrayscale Class (process grayscaled image)
public class ImageGrayscale implements Observer{

	// 객체가 생성될 때 클래스 이름을 출력한다.
	public ImageGrayscale() {
		// System.out.println(this); pdf 문제 결과와 같게 하기 위해 생략
	}

	// 클래스 이름을 반환한다.
	@Override
	public String toString() {
		return "ImageGrayscale";
	}

	// IProcessor 인터페이스의 메서드를 구현
	// process 메서드: 입력된 이미지를 grayscale하는 메서드
	@Override
	public BufferedImage process(BufferedImage image) {
		return grayscale(image);
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
