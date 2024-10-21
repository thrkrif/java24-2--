package observer;

public interface Observer {
    // 옵저버들은 subject에서 DailyHelathData 정보가 바뀌었다는 notify가 들어오면 
    // 자신의 정보를 update(갱신)한다.
    // 인터페이스는 어차피 public이므로 생략 가능
    void update(DailyHelathData d);
}
