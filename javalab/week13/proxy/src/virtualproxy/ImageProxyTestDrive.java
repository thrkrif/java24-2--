package virtualproxy;

import java.net.*;
import javax.swing.*;
import java.util.*;

public class ImageProxyTestDrive {
	ImageComponent imageComponent;
	JFrame frame = new JFrame("CD Cover Viewer");
	JMenuBar menuBar;
	JMenu menu;
	Hashtable<String, String> cds = new Hashtable<String, String>();

	public static void main (String[] args) throws Exception {
		ImageProxyTestDrive testDrive = new ImageProxyTestDrive();
	}

	// change image links from Amazon (which is blocked) to NASA
	public ImageProxyTestDrive() throws Exception{
		cds.put("The Medusa Nebula (2024 November 22)","https://apod.nasa.gov/apod/image/2411/MEDUSA_NEBULA_FINAL_BRS_SIGNED1024.jpg");
		cds.put("The Elephant's Trunk in Cepheus (2024 November 21)","https://apod.nasa.gov/apod/image/2411/LDN1105ElephantTrunk1024.jpg");
		cds.put("Earthset from Orion (2024 November 20)","https://apod.nasa.gov/apod/image/2411/earthset-snap01.png");
		cds.put("Undulatus Clouds over Las Campanas Observatory (2024 November 19)","https://apod.nasa.gov/apod/image/2411/ParallelClouds_Beletsky_960.jpg");
		cds.put("Stars and Dust in the Pacman Nebula (2024 November 18)","https://apod.nasa.gov/apod/image/2411/BokMan_Loro_960.jpg");
		cds.put("LDN 1471 A Windblown Star Cavity (2024 November 17)","https://apod.nasa.gov/apod/image/2411/LDN1471_HubbleSchmidt_960.jpg");

		URL initialURL = new URL((String)cds.get("The Medusa Nebula (2024 November 22)"));
		menuBar = new JMenuBar();
		menu = new JMenu("Favorite CDs");
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		for (Enumeration<String> e = cds.keys(); e.hasMoreElements();) {
			String name = (String)e.nextElement();
			JMenuItem menuItem = new JMenuItem(name);
			menu.add(menuItem); 
			menuItem.addActionListener(event -> {
				imageComponent.setIcon(new ImageProxy(getCDUrl(event.getActionCommand())));
				frame.repaint();
			});
		}

		// set up frame and menus

		Icon icon = new ImageProxy(initialURL);
		imageComponent = new ImageComponent(icon);
		frame.getContentPane().add(imageComponent);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setVisible(true);

	}

	URL getCDUrl(String name) {
		try {
			return new URL((String)cds.get(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
