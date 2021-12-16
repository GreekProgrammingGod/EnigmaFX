package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.List;

public class MainSceneController {

    @FXML
    private List<Label> rotor1List;

    @FXML
    private List<Label> rotor2List;

    @FXML
    private List<Label> rotor3List;

    @FXML
    private List<Label> rotorReflecteurList;

    @FXML
    private List<Label> alphabetLabelList;

    @FXML
    private TextField encryptionTextField;

    @FXML
    private TextField decryptionTextField;

    private static int []chemin = new int[9];

    private String messageAEncrypter;

    private String messageADecrypter;

    private String messageDecrypter = "";

    private String messageEncrypter = "";

    @FXML
    private ChoiceBox <String>  rotor1OrdreChoicebox;

    @FXML
    private ChoiceBox <String>  rotor2OrdreChoicebox;

    @FXML
    private ChoiceBox <String>  rotor3OrdreChoicebox;

    @FXML
    private ChoiceBox <String> rotor1DirectionChoicebox;

    @FXML
    private ChoiceBox <String> rotor2DirectionChoicebox;

    @FXML
    private ChoiceBox <String> rotor3DirectionChoicebox;

    @FXML
    private Spinner <Integer> rotor1DecalageSpinner;

    @FXML
    private Spinner <Integer> rotor2DecalageSpinner;

    @FXML
    private Spinner <Integer> rotor3DecalageSpinner;

    @FXML
    private RadioButton encrypterRadio;

    @FXML
    private RadioButton decrypterRadio;

    @FXML
    public ToggleGroup choixAction = new ToggleGroup();

    @FXML
    public Button resetBtn;

    @FXML
    public Label warningLabel;

    @FXML
    public void initialize() {
        warningLabel.setText("Veuillez-configurer les rotors avant de commencer");

        setting.nbDecalage = 0;
        setting.nbTourComplet = 0;

        //Ordre
        rotor1OrdreChoicebox.getItems().add("1");
        rotor1OrdreChoicebox.getItems().add("2");
        rotor1OrdreChoicebox.getItems().add("3");

        rotor2OrdreChoicebox.getItems().add("1");
        rotor2OrdreChoicebox.getItems().add("2");
        rotor2OrdreChoicebox.getItems().add("3");

        rotor3OrdreChoicebox.getItems().add("1");
        rotor3OrdreChoicebox.getItems().add("2");
        rotor3OrdreChoicebox.getItems().add("3");

        //Direction
        rotor1DirectionChoicebox.getItems().add("Gauche");
        rotor1DirectionChoicebox.getItems().add("Droite");

        rotor2DirectionChoicebox.getItems().add("Gauche");
        rotor2DirectionChoicebox.getItems().add("Droite");

        rotor3DirectionChoicebox.getItems().add("Gauche");
        rotor3DirectionChoicebox.getItems().add("Droite");

        rotor1OrdreChoicebox.setValue("1");

        rotor2OrdreChoicebox.setValue("2");

        rotor3OrdreChoicebox.setValue("3");

        rotor1DirectionChoicebox.setValue("Droite");

        rotor2DirectionChoicebox.setValue("Droite");

        rotor3DirectionChoicebox.setValue("Droite");

        setting.listOrdreRotor.add(1);
        setting.listOrdreRotor.add(2);
        setting.listOrdreRotor.add(3);

        setting.listDirectionRotor.add(1);
        setting.listDirectionRotor.add(1);
        setting.listDirectionRotor.add(1);

        //Choix entre encrypter ou Décrypter
        encrypterRadio.setToggleGroup(choixAction);
        decrypterRadio.setToggleGroup(choixAction);

        int count = 0;
        for(int row = 0; row < setting.rotor1.length; row++){
            for(int column = 0; column < setting.rotor1[row].length;column++ ){
                rotor1List.get(count).setText(Integer.toString(setting.rotor1[row][column]));
                count++;
            }
        }
        count = 0;
        for(int row = 0; row < setting.rotor2.length; row++){
            for(int column = 0; column < setting.rotor2[row].length;column++ ){
                rotor2List.get(count).setText(Integer.toString(setting.rotor2[row][column]));
                count++;
            }

        }
        count = 0;
        for(int row = 0; row < setting.rotor3.length; row++){
            for(int column = 0; column < setting.rotor3[row].length;column++ ){
                rotor3List.get(count).setText(Integer.toString(setting.rotor3[row][column]));
                count++;
            }

        }
        count = 0;
        for(int column = 0; column < setting.reflecteur.length ;column++ ){
            rotorReflecteurList.get(count).setText(Integer.toString(setting.reflecteur[column]));
            count++;
        }
    }

