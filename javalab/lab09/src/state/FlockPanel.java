// 자바프로그래밍 2분반 32207522 양상훈
package state;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
// 새들의 행동과 움직임을 담당하는 클래스
public class FlockPanel extends JPanel {
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private static final int NUM_BOIDS = 20;
    private static final int UPDATE_INTERVAL = 200;

    private List<Boid> boids;

    public FlockPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.WHITE);
        
        // create boids
        boids = new ArrayList<>();
        for (int i = 0; i < NUM_BOIDS; i++) {
            Boid boid = new Boid(PANEL_WIDTH, PANEL_HEIGHT);
            boids.add(boid);
        }

        // create and start a Timer to update and repaint boids
        new Timer(UPDATE_INTERVAL, e -> {
            updateBoids();
            repaint();
        }).start();
    }

    // update each boid's position and behavior based on its current state
    private void updateBoids() {
        for (Boid boid : boids) {
            boid.flock(boids); // apply state-based behavior
            boid.update();     // update position
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Boid boid : boids) {
            boid.draw(g);
            System.out.println(boid);
        }
    }
}
