package User_register;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafxapplication1.ZTM;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa implementujaca Initializable przechowujaca metody, pola i obiekty
 * odpowiadajace za rejestracje oraz edycje danych uzytownika.
 */

public class User_registerController implements Initializable {

    @FXML
    /** * Pole typu HBox okreslajace wielkosc i polozenie okna*/
    public HBox parent;
    /** * Pole typu Button okreslajace przycisk i rejestracji*/
    public Button buttonuserregister;
    /** * Pole typu JFXTextField sluzace do wpisania imienia w formularzu rejestracji*/
    public JFXTextField regname;
    /** * Pole typu JFXTextField sluzace do wpisania nazwiska w formularzu rejestracji*/
    public JFXTextField regsurname;
    /** * Pole typu JFXTextField sluzace do wpisania ID Karty podrozujacego w formularzu rejestracji*/
    public JFXTextField regcard;
    /** * Pole typu JFXTextField sluzace do wpisania kodu pocztowego w formularzu rejestracji*/
    public JFXTextField regpostal;
    /** * Pole typu JFXTextField sluzace do wpisania adresu w formularzu rejestracji*/
    public JFXTextField regaddress;
    /** * Pole typu JFXTextField sluzace do wpisania miasta w formularzu rejestracji*/
    public JFXTextField regcity;
    /** * Pole typu JFXTextField sluzace do wpisania telefonu w formularzu rejestracji*/
    public JFXTextField regphone;
    /** * Pole typu JFXTextField sluzace do wpisania emaila w formularzu rejestracji*/
    public JFXTextField regemail;
    /** * Pole typu JFXTextField sluzace do wpisania UserID w formularzu rejestracji*/
    public JFXTextField reguserid;
    /** * Pole typu JFXTextField sluzace do wpisania nazwy uzytkownika w formularzu rejestracji*/
    public JFXTextField regusername;
    /** * Pole typu JFXPasswordField sluzace do wpisania hasla w formularzu rejestracji*/
    public JFXPasswordField regpass,regpass2;
    /** * Pole typu Label sluzace do wyswietlenia odpowiedniego tekstu w formularzu rejestracji*/
    public Label signUpText;
    /** * Pole typu Button odpowiadajace za przycisk do edycji w formularzu rejestracji*/
    public Button buttonEdit;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy imie jest wprowadzone poprawnie*/
    public Label namecheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy nazwisko jest wprowadzone poprawnie*/
    public Label surnamecheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy ID karty podrozujacego jest wprowadzone poprawnie*/
    public Label idcheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy adres jest wprowadzony poprawnie*/
    public Label addresscheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy kod pocztowy jest wprowadzony poprawnie*/
    public Label postalcheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy miasto jest wprowadzone poprawnie*/
    public Label citycheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy nr telefonu jest wprowadzony poprawnie*/
    public Label phonecheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy email jest wprowadzony poprawnie*/
    public Label mailcheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy UserID jest wprowadzone poprawnie*/
    public Label useridcheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy nazwa uzytkownika jest wprowadzone poprawnie*/
    public Label usernamecheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji czy haslo jest wprowadzone poprawnie*/
    public Label passwordscheck;
    /** * Pole typu Label sluzace do wyswietlenia informacji o dodaniu uzytkownika*/
    public Label successcheck;
    /** * Pole typu double okreslajace pozycje x okna */
    public double xOffSet = 0;
    /** * Pole typu double okreslajace pozycje y okna */
    public double yOffSet = 0;
    /** * Pole typu int okreslajace numer okna */
    public int window;

    @FXML
    /***
     * Metoda sluzoca do oblugi przycisku minimalizacji aplikacji.
     * @param event parametr do obslugi klikniecia myszka
     */
    public void minimize_btn(MouseEvent event) {
        ZTM.stage.setIconified(true);
    }


    @FXML
    /***
     * Metoda sluzoca do oblugi przycisku zamykania aplikacji.
     * @param event parametr do obslugi klikniecia myszka
     */
    public void exit_btn(MouseEvent event) {
        System.exit(0);
    }


    @Override
    /***
     * Metoda sluzaca do inicjalizacji metody dzieki ktorej
     * scena bedzie mozliwa do przesuwania po ekranie.
     * @param location lokalizacja używana do rozwiązywania ścieżek względnych dla obiektu głównego lub null, jeśli lokalizacja nie jest znana.
     * @param resources zasoby używane do lokalizowania obiektu głównego lub null, jeśli obiekt główny nie został zlokalizowany.
     */
    public void initialize(URL location, ResourceBundle resources) {
        makeStageDragable();
        signUpText.setText("Sign Up!");
        signUpText.setTextFill(Color.DARKGRAY);
        buttonEdit.setVisible(false);
    }

