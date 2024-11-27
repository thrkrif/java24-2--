// 자바프로그래밍 2분반 32207522 양상훈

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Recipe10000Crawler extends AbstractWebCrawler {

    public Recipe10000Crawler(String url) {
        super(url);
    }

    @Override
    public void connectToWebsite() {
        System.out.println("Connecting to Recipe10000 website...");
    }

    @Override
    public void fetchPage() {
        try {
            document = Jsoup.connect(url).get();    // 웹페이지 가져오기
            System.out.println("Page fetched successfully.");
        } catch (Exception e) {
            System.out.println("Failed to fetch page: " + e.getMessage());
        }
    }

    @Override
    public void parsePage() {
        if (document != null) {
            System.out.println("Start parsing Recipe10000 page...");
        }
    }

    @Override
    public void process() {
        // fetchPage()가 정상적으로 실행된 경우에만 실행
        if (document != null) { 
            // .common_sp_list_ul li 사용 시 출력 제대로 안됨, 쇼핑 부분 -> slickList1
            Elements recipes = document.select(".slickList3 li"); // 화제의 TV 레시피
            int count = 0;

            for (Element recipe : recipes) {
                if (count >= 5) break; // 최대 5개까지만 출력

                // 레시피 기본 정보 추출
                String recipeTitle = recipe.select(".common_sp_caption_tit").text();
                String recipeDescription = recipe.select(".common_sp_caption_rv").attr("title");
                String recipeImage = recipe.select("img").attr("src");
                String recipeLink = recipe.select("a").attr("href"); // ex) /recipe/7035971
                
                // 상대 경로를 절대 경로로 변환
                if (!recipeLink.startsWith("http")) {
                    recipeLink = "https://www.10000recipe.com" + recipeLink;
                }

                System.out.printf("Recipe: %s | Description: %s | Image: %s | Link: %s%n",
                        recipeTitle, recipeDescription, recipeImage, recipeLink);

                // 상세 페이지로 이동하여 재료와 조리법 추출
                try {
                    Document recipePage = Jsoup.connect(recipeLink).get();

                    // 재료 추출
                    Elements ingredientElements = recipePage.select(".ready_ingre3 ul li");  // class="ready_ingre3"인 ul 태그 내의 li 태그를 선택한다.
                    String ingredients = ingredientElements.stream() // ingredientElements를 스트림으로 변환한다.
                            .map(Element::text) // 각 li 요소에서 텍스트만 추출한다. ex. <li>Carrot</li> -> Carrot만 추출
                            .reduce((a, b) -> a + ", " + b) // 스트림에서 각 텍스트를 결합하여 하나의 문자열로 만든다. 각 재료 사이에 ", "를 넣어준다.
                            .orElse("No ingredients found");

                    // 조리법 추출
                    Elements cookingStepElements = recipePage.select(".view_step_cont"); // class="view_step_cont" 인 요소들을 추출한다.
                    String cookingSteps = cookingStepElements.stream()
                            .map(step -> "- " + step.text())
                            .reduce((a, b) -> a + "\n" + b) // 각 조리법들이 한 줄로 출력될 수 있도록 한다.
                            .orElse("No cooking steps found");

                    // 결과 출력
                    System.out.println("Ingredients: " + ingredients);
                    System.out.println("Cooking Steps:\n" + cookingSteps);

                } catch (Exception e) {
                    System.out.println("Failed to fetch detailed recipe page: " + e.getMessage());
                }

                count++;
            }
        } else {
            System.out.println("Document is null. Unable to process recipes.");
        }
    }
}


