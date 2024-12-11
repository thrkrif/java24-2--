package imagevirtualproxy;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ProxyImage implements Image {
    private RealImage realImage;
    private String imageUrl;
    private ImageIcon placeholderIcon;

    public ProxyImage(String imageUrl) {
        this.imageUrl = imageUrl;
        this.placeholderIcon = new ImageIcon("placeholder.png"); // Placeholder image
    }

    @Override
    public void display(JLabel label) {
        if (realImage == null) {
            System.out.println("Displaying placeholder image...");
            label.setIcon(placeholderIcon); // Show placeholder image

            // Download real image in a separate thread
            new Thread(() -> {
                realImage = new RealImage(imageUrl);
                SwingUtilities.invokeLater(() -> realImage.display(label)); // Update UI with the real image
            }).start();
        } else {
            realImage.display(label);
        }
    }
}
