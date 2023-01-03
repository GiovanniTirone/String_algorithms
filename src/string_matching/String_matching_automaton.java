package string_matching;

import utility.Utility;


public class String_matching_automaton {

    private String pattern ;

    private char[] pat_ar;

    private char [] alphabet;

    private String [] status;

    public String_matching_automaton(String pattern, char [] alphabet) {
        this.pattern =  "#" + pattern;
        this.pat_ar = this.pattern.toCharArray();
        this.alphabet = alphabet;
        this.status = new String[this.pattern.length()];
        for(int i=0; i<this.pattern.length(); i++) status[i] = this.pattern.substring(0,i+1);
    }

    private class Data  {
        int [][] evolutionMat_int;
        String [][] evolutionMat_string;

        protected Data (int [][] evolutionMat_int,String [][] evolutionMat_string) {
            this.evolutionMat_int = evolutionMat_int;
            this.evolutionMat_string = evolutionMat_string;
        }
    }

    public Data buildAutomaton () {
        int m = pattern.length()-1;
        int n = alphabet.length;
        int [][] evolutionMat_int = new int[m][n];
        String [][] evolutionMat_string = new String[m+1][n+1];
        for(int i=1; i<m+1; i++) evolutionMat_string[i][0] = pattern.substring(0,i);
        for(int j=0; j<n+1; j++) evolutionMat_string[0][j] = j==0 ? ""  : String.valueOf(alphabet[j-1]);

        for(int q=0; q<=m-1; q++){
            for(int a=0; a<n; a++){
                int k = Math.min(m,q+1);
                while(!checkSubfix(pattern.substring(1,q+1)+alphabet[a], pattern.substring(1,k+1))) k--;
                evolutionMat_int[q][a] = k;
                evolutionMat_string[q+1][a+1] = status[k];
            }
        }
        return new Data(evolutionMat_int,evolutionMat_string);
    }


    public int string_matching_automaton (String text) {
        int m = pattern.length()-1;
        int n = text.length();
        char [] text_chars = text.toCharArray();
        int[][] evolutionMat_int = buildAutomaton().evolutionMat_int;
        int q = 0;
        for(int i=0; i<n; i++){
            q = evolutionMat_int[q][getPositionOfCharInAlphabet(text_chars[i])];
            if(q==m) return i-m+1;
        }
        return -1;
    }


    public boolean checkSubfix (String total,String subStr){
        if(subStr == "") return true;
        int t = total.length();
        int s = subStr.length();
        if(total.substring(t-s).equals(subStr)) return true;
        return false;
    }

    private int getPositionOfCharInAlphabet (char c){
        for(int i=0; i<alphabet.length; i++) if(alphabet[i] == c) return i;
        return -1;
    }

    public static void main(String[] args) {
        String pattern = "raro";
        char [] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String_matching_automaton string_matching = new String_matching_automaton(pattern,alphabet);
        Data data = string_matching.buildAutomaton();
        System.out.println(Utility.formatIntMatrix(data.evolutionMat_int));
        System.out.println(Utility.formatStringMatrix(data.evolutionMat_string));

        String text = "carararorana";
        int result = string_matching.string_matching_automaton(text);
        System.out.println(result);
    }

}
