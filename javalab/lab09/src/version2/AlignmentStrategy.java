// 자바프로그래밍 2분반 32207522 양상훈
package version2;

import java.awt.Point;
import java.util.List;

// Concrete Strategy : move toward the average direction
public class AlignmentStrategy implements BoidStrategy {
    @Override
    public void applyBehavior(Boid boid, List<Boid> boids) {
        Point averageVelocity = new Point(0, 0); // average direction(average velocity)
        int count = 0;  // 이웃 boid의 개수

        // Boid.VISION_RANGE 내에 있는 boid들을 탐지한다.(본인 제외)
        for (Boid other : boids) {
            if (other != boid && boid.getPosition().distance(other.getPosition()) < Boid.VISION_RANGE) {
                averageVelocity.x += other.getVelocity().x; // 탐지되는 boid들의 x방향(속도)의 합
                averageVelocity.y += other.getVelocity().y; // 탐지되는 boid들의 y방향(속도)의 합
                count++; // 탐지되는 boid 개수
            }
        }

        if (count > 0) {
            averageVelocity.x /= count; // x 속도 평균
            averageVelocity.y /= count; // y 속도 평균
            boid.applyForce(averageVelocity); // Boid를 평균 속도 방향으로 이동하도록 힘 적용

            // SeparationStrategy로 변경 : boid와 other의 거리가 < SEPARATION_DISTANCE 
            for (Boid other : boids) {
                if (other != boid && boid.getPosition().distance(other.getPosition()) < Boid.SEPARATION_DISTANCE) {
                    System.out.println(boid + " changed to SeparationStrategy");
                    boid.setBehavior(new SeparationStrategy());
                    return;
                }
            }
        // Boid.VISION_RANGE 내에 boid가 없는 경우, 즉, count = 0 인 경우 WanderStrategy로 변경
        } else {
            boid.setBehavior(new WanderStrategy());
        }
    }

    @Override
    public String toString() {
        return "AlignmentStrategy";
    }
}
