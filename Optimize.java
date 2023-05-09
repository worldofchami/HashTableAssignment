import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Optimize
{
    public static void main(String[] args)
    {
        try
        {
            LPHashTable hashTable = new LPHashTable(37);
            LPHashTable tempHashTable = new LPHashTable(37);
            int[][] weights = {
                {1, 2, 3, 4, 1, 2, 3, 4, 1},
                {1, 2, 2, 4, 1, 2, 3, 4, 1},
                {1, 2, 3, 1, 1, 2, 4, 4, 1},
                {1, 1, 3, 1, 1, 2, 4, 4, 1},
                {1, 3, 3, 1, 1, 2, 4, 4, 1},
                {1, 2, 1, 1, 1, 2, 4, 4, 1},
            };

            ArrayList<Integer> probes = new ArrayList<Integer>();

            // For each combination of weights
            for(int i = 0; i < weights.length; i++)
            {
                tempHashTable.empty();
                tempHashTable.setWeights(weights[i]);

                Scanner scFile = new Scanner(new File("mydata.txt"));

                int sumProbes = 0;

                while(scFile.hasNextLine())
                {
                    String username = scFile.nextLine();

                    tempHashTable.insert(username);

                    sumProbes += tempHashTable.getProbeCount();

                    tempHashTable.resetProbeCount();
                }

                probes.add(sumProbes);

                scFile.close();
            }

            System.out.println(probes);

            int minimum = min(probes);
            System.out.println(String.format("%d %d", minimum, occurences(minimum, probes)));
        }   
        
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private static int min(ArrayList<Integer> list)
    {
        int minimum = list.get(0);

        for(int num : list)
            if(num < minimum)
                minimum = num;

        return minimum;
    }

    private static int occurences(int num, ArrayList<Integer> list)
    {
        int count = 0;

        for(int entry : list)
            if(entry == num)
                count++;

        return count;
    }
}
