package Oblig1;

public class Oblig1 {

    public static void main(String[] args){
        int[] test = randomArray(50);

        for( int i : test){
            System.out.print(i + ", ");
        }
        System.out.println();
        System.out.println("Største tallet  i arrayen er: " + maks(test));
    };

    public static int[] randomArray(int n){
      int[] Array = new int[n];

      for(int i = 0; i < Array.length; i++){
        Array[i] = i+1;
      }

      for(int i = Array.length-1; i > 0; i--){
          int r = (int) (Math.random()*i) ;
          int tmp = Array[i];
          Array[i] = Array[r];
          Array[r] = tmp;
      }

      return Array;
    };

    public static int maks(int[] a){
        int maks = a[0];

        for(int i = 0; i < a.length - 1; i++){
           if(a[i] > a[i+1]){
                int tmp = a[i];
                a[i] = a[i+1];
                a[i+1] = tmp;

                maks = tmp;

            }
        }
        for( int i : a){
            System.out.print(i + ", ");
        }
        System.out.println();
        return maks;
    };
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