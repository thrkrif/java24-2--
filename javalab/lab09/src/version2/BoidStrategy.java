package version2;

import java.util.List;

public interface BoidStrategy {
    // 알고리즘
    void applyBehavior(Boid boid, List<Boid> boids);
}
