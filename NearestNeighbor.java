import java.util.Arrays;
import java.util.Comparator;

/**
 * URL: http://algogeeks.narkive.com/SakzFz8P/nearest-neighbour
 * Problem Description:
 * You have an array containing information regarding n people. Each person
 * is described using a string (their name) and a number (their position along
 * a number line). Each person has three friends, which are the three people
 * whose number is nearest their own. Describe an algorithm to identify each
 * person's three friends.
 *
 */
public class NearestNeighbor {
	
	/**
	 * Prints the friends for each person
	 * @param people
	 */
	public static void printFriends(Person[] people) {
		Arrays.sort(people); // sort people by their number
		for (int i=0; i<people.length; i++) {
			Person curr = people[i];
			System.out.print(curr + " has friends: ");
			System.out.println(Arrays.toString(getFriends(people, i)));
		}
	}
	
	/**
	 * Given a person at position i in people list, get person's list of friends
	 * @param people	sorted list of people
	 * @param i			index of person in people list
	 * @return			list of friends for person at index i
	 */
	public static Person[] getFriends(Person[] people, int i) {
		Person[] friends;	// to be determined
		/* corner case */
		if (people.length <= 4) { 
			friends = new Person[people.length - 1];
			System.arraycopy(people, 0, friends, 0, i);
			System.arraycopy(people, i + 1, friends, i, people.length - i - 1);
			return friends;
		}
		
		Person[] neighbors = new Person[6];
		int size = 0;	// size of neighbors list
		
		/* get previous 3 neighbors */
		int ptr = i - 1;
		while (ptr >= 0 && ptr >= i - 3) {
			neighbors[size] = people[ptr];
			size++;
			ptr--;
		}
		
		/* get next 3 neighbors */
		ptr = i + 1;
		while (ptr < people.length && ptr <= i + 3) {
			neighbors[size] = people[ptr];
			size++;
			ptr++;
		}
		
		/* get closest 3 neighbors */
		Person curr = people[i];
		neighbors = Arrays.copyOfRange(neighbors, 0, size);
		Arrays.sort(neighbors, new Comparator<Person>(){
			public int compare(Person a, Person b) {
				return Math.abs(a.number - curr.number) -
					   Math.abs(b.number - curr.number);
			}
		});
		friends = (Person[]) Arrays.copyOfRange(neighbors, 0, 3);
		return friends;
	}
	
	public static void main (String[] args) {
		Person[] people = {	new Person("John", 92),
							new Person("Mary", 23),
							new Person("Tom", 21),
							new Person("Dick", 7),
							new Person("Harry", 5),
							new Person("Jane", 57),
							new Person("Peter", 64),
							new Person("Ron", 12),
							new Person("Dexter", 43),
							new Person("James", 97)};
		printFriends(people);
	}
	
	
	/**
	 * Person class
	 */
	public static class Person implements Comparable<Person>{
		String name;
		int number;
		
		public Person (String name, int number) {
			this.name = name;
			this.number = number;
		}
		
		public int compareTo(Person aPerson) {
			return this.number - aPerson.number;
		}
		
		public String toString() {
			return name + ":" + number;
		}
	}
}
