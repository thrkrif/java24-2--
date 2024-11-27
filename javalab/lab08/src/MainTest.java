// 자바프로그래밍 2분반 32207522 양상훈

public class MainTest {
    public static void main(String[] args) {
        // Recipe10000Crawler 테스트
        AbstractWebCrawler crawler = new Recipe10000Crawler("https://www.10000recipe.com");
        crawler.crawlWebsite();

        // CGVCrawler 테스트
        crawler = new CGVCrawler("http://www.cgv.co.kr/movies/?lt=1&ft=0");
        crawler.crawlWebsite();

        // yourcode : Strategy Pattern
        System.out.println();
        System.out.println("Strategy Pattern");
        new strategy.MainTest();
    }
}

