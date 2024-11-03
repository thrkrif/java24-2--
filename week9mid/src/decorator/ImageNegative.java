// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

// Concrete Decorator로서 실제로 어떻게 Component의 기능을 확장하고 추가할지를 담당한다.
// ImageNegative Class (process negative image)
public class ImageNegative extends ImageProcessorDecorator {
	
	// Decorator가 꾸밀 ImageProcessor 객체를 받아 초기화
	public ImageNegative(ImageProcessor imageProcessor) {
		super(imageProcessor);
	}

	// ImageProcessorDecorator클래스의 추상 메서드를 구현
	// 원본 이름에 "Negative" 를 추가한다.
	@Override
	public String getName(){
		return imageProcessor.getName() + "Negative";
	}

	// ImageProcessorDecorator클래스의 추상 메서드를 구현
	// 원본 imageProcessor의 process 메서드로 이미지를 처리한 후 반전 이미지로 변환한다
	@Override
	public BufferedImage process(BufferedImage image){
		return negative(imageProcessor.process(image));
	}

	// 이미지를 반전시키는 메서드	
    // Image negative. Multiply each color value by -1.0 and add 255
	public static BufferedImage negative(BufferedImage image) {
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		RescaleOp op = new RescaleOp(-1.0f, 255f, null);
		newImage = op.filter(image, null);
		return newImage;
	}
}
