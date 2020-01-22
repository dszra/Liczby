import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Liczby {
    public static void main(String[] args) throws IOException {
        System.out.println("Proszę podać liczby całkowite: ");
        ArrayList<Object> lista = new ArrayList<>(); //główna lista przechowująca obiekty
        ArrayList<Integer> listaWewn = wpisywanieLiczba(); //lista obiekt przechowująca wpisane liczby
        lista.add(listaWewn); // dodanie listy z wpisanymi liczbami jako obiekt do głównej listy





        while(true) //pętla do działania na obiekcie
        {
            System.out.println("\n" + "Co chcesz dalej zrobić?");
            ArrayList<String> wybory = new ArrayList<>(); //utworzenie oznaczeń dla wyborów z menu
            for (int i = 0; i < menuLista(lista).size(); i++) {
                wybory.add("w" + i);
            }

            menu(wybory, lista);

            int liczba = wpisywanieJednejLiczby(); //wybór opcji
            if (liczba > wybory.size()) {
                System.out.println("proszę podać liczbę odpowiadającą wyborowi z menu");
            } else {

                if (wybory.get(liczba - 1).equals("w3")) //podział na liczby parzyste i nieparzyste
                {
                    ArrayList<Integer> nieparzysteLista = nieparzyste((ArrayList<Integer>) lista.get(0));
                    ArrayList<Integer> parzysteLista = parzyste((ArrayList<Integer>) lista.get(0));
                    lista.set(0, parzysteLista);
                    lista.add(nieparzysteLista);

                    System.out.print("Liczby parzyste to: ");
                    wpisane((ArrayList<Integer>) lista.get(0));
                    System.out.print("Liczby nieparzyste to: ");
                    wpisane((ArrayList<Integer>) lista.get(1));
                }

                if (wybory.get(liczba - 1).equals("w4")) //usunięcie powtarzających się liczb
                {
                    for (int i = 0; i < lista.size(); i++) {
                        ArrayList<Integer> usuniete = usuwanie((ArrayList<Integer>) lista.get(i));
                        lista.set(i, usuniete);
                        wpisane((ArrayList<Integer>) lista.get(i));

                    }
                }


                if (wybory.get(liczba - 1).equals("w5")) //sortowanie liczb od najmniejszej do największej
                {
                    for (int i = 0; i < lista.size(); i++) {
                        lista.set(i, sortowanie((ArrayList<Integer>) lista.get(i)));
                        wpisane((ArrayList<Integer>) lista.get(i));

                    }
                }

                if (wybory.get(liczba - 1).equals("w1")) //zakończenie programu
                {
                    System.out.println("Dziękujemy");
                    break;
                }
                if (wybory.get(liczba - 1).equals("w0")) //zresetowanie programu
                {
                    lista.clear();
                    System.out.println("Proszę podać liczby: ");
                    listaWewn = wpisywanieLiczba(); //lista obiekt przechowująca wpisane liczby
                    lista.add(listaWewn);
                }

                if (wybory.get(liczba - 1).equals("w2")) //dodawanie kolejnych liczb
                {

                    lista.clear();
                    System.out.println("Proszę wpisać kolejne liczby");
                    ArrayList<Integer> listaWewn2 = wpisywanieLiczba();
                    for (Integer integer : listaWewn2) {
                        listaWewn.add(integer);
                    }
                    lista.add(listaWewn);
                }


                if (wybory.get(liczba - 1).equals("w6")) //opcja dostępna tylko jeśli wcześniej podzielono wpisane liczby na parzyste i nieparzyste, służy do połącznia ich w jedną grupę
                {
                    lista.set(0, laczenie((ArrayList<Integer>) lista.get(0), (ArrayList<Integer>) lista.get(1)));
                    lista.remove(1);
                    wpisane((ArrayList<Integer>) lista.get(0));
                }

                if (wybory.get(liczba - 1).equals("w7")) //wyświetlenie wpisanych liczb
                    if (lista.size() == 1) {
                        wpisane((ArrayList<Integer>) lista.get(0));
                    } else {
                        for (int i = 0; i < lista.size(); i++) {
                            wpisane((ArrayList<Integer>) lista.get(i));
                        }
                    }
            }
        }
    }



    private static ArrayList<Integer> wpisywanieLiczba() throws IOException //umożliwia wpisywanie jedynie cyfr, w pętli do momentu przerwania przez użytkownika, w rzypadku wpisania innego znaku niż cyfra prosi o podanie cyfry
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        ArrayList<Integer> lista = new ArrayList<>();
        while(true) { //nieskończona pętla, którą można przerwać przez naciśnięcie enter
            String a = reader.readLine();
            if(a.equals("")) { //przerwanie pętli
                System.out.println("Dziękuję");
                break;
            }

            else
            {
                int j = 0; //zmienna służąca do porównania czy wszystkie wpisane znaki to cyfry
                char c = a.charAt(0);
                if(Character.isDigit(c) || c == '-'){ //sprawdza czy pierwszy wpisany znak to cyfra lub znak minus
                    j++;
                    if(a.length() > 1) //jeśli ilość wpisanych znaków jest większa od 1 sprawdza czy kolejne znaki to cyfry
                    {
                        for(int i = 1; i < a.length(); i++) {
                            char d = a.charAt(i);
                            if (Character.isDigit(d)) { //jeśli znak jest cyfrą to powiększą o 1 zmienną j
                                j++;
                            }
                        }

                    }
                    else //jeśli wpisany jest tylko jeden znak sprawdza czy jest to minus, jeśli tak prosi o podanie liczby
                    {
                        if(c == '-')
                        {
                            System.out.println("minus to nie liczba");
                            j--;
                        }
                    }
                }

                if(j == a.length()) //jeśli zmienna j ma taką samąwartość jak liczba wpisanych znaków, konwertuje to na typ Integer i zapisuje w tablicy
                {
                    long b = Long.parseLong(a);
                    if(b > 2147483647 || b < -2147483648)
                    {
                        System.out.println("Podana liczba nie mieści się w przedziale, proszę podać liczbą z przedziału między -2 147 483 648 a 2 147 483 647");
                    }
                    else{
                        int integer = Integer.parseInt(a);
                        lista.add(integer);
                    }

                }
                else //jeśli wartości j i ilość wpisanych znaków różnią się, prosi o wpisanie liczby
                {
                    System.out.println("proszę podać liczbę");
                }
            }
        }
        return lista;
    }

    private static Integer wpisywanieJednejLiczby() throws IOException //umożliwia wpisanie dowolnych znaków z klawiatury
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int liczba = 0;
        while (true) {

            int j = 0;
            String a = reader.readLine();
            for (int i = 0; i < a.length(); i++) {
                char c = a.charAt(i);
                if (Character.isDigit(c)) {
                    j++;
                }
            }

        if (j == a.length()) {
            liczba = Integer.parseInt(a);
            break;
        } else {
            System.out.println("proszę podać liczbę");
        }
    }

        return liczba;
    }

    private static void wpisane(ArrayList<Integer> x)
    {
        for(int a : x)
        {
            System.out.print(a + ", ");
        }
        System.out.println("");
    }
    @NotNull
    private static ArrayList<Integer> parzyste(ArrayList<Integer> x)
    {
        ArrayList<Integer> lista = x;
        ArrayList<Integer> parzyste = new ArrayList<Integer>();
        for (Integer integer : lista) {
            if (integer % 2 == 0) {
                parzyste.add(integer);
            }

        }
        return parzyste;
    }
    @NotNull
    private static ArrayList<Integer> nieparzyste(ArrayList<Integer> x)
    {
        ArrayList<Integer> lista = x;
        ArrayList<Integer> nieparzyste = new ArrayList<Integer>();
        for (Integer integer : lista) {
            if (integer % 2 != 0) {
                nieparzyste.add(integer);
            }

        }
        return nieparzyste;
    }

    private static ArrayList<Integer> usuwanie(ArrayList<Integer> x)
    {
        for(int i = 0; i < x.size(); i++)
        {
            for(int j = x.size()-1; j > i; j--)
            {
                if(x.get(i).equals(x.get(j)))
                {
                    x.remove(j);
                }
            }
        }
        return x;
    }

    private static ArrayList<Integer> sortowanie(ArrayList<Integer> x)
    {
        ArrayList<Integer> lista = x;
        for(int i = 0; i < lista.size() - 1; i++)
        {
            for (int j = i + 1; j < lista.size(); j++)
            {
                if (lista.get(i) > lista.get(j))
                {
                    int a = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, a);
                }
            }
        }
        return lista;
    }

    @NotNull
    private static ArrayList<Integer> laczenie(@NotNull ArrayList<Integer> x, ArrayList<Integer> y)
    {
        ArrayList<Integer> lista = new ArrayList<Integer>();

        for (Integer integer : x) {
            lista.add(integer);
        }
        for (Integer integer : y) {
            lista.add(integer);
        }
        return lista;
    }

    private static HashMap<String, String> menuLista(ArrayList<Object> lista) { //lista dostępnych opcji
        HashMap<String, String> listaWyborow = new HashMap<String, String>();

        listaWyborow.put("w0", "nowy;");
        listaWyborow.put("w1", "koniec;");
        listaWyborow.put("w2", "dodaj kolejne liczby;");
        listaWyborow.put("w3", "podział na liczby parzyste i nieparzyste;");
        listaWyborow.put("w4", "usunięcie powtarzających się liczb;");
        listaWyborow.put("w5", "uszeregowanie liczb od najmniejszej do największej;");
        listaWyborow.put("w6", "połącz, jeśli rozbite na dwie grupy;");
        listaWyborow.put("w7", "wyświetlenie wpisanych liczb;");


        return listaWyborow;
    }

    private static void menu(ArrayList<String> x, ArrayList<Object> lista)
    {
        int j = 0;

        if(lista.size() == 1) { //jeśli liczby nie są rozbite na dwie grupy to usuwa opcję łączenia ich

            x.remove("w6");
            x.remove("w1");
            x.add("w1");


            for (int i = 0; i < menuLista(lista).size(); i++) {
                if (i != 1 && i != 6) {
                    System.out.println((j + 1) + "- " + menuLista(lista).get("w" + i));
                    j++;
                }
            }
        }
        else //jeśli liczby rozbite są na dwie grupy (po wykonaniu operacji podziału na liczby parzyste i nieparzyste) to wyświetla opcję połączenia ich spowrotem w jedną grupę
        {
            x.remove("w1");
            x.add("w1");
            for (int i = 0; i < menuLista(lista).size(); i++) {
                if (i != 1) {
                    System.out.println((j + 1) + "- " + menuLista(lista).get("w" + i));
                    j++;
                }
            }
        }
        System.out.println((j + 1) + "- " + menuLista(lista).get("w1"));

    }
}
