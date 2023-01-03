package max_common_subseq;

// Obbiettivo: riconoscere se una stringa è una sottosequenza di un'altra


public class Subseq_with_finite_state {

    static class Evolution_datas {
        String evolution [] ;
        int currentState [] ;

        String states[] ;

        public Evolution_datas (String evolution [] , int currentState[], String states []) {
            this.currentState = currentState;
            this.evolution = evolution;
            this.states = states;
        }

    }


    public static Evolution_datas  subseq (String s1, String s2) throws Exception {
        if(s1.length()>s2.length()) throw new Exception("s1 > s2");
        int m = s1.length();
        int n = s2.length();
        char x [] = s1.toCharArray();
        char y [] = s2.toCharArray();

        String states [] = new String [m+1];

        for(int i=0; i<=m; i++){
            if(i==0) states[0] = "";
            else states[i] = states[i-1] + x[i-1];
        }

        String evolution [] = new String[n];
        int currentState [] = new int[n];

        int i = 0 ;
        int j = 0 ; // indica lo stato corrente
        while(i<n && j<m) {
            if (x[j] == y[i]) {
                j++;
                if(i==0) evolution[i] = String.valueOf(y[0]).toUpperCase();
                else evolution[i] = evolution[i-1] + String.valueOf(y[i]).toUpperCase();
            }
            else {
                if (i==0) evolution[i] = String.valueOf(y[0]);
                else evolution[i] = evolution[i-1] + y[i];
            }
            currentState[i] = j;
            i++;
        }

        return new Evolution_datas(evolution,currentState,states);
    }

    public static void main(String[] args) throws Exception {
        String s1 = "raro";
        String s2 = "carararorana";
        Evolution_datas ed = subseq(s1,s2);
        String evolution [] = ed.evolution;
        int currentStates [] = ed.currentState;
        String states [] = ed.states;

        for(int i=0; i<s2.length(); i++){
            System.out.println("Current index: " + i);
            System.out.println("Current evolution: " + evolution[i]);
            System.out.println("Current state: " + states[currentStates[i]]);
            System.out.println("---------------------------------");
        }

    }

}
