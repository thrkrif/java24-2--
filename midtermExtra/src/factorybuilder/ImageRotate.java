// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package factorybuilder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageRotate implements IProcessor {
	private double angle = 45.0;	// 회전하는 각도

	// 객체가 생성 될 때 회전 각도를 설정하고 클래스 이름과 각도를 출력한다.
	// pdf 1번 문제와 동일한 출력이 나오게 하기 위해 생략함
	public ImageRotate(double angle) {
		this.angle = angle;
		// System.out.println(this);
	}

	// 클래스 이름과 각도를 반환한다.
	@Override
	public String toString() {
		return "ImageRotate" + this.angle;
	}

	// IProcessor 인터페이스의 메서드를 구현
	// process 메서드: 입력된 이미지를 rotate하는 메서드
	@Override
	public BufferedImage process(BufferedImage image) {
		return rotate(image, angle);
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
