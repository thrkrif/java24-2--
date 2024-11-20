package maxfinder;

public class AbsNumMaxFinder extends AbstractMaxFinder {

	@Override
	public boolean isLesser(double a, double b) {
		return Math.abs(a) < Math.abs(b);
	}

}
