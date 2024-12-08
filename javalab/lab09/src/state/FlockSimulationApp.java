// 자바프로그래밍 2분반 32207522 양상훈
package state;
import javax.swing.JFrame;
// 사용자에게 보여주는 클래스
public class FlockSimulationApp extends JFrame {
    public FlockSimulationApp() {
        super("2D Flock of Birds Simulation");
        this.add(new FlockPanel());
        this.pack(); // 창 크기 조정
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작
        this.setLocationRelativeTo(null); // 화면 중앙에 배치
        this.setVisible(true); // 창 표시
    }
}
