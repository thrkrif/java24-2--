// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package factorybuilder;

import java.awt.image.BufferedImage;

// 이미지 처리 기능을 정의하는 인터페이스로 하위 클래스에서 공통적으로 구현해야 한다.
public interface IProcessor {
    BufferedImage process(BufferedImage image);
}
