// 자바프로그래밍 2분반 32207522 양상훈

import java.util.Map;
// Loader ->Importer
// FileImporter : Target
public class FileLoaderImporterAdapter<T> implements FileImporter<T> {
    private final FileLoader<T> fileLoader; // FileLoader 참조

    public FileLoaderImporterAdapter(FileLoader<T> fileLoader) {
        this.fileLoader = fileLoader;
    }

    @Override
    public Map<String, T> importFile(String filepath) {
        return fileLoader.load(filepath); // Adapter를 이용하여 load을 수행하도록 해줌.
    }

    @Override
    public void exportFile(String filepath, Map<String, T> map) {
        fileLoader.save(filepath, map); // Adapter를 이용하여 save을 수행하도록 해줌.
    }
}

