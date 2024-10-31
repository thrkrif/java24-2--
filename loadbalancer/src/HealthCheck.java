import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HealthCheck {
    private LoadBalancer loadBalancer;

    public HealthCheck(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public void start() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(loadBalancer::healthCheck, 0, 1, TimeUnit.SECONDS);
    }
}
