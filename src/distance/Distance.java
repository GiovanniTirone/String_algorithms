package distance;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Distanza tra 2 stringhe = numero minimo di trasformazioni necessarie per trasformare una stringa in un altra
    tramite le operazioni insericsci(char a) , cancella(char a) , sosituisci(char a , char b)
    D è la matrice dei sottoproblemi P(i,j)
    Un sottoproblema P(i,j) consiste nel trovare la distanza tra la stringa x_(i) = x[0,...,i-1] e la stringa y_(j) = y[0,...,j-1]
    Attenzione: i sottoproblemi P(0,j) e P(i,0) fanno riferimento alle stringhe x_(-1) e  y_(-1) che per definizione sono vuote,
    quindi quindi la distanza è data dalla lunghezza dell'altra stringa.
*/

public class Distance {

    public static int[][] distanceMatrix (String s1,String s2){
        char x [] = s1.toCharArray();
        char y [] = s2.toCharArray();
        int m = x.length;
        int n = y.length;

        int D [][] = new int [m+1][n+1];

        for(int i=0;i<m+1;i++)  D[i][0]=i;

        for(int j=1;j<n+1;j++)  D[0][j]=j;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(x[i]!=y[j]){
                    D[i+1][j+1] = 1 + Math.min(D[i+1][j],Math.min(D[i][j+1],D[i][j]));
                }else{
                    D[i+1][j+1]= D[i][j];
                }
            }
        }
        return D;
    }

    public static String formatIntMatrix (int mat [][]){
        AtomicInteger maxNumberOfDigits = new AtomicInteger();
        Arrays.stream(mat).forEach(row ->{
            Arrays.stream(row).forEach(n -> {
                int digits_of_n = (int) (Math.log10(n) + 1);
                if(digits_of_n>maxNumberOfDigits.get())
                    maxNumberOfDigits.set(digits_of_n);
            });
        });
        int n = mat[0].length;
        int m = mat.length;
        String matStr = "";
        int dn = maxNumberOfDigits.get();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                String formatString = "%" +dn +"d ";
                String newStr = String.format(formatString,mat[i][j]);
                matStr += newStr;
            }
            matStr += "\n";
        }
        return matStr;
    }

    public static String[][] substringsMatrix(String s1, String s2, int D [][]) {
        int m = s1.length();
        int n = s2.length();
        char x [] = s1.toCharArray();
        char y [] = s2.toCharArray();
        String S [][] = new String[m+1][n+1];
        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(j==0){
                    S[i][j] = s1.substring(0,i);
                }
                else if(i==0){
                    S[i][j] = s2.substring(0,j);
                }
                else{
                    if( D[i][j-1] + 1 == D[i][j] ){
                        S[i][j] = "+ " + String.valueOf(y[j-1]);
                    }
                    else if( D[i-1][j] + 1  == D[i][j] ){
                        S[i][j] = "- " + String.valueOf(x[i-1]);
                    }
                    else if( D[i-1][j-1] +1 == D[i][j] ){
                        S[i][j] = String.valueOf(x[i-1]) + "->" + String.valueOf(y[j-1]);
                    }else{
                        S[i][j] = String.valueOf(x[i-1]) + "=" + String.valueOf(y[j-1]);
                    }
                }
            }
        }
        return S;
    }

    public static String formatStringMatrix (String mat [][]){
        AtomicInteger maxNumberOfChars = new AtomicInteger();
        Arrays.stream(mat).forEach(row ->{
            Arrays.stream(row).forEach(str -> {
                if(str.length()>maxNumberOfChars.get())
                    maxNumberOfChars.set(str.length());
            });
        });
        int n = mat[0].length;
        int m = mat.length;
        String matStr = "";
        int cn = maxNumberOfChars.get();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                String formatString = "%" +cn +"s ";
                String newStr = String.format(formatString,mat[i][j]);
                matStr += newStr;
            }
            matStr += "\n";
        }
        return matStr;
    }

    public static String getSubProblem (String s1, String s2, String subMat[][] , int distMat[][],int x_index, int y_index) {
        String operationStr = "";
        if(x_index == 0 ) {
            for(int k=0; k<distMat[x_index][y_index]; k++)
                operationStr += subMat[0][k] + " + ";
        }
        else if(y_index == 0){
            for(int k=0; k<distMat[x_index][y_index]; k++)
                operationStr += subMat[k][0] + " + ";
        }
        else {
            for (int k = 0; k <distMat[x_index][y_index]; k++)
                operationStr += subMat[x_index - k][y_index - k] + " + ";
        }
        if(operationStr.length()>=2)
           operationStr = operationStr.substring(0,operationStr.length()-2);
        return "P(" + x_index + "," + y_index +") = " +
                "dist(" + s1.substring(0,x_index) +" , " +s2.substring(0,y_index) +") = " + distMat[x_index][y_index] + "\n" +
                "Operation: " + operationStr + //subMat[x_index][y_index] +
                "\n---------------------" ;
    }


    public static void main(String[] args) {
        String s1 = "risotto";
        String s2 = "presto";
        int dist [][] = distanceMatrix("risotto","presto");
        System.out.println(formatIntMatrix(dist));
        String sub [][] = substringsMatrix("risotto","presto",dist);
       // Arrays.stream(sub).forEach(row->Arrays.stream(row).forEach(str-> System.out.println(str)));
        System.out.println(formatStringMatrix(sub));

        System.out.println(getSubProblem(s1,s2,sub,dist,3,4));
    }


}
