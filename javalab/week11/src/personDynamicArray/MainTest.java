package personDynamicArray;

import java.util.Arrays;
import java.util.Iterator;

public class MainTest {

	public MainTest() {
		Person[] people = {
			new Person(3000, "Dooly"),
			new Person(30, "Ddochi"),
			new Person(25, "Michol"),
			new Person(20000, "Douner"),
			new Person(3, "HeeDong")
		};
		System.out.println("\noriginal people");
		Arrays.stream(people).forEach(System.out::println);

		System.out.println("\n\nPersonDynamicArray add & print");		
		PersonDynamicArray arr = new PersonDynamicArray();
		for (Person p : people) {
			arr.add(p);
		}
		arr.forEach(System.out::println); // Iterable test

		System.out.println("\n\nPersonDynamicArray insert & print");		
		arr.insert(3, new Person(12, "YoungHee")); // insert
		arr.print(); // array print

		System.out.println("\n\nPersonDynamicArray insert & print");		
		arr.insert(3, new Person(42, "KilDong")); // insert
		arr.print(); // array print

		System.out.println("\n\nPersonDynamicArray removeAll & print");		
		// remove all
		Iterator<Person> itr = arr.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
			itr.remove();
		}
		arr.print();

		System.out.println("\n\nPersonDynamicArray add again & print");		
		// add again
		for (Person p : people) {
			arr.add(p);
		}
		arr.print();

		System.out.println("\n\nPersonDynamicArray clear & print");		
		arr.clear();
		arr.print();
		
		System.out.println("\n\nPersonDynamicArray add again & print");		
		// add again
		for (Person p : people) {
			arr.add(p);
		}
		arr.print();
		
		System.out.println("\nsort by default");
		arr.sort();
		arr.print();
		
		System.out.println("\nsort by Name");
		arr.sort(Person.NameComparator);
		for (Person p : arr) {
			System.out.println(p);
		}
		
		System.out.println("\nsort by Age");
		arr.sort(Person.AgeComparator);
		for (Person p : people) {
			System.out.println(p);
		}
	}

}
