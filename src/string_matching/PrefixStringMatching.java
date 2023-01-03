package string_matching;

import java.util.Arrays;

public class PrefixStringMatching {

    // prefix(int j, String x) = |overlap(x_j , x)| = la cardinalià della più lunga stringa che è sia suffisso proprio di
    //                                                x_j che prefisso proprio di x

    public int[] calculatePrefix (String s){
        int m = s.length();
        char [] x = s.toCharArray();
        int [] prefix = new int[m];
        prefix[0] = 0;
        int j = 0; // indicizza i caratteri comuni trovati al passo precedente
        for(int k=1; k<m; k++){ // indicizza i caratteri della sottostringa
            System.out.println("x_" + k + " = " + s.substring(0,k+1));
            System.out.println("j = " +j);
            while(j>0 && x[j]!=x[k]) {
                System.out.println("attenzione: x[" + (j) + "] = " + x[j] +" MA " +"x[" +k +"] = " + x[k]);
                j = prefix[j-1]; // se il nuovo carattere non va bene, devo tornare indietro
            }
            if(x[j]==x[k]) {
                System.out.println("x[" + (j) + "] = x_" +k +"["+ k +"]");
                j++;
            }
            System.out.println("prefix["+k+"] = " + j);
            prefix[k] = j;
        }
        return prefix;
    }

    public void prefixStringMatching (String pattern, String text) {
        int n = text.length();
        int m = pattern.length();
        char [] p = pattern.toCharArray();
        char[] t = text.toCharArray();
        int [] prefix = calculatePrefix(pattern);
        int i = 0; // indicizza t
        int l = 0; //
        while(i < n-m+1){
            int j = l; // numero di caratteri trovati
            while(j<m && p[j]==t[i+j]) j++;
            if(j==m) {
                j--;
                System.out.println("trovata occorrenza di p alla posizione "+ i);
            }
            l = prefix[j] ; // mi dice da che carattere di p cominciare per la successiva ricerca
            if(j-l==0) i++;
            else i = i+j-l; // mi dice da che carattere di t cominciare per la prossima ricerca
        }
    }



    public static void main(String[] args) {
        /*
        String s = "raro";
        PrefixStringMatching psm = new PrefixStringMatching();
        int [] prefix = psm.calculatePrefix(s);
        System.out.println(Arrays.toString(prefix));
        */
        /*
        String s = "ranaranrae" ;
        PrefixStringMatching psm = new PrefixStringMatching();
        int [] prefix = psm.calculatePrefix(s);
        System.out.println(Arrays.toString(prefix));
        */
        String text = "oooabaooaboooababaoo";
        String pattern = "aba";
        PrefixStringMatching psm = new PrefixStringMatching();
        psm.prefixStringMatching(pattern,text);


    }

}
