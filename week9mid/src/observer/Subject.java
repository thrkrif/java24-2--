// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package observer;

// Subject는 Observer를 추가, 삭제, 업데이트 하는 기능을 가지고 있다.
// 하위클래스에서 기능들을 구현한다.
public interface Subject {
    void attachObserver(Observer o);
    void detachObserver(Observer o);
    void notifyObservers();
}
