package mid3;
public class Sandwich implements Ingredient{
    // concrete component

    // 샌드위치를 어떻게 조리할지 레시피를 추가 했다.
    // 전략 패턴 : 클라이언트 코드는 인터페이스를 참조한다.
    // 가져왔으니 여기서 recipe의 메서드 cook()을 사용할 수 있다.
    private Recipe recipe;


    // 필드가 존재하므로 생성자를 만들어 필드도 초기화 해줘야지
    public Sandwich(Recipe recipe){
        this.recipe = recipe;
    }


    @Override
    public String getDescription(){
        return "basic sandwich, recipe : " + recipe.cook();
    }

    @Override
    public double cost(){
        return 0.5; // 기본 샌드위치의 가격
    }


}
