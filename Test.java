public class Test {
    public static void main(String[] args) {
        LPHashTable lph = new LPHashTable(10);

        int hashCode = lph.hashFunction("abcdef012");

        String[] table = lph.table;

        table[5] = "not null!11";
        table[6] = "not null!2";
        table[7] = "not null!3";
        table[8] = "not null!4";

        String key = "not null!2";

        int probes = 0;

		while(table[hashCode] != null && probes < table.length) {
			probes++;
			if(table[hashCode].equalsIgnoreCase(key)) {
				System.out.println(hashCode);
                System.exit(1);
            }

			hashCode = (hashCode + 1) % table.length;
		}

		System.out.println(-1);
    }    
}
