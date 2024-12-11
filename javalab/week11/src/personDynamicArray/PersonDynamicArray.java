package personDynamicArray;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class PersonDynamicArray implements Iterable<Person> {
	private Person[] data = null;
	private int size = 0;
	private int capacity = 0;

	public PersonDynamicArray() {
		this.capacity = 3;
		this.data = new Person[this.capacity];
	}	

	public PersonDynamicArray(int capacity) {
		this.capacity = capacity;
		this.data = new Person[capacity];
	}	

	public Person[] getAll() {
		return this.data;
	}
	
	public Person get(int index){
		if (index >= 0 && index < this.size)
			return this.data[index];
		return null;
	}

	// increase array size & copy all data
	private void copy(Person data, int newCapacity) {
		Person[] newData = new Person[newCapacity];

		// copy before newSize - 1
        for(int i = 0; i < newCapacity - 1; i++){
        	newData[i] = this.data[i];
        }
        newData[newCapacity-1] = data; // copy at the end
        
        this.data = newData;
    }

	// increase array size & copy data before & after the index
	private void copy(int index, Person data, int newCapacity) {
		Person[] newData = new Person[newCapacity];

		// copy before index
        for(int i = 0; i < index; i++){
        	newData[i] = this.data[i];
        }
        newData[index] = data; // copy at the index
		// copy after index
        for(int i = newCapacity - 1; i > index; i--){
        	newData[i] = this.data[i-1];
        }
        
        this.data = newData;
    }
	
	public void add(Person data) {
		if (this.size < this.capacity) {
			this.data[size++] = data;
		}
		else {
			//System.out.println("dynamically increase array size=" + size);
			this.size++;
			this.capacity++;
			copy(data, this.capacity);
		}
	}
	
	public void addAll(Person[] array) {
		for (Person p : array) {
			add(p);
		}
	}
	
	public void insert(int index, Person data) {
        if(this.size + 1 < this.capacity) {
            // insert new data 
            for(int i = this.size - 1; i > index - 1; i--){
                this.data[i+1] = this.data[i];
            }
            this.data[index-1] = data;
        } else {
			System.out.println("insert dynamically increase array capacity=" + capacity);
			this.size++;
			this.capacity++;
            copy(index, data, this.capacity);
        }		
	}
	
	public void remove(int index) {
		if (index < 0 || index >= size) {
			System.out.println("Error! cannot remove Person due to index out of bound");
		}
		else {
			for (int i = index; i < size - 1; i++) {
				this.data[i] = this.data[i+1];				
			}
			this.size--;
			this.data[size] = null;
		}
	}
	
	public void removeAll() {
		Iterator<Person> itr = this.iterator();
		while(itr.hasNext()) {
			Person e = itr.next();
			//System.out.println("remove: " + e);
			itr.remove();
		}
	}
	
	// clear
	public void clear() {
		this.size = 0;
		this.capacity = 3;
		this.data = new Person[this.capacity];
	}

	// size
	public int size() {
		return size;
	}
	
	// capacity
	public int capacity() {
		return capacity;
	}
	
	// print
	public void print() {
		System.out.println(this.getClass().getName() + " capacity = " + capacity + " size = " + size);
		for (int i = 0; i < size; i++) {
			System.out.println(this.data[i]);				
		}
	}
	
	// contains
	public boolean contains(Person value) {
		return Arrays.asList(this.data).contains(value);
	}
	
	// sort by Comparable
	public void sort() { 
		Arrays.sort(this.data, null);
	}
	
	// sort by Comparator
	public void sort(Comparator<? super Person> comparator) {
		Arrays.sort(this.data, comparator);
	}
	
	public Person[] findIf(Predicate<? super Person> predicate) { // return found elements into a list
		return Arrays.stream(this.data).filter(predicate).toArray(Person[]::new);
	}
	
	public String[] findIf(Predicate<? super Person> predicate, Function<? super Person, String> func) { // return found elements into a String list
		return Arrays.stream(this.data).filter(predicate).map(func).toArray(String[]::new);
	}
	
	// inner class iterator
	private class PersonDynamicArrayIterator implements Iterator<Person> {
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Person next() {
			if (!hasNext()) { 
				throw new NoSuchElementException(); 
			}
			return (Person) get(index++);
		}
		
		@Override
		public void remove() {
			PersonDynamicArray.this.remove(--index);
		}
	}
	
	@Override
	public Iterator<Person> iterator() {
		return new PersonDynamicArrayIterator();
	}
}
