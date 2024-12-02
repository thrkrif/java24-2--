// 자바프로그래밍 2분반 32207522 양상훈

import javax.swing.*;

// Boid 시뮬레이션을 실행하기 위한 JFrame 클래스
public class FlockSimulationApp extends JFrame {
    public FlockSimulationApp() {
        setTitle("Flocking Simulation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Boid를 화면에 그리기 위한 패널(FlockPanel) 생성 및 추가
        FlockPanel panel = new FlockPanel();
        add(panel);

        // 타이머 설정 : 시뮬레이션 업데이트 및 다시 그리기
        // 45ms마다 이벤트 실행 (약 22 FPS)
        new Timer(45, e -> {
            panel.updateSimulation(); // Boid 상태와 위치를 업데이트
            panel.repaint(); // Boid를 다시 그리기
        }).start(); // 타이머 시작
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlockSimulationApp app = new FlockSimulationApp();
            app.setVisible(true);
        });
    }
}

