// 자바프로그래밍2 2분반 컴퓨터공학과 32207522 양상훈

package strategy;

public class MainTest {

    public MainTest() {

        // 처리할 이미지 파일 리스트
        String[] imageFiles = {"cat1.jpg", "cat2.jpg"};	

		// 첫 설정을 Negative로 설정함.
		// PlaneImage 클래스를 추가하여 첫 설정은 아무런 기능이 없는 기본 이미지로 설정도 가능함.
		// 초기 설정을 제대로 확인하고 싶다면 ImageNegative 클래스에서 생성자의 주석 처리를 해제하면 된다.
		IProcessor basic = new ImageNegative();
		ImageProcessor scheduler = new ImageProcessor(basic);

        // 사용할 프로세서들 리스트
        IProcessor[] processors = {new ImageGrayscale(), 
								new ImageBlur(), new ImageEdgeDetect(),
								new ImageNegative(), new ImageRotate(45)
								};
							
        // 각 이미지 파일에 대해 모든 프로세서를 적용
		// 실행 도중에 setProcessor에 의해 Negative 상태에서 다른 기능으로 변경됨.
        for (String imageFile : imageFiles) {
            for (IProcessor processor : processors) {
                scheduler.setProcessor(processor);
                scheduler.process(imageFile); // 파일 이름을 전달하여 처리
            }
        }

		
    }
}
