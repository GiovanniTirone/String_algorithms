package string_matching;

public class KnuthMorrisPratt {


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

    public void KMP (String pattern, String text){
        int n = text.length();
        int m = pattern.length();
        char [] p = pattern.toCharArray();
        char[] t = text.toCharArray();
        int [] prefix = calculatePrefix(pattern);
        int j = 0; //numero di caratteri di p trovati in t
        for(int i=0; i<n; i++){ // ciclo su tutto il testo t
            while(j>0 && p[j]!=t[i]) j = prefix[j-1];  // se il carattere successivo di p non è uguale a t[i] torno indietro con j
            if(p[j]==t[i]) j++;
            if(j==m){
                System.out.println("trovata occorrenza in posizione: "+ (i-m+1));
                j = prefix[j-1];
            }
        }
    }

    public static void main(String[] args) {

        String text = "oooabaooaboooababaoa";
        String pattern = "aba";
        PrefixStringMatching psm = new PrefixStringMatching();
        psm.prefixStringMatching(pattern,text);

    }





}
