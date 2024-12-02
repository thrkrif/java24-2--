// 자바프로그래밍 2분반 32207522 양상훈
// your code : Strategy Pattern
import java.awt.Point;
import java.util.List;

// BoidStrategy : 기존 Boid를 확장하여 Strategy Pattern 적용
public class BoidStrategy extends Boid {
    public static final int VISION_RANGE = 50;
    public static final int JOIN_THRESHOLD = 15; 
    public static final int SEPARATION_DISTANCE = 20;

    // Boid의 속성 
    private Point position; // 위치
    private Point velocity; // 속력(방향)
    private BoidState strategy; // 현재 어떤 state인지
    private int id; // boid 각각의 고유 ID
    private static int count = 0; // boid ID 생성하기 위한 static 변수

    // 생성자 : 초기 위치와 상태 설정
    public BoidStrategy(int x, int y) {
        super(x, y); // 부모 클래스 Boid의 생성자 호출
        this.strategy = new WanderState(); // 초기 상태를 Wander로 설정
        this.id = ++count; // boid 고유 ID
    }

    // 현재 상태(전략)를 설정
    public void setBoidStateToStrategy(BoidState strategy) {
        this.strategy = strategy;
    }

    // 현재 전략에 따라 행동 수행
    public void executeSeek(List<Boid> boids) {
        System.out.println("Boid ID: " + this.getId() + " using " + this.strategy + " 알고리즘");
        strategy.applyBehavior(this, boids); // 현재 전략 실행
    }

    // 기타 유틸리티 메서드 (기존 Boid와 동일)
    public Point getPosition() {
        return position;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void applyForce(Point force) {
        this.velocity.x += force.x;
        this.velocity.y += force.y;

        int MAX_SPEED = 7;
        velocity.x = Math.max(-MAX_SPEED, Math.min(MAX_SPEED, velocity.x));
        velocity.y = Math.max(-MAX_SPEED, Math.min(MAX_SPEED, velocity.y));

        this.position.x += this.velocity.x;
        this.position.y += this.velocity.y;
    }

    public BoidState getState() {
        return strategy;
    }

    // public void setState(BoidState state) {
    //     this.strategy = state;
    // }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Boid{id=" + id + ", position=" + position + ", velocity=" + velocity + ", strategy=" + strategy + "}";
    }

    public void keepWithinBounds(int width, int height) {
        if (position.x < 0) position.x = 0;
        if (position.x > width) position.x = width;
        if (position.y < 0) position.y = 0;
        if (position.y > height) position.y = height;
    }
}
