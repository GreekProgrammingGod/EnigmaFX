package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class setting {

    //Configuration initial des rotors (tableau public)
    public static int[][] rotor1 = {
            {17,4,19,21,7,11,3,-5,7,9,-10,9,17,6,-6,-2,-4,-7,-12,-5,3,4,-21,-16,-2,-21},
            {10,21,5,-17,21,-4,12,16,6,-3,7,-7,4,2,5,-7,-11,-17,-9,-6,-9,-19,2,-3,-21,-4}
    };
    public static ArrayList <List<Integer>> listRotor1 = intArrayToList(rotor1);

    public static int[][] rotor2 = {
            {25,7,17,-3,13,19,12,3,-1,11,5,-5,-7,10,-2,1,-2,4,-17,-8,-16,-18,-9,-1,-22,-16},
            {3,17,22,18,16,7,5,1,-7,16,-3,8,2,9,2,-5,-1,-13,-12,-17,-11,-4,1,-10,-19,-25}
    };
    public static ArrayList <List<Integer>> listRotor2 = intArrayToList(rotor2);

    public static int[][] rotor3 = {
            {12,-1,23,10,2,14,5,-5,9,-2,-13,10,-2,-8,10,-6,6,-16,2,-1,-17,-5,-14,-9,-20,-10},
            {1,16,5,17,20,8,-2,2,14,6,2,-5,-12,-10,9,10,5,-9,1,-14,-2,-10,-6,13,-10,-23}
    };
    public static ArrayList <List<Integer>> listRotor3 = intArrayToList(rotor3);
    @SuppressWarnings("rawtypes")
	public static ArrayList [] rotorTableau = {listRotor1, listRotor2, listRotor3};


    public static int[] reflecteur = {25,23,21,19,17,15,13,11,9,7,5,3,1,-1,-3,-5,-7,-9,-11,-13,-15,-17,-19,-21,-23,-25};
    public static ArrayList<Integer> listReflecteur = new ArrayList<>(new ArrayList<>(Arrays.asList(25,23,21,19,17,15,13,11,9,7,5,3,1,-1,-3,-5,-7,-9,-11,-13,-15,-17,-19,-21,-23,-25)));

    //Configuration des décalages initiaux (Ajouter la reference de l'interface)
    public static int decalageRotor1;
    public static int decalageRotor2;
    public static int decalageRotor3;

    public static ArrayList<Integer> listOrdreRotor= new ArrayList<Integer>();

    public static ArrayList<Integer> listDirectionRotor= new ArrayList<Integer>();

    //Suivi des décalages pendant l'encryption / decryption
    public static int nbTourComplet;
    public static int nbDecalage;

    @SuppressWarnings("unchecked")
	public static void decalagePassageEncryption(@SuppressWarnings("rawtypes") ArrayList [] rotorTableau){
        int nombreDecalageCompleter = nbTourComplet % 3;

        //Passe toutes les positions vs positions rendues
        for (int i = 0; i < rotorTableau.length; i++) {

            if (i == nombreDecalageCompleter) {

                //Passe tous les rotors vs les positions rendues
                for (int j = 0; j < rotorTableau.length; j++) {

                    if (nombreDecalageCompleter == listOrdreRotor.get(j)-1) {
                        decalageList(rotorTableau[j], listDirectionRotor.get(j));
                        nbDecalage++;
                    }
                }
            }
        }
        if(nbDecalage == 26) {
            nbDecalage = 0;
            nbTourComplet++;
            decalagePassageEncryption(rotorTableau);
        }
    }

    @SuppressWarnings("unchecked")
	public static void decalagePassageDecryption(@SuppressWarnings("rawtypes") ArrayList [] rotorTableau){
        int positionActuel = nbTourComplet % 3;
        //Passe toutes les positions vs positions rendues

        for (int i = rotorTableau.length-1; i >= 0; i--) {
            if (i == positionActuel) {
                //Passe tous les rotors vs les positions rendues
                for (int j = rotorTableau.length-1; j >= 0; j--) {
                    if (positionActuel+1 == listOrdreRotor.get(j)) {
                        nbDecalage--;
                        decalageList(rotorTableau[j], (listDirectionRotor.get(j)*-1));
                    }
                }
            }
        }
        if(nbDecalage == 0 && nbTourComplet == 0){
            System.out.println();
        }
        else if(nbDecalage == 0){
            nbDecalage = 26;
            nbTourComplet--;
            decalagePassageDecryption(rotorTableau);
        }
    }

    // Transforme un String en tableau de Int
    public static int[] stringToIntArray(String messageString){
        try {
            //Tableau pour qui va comprendre la valeur INT entre 0
            int[] messageIntArray = new int[messageString.length()];

            //Mettre le text en Uppercase pour avoir les bonnes valeurs dans la table ASCII
            messageString = messageString.toUpperCase();

            for (int i = 0; i < messageString.length(); i++) {
                if (messageString.charAt(i) == 32) {
                    messageIntArray[i] = messageString.charAt(i) - 6;
                } else {
                    messageIntArray[i] = messageString.charAt(i) - 65;
                }
            }
            return messageIntArray;
        } catch (Exception e) {
            System.out.println("Entrée non valide!");
            e.printStackTrace();
        }
        System.out.println("Entrée non valide!");
        return new int [0];
    }

    // Transforme un tableau de Int en String
    public static String intArrayToString(int[] message){
        StringBuilder messageTraduit = new StringBuilder();
        for (int j : message) {
            messageTraduit.append(intToChar(j));
        }
        return messageTraduit.toString();
    }

    // Transforme un int en char
    public static char intToChar(int chiffre){
        try {
            char lettre;
            if (chiffre == 26) {
                lettre = (char) chiffre;
                lettre += 6;
            } else {
                lettre = (char) chiffre;
                lettre += +65;
            }
            return lettre;
        } catch (Exception e) {
            System.out.println("Entrée non valide!");
            e.printStackTrace();
        }
        System.out.println("Entrée non valide!");
        return '0';
    }


    ////////////////////////////////////
    ////////////////////////////////////
    ////////// Test ArrayList //////////
    ////////////////////////////////////
    ////////////////////////////////////

    public static ArrayList<List<Integer>> intArrayToList(int [][] rotor){
        ArrayList <List<Integer>> listRotor = new ArrayList<>();

        ArrayList<Integer> listRow0 = new ArrayList<>();
        ArrayList<Integer> listRow1 = new ArrayList<>();

        for (int column = 0; column < rotor[0].length; column++){
            listRow0.add(rotor[0][column]);
        }

        for (int column = 0; column < rotor[1].length; column++){
            listRow1.add(rotor[1][column]);
        }

        listRotor.add(listRow0);
        listRotor.add(listRow1);

        return listRotor;
    }

    public static void decalageList(ArrayList<List<Integer>>listeRotor, int nombre){
        Collections.rotate(listeRotor.get(0), nombre);
        Collections.rotate(listeRotor.get(1), nombre);
    }

    public static void decalageInitialList(){
        Collections.rotate(listRotor1.get(0), decalageRotor1);
        Collections.rotate(listRotor1.get(1), decalageRotor1);

        Collections.rotate(listRotor2.get(0), decalageRotor2);
        Collections.rotate(listRotor2.get(1), decalageRotor2);

        Collections.rotate(listRotor3.get(0), decalageRotor3);
        Collections.rotate(listRotor3.get(1), decalageRotor3);
    }
}