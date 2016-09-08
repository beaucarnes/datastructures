
public class HashStructure {

	public static void main(String[] args) {

		// BC Test cases
		HashFunction addressHashTable = new HashFunction();

		addressHashTable.insert("Bob", "Smith", "bsmith@somewhere.com", "555-235-1111");
		addressHashTable.insert("Jane", "Williams", "jw@something.com", "555-235-1112");
		addressHashTable.insert("Mohammed", "al-Salam", "mas@someplace.com", "555-235-1113");
		addressHashTable.insert("Pat", "Jones", "pjones@homesweethome.com", "555-235-1114");
		addressHashTable.insert("Billy", "Kidd", "billy_the_kid@nowhere.com", "555-235-1115");
		addressHashTable.insert("H.", "Houdini", "houdini@noplace.com", "555-235-1116");
		addressHashTable.insert("Jack", "Jones", "jjones@hill.com", "555-235-1117");
		addressHashTable.insert("Jill", "Jones", "jillj@hill.com", "555-235-1118");
		addressHashTable.insert("John", "Doe", "jdoe@somedomain.com", "555-235-1119");
		addressHashTable.insert("Jane", "Doe", "jdoe@somedomain.com", "555-235-1120");
		addressHashTable.find("Pat", "Jones");
		addressHashTable.find("Billy", "Kidd");
		addressHashTable.delete("John", "Doe");
		addressHashTable.insert("Test", "Case", "Test_Case@testcase.com", "555-235-1121");
		addressHashTable.insert("Nadezhda", "Kanachekhovskaya", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru", "555-235-1122");
		addressHashTable.insert("Jo", "Wu", "wu@h.com", "555-235-1123");
		addressHashTable.insert("Millard", "Fillmore", "millard@theactualwhitehouse.us", "555-235-1124");
		addressHashTable.insert("Bob", "vanDyke", "vandyke@nodomain.com", "555-235-1125");
		addressHashTable.insert("Upside", "Down", "upsidedown@rightsideup.com", "555-235-1126");
		addressHashTable.find("Jack", "Jones");
		addressHashTable.find("Nadezhda", "Kanachekhovskaya");
		addressHashTable.delete("Jill", "Jones");
		addressHashTable.delete("John", "Doe");
		addressHashTable.find("Jill", "Jones");
		addressHashTable.find("John", "Doe");

	}
}

// BC Create hash function, starting with variables

class HashFunction {
	AddressList[] theArray;
	int arraySize = 13; // BC Amount of buckets in hash structure
	int itemsInArray = 0;

	public int hash(String addressToHash) {
		int hashIndex = addressToHash.hashCode() % arraySize;
		if (hashIndex < 0)
			hashIndex = hashIndex + arraySize;
		return hashIndex;

	}

	// BC Create the hash table and fill with AddressLists
	HashFunction() {

		theArray = new AddressList[arraySize];

		for (int i = 0; i < arraySize; i++) {

			theArray[i] = new AddressList();

		}

	}

	public void insert(String firstName, String lastName, String eMail, String phone) {

		// BC Create new Address object
		Address newAddress = new Address(firstName, lastName, eMail, phone);
		String addressToHash = newAddress.fullName;

		// BC Calculate hash based on full name
		int hashKey = hash(addressToHash);

		// BC Add the new address to hash table array

		theArray[hashKey].insert(newAddress, hashKey);
		
	}

	public Address find(String firstName, String lastName) {

		String fullName = new String(firstName + " " + lastName);
		fullName = fullName.toUpperCase();
		int hashKey = hash(fullName);

		// BC Find based on hash of full name

		Address theAddress = theArray[hashKey].find(fullName);

		return theAddress;

	}

	public Address delete(String firstName, String lastName) {

		String fullName = new String(firstName + " " + lastName);
		fullName = fullName.toUpperCase();

		int hashKey = hash(fullName);

		// BC Delete based on hash of full name

		Address theAddress = theArray[hashKey].delete(fullName);

		return theAddress;

	}

}

class Address {

	public String fullName;
	public String eMail;
	public String phone;

	public int key;

	// BC Reference to next Address made in the AddressList

	public Address next;

	public Address(String firstName, String lastName, String eMail, String phone) {
		String name = new String(firstName + " " + lastName);
		this.fullName = name.toUpperCase();
		this.eMail = eMail;
		this.phone = phone;

	}

	public String toString() {

		return "name: " + fullName + ", e-mail:  " + eMail + ", phone: " + phone;

	}

}

class AddressList {

	public Address firstAddress = null; // BC Used to keep track of first link in list

	public void insert(Address newAddress, int hashKey) {

		//BC Insert new entry in ArrayList.
		
		Address previous = null;
		Address current = firstAddress;

		newAddress.key = hashKey;

		while (current != null) {

			previous = current;
			current = current.next;

		}

		if (previous == null)
			firstAddress = newAddress;

		else
			previous.next = newAddress;

		newAddress.next = current;
		
		System.out.println("Address entry created for " + newAddress.fullName); //BC Print name of entry created

	}

	public Address find(String addressToFind) {

		// BC Search for key in the list and print out the full entry

		Address current = firstAddress;

		while (current != null) {

			if (current.fullName.equals(addressToFind)) {
				System.out.println(current);  // BC Print entry when found
				return current;
			}
			current = current.next;

		}

		System.out.println("Name not found: " + addressToFind); // BC Print if entry is not found
		return null;

	}

	public Address delete(String addressToDelete) {

		// BC Search for key and delete entry.

		Address previous = null;
		Address current = firstAddress;

		while (current != null) {

			if (current.fullName.equals(addressToDelete)) {
				if (current == firstAddress)
					firstAddress = current.next;
				else
					previous.next = current.next;
				
				System.out.println("Address entry deleted for " + current.fullName); // BC Print that the entry has been deleted
				
				return current;
			}

			previous = current;
			current = current.next;

		}
		System.out.println("Nothing deleted because name not found: " + addressToDelete); // BC Print when not found.
		return current;

	}
}
