// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

// Concrete Decorator로서 실제로 어떻게 Component의 기능을 확장하고 추가할지를 담당한다.
// ImageBlur Class (process gaussian blur)
public class ImageBlur extends ImageProcessorDecorator {

	// Decorator가 꾸밀 ImageProcessor 객체를 받아 초기화
	public ImageBlur(ImageProcessor imageProcessor) {
		super(imageProcessor);
	}

	// ImageProcessorDecorator클래스의 추상 메서드를 구현
	// 원본 이름에 "Blur" 를 추가한다.
	@Override
	public String getName() {
		return imageProcessor.getName() + "Blur";
	}

	// ImageProcessorDecorator에서 추상 메서드를 오버라이드
	// 원본 imageProcessor의 process 메서드로 이미지를 처리한 후 블러 효과를 적용
	@Override
	public BufferedImage process(BufferedImage image) {
		return blur(imageProcessor.process(image));
	}
		
	// blur kernel
	//public static final float[] blurKernel = { 0.1f, 0.1f, 0.1f, 0.1f, 0.2f, 0.1f, 0.1f, 0.1f, 0.1f };
	public static final float[] blur3x3Kernel = { 1/16f, 1/8f, 1/16f, 1/8f, 1/4f, 1/8f, 1/16f, 1/8f, 1/16f };
	public static final float[] blur5x5Kernel = { 1/273f, 4/273f, 7/273f, 4/273f, 1/273f, 
												  4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
												  7/273f, 26/273f, 41/273f, 26/273f, 7/273f,
												  4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
												  1/273f, 4/273f, 7/273f, 4/273f, 1/273f };

	// gaussian blur
	public static BufferedImage blur(BufferedImage image)	{
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		//ConvolveOp op = new ConvolveOp(new Kernel(3, 3, blur3x3Kernel));
		ConvolveOp op = new ConvolveOp(new Kernel(5, 5, blur5x5Kernel));
		newImage = op.filter(image, null);
		return newImage;
	}

	

}
