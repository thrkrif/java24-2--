// 자바프로그래밍 2분반 32207522 양상훈

import java.awt.Point;
import java.util.List;
import java.util.Random;

// Concrete State : Boids moves randomly
public class WanderState implements BoidState{
    private static final int MAX_FORCE = 1;


    @Override
    public void applyBehavior(Boid boid, List<Boid> boids) {
        // boid를 무작위로 이동시킨다.
        Random random = new Random();
        int dx = random.nextInt(2 * MAX_FORCE + 1) - MAX_FORCE; // -1, 0, 1
        int dy = random.nextInt(2 * MAX_FORCE + 1) - MAX_FORCE; // -1, 0, 1
        boid.applyForce(new Point(dx, dy));

        // 자신을 제외한 boid들과의 거리를 검사한다. 
        // 조건을 만족하면 State를 바꾸고 바로 종료한다. -> CohesionState로 넘어감
        for (Boid other : boids) {
            if (other != boid &&
                boid.getPosition().distance(other.getPosition()) < Boid.VISION_RANGE) {
                System.out.println("boid id=" + boid.getId() + " state=" + boid.getState() + " changed to CohesionState");
                boid.setState(new CohesionState());
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "WanderState";
    }
}
