package distance_all_data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static utility.Utility.formatIntMatrix;
import static utility.Utility.formatStringMatrix;


/*
    Distanza tra 2 stringhe = numero minimo di trasformazioni necessarie per trasformare una stringa in un altra
    tramite le operazioni insericsci(char a) , cancella(char a) , sosituisci(char a , char b)
    D è la matrice dei sottoproblemi P(i,j)
    Un sottoproblema P(i,j) consiste nel trovare la distanza tra la stringa x_(i) = x[0,...,i-1] e la stringa y_(j) = y[0,...,j-1]
    Attenzione: i sottoproblemi P(0,j) e P(i,0) fanno riferimento alle stringhe x_(-1) e  y_(-1) che per definizione sono vuote,
    quindi quindi la distanza è data dalla lunghezza dell'altra stringa.
*/

public class Distance_all_data {

    public static Distance_datas distance (String s1, String s2){
        char x [] = s1.toCharArray();
        char y [] = s2.toCharArray();
        int m = x.length;
        int n = y.length;

        int D [][] = new int [m+1][n+1];
        String S [][] = new String[m+1][n+1];
        String P [][] = new String[m+1][n+1];
        String Paths [][] = new String[m+1][n+1];
        P[0][0] = " ";
       // Paths[0][0] = "(" + 0 + "," + 0 +")";

        for(int i=0;i<m+1;i++) {
            D[i][0]=i;
            S[i][0]= s1.substring(0,i);
            if(i>0) {
                P[i][0] = P[i-1][0] + " -" + S[i][0].substring(S[i][0].length()-1);
             //   Paths[i][0] = Paths[i-1][0] + " -> " + "(" + i + "," + 0 +")";
            }

        }

        for(int j=1;j<n+1;j++) {
            D[0][j]=j;
            S[0][j]= s2.substring(0,j);
            if(j>0) {
                P[0][j] = P[0][j-1] + " +" + S[0][j].substring(S[0][j].length()-1);
           //     Paths[0][j] = Paths[0][j-1] + " -> " + "(" + 0 + "," + j +")";
            }

        }

        enum Directions {LEFT, UP, DIAG;}

        class Label_number  {
            Directions label;
            int number;
            private  Label_number (Directions label, int number){
                this.label = label;
                this.number = number;
            }
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(x[i-1]!=y[j-1]){
                    Label_number prev_dists [] = {
                            new Label_number(Directions.LEFT, D[i][j-1]),
                            new Label_number(Directions.UP, D[i-1][j]) ,
                            new Label_number(Directions.DIAG,D[i-1][j-1])
                    };

                    List<Label_number> sorted_prev_dists = Arrays.stream(prev_dists)
                                            .sorted(Comparator.comparingInt(o -> o.number))
                                            .collect(Collectors.toList());

                    D[i][j] = 1 + sorted_prev_dists.get(0).number;

                    switch (sorted_prev_dists.get(0).label) {
                        case LEFT:
                            S[i][j] = "+" + y[j-1];
                            P[i][j] = P[i][j-1] + " " +  S[i][j];
                            break;
                        case UP:
                            S[i][j] = "-" + x[i-1];
                            P[i][j] = P[i-1][j] + " " +S[i][j];
                            break;
                        case DIAG:
                            S[i][j] = x[i-1] + "->" + y[j-1];
                            P[i][j] = P[i-1][j-1] + " " + S[i][j];
                    }
                }else{
                    D[i][j]= D[i-1][j-1];
                    S[i][j] = x[i-1] + "=" + y[j-1];
                    P[i][j] = P[i-1][j-1] + " " + S[i][j];
                }
            }
        }

        for(int i=0; i<m+1; i++){
            for(int j=0; j<n+1; j++){
                String str =
                        "P(" + i+ "," + j+") = " +
                        "dist(" + s1.substring(0,i) +" , " +s2.substring(0,j) +") = " + D[i][j] + "\n" +
                        "Operations: " + P[i][j] +
                        "\n---------------------" ;
                P[i][j] = str;
            }
        }

        return new Distance_datas(D,S,P);
    }




    public static void main(String[] args) {
        String s1 = "risotto";
        String s2 = "presto";
        Distance_datas datas = distance("risotto","presto");
        int D [][] = datas.getDistance_matrix();
        String S [][] = datas.getOperations_matrix();
        String P [][] = datas.getSub_problems_matrix();
        System.out.println(formatIntMatrix(D));
        System.out.println(formatStringMatrix(S));
        for(int i=0; i<=s1.length(); i++){
            for(int j=0; j<=s2.length(); j++){
                System.out.println(P[i][j]);
            }
        }
    }


}
