// 자바프로그래밍 2분반 32207522 양상훈
// your code

package strategy;

public class StrategyWebCrawler {
    private IStrategyWebCrawler strategy;   // 인터페이스 객체를 참조한다.

    // 기본 생성자, strategy의 default = null

    // Main에서 실행중에 Strategy를 바꾸기 위한 메서드
    public void setStrategy(IStrategyWebCrawler strategy) {
        this.strategy = strategy;
    }

    public void crawlWebsite() {
        strategy.connectToWebsite();
        strategy.fetchPage();
        strategy.parsePage();
        strategy.process();
        strategy.disconnectFromWebsite();
    }
}

