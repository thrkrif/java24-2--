// 자바프로그래밍 2분반 32207522 양상훈
// your code

package strategy;

public class MainTest{
    public MainTest(){
        IStrategyWebCrawler[] strategies = {
            new Recipe10000CrawlerStrategy("https://www.10000recipe.com"),
            new CGVCrawlerStrategy("http://www.cgv.co.kr/movies/?lt=1&ft=0")};
        
        StrategyWebCrawler crawler = new StrategyWebCrawler();

        for (IStrategyWebCrawler strategy : strategies){
            crawler.setStrategy(strategy);
            crawler.crawlWebsite();
        }

    }
}
