// 자바프로그래밍 2분반 32207522 양상훈
package version2;

import java.awt.Point;
import java.util.List;

// Concrete Strategy : move away from nearby neighbors
public class SeparationStrategy implements BoidStrategy {
    @Override
    public void applyBehavior(Boid boid, List<Boid> boids) {
        Point separationForce = new Point(0, 0); // boid가 멀어지기 위해 가해야 할 힘(방향)을 저장하는 변수

        for (Boid other : boids) {
            // SEPARATION_DISTANCE 내의 boids 탐지(본인 제외)
            if (other != boid && boid.getPosition().distance(other.getPosition()) < Boid.SEPARATION_DISTANCE) {
                // 가까운 boid와 멀어지기 위한 방향 벡터 계산
                separationForce.x += boid.getPosition().x - other.getPosition().x;
                separationForce.y += boid.getPosition().y - other.getPosition().y;
            }
        }

        // 계산된 방향 벡터를 boid에 적용하여 위치 업데이트
        boid.applyForce(separationForce);

        // Boid가 충분히 멀어졌는지 확인
        boolean isSeparated = true; // default : 충분히 멀어졌다고 가정
        for (Boid other : boids) {
            if (other != boid && boid.getPosition().distance(other.getPosition()) < Boid.SEPARATION_DISTANCE) {
                isSeparated = false; // SEPARATION_DISTANCE 내에 boid가 존재함
                break;
            }
        }

        // 충분히 멀어짐( distance가 SEPARATION_DISTANCE 크면 ) WanderStrategy로 Strategy 변경
        if (isSeparated) {
            System.out.println(boid + " changed to WanderStrategy");
            boid.setBehavior(new WanderStrategy());
        }
    }

    @Override
    public String toString() {
        return "SeparationStrategy";
    }
}
