package imagevirtualproxy;

import java.awt.*;
import javax.swing.*;

public class ImageVirtualProxyMainTest {
    public static void main(String[] args) {
        // NASA image URL (MEDUSA NEBULA)
        String nasaImageUrl = "https://apod.nasa.gov/apod/image/2411/MEDUSA_NEBULA_FINAL_BRS_SIGNED1024.jpg";

        // Create JFrame
        JFrame frame = new JFrame("Virtual Proxy Example with NASA Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // JLabel for displaying images
        JLabel label = new JLabel("Loading...", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(label, BorderLayout.CENTER);

        // Create a ProxyImage
        Image proxyImage = new ProxyImage(nasaImageUrl);

        // Display image through ProxyImage
        proxyImage.display(label);

        // Show the frame
        frame.setVisible(true);
    }
}
