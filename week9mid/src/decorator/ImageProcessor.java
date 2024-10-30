package decorator;
import java.awt.image.BufferedImage;

public abstract class ImageProcessor {
	protected BufferedImage image = null;
	protected String name = null;
	protected String ext = null;
	protected String path = null;

	public void load(String filename) {
		image = ImageUtil.load(filename);
		name = ImageUtil.getFilenameWithoutExt(filename);
		ext = ImageUtil.getExtension(filename);
		path = ImageUtil.getPath(filename);
	}
	
	public void process() {
		BufferedImage newImage = process(getImage()); // decorated process
		String outputFile = getPath() != null ? getPath() + "/" + getName() + "." + getExt() : getName() + "." + getExt();
		//System.out.println("save : " + outputFile);
		if (newImage != null) ImageUtil.save(newImage, getExt(), outputFile);
	}
		
	public BufferedImage getImage() {
		return image;
	}
	
	public abstract String getName(); // name changed

	public String getExt() {
		return ext;
	}
	
	public String getPath() {
		return path;
	}
	
	public abstract BufferedImage process(BufferedImage image); // image process changed
}
