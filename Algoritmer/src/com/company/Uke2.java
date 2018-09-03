package com.company;

public class Uke2 {

    public static void main(String[] args){
        int[] a = {0,1,2,3,4,5};
        int[] b = {5,1,8,3,0,2};

        int min = finnMin(b,0,a.length);
        System.out.println(min);

        String tekst = skrivUtArray(a);
        System.out.println(tekst);



    }


    //Oppgave 1.2.1
    //1
    public static int finnMin(int[] a, int fra, int til){

        int indeks = fra;
        int minVerdi = a[fra];

        for(int i = fra+1; i < til; i++ ){
            if(minVerdi > a[i]){
                indeks = i;
                minVerdi = a[i];
            }
        }

        return indeks;
    };

    //2
    public static String skrivUtArray(int[] a){
        String tekst = "[ ";

        for(int i : a){
            tekst = tekst + i + ", ";
        }
        tekst = tekst + " ]";

        return tekst;
    };

}
