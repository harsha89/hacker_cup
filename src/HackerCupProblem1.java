import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 1/26/13
 * Time: 9:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class HackerCupProblem1 {
    public static void main(String[] args) throws IOException {
        int testCases;
        int count=0;
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        testCases=Integer.parseInt(bufferedReader.readLine());
        List<String> inputs=new ArrayList<String>();
        while(count<testCases)
        {
            inputs.add(bufferedReader.readLine().toLowerCase().replaceAll("[^a-z]+", ""));
            count++;
        }
        List<Map<Character,Integer>> counts=getDistinctLetterCount(inputs);
        List<int[]> solution=new ArrayList<int[]>();
        for (Map<Character,Integer> lCounts:counts)
        {
            int tempCount=0;
            int[] arr=new int[lCounts.keySet().size()];
            for (Map.Entry<Character, Integer> entry : lCounts.entrySet()) {
                arr[tempCount]=entry.getValue();
                tempCount++;
            }
            Arrays.sort(arr);
            solution.add(arr);
        }
        int testCaseNum=1;
        for(int[] a:solution)
        {
            int out=0;
            int fixed=26;
            for(int b=a.length;b>0;b--)
            {
              out+=fixed * a[b-1];
                fixed--;
            }
            System.out.println("Case #"+testCaseNum+": "+out);
            testCaseNum++;
        }
    }

    public static List<Map<Character,Integer>> getDistinctLetterCount(List<String> inputs)
    {
        char[] input;
        int count;
        List<Map<Character,Integer>> output=new ArrayList<Map<Character,Integer>>();
        for(String s:inputs)
        {
            Map<Character,Integer> letterCount=new HashMap<Character, Integer>();
            input=s.toCharArray();

            for(char a:input)
            {
                if(letterCount.containsKey(a))
                {
                    count=letterCount.get(a);
                    count++;
                    letterCount.put(a,count);
                }
                else
                {
                    letterCount.put(a,1);
                }
            }
            output.add(letterCount);
        }
        return output;
    }
}
