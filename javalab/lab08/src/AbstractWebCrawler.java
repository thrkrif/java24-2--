// 자바프로그래밍 2분반 32207522 양상훈
// template method uses inheritance
import org.jsoup.nodes.Document;

public abstract class AbstractWebCrawler {
    protected String url; // 웹 크롤러의 기본 URL
    protected Document document; // 웹 페이지를 저장할 Document 객체
    

    public AbstractWebCrawler(String url) {
        this.url = url;
    }

    // template method
    // skeleton
    public final void crawlWebsite() {
        connectToWebsite();
        fetchPage();
        parsePage();
        process();
        disconnectFromWebsite();
    }

    public abstract void connectToWebsite();

    public abstract void fetchPage();

    public abstract void parsePage();

    public abstract void process();

    public void disconnectFromWebsite() {
        System.out.println("Disconnecting from website...");
    }
}

