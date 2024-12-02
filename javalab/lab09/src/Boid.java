// 자바프로그래밍 2분반 32207522 양상훈
// Context : BoidState를 참조하고 Concrete state 중 하나를 설정한다.
import java.awt.Point;

public class Boid {
    public static final int VISION_RANGE = 50;
    public static final int JOIN_THRESHOLD = 15; 
    public static final int SEPARATION_DISTANCE = 20;

    // Boid의 속성 
    private Point position; // 위치
    private Point velocity; // 속력(방향)
    private BoidState state; // 현재 어떤 state인지
    private int id; // boid 각각의 고유 ID
    private static int count = 0; // boid ID 생성하기 위한 static 변수

    // 기본값 : WanderState
    public Boid(int x, int y) {
        this.position = new Point(x, y); // 위치
        this.velocity = new Point(0, 0); // 속력
        this.state = new WanderState(); // 상태
        this.id = ++count; // boid 고유 ID
    }

    public Point getPosition() {
        return position;
    }

    public Point getVelocity() {
        return velocity;
    }

    // boid에 힘을 가하여 속도와 위치를 업데이트
    public void applyForce(Point force) {
        // 속도에 힘을 추가한다.
        this.velocity.x += force.x;
        this.velocity.y += force.y;
        
        // MAX_SPEED를 넘지 못하도록 설정
        int MAX_SPEED = 7;
        velocity.x = Math.max(-MAX_SPEED, Math.min(MAX_SPEED, velocity.x));
        velocity.y = Math.max(-MAX_SPEED, Math.min(MAX_SPEED, velocity.y));

        // 위치를 업데이트
        this.position.x += this.velocity.x;
        this.position.y += this.velocity.y;
    }

    public BoidState getState() {
        return state;
    }

    public void setState(BoidState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Boid{id=" + id + ", position=" + position + ", velocity=" + velocity + ", state=" + state + "}";
    }


    // boid가 화면 밖으로 나가지 않도록 경계 처리
    public void keepWithinBounds(int width, int height) {
        if (position.x < 0) position.x = 0; // 왼쪽 경계
        if (position.x > width) position.x = width; // 오른쪽 경계
        if (position.y < 0) position.y = 0; // 위쪽 경계
        if (position.y > height) position.y = height; // 아래쪽 경계
    }

}

