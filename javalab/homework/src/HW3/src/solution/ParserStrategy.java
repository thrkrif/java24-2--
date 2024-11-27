package HW3.src.solution;
// 제네릭 타입
public interface ParserStrategy<T> {
    T parse(String line);
}
