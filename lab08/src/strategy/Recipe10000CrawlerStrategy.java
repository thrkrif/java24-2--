// 자바프로그래밍 2분반 32207522 양상훈
// your code

package strategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Recipe10000CrawlerStrategy implements IStrategyWebCrawler{
    private String url;
    private Document document;

    public Recipe10000CrawlerStrategy(String url) {
        this.url = url;
    }

    @Override
    public void connectToWebsite() {
        System.out.println("Connecting to Recipe10000 website...");
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
            System.out.println("Start parsing Recipe10000 page...");
        }
    }

    @Override
    public void process() {
        if (document != null) {
            Elements recipes = document.select(".slickList3 li");
            int count = 0;

            for (Element recipe : recipes) {
                if (count >= 5) break;
                String recipeTitle = recipe.select(".common_sp_caption_tit").text();
                String recipeDescription = recipe.select(".common_sp_caption_rv").attr("title");
                String recipeImage = recipe.select("img").attr("src");
                String recipeLink = recipe.select("a").attr("href");

                if (!recipeLink.startsWith("http")) {
                    recipeLink = "https://www.10000recipe.com" + recipeLink;
                }

                System.out.printf("Recipe: %s | Description: %s | Image: %s | Link: %s%n",
                        recipeTitle, recipeDescription, recipeImage, recipeLink);

                try {
                    Document recipePage = Jsoup.connect(recipeLink).get();
                    Elements ingredientElements = recipePage.select(".ready_ingre3 ul li");
                    String ingredients = ingredientElements.stream()
                            .map(Element::text)
                            .reduce((a, b) -> a + ", " + b)
                            .orElse("No ingredients found");

                    Elements cookingStepElements = recipePage.select(".view_step_cont");
                    String cookingSteps = cookingStepElements.stream()
                            .map(step -> "- " + step.text())
                            .reduce((a, b) -> a + "\n" + b)
                            .orElse("No cooking steps found");

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

    @Override
    public void disconnectFromWebsite() {
        System.out.println("Disconnecting from website...");
    }
}
