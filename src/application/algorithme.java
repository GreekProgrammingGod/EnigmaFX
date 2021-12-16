package application;

public class algorithme {

    public static int[] encrypter(int[] messageClair){
        //Cr�er un tableau pour entrer le message encrypter
        int[] messageCrypter = new int[messageClair.length];

        //Boucle pour passer les messages lettre par lettre
        for(int i = 0; i < (messageClair.length); i++){
            int somme = messageClair[i];

            //Conserver les espaces entre les mots
            if (messageClair[i] == 26){
                messageCrypter[i]= messageClair[i];
            }

            //Encrypte les lettres entre a --> z
            else{

                //Rotor #1 (premier passage)
                MainSceneController.setChemin(somme, 1);
                somme += setting.listRotor1.get(1).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #2 (premier passage)
                MainSceneController.setChemin(somme, 2);
                somme += setting.listRotor2.get(1).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #3 (premier passage)
                MainSceneController.setChemin(somme, 3);
                somme += setting.listRotor3.get(1).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor R�flecteur
                MainSceneController.setChemin(somme, 4);
                somme += setting.listReflecteur.get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #3 (deuxi�me passage)
                MainSceneController.setChemin(somme, 5);
                somme += setting.listRotor3.get(0).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #2 (deuxi�me passage)
                MainSceneController.setChemin(somme, 6);
                somme += setting.listRotor2.get(0).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #1 (deuxi�me passage)
                MainSceneController.setChemin(somme, 7);
                somme += setting.listRotor1.get(0).get(somme);
                somme = Math.floorMod(somme,26);

                messageCrypter[i] = somme;


                setting.decalagePassageEncryption(setting.rotorTableau);
            }
        }

        return messageCrypter;
    }


    public static int[] decrypter(int[] messageClair){
        // Cr�er un tableau pour entrer le message encrypter
        int[] messageDecrypter = new int[messageClair.length];

        //Boucle pour passer les messages lettre par lettre
        for(int i = messageClair.length-1; i >= 0; i--){
            int somme = messageClair[i];

            //Conserver les espaces entre les mots
            if (messageClair[i] == 26){
                messageDecrypter[i]= messageClair[i];
            }

            //Encrypte les lettres entre a --> z
            else{

                setting.decalagePassageDecryption(setting.rotorTableau);

                MainSceneController.setChemin(somme, 1);
                somme += setting.listRotor1.get(1).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #2 (premier passage)
                MainSceneController.setChemin(somme, 2);
                somme += setting.listRotor2.get(1).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #3 (premier passage)
                MainSceneController.setChemin(somme, 3);
                somme += setting.listRotor3.get(1).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor R�flecteur
                MainSceneController.setChemin(somme, 4);
                somme += setting.listReflecteur.get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #3 (deuxi�me passage)
                MainSceneController.setChemin(somme, 5);
                somme += setting.listRotor3.get(0).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #2 (deuxi�me passage)
                MainSceneController.setChemin(somme, 6);
                somme += setting.listRotor2.get(0).get(somme);
                somme = Math.floorMod(somme,26);

                //Rotor #1 (deuxi�me passage)
                MainSceneController.setChemin(somme, 7);
                somme += setting.listRotor1.get(0).get(somme);
                somme = Math.floorMod(somme,26);

                messageDecrypter[i] = somme;

            }
        }
        return messageDecrypter;
    }
}
