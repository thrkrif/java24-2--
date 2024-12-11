package personDynamicArray;
import java.util.Comparator;

public class Person implements Comparable<Person> {
	private int age;
	private String name;
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Person other) {
		return this.getAge() - other.getAge();
	}
	
	public static Comparator<Person> NameComparator = new Comparator<Person>() {

		@Override
		public int compare(Person o1, Person o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};

	public static Comparator<Person> AgeComparator = new Comparator<Person>() {

		@Override
		public int compare(Person o1, Person o2) {
			return o1.getAge() - o2.getAge(); // ascending order
		}
	};
}
