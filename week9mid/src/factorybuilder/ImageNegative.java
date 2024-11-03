// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package factorybuilder;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

// ImageNegative Class (process negative image)
public class ImageNegative implements IProcessor{

	// 객체 생성될 때 클래스 이름을 출력한다.
	// pdf 1번 문제와 동일한 출력이 나오게 하기 위해 생략함
	public ImageNegative() {
		// System.out.println(this);
	}

	// 클래스 이름을 반환
	@Override
	public String toString() {
		return "ImageNegative";
	}

	// IProcessor 인터페이스의 메서드를 구현
	// process 메서드: 입력된 이미지를 negative하는 메서드
	@Override
	public BufferedImage process(BufferedImage image) {
		return negative(image);
	}

    // Image negative. Multiply each color value by -1.0 and add 255
	public static BufferedImage negative(BufferedImage image) {
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		RescaleOp op = new RescaleOp(-1.0f, 255f, null);
		newImage = op.filter(image, null);
		return newImage;
	}



}