    @FXML
    public void reinitialize() {

        resetCouleur();

        warningLabel.setText("Veuillez-configurer les rotors avant de commencer");

        setting.nbDecalage = 0;
        setting.nbTourComplet = 0;

        //Configuration des variables ordres dans setting
        setting.listOrdreRotor.set(0,1);
        setting.listOrdreRotor.set(1,2);
        setting.listOrdreRotor.set(2,3);

        //Configuration des variables directions dans setting
        setting.listDirectionRotor.set(0,1);
        setting.listDirectionRotor.set(1,1);
        setting.listDirectionRotor.set(2,1);

        setting.decalageRotor1 = 0;
        setting.decalageRotor2 = 0;
        setting.decalageRotor3 = 0;

        rotor1OrdreChoicebox.setValue("1");

        rotor2OrdreChoicebox.setValue("2");

        rotor3OrdreChoicebox.setValue("3");


        //Direction
        rotor1DirectionChoicebox.setValue("Droite");

        rotor2DirectionChoicebox.setValue("Droite");

        rotor3DirectionChoicebox.setValue("Droite");

        decryptionTextField.setText("");

        encryptionTextField.setText("");

        messageAEncrypter = null;

        messageEncrypter = "";

        messageADecrypter = null;

        messageDecrypter = "";

        rotor1DecalageSpinner.getValueFactory().setValue(0);
        rotor2DecalageSpinner.getValueFactory().setValue(0);
        rotor3DecalageSpinner.getValueFactory().setValue(0);

        int count = 0;
        for(int row = 0; row < setting.rotor1.length; row++){
            for(int column = 0; column < setting.rotor1[row].length;column++ ){
                rotor1List.get(count).setText(Integer.toString(setting.rotor1[row][column]));
                count++;
            }

        }
        count = 0;
        for(int row = 0; row < setting.rotor2.length; row++){
            for(int column = 0; column < setting.rotor2[row].length;column++ ){
                rotor2List.get(count).setText(Integer.toString(setting.rotor2[row][column]));
                count++;
            }

        }
        count = 0;
        for(int row = 0; row < setting.rotor3.length; row++){
            for(int column = 0; column < setting.rotor3[row].length;column++ ){
                rotor3List.get(count).setText(Integer.toString(setting.rotor3[row][column]));
                count++;
            }

        }
        count = 0;
        for(int column = 0; column < setting.reflecteur.length ;column++ ){
            rotorReflecteurList.get(count).setText(Integer.toString(setting.reflecteur[column]));
            count++;
        }
    }

    @FXML
    public void actionConfigurerRotor() {
        configurerVariable();
        updateRotorModifier();
        warningLabel.setText("Entrez votre texte à encrypter dans la boite désignée");
    }

    @FXML
    public void configurerVariable(){

        //Configuration des variables ordres dans setting
        switch (Integer.parseInt(rotor1OrdreChoicebox.getValue())) {
            case 1 -> setting.listOrdreRotor.set(0,1);
            case 2 -> setting.listOrdreRotor.set(0,2);
            case 3 -> setting.listOrdreRotor.set(0,3);
        }
        switch (Integer.parseInt(rotor2OrdreChoicebox.getValue())) {
            case 1 -> setting.listOrdreRotor.set(1,1);
            case 2 -> setting.listOrdreRotor.set(1,2);
            case 3 -> setting.listOrdreRotor.set(1,3);
        }
        switch (Integer.parseInt(rotor3OrdreChoicebox.getValue())) {
            case 1 -> setting.listOrdreRotor.set(2,1);
            case 2 -> setting.listOrdreRotor.set(2,2);
            case 3 -> setting.listOrdreRotor.set(2,3);
        }

        //Configuration des variables directions dans setting
        switch (rotor1DirectionChoicebox.getValue()) {
            case "Gauche" -> setting.listDirectionRotor.set(0,-1);
            case "Droite" -> setting.listDirectionRotor.set(0,1);
        }
        switch (rotor2DirectionChoicebox.getValue()) {
            case "Gauche" -> setting.listDirectionRotor.set(1,-1);
            case "Droite" -> setting.listDirectionRotor.set(1,1);
        }
        switch (rotor3DirectionChoicebox.getValue()) {
            case "Gauche" -> setting.listDirectionRotor.set(2,-1);
            case "Droite" -> setting.listDirectionRotor.set(2,1);
        }

        //Configuration des variables décalages dans setting
        setting.decalageRotor1 = Integer.parseInt(rotor1DecalageSpinner.getValue().toString());
        setting.decalageRotor2 = Integer.parseInt(rotor2DecalageSpinner.getValue().toString());
        setting.decalageRotor3 = Integer.parseInt(rotor3DecalageSpinner.getValue().toString());

        setting.decalageInitialList();
    }

    public void updateRotorModifier() {
        int count = 0;
        for(int row = 0; row < setting.listRotor1.size(); row++){
            for(int column = 0; column < setting.listRotor1.get(row).size();column++ ){
                rotor1List.get(count).setText(Integer.toString(setting.listRotor1.get(row).get(column)));
                count++;
            }
        }
        count = 0;
        for(int row = 0; row < setting.listRotor2.size(); row++){
            for(int column = 0; column < setting.listRotor2.get(row).size();column++ ){
                rotor2List.get(count).setText(Integer.toString(setting.listRotor2.get(row).get(column)));
                count++;
            }
        }
        count = 0;
        for(int row = 0; row < setting.listRotor3.size(); row++){
            for(int column = 0; column < setting.listRotor3.get(row).size();column++ ){
                rotor3List.get(count).setText(Integer.toString(setting.listRotor3.get(row).get(column)));
                count++;
            }
        }
        count = 0;
        for(int column = 0; column < setting.listReflecteur.size(); column++ ){
            rotorReflecteurList.get(count).setText(Integer.toString(setting.listReflecteur.get(column)));
            count++;
        }
    }

