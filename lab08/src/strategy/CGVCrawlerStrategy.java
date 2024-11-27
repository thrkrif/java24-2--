// 자바프로그래밍 2분반 32207522 양상훈
// your code

package strategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CGVCrawlerStrategy implements IStrategyWebCrawler{

    private String url;
    private Document document;

    public CGVCrawlerStrategy(String url) {
        this.url = url;
    }

    @Override
    public void connectToWebsite() {
        System.out.println("Connecting to CGV website...");
    }

    @Override
    public void fetchPage() {
        try {
            document = Jsoup.connect(url).get();
            System.out.println("Page fetched successfully.");
        } catch (Exception e) {
            System.out.println("Failed to fetch page: " + e.getMessage());
        }
    }

    @Override
    public void parsePage() {
        if (document != null) {
            System.out.println("Start parsing CGV page...");
        }
    }

    @Override
    public void process() {
        if (document != null) {
            Elements movies = document.select(".sect-movie-chart ol li");
            int count = 0;
            for (Element movie : movies) {
                if (count >= 5) break;
                String movieTitle = movie.select(".title").text();
                String releaseDate = movie.select(".txt-info strong").text();
                String rating = movie.select(".percent span").text();
                String movieImage = movie.select(".thumb-image img").attr("src");

                System.out.printf("Movie: %s | Release: %s | Rating: %s | Image: %s%n",
                        movieTitle, releaseDate, rating, movieImage);
                count++;
            }
        }
    }

    @Override
    public void disconnectFromWebsite() {
        System.out.println("Disconnecting from website...");
    }
}
