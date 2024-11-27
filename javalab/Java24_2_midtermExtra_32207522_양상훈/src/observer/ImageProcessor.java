// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package observer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class ImageProcessor implements Subject{

    // 옵저버들을 관리하기 위한 리스트를 생성
    private List<Observer> observers = new ArrayList<>();

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
    public void notifyObservers(String filename){

        BufferedImage image = ImageUtil.load(filename); // 저장된 파일 이름을 사용해 이미지 로드
        String format = ImageUtil.getExtension(filename); // "jpg"가 출력된다.

        for (Observer observer : observers) {
            if (image != null) {
                BufferedImage outputImage = observer.process(image); // 기능에 맞는 process 메서드를 수행
                // outputFile : observer마다 고유한 파일 이름을 가진다.
                String outputFile = ImageUtil.getFullpathWithoutExt(filename) + observer.toString() + "." + format;
                ImageUtil.save(outputImage, format, outputFile); // 처리 된 이미지를 해당 경로에 저장한다.
            }
        }
    }
}
