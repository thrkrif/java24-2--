// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package observer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class ImageProcessor implements Subject{

    // 옵저버들을 관리하기 위한 리스트를 생성
    private List<Observer> observers = new ArrayList<>();
    private BufferedImage image; // 처리할 이미지 필드 추가

    // 옵저버를 리스트에 추가
    @Override
    public void attachObserver(Observer o){
        observers.add(o);
    }
    
    // 옵저버를 리스트에서 제외
    @Override
    public void detachObserver(Observer o){
        observers.remove(o);
    }
    
    // 변경사항이 생길 시 옵저버들에게 통지한다.
    // 구독된 옵저버들만 통지를 받을 수 있다.
    @Override
    public void notifyObservers(){

        
        
        for (Observer observer : observers) {
            if (image != null) {
                observer.process(image); // 이미지를 전달하며 처리 요청
            }
        }
    }
}
