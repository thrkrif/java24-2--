// 자바프로그래밍 2분반 32207522 양상훈
package version2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Boid 객체들을 화면에 그리기 위한 JPanel
public class FlockPanel extends JPanel {
    private List<Boid> boids; // 시뮬레이션에 참여하는 Boid 리스트

    public FlockPanel() {
        boids = new ArrayList<>();  // Boid 리스트 초기화
        initializeBoids(); // Boid 생성 및 초기화
    }

    private void initializeBoids() {
        for (int i = 0; i < 20; i++) { // 20개의 Boid 생성
            // Boid들을 화면 중앙 근처에 배치
            int x = (int) (Math.random() * 200) + 300;
            int y = (int) (Math.random() * 200) + 200;
            boids.add(new Boid(x, y));
        }
    }

    // 모든 Boid의 상태와 위치를 업데이트
    public void updateSimulation() {
        for (Boid boid : boids) {
            boid.getBehavior().applyBehavior(boid, boids); // 현재 Boid의 Strategy에 따라 행동을 수행 
            boid.keepWithinBounds(getWidth(), getHeight()); // Boid가 화면 밖으로 나가지 않도록 한다.
        }
    }

    // Boid 화면에 그리기 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 화면 초기화
        for (Boid boid : boids) {
            drawBoid(g, boid); // 각 Boid 그리기
        }
    }

    // Boid 하나를 화면에 그리기
    private void drawBoid(Graphics g, Boid boid) {
        g.setColor(Color.BLUE);
        int x = boid.getPosition().x;
        int y = boid.getPosition().y;
        g.fillOval(x, y, 5, 5); // Boid를 (x,y) 위치에 반지름 5크기의 원으로 그림

        // 상태를 텍스트로 표시
        g.setColor(Color.BLACK);
        g.drawString(boid.getBehavior().toString(), x + 6, y - 6); // Strategy를 Boid 위에 표시
    }
}
    