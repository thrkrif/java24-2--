// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package observer;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

// ImageBlur Class (process gaussian blur)
public class ImageBlur implements Observer{

	// 객체가 생성될 때 클래스 이름을 출력한다.
	public ImageBlur() {
		// System.out.println(this); pdf 문제 결과와 같게 하기 위해 생략
	}
	
	// 클래스 이름을 반환한다.
	@Override
	public String toString() {
		return "ImageBlur";
	}
	
    // IProcessor 인터페이스의 메서드를 구현
	// process 메서드: 입력된 이미지를 블러 처리하는 메서드
	@Override
	public BufferedImage process(BufferedImage image) {
		return blur(image);
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
