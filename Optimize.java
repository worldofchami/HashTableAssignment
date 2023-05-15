import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Optimize
{
    public static void main(String[] args)
    {
        try
        {
            LPHashTable hashTable = new LPHashTable(37);
            int[][] weights = getCombinations();

            ArrayList<Integer> probes = new ArrayList<Integer>();

            // For each combination of weights
            for(int i = 0; i < weights.length; i++)
            {
                hashTable.empty();
                hashTable.setWeights(weights[i]);

                Scanner scFile = new Scanner(new File("mydata.txt"));

                int sumProbes = 0;

                while(scFile.hasNextLine())
                {
                    String username = scFile.nextLine();

                    hashTable.insert(username);

                    sumProbes += hashTable.getProbeCount();

                    hashTable.resetProbeCount();
                }

                probes.add(sumProbes);

                scFile.close();
            }

            int minimum = min(probes);

            File file = new File("results.txt");
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(String.format("%d %d", minimum, occurences(minimum, probes)));

            fileWriter.close();
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

    private static int[][] getCombinations()
    {
        int numCombinations = (int) Math.pow(5, 9);
        int [][] weights = new int[numCombinations][9];

        for (int i = 0; i < numCombinations; i++) {
            int value = i;
            for (int j = 8; j >= 0; j--) {
                weights[i][j] = value % 5;
                value /= 5;
            }
        }

        return weights;
    }
}
