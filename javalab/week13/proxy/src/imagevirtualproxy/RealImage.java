package imagevirtualproxy;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class RealImage implements Image {
    private String imageUrl;
    private ImageIcon imageIcon;

    public RealImage(String imageUrl) {
        this.imageUrl = imageUrl;
        loadFromSite(); // Download the image
    }

    private void loadFromSite() {
        System.out.println("Downloading image from NASA: " + imageUrl);
        try {
            // Load the image from URL
            URL url = new URL(imageUrl);
            imageIcon = new ImageIcon(url);
            System.out.println("Download complete: " + imageUrl);
        } catch (Exception e) {
            System.err.println("Error downloading image: " + e.getMessage());
        }
    }

    @Override
    public void display(JLabel label) {
        System.out.println("Displaying real image from NASA: " + imageUrl);
        label.setIcon(imageIcon); // Set the downloaded image on JLabel
    }
}
