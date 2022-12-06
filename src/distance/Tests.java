package distance;

import org.testng.annotations.Test;

import static distance.Distance.*;

public class Tests {

    public void testDistance ( String s1, String s2){
        int [][] D = distanceMatrix(s1,s2);
        System.out.println(formatIntMatrix(D));
        String [][] S = substringsMatrix(s1,s2,D);
        System.out.println(formatStringMatrix(S));
        for(int i=0; i<=s1.length(); i++){
            for(int j=0; j<=s2.length(); j++){
                System.out.println(getSubProblem(s1,s2,S,D,i,j));
            }
        }
    }

    @Test
    public void test_00 ( ){
        testDistance("a","b");
    }

    @Test
    public void test_01 (){
        testDistance("a","ab");
    }

    @Test
    public void test_02 (){
        testDistance("ac","ab");
    }

    @Test
    public void test_03 (){
        testDistance("ac","abc");
    }

    @Test
    public void test_04 (){
        testDistance("ace","abd");
    }

}
