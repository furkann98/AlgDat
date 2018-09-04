package Oblig1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {

 /*  public static void main(String[] args) {

        //int[] test = randomArray(10);

        int[] test = {3,1,8, -9};

       System.out.println("Før");
       System.out.println(Arrays.toString(test));
       antallUlikeUsortert(test);
       System.out.println("Etter");
       System.out.println(Arrays.toString(test));
       // System.out.println(antallUlikeUsortert(test));


    }
    */

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

        for (int i = 0; i < a.length; i++) {
            if (!ulikeVerdier.contains(a[i])) {
                ulikeVerdier.add(a[i]);
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



/*
        ArrayList<Integer> ulikeVerdier = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            if (!ulikeVerdier.contains(a[i])) {
                ulikeVerdier.add(a[i]);
            }
        }

        antallVerdier = ulikeVerdier.size();

        return antallVerdier;
*/

    }

    //OPPGAVE 4

    public static void delsortering(int[] a){

        //System.out.println("Random Array: " + Arrays.toString(a));
        int antOdd = 0;

        //Sorterer hele arrayen i stigende rekkefølge
        Arrays.sort(a);

        //Sorterer oddetall på venstre side
        for(int t = 0; t < a.length; t++){
            int tmp = 0;

            if(a[t]%2 == 1){
                tmp = a[antOdd];
                a[antOdd] = a[t];
                a[t] = tmp;
                antOdd++;
            }

        }
        Arrays.sort(a,a.length-antOdd,a.length);
        //System.out.println("DelSortert Array: " +Arrays.toString(a));




    }


}