package factorybuilder;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageProcessor {
    private List<IProcessor> processors = new ArrayList<>();

    private ImageProcessor(Builder builder) {
        this.processors = builder.processors;
    }

    public List<IProcessor> getProcessors(){
        return this.processors;
    }

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

    public static class  Builder {
        private List<IProcessor> processors = new ArrayList<>();

        public Builder add(IProcessor processor){
            processors.add(processor);
            return this;
        }

        public ImageProcessor build(){
            return new ImageProcessor(this);
        }
    }

}
