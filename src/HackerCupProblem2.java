import com.sun.jmx.snmp.SnmpStringFixed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 1/26/13
 * Time: 10:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class HackerCupProblem2 {
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
        boolean [] outs=new boolean[testCases];
        int cnt=0;
        for(String s:inputs)
        {
            ArrayList<String> simplified=isUnderstandable(s);
            outs[cnt]=false;
            for(String a:simplified)
            {
               if(a.isEmpty())
               {
                   outs[cnt]=true;
                   break;
               }
               else if(isUnderstandableString(a).isEmpty())
               {
                   outs[cnt]=true;
                   break;
               }
            }
            cnt++;
        }
        int testCaseNum=1;
        String testOut="NO";
        for(boolean out:outs)
        {
            if(!out)
            {
                testOut="NO";
            }
            else
            {
                testOut="YES";
            }
            System.out.println("Case #"+testCaseNum+": "+testOut);
            testCaseNum++;
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

    public static ArrayList<String> isUnderstandable(String abc)
    {
        ArrayList<String> simplyfied=new ArrayList<String>();
        Queue<String> stringQueue=new LinkedList<String>();
        boolean isContains=true;
        int iteration=0;
        if(abc.contains(":"))
        {
            while (isContains)
            {
                if(stringQueue.isEmpty())
                {
                    //simplyfied.add(thinkAsSmily(abc));
                    //simplyfied.add(thinkNotAsSmily(abc));
                    stringQueue.add(thinkAsSmily(abc));
                    stringQueue.add(thinkNotAsSmily(abc));
                    iteration++;
                }
                if(iteration!=1)
                {
                    String temp;
                    for(int i=0;i<iteration;i++)
                    {
                        temp=stringQueue.poll();
                        //simplyfied.add(thinkAsSmily(simplyfied.get(i)));
                        //simplyfied.add(thinkNotAsSmily(simplyfied.get(i)));
                        stringQueue.add(thinkAsSmily(temp));
                        stringQueue.add(thinkNotAsSmily(temp));
                    }

                }
                iteration=iteration*2;
                if(!stringQueue.peek().contains(":"))
                {
                    isContains=false;
                }
            }
        }
        else
        {
            stringQueue.add(abc);
        }
        return new ArrayList<String>(stringQueue);
    }

    public static String thinkAsSmily(String abc)
    {
        if(abc.indexOf(":")<abc.length()-1)
        {
            if(abc.charAt(abc.indexOf(":")+1)==')')
            {
                return abc.replaceFirst(":\\)","");
            }
            else
            {
                return abc.replaceFirst(":\\(","");
            }
        }
        else
        {
            return abc.replace(":","");
        }
    }

    public static String thinkNotAsSmily(String abc)
    {
        return abc.replaceFirst(":","");
    }

    public static String isUnderstandableString(String abcd)
    {
        if(abcd.contains("()"))
        {
            abcd=abcd.replaceAll("\\(\\)","");
            isUnderstandableString(abcd);
        }
        return abcd;
    }
}

