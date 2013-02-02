import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 1/29/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class HackerCup_2 {
    public static void main(String[] args) throws IOException {
        int testCases;
        int count=0;
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        testCases=Integer.parseInt(bufferedReader.readLine());
        List<String> inputs=new ArrayList<String>();
        String temp;

        while(count<testCases)
        {
            temp=bufferedReader.readLine().toLowerCase().replaceAll("[^:\\(\\)]+", "");
            inputs.add(simplifyMore(temp));
            count++;
        }
        int outNum=1;
        for(String s:inputs)
        {
            char[] temp1=null;
            for (int i=0;i<s.length()/2;i++)
            {
                if(s.charAt(i)=='(' && s.charAt(s.length()-1-i)==')')
                {
                    temp1=s.toCharArray();
                    temp1[i]='a';
                    temp1[s.length()-1-i]='a';
                }
            }
            s=temp1.toString().replaceAll("a","");
            s=s.replaceAll("(:\\()","");
            s=s.replaceAll("(:\\))","");
            if(s.isEmpty())
            {
                System.out.println("Case #"+outNum+": "+"YES");
            }
            else
            {
                System.out.println("Case #"+outNum+": "+"NO");
            }
            outNum++;
        }
    }

    public static String simplifyMore(String input)
    {
        char[] inputChar=input.toCharArray();
        if(inputChar[0]==':')
        {
            if(inputChar[1]!='(' && inputChar[1]!=')')
            {
                inputChar[0]='a';
            }
        }
        if(inputChar[inputChar.length-1]==':')
        {
            if(inputChar[inputChar.length-2]!='(' && inputChar[inputChar.length-2]!=')')
            {
                inputChar[inputChar.length-1]='a';
            }
        }
        for(int i=1;i<inputChar.length-1;i++)
        {
            if(inputChar[i]==':')
            {
                if(inputChar[i-1]!='(' && inputChar[i-1]!=')'&& inputChar[i+1]!='(' && inputChar[i+1]!=')')
                {
                    inputChar[i]='a';
                }
            }
        }
        return new String(inputChar).replaceAll("a","");
    }
}
