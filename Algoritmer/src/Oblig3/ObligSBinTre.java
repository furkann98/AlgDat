package Oblig3;

////////////////// ObligSBinTre /////////////////////////////////

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
        {
            this.verdi = verdi;
            venstre = v; høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString(){ return "" + verdi;}

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public ObligSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    @Override
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

       if(tom()){
           rot = new Node<T>(verdi,null);
       }

       else {
           Node<T> p = rot, q = null;               // p starter i roten
           int cmp = 0;                             // hjelpevariabel

           while (p != null)                        // fortsetter til p er ute av treet
           {
               q = p;                                 // q er forelder til p
               cmp = comp.compare(verdi, p.verdi);     // bruker komparatoren
               p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
           }

           // p er nå null, dvs. ute av treet, q er den siste vi passerte

           p = new Node<>(verdi,q);                  // oppretter en ny node

           if (q == null) rot = p;                  // p blir rotnode
           else if (cmp < 0) q.venstre = p;         // venstre barn til q
           else q.høyre = p;                        // høyre barn til q


       }

        antall++;
        endringer++;// én verdi mer i treet
        return true;
    }

    @Override
    public boolean inneholder(T verdi)
    {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    @Override
    public boolean fjern(T verdi)
    {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi)
    {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    @Override
    public int antall()
    {
        return antall;
    }

    public int antall(T verdi)
    {
        if(verdi.equals(null))return 0;


        int antallLike = 0;
        Node<T> p = rot;

        while( p!= null){
            int cmp = comp.compare(verdi, p.verdi);
            if(cmp<0)p=p.venstre;
            else{
                if(cmp==0) antallLike++;
                p = p.høyre;
            }

        }
        return antallLike;


       // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    @Override
    public boolean tom()
    {
        return antall == 0;
    }

    @Override
    public void nullstill()
    {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> nesteInorden(Node<T> p)
    {
      if(p.høyre !=null){
           p = p.høyre;
           while(p.venstre != null){
               p = p.venstre;
           }
           return p;
       }else{
           while(p!=null){
               if(p.forelder!=null && p.forelder.høyre != p){
                   return p.forelder;
               }
               p = p.forelder;
           }
       }
       return p;

    /*
        if(p.høyre == null && p.venstre == null) return null;
         if(p.høyre !=null){
            p = p.høyre;
            while(p.venstre != null && p.forelder.høyre != p){
                p = p.venstre;
            }
             return p.forelder;
        }
        else if(p.høyre == null && p.venstre!= null){
            return p.forelder;
        }
        return p; */

        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    @Override
    public String toString()
    {
       if(tom()) return "[]";


       StringBuilder sb = new StringBuilder();
       sb.append("[");

       Node<T> p = rot;
       while(p.venstre != null){
           p = p.venstre;
       }

       for(int i = 0; i < antall; i++){
           sb.append(p.verdi);
           if(i != (antall-1)) sb.append(", ");
           p = nesteInorden(p);
       }

       sb.append("]");
        //System.out.println(sb.toString());


        return sb.toString();
       /*
      else if(antall == 1) return"[" + rot.verdi + "]";


       String tekst = "[";

        Node<T> p=  rot;

        while(p.venstre != null){
            p = p.venstre;
        }

        tekst += p.verdi;
        p = nesteInorden(p);

        while(nesteInorden(p)!= null){
            tekst += ", " + p.verdi;
            p = nesteInorden(p);
        }

        tekst += "]";

        return tekst;
*/

      /* if(antall < 1){ return  "[]";}
        //else if(tom()){return"[]";}

       String tekst = "[";
       Node<T> p = rot;

       for (;p!=null;){
           p = p.venstre;
       }


       tekst += p.verdi;
       p = nesteInorden(p);


       for(int i = 1;i<antall;i++){
           tekst += "," + p.verdi;
           p = nesteInorden(p);
       }


       tekst += "]";
       return tekst;
*/


      //  throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public String omvendtString()
    {
        if(tom()) return "[]";
        StringBuilder sb = new StringBuilder();
        Deque<Node> stack = new ArrayDeque<>();
        sb.append("[");

        Node<T> p = rot;

        while(p.venstre != null){
            p = p.venstre;
        }

        for(int i = 0; i < antall; i++){
            stack.addFirst(p);
            p = nesteInorden(p);
           // System.out.println("STACK " +  stack.pop());
        }


        for(int i = 0; i< antall; i++){
            sb.append(stack.pop());
            if(i!=(antall-1)) sb.append(", ");
            //stack.removeFirst();
        }

        sb.append("]");
        //System.out.println(sb.toString());

        return sb.toString();



        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public String høyreGren()
    {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public String lengstGren()
    {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public String[] grener()
    {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public String bladnodeverdier()
    {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public String postString()
    {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    @Override
    public Iterator<T> iterator()
    {
        return new BladnodeIterator();
    }

    private class BladnodeIterator implements Iterator<T>
    {
        private Node<T> p = rot, q = null;
        private boolean removeOK = false;
        private int iteratorendringer = endringer;

        private BladnodeIterator()  // konstruktør
        {
            throw new UnsupportedOperationException("Ikke kodet ennå!");
        }

        @Override
        public boolean hasNext()
        {
            return p != null;  // Denne skal ikke endres!
        }

        @Override
        public T next()
        {
            throw new UnsupportedOperationException("Ikke kodet ennå!");
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Ikke kodet ennå!");
        }

    } // BladnodeIterator

} // ObligSBinTre
