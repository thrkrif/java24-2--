// 자바프로그래밍 2분반 32207522 양상훈
package state;

import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Boid {
    public static final int SPEED = 3;
    public static final int SEPARATION_DISTANCE = 20;
    public static final int VISION_RANGE = 50;
    public static final int JOIN_THRESHOLD = 15;

    private Point position; // 현재 Boid 위치
    private Point velocity; // 현재 Boid 속도
    private int panelWidth; // 화면 가로 크기
    private int panelHeight; // 화면 세로 크기
    private Random random;
    
    private BoidState state; // 현재 어떤 state인지
    private int id; // boid 각각의 고유 ID
    private static int count = 0; // boid ID 생성하기 위한 static 변수

    public Boid(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.random = new Random();

        this.state = new WanderState(); // 기본 값 : WanderState
        this.id = ++count; // boid 고유 ID

        // Boid의 초기 위치와 속도를 랜덤으로 설정
        position = new Point(random.nextInt(panelWidth), random.nextInt(panelHeight));
        velocity = new Point(random.nextInt(2 * SPEED) - SPEED, random.nextInt(2 * SPEED) - SPEED);
    }

    public void flock(List<Boid> boids) {
        Point separation = separate(boids);
        Point alignment = align(boids);
        Point cohesion = cohere(boids);

        // Combine forces with weights
        velocity.translate((int)(separation.x * 1.5 + alignment.x * 1.0 + cohesion.x * 1.0),
                           (int)(separation.y * 1.5 + alignment.y * 1.0 + cohesion.y * 1.0));

        // Limit speed
        limitSpeed();
    }

    private Point separate(List<Boid> boids) {
        Point steer = new Point(0, 0); // 회피 방향
        int count = 0; // 근처 Boid 수

        for (Boid other : boids) {
            int distance = (int) position.distance(other.position);
            if (other != this && distance > 0 && distance < SEPARATION_DISTANCE) {
                Point diff = new Point(position.x - other.position.x, position.y - other.position.y); // 거리 벡터
                steer.translate(diff.x / distance, diff.y / distance); // 방향 업데이트
                count++;
            }
        }

        if (count > 0) {
            // 평균 방향 계산
            steer.x /= count;
            steer.y /= count;
        }
        return steer;
    }

    private Point align(List<Boid> boids) {
        Point avgVelocity = new Point(0, 0); // 평균 속도
        int count = 0; // 근처 Boid 수

        for (Boid other : boids) {
            if (other != this && position.distance(other.position) < VISION_RANGE) {
                avgVelocity.translate(other.velocity.x, other.velocity.y); // 속도 누적
                count++;
            }
        }

        if (count > 0) {
            // 속도 평균을 내고 어디로 가야할지 방향을 계산한다.
            avgVelocity.x /= count;
            avgVelocity.y /= count;
            avgVelocity.translate(-velocity.x, -velocity.y);
        }
        return avgVelocity;
    }

    private Point cohere(List<Boid> boids) {
        Point center = new Point(0, 0); // 중심점
        int count = 0; // 근처 Boid tn

        for (Boid other : boids) {
            if (other != this && position.distance(other.position) < VISION_RANGE) {
                center.translate(other.position.x, other.position.y); // 위치 누적
                count++;
            }
        }

        if (count > 0) {
            // 중심점을 계산하고 방향을 정함
            center.x /= count;
            center.y /= count;
            center.translate(-position.x, -position.y);
        }
        return center;
    }

    // Boid에 힘 적용
    public void applyForce(Point force) {
        velocity.translate(force.x, force.y);
    }  

    // 속도 제한: Boid가 최대 SPEED를 초과하지 않도록 설정
    private void limitSpeed() {
        double magnitude = Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y);
        if (magnitude > SPEED) {
            velocity.x = (int) ((velocity.x / magnitude) * SPEED);
            velocity.y = (int) ((velocity.y / magnitude) * SPEED);
        }
    }

    // Boid의 위치 업데이트
    public void update() {
        position.translate(velocity.x, velocity.y);
        // wrap-around at screen edges
        if (position.x < 0) position.x = panelWidth;
        else if (position.x > panelWidth) position.x = 0;
        if (position.y < 0) position.y = panelHeight;
        else if (position.y > panelHeight) position.y = 0;
    }

    // draw boid as a triangle (position, velocity)
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
   
        double angle = Math.atan2(velocity.y, velocity.x);
        
        g2d.translate(position.x, position.y);
        g2d.rotate(angle);

        // 삼각형 그리기
        int[] xPoints = {0, -10, -10};
        int[] yPoints = {0, -5, 5};
        g2d.setColor(Color.BLUE);
        g2d.fillPolygon(xPoints, yPoints, 3);
        // 변환 초기화
        g2d.rotate(-angle);
        g2d.translate(-position.x, -position.y);

    }

    public Point getPosition() { return position; }
    public Point getVelocity() { return velocity; }
    public BoidState getState() { return state; }
    public int getId() { return id; }

    public void setState(BoidState state) { this.state = state;}

    @Override
    public String toString() {
        return "Boid{id=" + id + ", position=" + position + ", velocity=" + velocity + ", state=" + state + "}";
    }
}
