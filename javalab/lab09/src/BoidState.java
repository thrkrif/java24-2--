// 자바프로그래밍 2분반 32207522 양상훈

// State Interface
import java.util.List;

public interface BoidState {
    // 주어진 boid와 주변의 boids를 받아 현재 상태에 맞는 Concrete State를 적용한다.
    void applyBehavior(Boid boid, List<Boid> boids);
}
