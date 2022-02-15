package User_login;

import Timetable.TimetableController;
import User_panel.User_panelController;
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
 *  Klasa implementujaca Initializable przechowujaca metody, pola i obiekty
 *  odpowiadajace za obsluge logowania uztytkownikow.
 */

public class User_loginController implements Initializable {

    @FXML
    /** * Pole typu HBox odpowiadajace za wielkosc i polozenie okna. */
    public HBox parent;
    /** * Pole typu JFXTextField odpowiadajace za wpisywanie loginu */
    public JFXTextField user_login_field;
    /** * Pole typu JFXTextField odpowiadajace za wpisywanie hasla */
    public JFXPasswordField user_pass_field;
    /** * Pole typu Button odpowiadajace za przycisk logowania */
    public Button btnsignin;
    /** * Pole typu Label odpowiadajace za wyswietlanie komunikatow logowania */
    public Label lblErrors;

    /** * Pole typu double okreslajace pozycje x okna */
    public double xOffSet = 0;
    /** * Pole typu double okreslajace pozycje y okna */
    public double yOffSet = 0;
    /** * Pole typu int okreslajace numer okna */
    public int window = 2;

    /** * Pole typu String okreslajace imie*/
    public String name;
    /** * Pole typu String okreslajace nazwisko*/
    public String surname;
    /** * Pole typu Integer okreslajace ID*/
    public Integer id;
    /** * Pole typu String okreslajace ID Karty podrozujacego*/
    public String cardid;
    /** * Pole typu String okreslajace miasto*/
    public String city;
    /** * Pole typu String okreslajace kod pocztowy*/
    public String postal;
    /** * Pole typu String okreslajace adres*/
    public String address;
    /** * Pole typu String okreslajace nr telefonu*/
    public String phone;
    /** * Pole typu String okreslajace email*/
    public String email;
    /** * Pole typu String okreslajace typ konta*/
    public String accounttype;
    /** * Pole typu String okreslajace nazwe uzytkownika*/
    public String username;
    /** * Pole typu String okreslajace UserID*/
    public String dob;
    /** * Pole typu String okreslajace stan konta*/
    public String accountbalance;


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
     * @throws IOException rzucanie wyjatkow IO
     */
    public void home_btn(MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/javafxapplication1/ZTM.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }


    @FXML
    /***
     * Metoda sluzaca do logowania sie do aplikacji zarowno uzytkownika jak i administratora.
     * Wysyla ona wprowadzone dane do serwera a nastepnie otrzymuje wiadomosc czy sa poprawne.
     * Jesli tak, otwiera okno logowania/panel administracyjny z odpowiednimi danymi.
     * Jesli nie, pokazuje odpowiedni komunikat.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     * @throws ClassNotFoundException rzucanie wyjatkow ClassNotFound
     */
    public void logIn(MouseEvent event) throws IOException, ClassNotFoundException {

        Socket soket = new Socket("localhost", 9999);

        boolean logowanie;
        String type1 = "default_user";
        String type2 = "admin";

        ObjectOutputStream send = new ObjectOutputStream(soket.getOutputStream());
        send.flush();
        ObjectInput receive = new ObjectInputStream(soket.getInputStream());
        send.writeObject(window);

        send.writeObject(user_login_field.getText().toLowerCase());
        send.flush();

        send.writeObject(user_pass_field.getText());
        send.flush();

        logowanie = (boolean) receive.readObject();
        id = (Integer) receive.readObject();
        name = (String) receive.readObject();
        surname = (String) receive.readObject();
        cardid = (String) receive.readObject();
        city = (String) receive.readObject();
        postal = (String) receive.readObject();
        address = (String) receive.readObject();
        phone = (String) receive.readObject();
        email = (String) receive.readObject();
        accounttype = (String) receive.readObject();
        username = (String) receive.readObject();
        dob = (String) receive.readObject();
        accountbalance = (String) receive.readObject();


        if(logowanie){
            if(type1.equals(accounttype)){
                goToUserPanel(event);
            }else{
                goToAdminPanel(event);
            }
        }
        else{
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Enter correct username or password!!");
        }
    }

    @FXML
    /***
     * Metoda dzieki ktorej po poprawnie wprowadzonych danych logowania przez uzytkownika
     * przenosi go na odpowiedni ekran z jego danymi.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     */
    public void goToUserPanel(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/User_panel/User_panel.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);

        User_panelController usrControl = loader.getController();
        usrControl.myFunction(id.toString(), name, surname, cardid, address, phone, email, username, dob, accountbalance, city, postal);

        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window3.setScene(tableViewScene);
        window3.show();
    }

    @FXML
    /***
     * Metoda dzieki ktorej po poprawnie wprowadzonych danych logowania przez administratora
     * przenosi go na ekran panelu administracyjnego.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     */
    public void goToAdminPanel(MouseEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Timetable/Timetable.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);

        TimetableController timetbl = loader.getController();
        timetbl.ifIsAdmin();

        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window3.setScene(tableViewScene);
        window3.show();
    }

    @FXML
    /***
     * Metoda obslugujaca przycisk sluzacy do rejestracji nowego uzytkownika.
     * Po kliknieciu przycisku SIGN UP przenosi uzytkownika na ekran rejestracji.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     */
    public void user_register_btn(MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/User_register/User_register.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window2.setScene(tableViewScene);
        window2.show();
    }
}
