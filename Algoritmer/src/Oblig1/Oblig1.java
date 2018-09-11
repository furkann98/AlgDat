package Oblig1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {

   public static void main(String[] args) {

       //flett("AM ","L","GEDS","ORATKRR","","R TRTE","IO","TGAUU");

       int[] test = {6, 10, 16, 11, 7, 12, 3, 9, 8, 5};

       int[] svar = indekssortering(test);

       for (int i = 0; i < svar.length ; i++) {
           System.out.print(svar[i] + " ");

       }

       // Utskrift: [6, 9, 0, 4, 8, 7, 1, 3, 5, 2]
    }


    public static int maxValueArray(int[] a){
       int størst = a[0];

       for(int i : a){
           if(i > størst){
               størst = i;
           }
       }

       return størst;
    }

    public static int[] randomArray(int n) {
        int[] Array = new int[n];

        for (int i = 0; i < Array.length; i++) {
            Array[i] = i + 1;
        }

        for (int i = Array.length - 1; i > 0; i--) {
            int r = (int) (Math.random() * i);
            int tmp = Array[i];
            Array[i] = Array[r];
            Array[r] = tmp;
        }

        return Array;
    }



    //OPPGAVE 1
    public static int maks(int[] a) {
        if (a.length == 0) throw new NoSuchElementException("Arrayen er tom");


        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                int tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;

                //maks = tmp;

            }
        }
        //Arrays.toString(a);
        return a[a.length-1];
    }

    //OPPGAVE 1
    public static int ombyttinger(int[] a) {
        if (a.length == 0) throw new NoSuchElementException("Arrayen er tom");

        int antall = 0;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                int tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;

                antall++;

            }
        }
        return antall;

    }




    /*
     *
     * //Når blir det flest ombyttinger?
     * Når det største tallet er først i rekken(Arrayen)
     *
     *
     *
     * //Når blir det færrest?
     * Når det største tallet er sist i rekken
     *
     * //Hvor mange blir det i gjennomsnitt?
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * */


    //OPPGAVE 2
    public static int antallUlikeSortert(int[] a) {

        for(int t = 0; t < a.length-1; t++){
            if(a[t] > a[t+1]) throw new IllegalStateException("Arrayen er ikke sortert i stigende rekkefølge!");
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

        //Sorterer oddetall på venstre side
        for(int i = 0; i < a.length; i++){

            if(a[i]%2 != 0){
                int tmp = a[antOdd];
                a[antOdd] = a[i];
                a[i] = tmp;
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
                //Flytter første verdiene
                for (int i = 0; i < k; i++){
                    b[i] = a[a.length - k + i];
                }
                //Flytter resterende
                for (int j = k; j < a.length; j++){
                    b[j] = a[j-k];
                }
            }
            else{
                //Flytter første verdiene
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

    // Utskrift: [6, 10, 16, 11, 7, 12, 3, 9, 8, 5] a er ikke endret
    // Utskrift: [6, 9, 0, 4, 8, 7, 1, 3, 5, 2]

    public static int[] indekssortering(int[] a){
        //Indeks tabell
        int[] aI = new int[a.length];

        //Hjelpetabell
        int[] aHjelp = a.clone();


        //Sorterer i stigende rekkefølge
        Arrays.sort(aHjelp);

        //Finner indeks/posisjon til verdiene.
        for (int j = 0; j < aHjelp.length ; j++) {
            for( int t = 0; t < a.length; t++){
                if (aHjelp[j] == a[t] ){
                    aI[j] = t;
                }
            }
        }



        return aI;
    }


    //OPPGAVE 9

    public static int[] tredjeMin(int[] a){

        int n = a.length;     // tabellens lengde
        if (n < 2) throw      // må ha minst to verdier
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");


        //int[] tredjeMin = {a[0],a[1], a[2]};

        // int[] ITM = indekssortering(tredjeMin);



        int IEn = 0;  // Posisjon til minste verdi
        int ITo = 1;  // Posisjon til nest minste Verdi
        int ITre = 2;  // Posisjon til tredje minste verdi

/*
        if(a[0] > a[1]){
            IEn = 1;
            ITo = 0;
            ITre = 3;
        }

*/

        int VEn = a[0];     //Verdi en i posisjon 0
        int VTo = a[1];     //Verdi en i posisjon 1
        int VTre = a[2];    //Verdi en i posisjon 2


        for(int i = 3; i < a.length; i++){
            if(a[i] < VEn){
//                ITre  = ITo;
//                VTre = VTo;
//
//                ITo  = IEn;
//                VTo = VEn;

                IEn = i;
                VEn = a[i];


            }


            else  if(a[i] < VTo){
//                ITo  = IEn;
//                VTo = VEn;
//
//                ITre  = ITo;
//                VTre = VTo;

                ITo = i;
                VTo = a[i];

            }


            else   if(a[i] < VTre){
//                ITre  = i;
//                VTre = a[i];
//
//                ITo  = IEn;
//                VTo = VEn;

                ITre = i;
                VTre = a[i];

            }

        }
        return new int[]{IEn,ITo, ITre};

        //return ITM;
    }


  



}