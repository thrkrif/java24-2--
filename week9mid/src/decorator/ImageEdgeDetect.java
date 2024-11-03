// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

// Concrete Decorator로서 실제로 어떻게 Component의 기능을 확장하고 추가할지를 담당한다.
// ImageEdgeDetect Class (process image edge detection)
public class ImageEdgeDetect extends ImageProcessorDecorator {

	// Decorator가 꾸밀 ImageProcessor 객체를 받아 초기화
	public ImageEdgeDetect(ImageProcessor imageProcessor){
		super(imageProcessor);
	}
	
	// ImageProcessorDecorator클래스의 추상 메서드를 구현
	// 원본 이름에 "EdgeDetect" 를 추가한다.
	@Override
	public String getName() {
		return imageProcessor.getName() + "EdgeDetect";
	}

	// ImageProcessorDecorator에서 추상 메서드를 오버라이드
	// 원본 imageProcessor의 process 메서드로 이미지를 처리한 후 edge detect 적용
	@Override
	public BufferedImage process(BufferedImage image) {
		return edgeDetect(imageProcessor.process(image));
	}

	// edge detection kernel
	public static final float[] edge3x3Kernel = { 0.0f, -0.75f, 0.0f, -0.75f, 3.0f, -0.75f, 0.0f, -0.75f, 0.0f };

	// edge detect
	public static BufferedImage edgeDetect(BufferedImage image)	{
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		ConvolveOp op = new ConvolveOp(new Kernel(3, 3, edge3x3Kernel));
		newImage = op.filter(image, null);
		return newImage;
	}

	
}
