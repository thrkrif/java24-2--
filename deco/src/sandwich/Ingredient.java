package sandwich;
public interface Ingredient {
    //component
    String getDescription();
    double cost();
}

// 컴포넌트,  데코레이터는 인터페이스 혹은 추상 클래스로 작성한다.
// 실질적 컴포넌트 말고 컴포넌트를 데코레이터에서 참조해야 여러 실질적 컴포넌트를 사용 가능하다.

