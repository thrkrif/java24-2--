package barista;

public abstract class CaffeineBeverageWithHook {

	// template method
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if (customerWantsCondiments()) {
			addCondiments();			
		}
	}

	// hook
	protected boolean customerWantsCondiments() {
		return false;
	}

	// primitiveOperations
	// 하위 클래스에서 오버라이드 해야하므로 protected
	protected abstract void brew();
	protected abstract void addCondiments();

	// concreteOperations
	// 하위 클래스에서 바꿀 필요가 없음. private
	private void boilWater() {
		System.out.println("Boiling Water");
	}

	private void pourInCup() {
		System.out.println("Pouring into Cup");
	}


}


// public abstract class CaffeineBeverageWithHook{
// 	final void prepareRecipe(){
// 		boilWater();
// 		brew();
// 		pourInCup();
// 		if(customerWantsCondiments()){
// 			addCondiments();
// 		}
// 	}
// 	protected boolean customerWantsCondiments(){
// 		return false;
// 	}
// 	protected abstract void brew();
// 	protected abstract void addCondiments();
	
// 	private void boilWater(){
// 		System.out.println();
// 	}
// 	private void pourInCup(){
// 		System.out.println();
// 	}



// }
