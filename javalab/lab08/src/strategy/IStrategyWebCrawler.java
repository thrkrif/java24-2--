// 자바프로그래밍 2분반 32207522 양상훈
// your code
// strategy uses delegation
package strategy;

public interface IStrategyWebCrawler {
    void connectToWebsite();
    void fetchPage();
    void parsePage();
    void process();
    void disconnectFromWebsite();
}
