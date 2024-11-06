// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package strategy;

import java.awt.image.BufferedImage;

// Context 클래스(strategy를 등록하고 실행한다.)
public class ImageProcessor {

    // Composition 이용
    private IProcessor processor;

    // 생성자를 통해 전략 객체를 초기화한다. -> 초기 설정
    public ImageProcessor(IProcessor processor) {
        this.processor = processor;
    }

    // 실행 중 전략을 변경할 수 있는 메서드
    public void setProcessor(IProcessor processor){
        this.processor = processor;
    }

	// 파일을 받아 이미지 처리 수행
    public void process(String filename) {
        String format = ImageUtil.getExtension(filename);
        BufferedImage image = ImageUtil.load(filename);
        BufferedImage outputImage = processor.process(image);
        String outputFile = ImageUtil.getFullpathWithoutExt(filename) 
        + processor.toString() + "." + format;
        ImageUtil.save(outputImage, format, outputFile);
    }
}
