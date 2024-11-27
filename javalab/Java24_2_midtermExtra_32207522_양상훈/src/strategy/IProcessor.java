// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package strategy;

import java.awt.image.BufferedImage;

// strategey 부분, 이미지 처리 기능은 여러가지가 존재한다. 
// 기능들을 미리 전략으로 정의하여 손쉽게 교체 가능하하도록 한다.
// 이미지 처리 기능을 정의하는 인터페이스로 하위 클래스에서 공통적으로 구현해야 한다.
public interface IProcessor {
    BufferedImage process(BufferedImage image);
}
