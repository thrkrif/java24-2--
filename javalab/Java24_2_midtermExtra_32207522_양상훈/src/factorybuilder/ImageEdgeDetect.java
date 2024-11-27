// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package factorybuilder;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

// ImageEdgeDetect Class (process image edge detection)
public class ImageEdgeDetect implements IProcessor {

	// 객체가 생성될 때 클래스 이름을 출력한다.
	// pdf 1번 문제와 동일한 출력이 나오게 하기 위해 생략함
	public ImageEdgeDetect() {
		// System.out.println(this);
	}

	// 클래스 이름을 반환한다.
	@Override
	public String toString() {
		return "ImageEdgeDetect";
	}
	
	// IProcessor 인터페이스의 메서드를 구현
	// process 메서드: 입력된 이미지를 edgeDetect하는 메서드
	@Override
	public BufferedImage process(BufferedImage image) {
		return edgeDetect(image);
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
