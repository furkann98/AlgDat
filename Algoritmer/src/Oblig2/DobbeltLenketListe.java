package Oblig2;

/*
 *   @author Pedram Rahdeirjoo s325906
 *   @author Muhammed Furkan Ergin s325881
 * */




import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T>
{
    private static final class Node<T>   // en indre nodeklasse
    {
        // instansvariabler
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste)  // konstruktør
        {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        protected Node(T verdi)  // konstruktør
        {
            this(verdi, null, null);
        }

    } // Node


    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;      // antall endringer i listen

    // hjelpemetode
    private Node<T> finnNode(int indeks)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    // konstruktør
    public DobbeltLenketListe()
    {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    // konstruktør
    public DobbeltLenketListe(T[] a)
    {
        Objects.requireNonNull(a,"Tabellen a er Null!");

        for(T t : a) {
            if (t == null) {

            } else if(tom()){
                Node<T> node = new Node<>(t, null, null);
                hode = hale = node;
                antall++;
            }else{
                Node<T> node = new Node<>(t, hale,null);
                hale.neste = node;
                hale = node;
                antall++;
            }
        }
    }

    // subliste
    public Liste<T> subliste(int fra, int til)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public int antall()
    {
        return antall;

        /*
                 public int antall(){
                    int antall = 0;
                    for (T t : this) antall++;   // bruker en forAlle-løkke
                    return antall;
                  }

        */
    }

    @Override
    public boolean tom()
    {
        return antall == 0;     // listen er tom hvis antall er 0
    }

    @Override
    public boolean leggInn(T verdi)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public void leggInn(int indeks, T verdi)
    {
        //throw new UnsupportedOperationException("Ikke laget ennå!");

        /*
        if (verdi == null) throw new
                IllegalArgumentException("Ulovlig å legge inn null-verdier!");

        if (indeks < 0 || indeks > antall) throw new
                IndexOutOfBoundsException("Indeks " + indeks + " er ulovlig!");

        // En full tabell utvides med 50%
        if (antall == a.length) a = Arrays.copyOf(a,(3*antall)/2 + 1);

        // rydder plass til den nye verdien
        for (int i = antall; i > indeks; i--) a[i] = a[i-1];
        a[indeks] = verdi;     // setter inn ny verdi

        antall++;
        */
    }

    @Override
    public boolean inneholder(T verdi)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public T hent(int indeks)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public int indeksTil(T verdi)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");

        /*
        for (int i = 0; i < antall; i++)
       {
            if (a[i].equals(verdi)) return i;   // funnet!
         }
             return -1;   // ikke funnet!
         */
    }

    @Override
    public T oppdater(int indeks, T nyverdi)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public boolean fjern(T verdi)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public T fjern(int indeks)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public void nullstill()
    {

        /*
         public int antall(){
            int antall = 0;
            for (T t : this) antall++;   // bruker en forAlle-løkke
            return antall;
          }

        */

        /*

        hode = null;
        hale = null;
        antall = 0;
        antallEndringer++;
         */
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public String toString()
    {
        StringBuilder sb  = new StringBuilder();
        sb.append("[");

        if(!tom()){
            Node<T> p = hode;
            sb.append(p.verdi);
            p = p.neste;

            for(; p != null;){
                sb.append(", ").append(p.verdi);
                p = p.neste;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public String omvendtString()
    {
        StringBuilder sb  = new StringBuilder();
        sb.append("[");

        if(!tom()){
            Node<T> p = hale;
            sb.append(p.verdi);
            p = p.forrige;

            for(; p != null;){
                sb.append(", ").append(p.verdi);
                p = p.forrige;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public Iterator<T> iterator()
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    public Iterator<T> iterator(int indeks)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator()
        {
            denne = hode;     // denne starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks)
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
        }

        @Override
        public boolean hasNext()
        {
            return denne != null;  // denne koden skal ikke endres!
        }

        @Override
        public T next()
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
            /*
         if (!hasNext()){
         throw new NoSuchElementException("Tomt eller ingen verdier igjen!");
         }


          T temp = a[denne];         // henter aktuell verdi
          denne++;                   // flytter indeksen
          removeOK = true;           // nå kan remove() kalles
          return temp;
          */
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");

            /*
               if (!removeOK) {throw new IllegalStateException("Ulovlig tilstand!");}

              removeOK = false;          // remove() kan ikke kalles på nytt

              // verdien i posisjon denne - 1 skal fjernes siden den ble returnert
              // i det siste kallet på next(), verdiene fra og med denne flyttes
              // derfor en enhet mot venstre

              antall--;           // en verdi vil bli fjernet
              denne--;            // denne må flyttes til venstre

              for (int i = denne; i < antall; i++)
              {
                a[i] = a[i+1];    // verdiene flyttes
              }

              a[antall] = null;   // verdien som lå lengst til høyre nulles
             }
                     */




        }

    } // DobbeltLenketListeIterator

} // DobbeltLenketListe






//MAIN
class Main {
    public static void main(String[] args) {
        String[] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        System.out.println(l1.toString() + " "+ l2.toString()+ " "+ l3.toString());
    }

}