    /***
     * Metoda sprawdzajaca czy dany ciag znakow zawiera w sobie cyfry.
     * @param string Sprawdzany ciag znakow
     */
    public boolean containsNumber(String string){
        return string.matches(".*\\d+.*");
    }

    /***
     * Metoda dzieki ktorej mozna przenosić scene po ekranie.
     */
    public void makeStageDragable() {
        parent.setOnMousePressed((event) -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        parent.setOnMouseDragged((event) -> {
            ZTM.stage.setX(event.getScreenX() - xOffSet);
            ZTM.stage.setY(event.getScreenY() - yOffSet);
            ZTM.stage.setOpacity(0.8f);
        });
        parent.setOnDragDone((event) -> {
            ZTM.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((event) -> {
            ZTM.stage.setOpacity(1.0f);
        });
    }


    @FXML
    /***
     * Metoda sluzaca do oblugi przycisku sluzacego do powrotu na ekran startowy.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatku IO
     */
    public void home_btn(MouseEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/javafxapplication1/ZTM.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    /***
     * Metoda sprawdzajaca czy uzytkownik o podanym przez klienta 'username' istenieje juz w bazie.
     * W zaleznosci od otrzymanej informacji z serwera wyswietlany jest odpowiedni komunikat
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatku IO
     * @throws ClassNotFoundException rzucanie wyjatku ClassNotFound
     */
    public void checkExists(MouseEvent event) throws IOException, ClassNotFoundException {

        boolean exists;
        Socket existsSocket = new Socket("localhost", 9999);
        ObjectOutputStream send = new ObjectOutputStream(existsSocket.getOutputStream());
        ObjectInput receive = new ObjectInputStream(existsSocket.getInputStream());
        send.flush();
        window = 9;
        send.writeObject(window);
        send.flush();
        send.writeObject(regusername.getText().toLowerCase());
        send.flush();
        exists = (boolean) receive.readObject();
        if(exists == true) {
            addUser(event);
            usernamecheck.setTextFill(Color.GREEN);
            usernamecheck.setText("Correct!");
        }
        else if(regusername.getText().isEmpty()){
            usernamecheck.setTextFill(Color.RED);
            usernamecheck.setText("Incorrect!");
        }else{
            usernamecheck.setTextFill(Color.TOMATO);
            usernamecheck.setText("Username EXISTS!");
        }
    }

    @FXML
    /***
     * Metoda sluzaca do dodawania uzytkownika.
     * Sprawdza czy dane wprowadzone w formularzu sa poprawne.
     * Jesli tak wysyla je na serwer aby dodac je do bazy oraz wyswietla komunikat o poprawnym dodaniu użytkownika.
     * Jesli nie wyswietlane sa stosowne komunikaty.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatku IO
     */
    public void addUser(MouseEvent event) throws IOException{

        boolean name = false;
        boolean surname = false;
        boolean id = false;
        boolean address = false;
        boolean postal = false;
        boolean city = false;
        boolean phone = false;
        boolean mail = false;
        boolean userid = false;
        boolean username = false;
        boolean pass = false;

        if ((regname.getText().isEmpty()) || (containsNumber(regname.getText())==true)) {
            namecheck.setTextFill(Color.RED);
            namecheck.setText("Incorrect!");
            regname.clear();
        } else {
            namecheck.setTextFill(Color.GREEN);
            namecheck.setText("Correct!");
            name = true;
        }
        if ((regsurname.getText().isEmpty())  || (containsNumber(regsurname.getText())==true)) {
            surnamecheck.setTextFill(Color.RED);
            surnamecheck.setText("Incorrect!");
            regsurname.clear();
        } else {
            surnamecheck.setTextFill(Color.GREEN);
            surnamecheck.setText("Correct!");
            surname = true;
        }
        if (regcard.getText().isEmpty()) {
            idcheck.setTextFill(Color.RED);
            idcheck.setText("Incorrect!");
            regcard.clear();
        } else {
            idcheck.setTextFill(Color.GREEN);
            idcheck.setText("Correct!");
            id = true;
        }
        if (regaddress.getText().isEmpty()) {
            addresscheck.setTextFill(Color.RED);
            addresscheck.setText("Incorrect!");
            regaddress.clear();
        } else {
            addresscheck.setTextFill(Color.GREEN);
            addresscheck.setText("Correct!");
            address = true;
        }
        if ((regpostal.getText().isEmpty()) || (regpostal.getText().length() >6)) {
            postalcheck.setTextFill(Color.RED);
            postalcheck.setText("Incorrect!");
            regpostal.clear();
        } else {
            postalcheck.setTextFill(Color.GREEN);
            postalcheck.setText("Correct!");
            postal = true;
        }
        if ((regcity.getText().isEmpty()) || (containsNumber(regsurname.getText())==true)) {
            citycheck.setTextFill(Color.RED);
            citycheck.setText("Incorrect!");
            regcity.clear();
        } else {
            citycheck.setTextFill(Color.GREEN);
            citycheck.setText("Correct!");
            city = true;
        }
        if (regphone.getText().length()!=9) {
            phonecheck.setTextFill(Color.RED);
            phonecheck.setText("Incorrect!");
            regphone.clear();
        } else {
            phonecheck.setTextFill(Color.GREEN);
            phonecheck.setText("Correct!");
            phone = true;
        }
        if (regemail.getText().isEmpty()) {
            mailcheck.setTextFill(Color.RED);
            mailcheck.setText("Incorrect!");
            regemail.clear();
        } else {
            mailcheck.setTextFill(Color.GREEN);
            mailcheck.setText("Correct!");
            mail = true;
        }
        if ((reguserid.getText().isEmpty()) || (containsNumber(regsurname.getText()))) {
            useridcheck.setTextFill(Color.RED);
            useridcheck.setText("Incorrect!");
            reguserid.clear();
        } else {
            userid = true;
            useridcheck.setTextFill(Color.GREEN);
            useridcheck.setText("Correct!");
        }
        if (regusername.getText().isEmpty()) {
            usernamecheck.setTextFill(Color.RED);
            usernamecheck.setText("Incorrect!");
            regusername.clear();
        } else {
            usernamecheck.setTextFill(Color.GREEN);
            usernamecheck.setText("Correct!");
            username = true;
        }
        if (((regpass.getText()).isEmpty()) && ((regpass2.getText()).isEmpty())) {
            passwordscheck.setTextFill(Color.RED);
            passwordscheck.setText("Incorrect!");
            regpass.clear();
            regpass2.clear();
        } else if (!(regpass.getText().equals(regpass2.getText()))) {
            passwordscheck.setTextFill(Color.RED);
            passwordscheck.setText("Incorrect Passwords!");
            regpass.clear();
            regpass2.clear();
        } else {
            passwordscheck.setTextFill(Color.GREEN);
            passwordscheck.setText("Correct!");
            pass = true;
        }

            if ((name == true) && (surname && true) && (id == true) && (address == true) && (postal == true) && (city == true) && (phone == true) && (mail == true) && (userid == true) && (username == true) && (pass == true)) {
                Socket regSocket = new Socket("localhost", 9999);
                ObjectOutputStream send = new ObjectOutputStream(regSocket.getOutputStream());
                send.flush();
                window = 1;
                send.writeObject(window);
                send.flush();
                send.writeObject(regname.getText().toLowerCase());
                send.flush();
                send.writeObject(regsurname.getText());
                send.flush();
                send.writeObject(regcard.getText());
                send.flush();
                send.writeObject(regaddress.getText());
                send.flush();
                send.writeObject(regpostal.getText());
                send.flush();
                send.writeObject(regcity.getText());
                send.flush();
                send.writeObject(regphone.getText());
                send.flush();
                send.writeObject(regemail.getText());
                send.flush();
                send.writeObject(reguserid.getText());
                send.flush();
                send.writeObject(regusername.getText());
                send.flush();
                send.writeObject(regpass.getText());
                send.flush();
                regname.clear();
                regsurname.clear();
                regcard.clear();
                regaddress.clear();
                regpostal.clear();
                regcity.clear();
                regphone.clear();
                regemail.clear();
                reguserid.clear();
                regusername.clear();
                regpass.clear();
                regpass2.clear();
                successcheck.setTextFill(Color.GREEN);
                successcheck.setText("USER ADDED!");
            }
        }


    /***
     * Metoda ustawiajaca pola w formularzu edycji na aktualne dane uzytkownika.
     * @param name imie uzytkownika
     * @param surname nazwisko uzytkownika
     * @param cardid ID Karty podrozujacego
     * @param city miasto uzytkownika
     * @param postalcode kod pocztowy uzytkownika
     * @param address adres uzytkownika
     * @param phone nr telefonu uzytkownika
     * @param email email uzytkownika
     * @param dob  UserID uzytkownika
     * @param username nazwa uzytkownika
     */
    public void editUser(String name, String surname, String cardid, String city, String postalcode, String address, String phone, String email, String dob, String username){
        signUpText.setText("Edit account!");
        buttonEdit.setVisible(true);
        regname.setText(name);
        regsurname.setText(surname);
        regcard.setText(cardid);
        regaddress.setText(address);
        regpostal.setText(postalcode);
        regcity.setText(city);
        regphone.setText(phone);
        regemail.setText(email);
        reguserid.setText(dob);
        regusername.setText(username);
        regusername.setDisable(true);
        buttonuserregister.setVisible(false);
        regpass.setDisable(true);
        regpass2.setDisable(true);
    }

    @FXML
    /***
     * Metoda sluzaca do edycji uzytkownika.
     * Sprawdza czy dane wprowadzone w formularzu sa poprawne.
     * Jesli tak wysyla je na serwer aby dodac je do bazy oraz wyswietla komunikat o poprawnej edycji danych uzytkownika.
     * Jesli nie wyswietlane sa stosowne komunikaty.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatku IO
     */
    public void confirmEdit(MouseEvent event) throws IOException{

        boolean validity = false;
        boolean name = false;
        boolean surname = false;
        boolean id = false;
        boolean address = false;
        boolean postal = false;
        boolean city = false;
        boolean phone = false;
        boolean mail = false;
        boolean userid = false;

        if ((regname.getText().isEmpty()) || (containsNumber(regname.getText())==true)) {
            namecheck.setTextFill(Color.RED);
            namecheck.setText("Incorrect!");
            regname.clear();
        } else {
            namecheck.setTextFill(Color.GREEN);
            namecheck.setText("Correct!");
            name = true;
        }
        if ((regsurname.getText().isEmpty())  || (containsNumber(regsurname.getText())==true)) {
            surnamecheck.setTextFill(Color.RED);
            surnamecheck.setText("Incorrect!");
            regsurname.clear();
        } else {
            surnamecheck.setTextFill(Color.GREEN);
            surnamecheck.setText("Correct!");
            surname = true;
        }
        if (regcard.getText().isEmpty()) {
            idcheck.setTextFill(Color.RED);
            idcheck.setText("Incorrect!");
            regcard.clear();
        } else {
            idcheck.setTextFill(Color.GREEN);
            idcheck.setText("Correct!");
            id = true;
        }
        if (regaddress.getText().isEmpty()) {
            addresscheck.setTextFill(Color.RED);
            addresscheck.setText("Incorrect!");
            regaddress.clear();
        } else {
            addresscheck.setTextFill(Color.GREEN);
            addresscheck.setText("Correct!");
            address = true;
        }
        if ((regpostal.getText().isEmpty()) || (regpostal.getText().length() >6)) {
            postalcheck.setTextFill(Color.RED);
            postalcheck.setText("Incorrect!");
            regpostal.clear();
        } else {
            postalcheck.setTextFill(Color.GREEN);
            postalcheck.setText("Correct!");
            postal = true;
        }
        if ((regcity.getText().isEmpty()) || (containsNumber(regsurname.getText())==true)) {
            citycheck.setTextFill(Color.RED);
            citycheck.setText("Incorrect!");
            regcity.clear();
        } else {
            citycheck.setTextFill(Color.GREEN);
            citycheck.setText("Correct!");
            city = true;
        }
        if (regphone.getText().length()!=9) {
            phonecheck.setTextFill(Color.RED);
            phonecheck.setText("Incorrect!");
            regphone.clear();
        } else {
            phonecheck.setTextFill(Color.GREEN);
            phonecheck.setText("Correct!");
            phone = true;
        }
        if (regemail.getText().isEmpty()) {
            mailcheck.setTextFill(Color.RED);
            mailcheck.setText("Incorrect!");
            regemail.clear();
        } else {
            mailcheck.setTextFill(Color.GREEN);
            mailcheck.setText("Correct!");
            mail = true;
        }
        if ((reguserid.getText().isEmpty()) || (containsNumber(regsurname.getText()))) {
            useridcheck.setTextFill(Color.RED);
            useridcheck.setText("Incorrect!");
            reguserid.clear();
        } else {
            userid = true;
            useridcheck.setTextFill(Color.GREEN);
            useridcheck.setText("Correct!");
        }

        if((name == true) && (surname && true) && (id==true) && (address==true) && (postal==true) && (city==true) && (phone==true) && (mail==true) && (userid==true)){

            Socket regSocket = new Socket("localhost", 9999);
            window = 7;
            ObjectOutputStream send = new ObjectOutputStream(regSocket.getOutputStream());
            send.flush();

            send.writeObject(window);
            send.flush();
            send.writeObject(regname.getText().toLowerCase());
            send.flush();
            send.writeObject(regsurname.getText());
            send.flush();
            send.writeObject(regcard.getText());
            send.flush();
            send.writeObject(regaddress.getText());
            send.flush();
            send.writeObject(regpostal.getText());
            send.flush();
            send.writeObject(regcity.getText());
            send.flush();
            send.writeObject(regphone.getText());
            send.flush();
            send.writeObject(regemail.getText());
            send.flush();
            send.writeObject(reguserid.getText());
            send.flush();
            send.writeObject(regusername.getText());
            send.flush();
            regname.clear();regsurname.clear();regcard.clear();regaddress.clear();regpostal.clear();regcity.clear();
            regphone.clear();regemail.clear();reguserid.clear();
            successcheck.setTextFill(Color.GREEN);
            successcheck.setText("USER EDITED!");
        }
    }
}