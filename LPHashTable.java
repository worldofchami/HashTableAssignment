import java.util.ArrayList;
import java.util.List;

/**
 * Simple hash table implementation using linear probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class LPHashTable extends HashTable {
	/**
	 * Create an LPHashTable with DEFAULT_SIZE table.
	 */
	public LPHashTable() {
		super();
	}

	/**
	 * Create an LPHashTable with the given default size table.
	 */
	public LPHashTable(final int size) {
		super(size);
	}

	/**
	 * Find the index for entry: if entry is in the table, then returns its
	 * position;
	 * if it is not in the table then returns the index of the first free slot.
	 * Returns -1 if a slot is not found (such as when the table is full under LP).
	 *
	 */
	protected int findIndex(String key) {
		// Implement using linear probing.
		int hashCode = this.hashFunction(key);
		String[] table = this.table;

		this.incProbeCount();

		while(table[hashCode] != null)
		{			
			if(this.getProbeCount() < this.tableSize())
			{
				this.incProbeCount();

				if(table[hashCode].equalsIgnoreCase(key))
					return hashCode;

				hashCode = (hashFunction(key) + 1) % this.tableSize();
			}

			else
				return -1;
		}

		return hashCode;
	}
}
