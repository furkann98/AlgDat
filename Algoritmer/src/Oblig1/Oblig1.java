package Oblig1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {

   public static void main(String[] args) {

       //int[] test = randomArray(10);
       char[] test = {'a','b','c','d'};
       rotasjon(test,-3);
       System.out.println(Arrays.toString(test));

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


    //OPPGAVE






}