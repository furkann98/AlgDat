package Oblig1;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Oblig1 {

    public static void main(String[] args) {



        //int[] test = randomArray(10);
        int[] test2 = {1,2,2,2,4,5};
        //int testAntall = AntallUlikeSortert(test);
        //System.out.println(testAntall);
        int testAntall2 = AntallUlikeSortert(test2);
        System.out.println(testAntall2);
        /*
        for( int i : test){
            System.out.print(i + ", ");
        }
        System.out.println();
        System.out.println("Største tallet  i arrayen er: " + ombyttinger(test));
        */

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

    ;

    //OPPGAVE 1
    public static int maks(int[] a) {
        if (a.length == 0) throw new NoSuchElementException("Arrayen er tom");

        int maks = a[0];

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                int tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;

                maks = tmp;

            }
        }
        for (int i : a) {
            System.out.print(i + ", ");
        }
        System.out.println();
        return maks;
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
    public static int AntallUlikeSortert(int[] a) {

        for(int t = 0; t < a.length-1; t++){
            if(a[t] > a[t+1]) throw new IllegalStateException("Arrayen er ikke sortert i stigende rekkefølge!");
        }

        int antallVerdier = 0;

        ArrayList<Integer> ulikeVerdier = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            if (!ulikeVerdier.contains(a[i])) {
                ulikeVerdier.add(a[i]);
            }
        }

        antallVerdier = ulikeVerdier.size();

        return antallVerdier;

    }






    // OPPGAVE 3


    public static int AntallUlikeUSortert(int[] a) {


        int antallVerdier = 0;

        ArrayList<Integer> ulikeVerdier = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            if (!ulikeVerdier.contains(a[i])) {
                ulikeVerdier.add(a[i]);
            }
        }

        antallVerdier = ulikeVerdier.size();

        return antallVerdier;

    }
}