    //Event Listener sur btnEtapeSuivanteCliquee.action
    @FXML
    public void btnEtapeSuivanteCliquee(ActionEvent event) {
        warningLabel.setText("");
        if(choixAction.getSelectedToggle() == encrypterRadio) {
            messageAEncrypter = (messageAEncrypter == null) ? encryptionTextField.getText() : messageAEncrypter;
            try {
                resetCouleur();
                updateRotorModifier();

                String transformation = (Character.toString(messageAEncrypter.charAt(0)));

                int[] transformation2 = setting.stringToIntArray(transformation);
                chemin[0] = transformation2[0];
                transformation2 = algorithme.encrypter(transformation2);

                chemin[8] = transformation2[0];
                String derniereTransformation = setting.intArrayToString(transformation2);
                messageEncrypter += derniereTransformation;
                messageAEncrypter = messageAEncrypter.substring(1);

                decryptionTextField.setText(messageEncrypter);

                colorerChemin();
            } catch (Exception e) {
                warningLabel.setText("Fin de l'encryptions, vous pouvez maintenant décrypter le message !");
            }
        } else  if (choixAction.getSelectedToggle() == decrypterRadio) {
            messageADecrypter = (messageADecrypter == null) ? decryptionTextField.getText() : messageADecrypter;
            try {
                resetCouleur();
                updateRotorModifier();

                String transformation = (Character.toString(messageADecrypter.charAt(messageADecrypter.length()-1)));

                int[] transformation2 = setting.stringToIntArray(transformation);
                chemin[0] = transformation2[0];
                transformation2 = algorithme.decrypter(transformation2);

                chemin[8] = transformation2[0];
                String derniereTransformation = setting.intArrayToString(transformation2);
                messageDecrypter = derniereTransformation + messageDecrypter;
                messageADecrypter = messageADecrypter.substring(0, messageADecrypter.length()-1);

                encryptionTextField.setText(messageDecrypter);
                colorerChemin();
            } catch (Exception e) {
                warningLabel.setText("Fin de la decryption");
            }
        }
    }

    public void colorerChemin() {
        rotor1List.get(chemin[1] + 26).setTextFill(Color.RED);
        rotor1List.get(chemin[1] + 26).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        rotor2List.get(chemin[2] + 26).setTextFill(Color.RED);
        rotor2List.get(chemin[2] + 26).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        rotor3List.get(chemin[3] + 26).setTextFill(Color.RED);
        rotor3List.get(chemin[3] + 26).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        rotorReflecteurList.get(chemin[4]).setTextFill(Color.RED);
        rotorReflecteurList.get(chemin[4]).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        rotor3List.get(chemin[5]).setTextFill(Color.BLUE);
        rotor3List.get(chemin[5]).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        rotor2List.get(chemin[6]).setTextFill(Color.BLUE);
        rotor2List.get(chemin[6]).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        rotor1List.get(chemin[7]).setTextFill(Color.BLUE);
        rotor1List.get(chemin[7]).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        for(int i = 0; i < 26; i++) {
            if (alphabetLabelList.get(i).getText().equals(setting.intArrayToString(new int [] {chemin[0]}))){
                alphabetLabelList.get(i).setTextFill(Color.RED);
                alphabetLabelList.get(i).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            }
            if (alphabetLabelList.get(i).getText().equals(setting.intArrayToString(new int [] {chemin[8]}))) {
                alphabetLabelList.get(i).setTextFill(Color.BLUE);
                alphabetLabelList.get(i).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            }
        }
    }

    public void resetCouleur() {
        for (int i = 0; i < 26; i++) {
            rotor1List.get(i + 26).setTextFill(Color.BLACK);
            rotor1List.get(i + 26).setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            rotor2List.get(i + 26).setTextFill(Color.BLACK);
            rotor2List.get(i + 26).setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            rotor3List.get(i + 26).setTextFill(Color.BLACK);
            rotor3List.get(i + 26).setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            rotor1List.get(i).setTextFill(Color.BLACK);
            rotor1List.get(i).setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            rotor2List.get(i).setTextFill(Color.BLACK);
            rotor2List.get(i).setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            rotor3List.get(i).setTextFill(Color.BLACK);
            rotor3List.get(i).setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            rotorReflecteurList.get(i).setTextFill(Color.BLACK);
            rotorReflecteurList.get(i).setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            alphabetLabelList.get(i).setTextFill(Color.BLACK);
            alphabetLabelList.get(i).setFont(Font.font("Century", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        }
    }

    public static void setChemin(int somme, int position) {
        chemin[position] = somme;
    }
}