// 자바프로그래밍 2분반 32207522 양상훈
package version2;

import java.awt.Point;
import java.util.List;

// Concrete Strategy : move towards the midpoint
public class CohesionStrategy implements BoidStrategy {
    @Override
    public void applyBehavior(Boid boid, List<Boid> boids) {
        Point center = new Point(0, 0); // 이웃 boid들의 중심
        int count = 0;  // 이웃 boid의 개수 

        // Boid.VISION_RANGE 내에 있는 boid들을 탐지한다.(본인 제외)
        for (Boid other : boids) {
            if (other != boid && boid.getPosition().distance(other.getPosition()) < Boid.VISION_RANGE) {
                center.x += other.getPosition().x; // 탐지되는 boid들의 x좌표의 합
                center.y += other.getPosition().y; // 탐지되는 boid들의 y좌표의 합
                count++; // 탐지되는 boid 개수
            }
        }

        if (count > 0) {
            center.x /= count; // x좌표의 평균
            center.y /= count; // y좌표의 평균
             // Boid가 평균 위치로 이동하도록 방향 벡터 계산
            Point direction = new Point(center.x - boid.getPosition().x, center.y - boid.getPosition().y);
            boid.applyForce(direction); // // Boid에 힘을 가해 이동 방향 설정

            // boid와 center 사이의 거리가 JOIN_THRESHOLD보다 좁혀졌다면, State를 AlignmentStrategy로 변경
            if (boid.getPosition().distance(center) < Boid.JOIN_THRESHOLD) {
                System.out.println(boid + " changed to AlignmentStrategy");
                boid.setBehavior(new AlignmentStrategy());
            }
        // Boid.VISION_RANGE 내에 boid가 없는 경우, 즉, count = 0 인 경우 WanderStrategy로 변경
        } else {
            boid.setBehavior(new WanderStrategy());
        }
    }

    @Override
    public String toString() {
        return "CohesionStrategy";
    }
}

