// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package observer;

import java.awt.image.BufferedImage;

// Observer는 Subject로부터 notify를 받으면 이미지를 처리한다.
// 인터페이스는 기능만 선언하고 하위 클래스에서 직접 구현한다.
public interface Observer {
    BufferedImage process(BufferedImage image);
}
