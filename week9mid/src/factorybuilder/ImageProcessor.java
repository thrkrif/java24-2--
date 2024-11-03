// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package factorybuilder;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageProcessor {

    // IProcessor 인터페이스를 구현한 여러 프로세서를 저장할 리스트
    private List<IProcessor> processors = new ArrayList<>();

    // 빌더 패턴을 이용하여 필드를 초기화 한다.
    private ImageProcessor(Builder builder) {
        this.processors = builder.processors;
    }

    // 프로세서 리스트를 반환하는 메서드
    public List<IProcessor> getProcessors(){
        return this.processors;
    }

    // 리스트에 들어있는 모든 프로세서를 실행한다.
    public void process(String filename){
        for(IProcessor processor : processors){
            String format = ImageUtil.getExtension(filename);
			BufferedImage image = ImageUtil.load(filename);
            BufferedImage outputImage = processor.process(image);
			String outputFile = ImageUtil.getFullpathWithoutExt(filename) 
            + processor.toString() + "." + ImageUtil.getExtension(filename);
			ImageUtil.save(outputImage, format, outputFile);
        }
    }

    // Builder 클래스로 ImageProcessor 클래스 내부에 작성한다.
    public static class  Builder {
        // IProcessor 객체들을 저장할 리스트
        private List<IProcessor> processors = new ArrayList<>();

        // 빌더에 IProcessor를 추가하는 메서드
        public Builder add(IProcessor processor){
            processors.add(processor);
            return this;
        }
        
        // ImageProcessor 객체 생성 및 반환 메서드
        public ImageProcessor build(){
            return new ImageProcessor(this);
        }
    }

}
