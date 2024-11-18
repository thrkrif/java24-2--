// 자바프로그래밍 2분반 32207522 양상훈

import java.util.Map;
// Importer -> Loader
// FileLoader : Target
public class FileImporterLoaderAdapter<T> implements FileLoader<T> {
    private final FileImporter<T> fileImporter; // FileImporter 참조

    public FileImporterLoaderAdapter(FileImporter<T> fileImporter) {
        this.fileImporter = fileImporter;
    }

    @Override
    public Map<String, T> load(String filepath) {
        return fileImporter.importFile(filepath); // Adapter를 이용하여 importFile을 수행하도록 해줌.
    }

    @Override
    public void save(String filepath, Map<String, T> map) {
        fileImporter.exportFile(filepath, map); // Adapter를 이용하여 exportFile을 수행하도록 해줌.
    }
}
