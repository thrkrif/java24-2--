package decorator;
import java.awt.image.BufferedImage;

// Component로 ImageProcessorDecorator에서 참조한다.
// 추상 클래스로 두어 하위에 여러개의 Concrete Component를 생성 가능하다.
public abstract class ImageProcessor {

	protected BufferedImage image = null;	// 이미지 파일
	protected String name = null;	// 이미지 파일명
	protected String ext = null;	// 이미지 파일 확장자
	protected String path = null;	// 이미지 파일 경로

	// ImageUtil을 이용하여 필드 초기화
	public void load(String filename) {
		image = ImageUtil.load(filename);
		name = ImageUtil.getFilenameWithoutExt(filename);
		ext = ImageUtil.getExtension(filename);
		path = ImageUtil.getPath(filename);
	}
	
	// 처리된 이미지를 저장하는 메서드
	public void process() {
		// 하위 클래스인 Concrete Component에서 새로운 이미지를 생성할 것임.
		BufferedImage newImage = process(getImage()); // decorated process
		// 저장할 출력 파일의 경로 설정
		String outputFile = getPath() != null ? getPath() + "/" + getName() + "." + getExt() : getName() + "." + getExt();
		//System.out.println("save : " + outputFile);
		// 새로운 이미지가 생성된 경우 해당 경로에 이미지를 저장한다.
		if (newImage != null) ImageUtil.save(newImage, getExt(), outputFile);
	}
	
	// 현재 이미지를 반환한다.
	public BufferedImage getImage() {
		return image;
	}
	
	public abstract String getName(); // name changed

	// 파일 확장자를 반환한다.
	public String getExt() {
		return ext;
	}
	
	// 파일 경로를 반환한다.
	public String getPath() {
		return path;
	}
	
	public abstract BufferedImage process(BufferedImage image); // image process changed
}
