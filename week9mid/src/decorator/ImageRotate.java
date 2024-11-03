// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package decorator;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// Concrete Decorator로서 실제로 어떻게 Component의 기능을 확장하고 추가할지를 담당한다.
// ImageRotate Class (process rotate image)
public class ImageRotate extends ImageProcessorDecorator {
	
	// Decorator가 꾸밀 ImageProcessor 객체를 받아 초기화
	private double angle = 45.0;	// 멤버 추가, 회전 시킬 각도
	public ImageRotate(ImageProcessor imageProcessor, double angle) {
		super(imageProcessor);
		this.angle = angle;
	}

	// ImageProcessorDecorator클래스의 추상 메서드를 구현
	// 원본 이름에 "Rotate" 와 각도를 추가한다.
	@Override
	public String getName() {
		return imageProcessor.getName() + "Rotate" + angle;
	}

	// ImageProcessorDecorator에서 추상 메서드를 오버라이드
	// 원본 imageProcessor의 process 메서드로 이미지를 처리한 후 rotate 적용
	@Override
	public BufferedImage process(BufferedImage image) {
		return rotate(imageProcessor.process(image), angle);
	}
	
	public static BufferedImage rotate(BufferedImage image, double rotateAngle) {
		if (image == null) return null;
		// creates output image
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		// rotate the input image by angle to the output image
		Graphics2D g2d = newImage.createGraphics();
		g2d.rotate(Math.toRadians(rotateAngle), image.getWidth()/2, image.getHeight()/2);  
		g2d.drawImage(image, null, 0, 0);
		return newImage;  
	}

	

}
