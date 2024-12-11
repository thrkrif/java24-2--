package iterenum;

import java.util.*;

public class IteratorEnumerationMainTest {
	public IteratorEnumerationMainTest() {
		String[] names = { "Seoul", "Busan", "Incheon", "Ulsan", "Daejeon" };
		Vector<String> v = new Vector<String>(Arrays.asList(names));

		Enumeration<String> enumeration = v.elements();
		while (enumeration.hasMoreElements()) {
			System.out.println(enumeration.nextElement());
		}
		System.out.println();

		Iterator<String> iterator = v.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println();

		Iterator<?> iterator2 = new EnumerationIterator(v.elements());
		while (iterator2.hasNext()) {
			System.out.println(iterator2.next());
		}
		System.out.println();

		Enumeration<?> enumeration2 = new IteratorEnumeration(v.iterator());
		while (enumeration2.hasMoreElements()) {
			System.out.println(enumeration2.nextElement());
		}
		System.out.println();
	}
}
