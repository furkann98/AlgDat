package Oblig2;

/*
 *   @author Pedram Rahdeirjoo s325906
 *   @author Muhammed Furkan Ergin s325881
 * */




import java.util.*;

//MAIN
class Main {
    public static void main(String[] args) {

       /* DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        for (int i = 0; i < 100000 ; i++) {
            liste.leggInn(i);
        }

        long tid = System.currentTimeMillis();
        for (int i = 0; i < 100000 ; i++) {
            liste.nullstill();
        }
        tid = System.currentTimeMillis()-tid;
        System.out.println(tid); */

        /*
        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste = new DobbeltLenketListe<>(navn);
        liste.forEach(s -> System.out.print(s + " "));
        System.out.println();
        for(String s : liste) System.out.print(s + " ");



        DobbeltLenketListe<String> liste2 =new DobbeltLenketListe<>(new String[]{"Birger","Lars","Anders","Bodil","Kari","Per","Berit"});
        liste2.fjernHvis(navn2 -> navn2.charAt(0) == 'B');
        // fjerner navn som starter med B
        System.out.println();
        System.out.println(liste2 + " "+ liste2.omvendtString());

        */
        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste1 = new DobbeltLenketListe<>(navn);
        Liste<String> liste2 = new TabellListe<>(navn);
        Liste<String> liste3 = new EnkeltLenketListe<>(navn);
        DobbeltLenketListe.sorter(liste1, Comparator.naturalOrder());
        DobbeltLenketListe.sorter(liste2, Comparator.naturalOrder());
        DobbeltLenketListe.sorter(liste3, Comparator.naturalOrder());

            System.out.println(liste1);  // [Anders, Berit, Bodil, Kari, Lars, Per]
            System.out.println(liste2);  // [Anders, Berit, Bodil, Kari, Lars, Per]
            System.out.println(liste3);  // [Anders, Berit, Bodil, Kari, Lars, Per]

        // Tabellen navn er upåvirket:
            System.out.println(Arrays.toString(navn));
        // [Lars, Anders, Bodil, Kari, Per, Berit]
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
    public boolean fjern(T verdi)  // Passerer testen 4 av 5 ganger
    {
        if(verdi == null) return false; //tar ikke null verdier, finnes ikke i liste

        Node<T> node = hode;

        while (node != null) {
            if (node.verdi.equals(verdi)) {
                break;
            }

            node = node.neste;
        }
        if (node == null) return false; //Dersom den er ute av intervallet - return false


        if(node == hode){//Verdien er hode
            hode = hode.neste;
            if (hode != null) { // Hvis det bare finnes en verdi i tabellen.
                hode.forrige = null;
            } else {
                hale = null;
            }
        }else if (node == hale){ //verdien er hale
            hale = hale.forrige;
            hale.neste = null;
        }else{ //Verdier er i midten
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }

        antall--;
        endringer++;

        return true;

    }

    @Override
    public T fjern(int indeks)
    {

          indeksKontroll(indeks,false);

          if(tom()) return null;

          Node<T> node;
          T verdi;


          if(antall == 1){
              verdi = hode.verdi;
              hode = hale = null;
          }else if(indeks == 0){
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
          }
          else {
              node = finnNode(indeks);
              verdi = node.verdi;
              node.neste.forrige = node.forrige;
              node.forrige.neste = node.neste;
          }
            antall--;
            endringer++;
          return verdi;

    }

    @Override
    public void nullstill()
    {
/*
        //Tregere metode
        Node<T> node = hode;

        for (int i = 0; i < antall-1 ; i++) {
            node = node.neste;
            node.forrige.neste = null;
            node.forrige.verdi = null;
            endringer ++;
        }
        hode = hale = null;
        antall=0;

*/

        //Mye raskeere metode
        while(hode != null){
            fjern(0);
            endringer++;
        }
        antall = 0;




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

        int antall = liste.antall();
        T temp;

        boolean isComplete = false;

        for(int i = 0; i< antall -1; i++){
            if(c.compare(liste.hent(i), liste.hent(i+1)) > 0){ // sammenligner om tallene er større enn null
               isComplete = true;
                temp = liste.hent(i); //tar vare op første variabel

                liste.oppdater(i, liste.hent(i+1));        //indeks, verdi
                liste.oppdater(i+1, temp);                // indeks, verdi
            }
        }
        //Sjekker om lista er ferdig sortert
        if(isComplete){sorter(liste, c);}
        //Hvis ikke så sorterer lista på nytt. kaller på metoden rekursivt.
    }

    @Override
    public Iterator<T> iterator()
    {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks)
    {
        indeksKontroll(indeks, false);
       DobbeltLenketListeIterator hussi = new  DobbeltLenketListeIterator(indeks);
       return hussi;

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
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;  // teller endringer

        }

        @Override
        public boolean hasNext()
        {
            return denne != null;  // denne koden skal ikke endres!
        }

        @Override
        public T next()
        {
            //A
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("Ulik endringer");
            }

            if(!hasNext()){
                throw new NoSuchElementException("Ikke true");
            }
            fjernOK = true;
            T temp = denne.verdi; //Peker på samme verdi, som nåværende node
            denne = denne.neste; // Oppdaterer node
            return temp;           // Returnerer original verdi


        }

        @Override
        public void remove()
        {

            if(iteratorendringer!= endringer){
                throw new ConcurrentModificationException();
            }
            if (fjernOK == false) {
                throw new  IllegalStateException();
            }



            if(antall == 1){
                hode = hale = null;
            }else if(denne == null){
                hale = hale.forrige;
                hale.neste = null;
            }else if(denne.forrige == hode){
                hode = hode.neste;
                hode.forrige = null; 
            }else{
                denne.forrige.forrige.neste = denne;
                denne.forrige = denne.forrige.forrige;
            }


            fjernOK = false;
            antall--;
            endringer++;
            iteratorendringer++;



         // verdien som lå lengst til høyre nulles


        }

    } // DobbeltLenketListeIterator

} // DobbeltLenketListe








