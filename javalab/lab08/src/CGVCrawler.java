// 자바프로그래밍 2분반 32207522 양상훈

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CGVCrawler extends AbstractWebCrawler {

    public CGVCrawler(String url) {
        super(url);
    }

    @Override
    public void connectToWebsite() {
        System.out.println("Connecting to CGV website...");
    }

    @Override
    public void fetchPage() {
        try {
            document = Jsoup.connect(url).get();    // 웹 페이지 가져오기
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
        // fetchPage()가 정상적으로 실행된 경우에만 실행
        if (document != null) {
            // 처음에 movieImage가 설정되지 않았던 이유 
            // Elements movies = document.select(".sect-movie-chart .box-content");
            // sect-movie-chart 하위에 box-content와 box-image가 존재하였음 직접 확인함.
            // 아래와 같이 고치니까 해결됨.
            Elements movies = document.select(".sect-movie-chart ol li");
            int count = 0;
            for (Element movie : movies) {
                if (count >= 5) break; // 5개만 출력
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

}
