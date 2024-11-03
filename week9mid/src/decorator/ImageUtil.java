// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package decorator;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// 이미지 관련 유틸리티 기능을 제공하는 클래스
public class ImageUtil {
	// 이미지를 로드하는 메서드
    public static BufferedImage load(String fullPath) {
    	try {
			// 이미지를 읽고 호환 가능한 이미지로 변환하여 반환
			return toCompatibleImage(ImageIO.read(new File(fullPath)));
		} catch (IOException e) {
			// 로드 실패 시 오류 메시지를 출력
			System.out.println(fullPath  + " could not be loaded. It's not an image!");
			e.printStackTrace();
		}
		return null;
    }

    // 읽은 이미지를 기본 화면 장치와 호환되는 BufferedImage로 변환하는 메서드
    public static BufferedImage toCompatibleImage(BufferedImage image) {
        BufferedImage compatibleImage = null;
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	GraphicsConfiguration gc = gd.getDefaultConfiguration();
    	compatibleImage = gc.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
    	Graphics2D g2d = compatibleImage.createGraphics();
    	g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    	g2d.dispose();
        return compatibleImage;
    }

    // 이미지를 지정한 경로와 형식으로 저장하는 메서드
    public static void save(BufferedImage image, String format, String fullPath) {
    	try {
			boolean result = ImageIO.write(image, format, new File(fullPath));
			if (result) {
				System.out.println("save to " + fullPath + " in format " + format + " successfully.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
    // 파일 경로에서 디렉터리 경로를 추출하는 메서드 (예: C:/JAVA/IMG.jpg -> C:/JAVA/)
    public static String getPath(String fullPath) {
    	return fullPath.lastIndexOf('/') == -1 ? null : fullPath.substring(0, fullPath.lastIndexOf('/'));
    }
    
    // 파일 경로에서 확장자를 제외한 파일 이름을 추출하는 메서드 (예: C:/JAVA/IMG.jpg -> IMG)
    public static String getFilenameWithoutExt(String fullPath) {
    	return fullPath.substring(fullPath.lastIndexOf('/') + 1, fullPath.lastIndexOf('.'));
    }    
    
    // 파일 경로에서 확장자를 제외한 전체 경로를 추출하는 메서드 (예: C:/JAVA/IMG.jpg -> C:/JAVA/IMG)
    public static String getFullpathWithoutExt(String fullPath) {
    	return fullPath.substring(0, fullPath.lastIndexOf('.'));
    }
    
    // 파일 경로에서 확장자만 추출하는 메서드 (예: C:/JAVA/IMG.jpg -> jpg)
    public static String getExtension(String fullPath) {
    	return fullPath.substring(fullPath.lastIndexOf('.') + 1);
    }
}
