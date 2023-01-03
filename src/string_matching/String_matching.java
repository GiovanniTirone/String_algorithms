package string_matching;

import utility.Utility;

import java.util.Arrays;

public class String_matching {

    private String pattern ;

    private char[] pat_ar;

    private char [] alphabet;

    private String [] status;

    public String_matching (String pattern,char [] alphabet) {
        this.pattern =  "#" + pattern;
        this.pat_ar = this.pattern.toCharArray();
        this.alphabet = alphabet;
        this.status = new String[this.pattern.length()];
        for(int i=0; i<pattern.length(); i++) status[i] = this.pattern.substring(0,i);
    }

    private class Data  {
        int [][] evolutionMat_int;
        String [][] evolutionMat_string;

        String[][] explanationMat;
        protected Data (int [][] evolutionMat_int,String [][] evolutionMat_string,String[][] explanationMat) {
            this.evolutionMat_int = evolutionMat_int;
            this.evolutionMat_string = evolutionMat_string;
            this.explanationMat = explanationMat;
        }
    }

    public Data buildAutomation () {
        return buildAutomaton(pattern,alphabet);
    }


    private Data buildAutomaton (String pattern , char [] alphabet) {
        int m = pattern.length(); //-1
        int n = alphabet.length;
        int [][] evolutionMat_int = new int[m][n];
        String [][] evolutionMat_string = new String[m+1][n+1];
        String explanationMat [][] = new String [m][n];

        for(int i=1; i<m+1; i++) evolutionMat_string[i][0] = pattern.substring(0,i);
        for(int j=0; j<n+1; j++) evolutionMat_string[0][j] = j==0 ? ""  : String.valueOf(alphabet[j-1]);

        for(int q=1; q<m-1; q++){
            for(int i=0; i<alphabet.length; i++){
               int k = Math.min(m,q+1);
               // int k=q+1;
                while(!checkSubfix(pattern.substring(1,q+1)+alphabet[i],pattern.substring(1,k))) {
                    k = k - 1;
                }
                evolutionMat_int[q][i] = k;
                evolutionMat_string[q][i+1] = status[k];
                explanationMat [q][i] = "The subfix between " + pattern.substring(1,q+1)+alphabet[i] + " and "+
                                            pattern.substring(1,q+1) + " is " + status[k] +
                                        "\n -----------------------------------";
            }
        }
        return new Data(evolutionMat_int,evolutionMat_string,explanationMat);
    }




    public boolean checkSubfix (String total,String subStr){
        if(subStr == "") return true;
        int t = total.length();
        int s = subStr.length();
        if(total.substring(t-s).equals(subStr)) return true;
        return false;
    }

    public static void main(String[] args) {
        String pattern = "raro";
        char [] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String_matching string_matching = new String_matching(pattern,alphabet);
        Data datas = string_matching.buildAutomation();
        System.out.println(Utility.formatIntMatrix(datas.evolutionMat_int));
        System.out.println(Utility.formatStringMatrix(datas.evolutionMat_string));
        Arrays.stream(datas.explanationMat).forEach(row -> Arrays.stream(row).forEach(System.out::println));
    }

}
