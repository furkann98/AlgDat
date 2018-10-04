package Oblig2;

/*
 *   @author Pedram Rahdeirjoo s325906
 *   @author Muhammed Furkan Ergin s325881
 * */




import java.util.*;

//MAIN
class Main {
    public static void main(String[] args) {

        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        System.out.println(liste.toString() + ""+ liste.omvendtString());
        for(int i = 1; i <= 3; i++){liste.leggInn(i);
            System.out.println(liste.toString() + ""+ liste.omvendtString());
        }
    }

}

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
        Node<T> returnNode;

        if(indeks < antall/2){
            returnNode = hode;
            for (int i = 0; i < indeks ; i++) {
                returnNode = returnNode.neste;
            }
        }else{ //feil logikk
            returnNode = hale;
            for (int i = 1; i < (antall - indeks); i++) {
               returnNode = returnNode.forrige;
            }

        }
        return returnNode;
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

    //FraTilKontroll
    private void fratilKontroll(int antall, int fra, int til)
      {
        if (fra < 0)                                  // fra er negativ
          throw new IndexOutOfBoundsException
            ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
          throw new IndexOutOfBoundsException
            ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
          throw new IllegalArgumentException
            ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
      }


    // subliste
    public Liste<T> subliste(int fra, int til)
    {
        endringer = 0;
        fratilKontroll(antall,fra,til);

        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();


        for (int i = fra; i < til ; i++) {
             subliste.leggInn(hent(i));
        }




        return  subliste;
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
        Objects.requireNonNull(verdi,"Verdien kan ikke være Null"); // Sjekker om tabellen bare har null verdider

        //Sjekker om Listen er tom, og gjør den første verdi til: node = hale = hode;
        if(tom()){
            Node<T> node = new Node<>(verdi, null,null);
            hale = hode = node;
            antall++;
            endringer++;
            return true;
        }else{ //Legger til bakerst
            Node<T> node = new Node<>(verdi, hale,null);
            hale.neste = node;
            hale = node;
            antall++;
            endringer++;
            return true;
        }

        //throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public void leggInn(int indeks, T verdi)
    {
        Objects.requireNonNull(verdi,"Verdien kan ikke være Null"); // Sjekker om tabellen bare har null verdider
        if(indeks < 0 || indeks > antall) {
            throw new IndexOutOfBoundsException("Indeksen er ute av intervallet");
        }
        if(verdi == null){
            throw new NullPointerException("Verdien kan ikke være Null");
        }
           Node<T> node; //Oppretter node


        if(antall == 0){
            node = new Node<>(verdi,null, null);
            hode = hale = node;
            antall++;
            endringer++;

        }else if(tom() || indeks >=antall){
            leggInn(verdi);
        }
        else if(indeks == 0){
            node = new Node<>(verdi,null, hode);
            hode.forrige = node;
            hode = node;
            antall++;
            endringer++;
        }else{
            Node<T> gammelNode = finnNode(indeks);
            node = new Node<>(verdi,gammelNode.forrige,gammelNode);
            gammelNode.forrige = node;
            node.forrige.neste = node; //
            antall++;
            endringer++;
        }
        }


    @Override
    public boolean inneholder(T verdi)
    {
        if(indeksTil(verdi) == -1) return false;
        return true;
    }

    @Override
    public T hent(int indeks)
    {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi)
    {
        for (int i = 0; i < antall ; i++) {
            if(hent(i).equals(verdi)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi)
    {
        indeksKontroll(indeks,false);
        Objects.requireNonNull(nyverdi,"Verdien kan ikke være null");
        
        Node<T> gammelNode = finnNode(indeks);
        T gammelVerdi = gammelNode.verdi;

        gammelNode.verdi = nyverdi;


        endringer++;
        return gammelVerdi;

    }

    @Override
    public boolean fjern(T verdi)
    {
        Node<T> node = hode;

        for (int i = 0; i < antall; i++ ){
            if(node.verdi == verdi){
                fjern(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public T fjern(int indeks)
    {

          indeksKontroll(indeks,false);

          if(tom()) return null;

          Node<T> node;
          T verdi;

          if(indeks == 0){
              if(antall == 2){
                  hode = hale;
                  hale.neste = null;
                  hode.forrige = null;


              }else {
                  node = hode.neste;
                  node.forrige = null;
                  hode = node;
              }
              verdi = hode.verdi;
              antall--;
              endringer++;
          }
          else if(indeks == antall-1){

              if(antall == 2){
                   hale = hode;
                   hale.neste = null;
                   hode.forrige = null;
              }else{
                  node = hale.forrige;
                  node.neste = null;
                  hale = node;
              }
              verdi = hale.verdi;
              antall--;
              endringer++;
          }
          else {
              node = finnNode(indeks);
              verdi = node.verdi;
              node.neste.forrige = node.forrige;
              node.forrige.neste = node.neste;
              antall--;
              endringer++;
          }
          return verdi;
     /*
        if(!tom()) {

            indeksKontroll(indeks, false);

            Node<T> node = finnNode(indeks);
            T verdi = hent(indeks);


            if(antall == 2){
                if(indeks == antall-1){
                    hale = hode = node.forrige;
                    hode.forrige=null;
                    hode.neste=null;
                }else{
                    hale = hode = node.neste;
                    hode.forrige=null;
                    hode.neste=null;
                }

            }
            else if (indeks == 0) {
                hode = node.neste;
                node.neste.forrige = null;
                antall--;
                endringer++;
            } else if (indeks == antall - 1) {
                node.forrige = hale;
                node.forrige.neste = null;
                antall--;
                endringer++;
            } else {
                node.forrige.neste = node.neste;
                node.neste.forrige = node.forrige;
                antall--;
                endringer++;
            }
            return verdi;
        }
        return null;
        */
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

            while( p != null){
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








