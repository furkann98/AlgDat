package Oblig1;

/*
*   @author Pedram Rahdeirjoo s325906
*   @author Muhammed Furkan Ergin s325881
* */



import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {





    //OPPGAVE 1
    public static int maks(int[] a) {
        if (a.length == 0) throw new NoSuchElementException("Arrayen er tom");

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                bytt(a,i,i+1);
            }
        }
        //Arrays.toString(a);
        return a[a.length-1];
    }



    //OPPGAVE 1 - B
    public static int ombyttinger(int[] a) {
        if (a.length == 0) throw new NoSuchElementException("Arrayen er tom");

        int antall = 0;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                bytt(a,i,i+1);
                antall++;
            }
        }
        return antall;

    }

    /*  TILLEGGSOPPGAVER TIL 1)
     *
     * //Naar blir det flest ombyttinger?
     * Det blir flest ombyttinger naar det stoerste tallet er foerst i rekken(Arrayen)
     *
     * //Naar blir det faerrest?
     * Det blir faerest obmbyttinger naar det stoerste tallet er sist i rekken
     *
     * //Hvor mange blir det i gjennomsnitt?
     * Det varierer fra hvor mange tall vi har i arrayen.
     *
     * */



    //OPPGAVE 2
    public static int antallUlikeSortert(int[] a) {

        for(int t = 0; t < a.length-1; t++){
            if(a[t] > a[t+1]) throw new IllegalStateException("Arrayen er ikke sortert i stigende rekkefoelge!");
        }

        int antallVerdier = 0;

        ArrayList<Integer> ulikeVerdier = new ArrayList<>();

        for (int i : a) {
            if (!ulikeVerdier.contains(i)) {
                ulikeVerdier.add(i);
            }
        }

        antallVerdier = ulikeVerdier.size();

        return antallVerdier;
    }






    // OPPGAVE 3
    public static int antallUlikeUsortert(int[] a) {

        if(a.length == 0){
            return 0;
        }
        if(a.length == 1){
            return 1;
        }

        int antallVerdier = 0;
        int maksVerdi = maxValueArray(a);
        int startVerdi = 0;

        for(int j = 0; j <= maksVerdi; j++) {

            for (int i : a) {
                if(startVerdi == i) {
                    antallVerdier++;
                    break;
                }
            }
            if(startVerdi > maksVerdi){
                System.out.println(startVerdi);
                break;
            }
            startVerdi++;
        }
        return antallVerdier;
    }


    //OPPGAVE 4
    public static void delsortering(int[] a){

        int antOdd = 0;

        //Sorterer oddetall paa venstre side
        for(int i = 0; i < a.length; i++){

            if(a[i]%2 != 0){
                bytt(a,antOdd,i);
                antOdd++;
            }
        }
        if (antOdd == 0 || antOdd == a.length){
            Arrays.sort(a);
        }
        else {
            Arrays.sort(a,0,antOdd);
            Arrays.sort(a,antOdd,a.length);
        }
    }
    //Kan også bruke partisjonsteknikken



    //OPPGAVE 5
    public static void rotasjon(char[] a){
        if(!(a.length == 0)){
            char[] b = new char[a.length];

            b[0] = a[a.length - 1];

            for(int i = 1; i < a.length; i++){
                b[i] = a[i-1];
            }

            for (int j = 0; j<b.length; j++){
                a[j] = b[j];
            }
        }
    }


    //OPPGAVE 6
    public static void rotasjon(char[] a, int k){
        if(!(a.length == 0 || k == 0)){

            //Sjekker antall syklus
            if(k > 0){
                while(k > a.length){
                    k -= a.length;
                }
            }
            if(k < 0){
                while(k < (a.length * -1)){
                    k -= (a.length * -1);
                }
            }


            //hjelpe-array
            char[] b = new char[a.length];

            if(k > 0){
                //Flytter foerste verdiene
                for (int i = 0; i < k; i++){
                    b[i] = a[a.length - k + i];
                }
                //Flytter resterende
                for (int j = k; j < a.length; j++){
                    b[j] = a[j-k];
                }
            }
            else{
                //Flytter foerste verdiene
                for (int i = a.length+k; i < a.length; i++){
                    b[i] = a[i - (a.length + k)];
                }
                //Flytter resterende
                for (int j = 0; j < a.length+k; j++){
                    b[j] = a[j+(k*(-1))];
                }
            }

            //Kopierer Array
            for (int m = 0; m<b.length; m++){
                a[m] = b[m];
            }
        }

    }


    //OPPGAVE 7
    //A
    public static String flett(String s, String t){
        StringBuilder sb = new StringBuilder();
        int min = Math.min(s.length(), t.length());

        //Legger til annehver
        for(int i = 0; i < min; i++){
            sb.append(s.charAt(i));
            sb.append(t.charAt(i));
        }

        //Legger til resterende
        sb.append(s.substring(min));
        sb.append(t.substring(min));

        return sb.toString();
    }

    //B
    public static String flett(String... s){
        StringBuilder sb = new StringBuilder();

        //finner maks
        int max = 0;
        for (int t = 0; t < s.length; t++){
            if(s[t].length() > max){
                max = s[t].length();
            }
        }


        //Flett
        for(int i = 0; i < max; i++){
            for(int j = 0; j < s.length; j++){
                if(s[j].length() <= i){
                    continue;
                }else{
                    sb.append(s[j].charAt(i));
                    continue;
                }
            }

        }

        return sb.toString();
    }



    //OPPGAVE 8

    public static int[] indekssortering(int[] a){
        //Indeks tabell
        int[] aI = new int[a.length];

        //Hjelpetabell
        int[] aHjelp = a.clone();


        //Sorterer i stigende rekkefoelge
        Arrays.sort(aHjelp);

        //Finner indeks/posisjon til verdiene.
        for (int j = 0; j < aHjelp.length ; j++) {
            for( int t = 0; t < a.length; t++){
                if (aHjelp[j] == a[t] ){
                    aI[j] = t;
                    break;
                }
            }
        }



        return aI;
    }


    //OPPGAVE 9

    public static int[] tredjeMin(int[] a){

        if (a.length < 3) throw new java.util.NoSuchElementException("a.length(" + a.length + ") < 2!");  // maa ha minst to verdier


        int[] tredjeMin = {a[0],a[1], a[2]};

        int[] IndekssorteringTredjeMin = indekssortering(tredjeMin);

        int IEn = IndekssorteringTredjeMin[0];  // Posisjon til minste verdi
        int ITo = IndekssorteringTredjeMin[1];  // Posisjon til nest minste Verdi
        int ITre = IndekssorteringTredjeMin[2];  // Posisjon til tredje minste verdi

        int VEn = a[IEn];     //Verdi en i posisjon 0
        int VTo = a[ITo];     //Verdi en i posisjon 1
        int VTre = a[ITre];    //Verdi en i posisjon 2

        //Itererer gjennom resten av arrayet, og plaserer mindre verdier i tredjeMin
        for(int i = 3; i < a.length; i++){
            if(a[i] < VEn){
                ITre  = ITo;
                VTre = VTo;

                ITo  = IEn;
                VTo = VEn;

                IEn = i;
                VEn = a[i];
            }


            else if(a[i] < VTo){
                ITre  = ITo;
                VTre = VTo;

                ITo = i;
                VTo = a[i];
            }


            else if(a[i] < VTre){
                ITre = i;
                VTre = a[i];
            }

        }
        return new int[]{IEn,ITo, ITre};
    }


    //OPPGAVE 10

    public static boolean inneholdt(String en, String to){
        if (en.length() > to.length()) return false;

        char[] a = en.toCharArray();
        char[] b = to.toCharArray();

        a = a.clone();     // lager en kopi
        b = b.clone();     // lager en kopi

        Arrays.sort(a);    // kvikksortering
        Arrays.sort(b);    // kvikksortering

        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length)
        {
            if (a[i] > b[j]) j++;
            else if (a[i] == b[j])
            {
                i++; j++;
            }
            else return false;
        }

        return i == a.length;

    }



    //HJELPEMETODER

    //Bytte metoden.
    public static void bytt(int[] a, int iEn, int iTo){
        int tmp = a[iEn];
        a[iEn] = a[iTo];
        a[iTo] = tmp;
    }

    // finner stoerste int verdi
    public static int maxValueArray(int[] a){
        int stoerst = a[0];

        for(int i : a){
            if(i > stoerst){
                stoerst = i;
            }
        }

        return stoerst;
    }

    //Lager random array fra 1 til n
    public static int[] randomArray(int n) {
        int[] Array = new int[n];

        for (int i = 0; i < Array.length; i++) {
            Array[i] = i + 1;
        }

        for (int i = Array.length - 1; i > 0; i--) {
            int r = (int) (Math.random() * i);
            bytt(Array,i,r);
        }

        return Array;
    }

}