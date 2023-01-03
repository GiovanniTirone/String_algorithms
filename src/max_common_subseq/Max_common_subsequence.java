package max_common_subseq;


import static utility.Utility.formatIntMatrix;
import static utility.Utility.formatStringMatrix;

public class Max_common_subsequence {

    public static Subseq_datas max_subseq (String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        char x [] = s1.toCharArray();
        char y [] = s2.toCharArray();
        int L [][] = new int[m+1][n+1];
        String S [][] = new String[m+1][n+1];

        for(int i=m; i>=0; i--){
            for(int j=n; j>=0; j--){
                if(i==m || j==n){
                    L[i][j] = 0;
                    S[i][j] = "";
                }else{
                    if(x[i]==y[j]){
                        L[i][j] = 1+ L[i+1][j+1];
                        S[i][j] = S[i+1][j+1] + x[i];
                    }
                    else{
                        if(L[i+1][j]>L[i][j+1]){
                            L[i][j] = L[i+1][j];
                            S[i][j] = S[i+1][j];
                        }
                        else{
                            L[i][j] = L[i][j+1];
                            S[i][j] = S[i][j+1];
                        }
                    }
                }
            }
        }

        return new Subseq_datas(L,S);
    }

    public static void main(String[] args) {
        String s1 = "grafi";
        String s2 = "algoritmi";
        Subseq_datas subseq_datas = max_subseq(s1,s2);
        int [][] L = subseq_datas.getL();
        String [][] S = subseq_datas.getS();
        System.out.println(formatIntMatrix(L));
        System.out.println(formatStringMatrix(S));
    }

}
