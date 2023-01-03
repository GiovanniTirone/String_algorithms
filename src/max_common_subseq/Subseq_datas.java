package max_common_subseq;

public class Subseq_datas {

    private int L[][]; // matrix that memorize the max length of the common subequence between x[i,...,m] and y[j,...,n]

    private String S[][]; // matrix that memorize the common subsequence between x[i,...,m] and y[j,...,n]



    public Subseq_datas(int L[][], String S[][]){
        this.L = L;
        this.S = S;
    }

    public int[][] getL() {
        return L;
    }

    public String[][] getS() {
        return S;
    }
}
