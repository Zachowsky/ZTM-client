package User_panel;

import User_register.User_registerController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 * obslugujace panel uzytkownika.
 */
public class User_panelController implements Initializable {

    @FXML
    /** * Pole typu Label wyswietlajace ID*/
    public Label idd;
    /** * Pole typu Label wyswietlajace imie*/
    public Label name;
    /** * Pole typu Label wyswietlajace nazwisko*/
    public Label surname;
    /** * Pole typu Label wyswietlajace ID Karty podrozujacego*/
    public Label cardid;
    /** * Pole typu Label wyswietlajace adres*/
    public Label address;
    /** * Pole typu Label wyswietlajace nr telefonu*/
    public Label phone;
    /** * Pole typu Label wyswietlajace email*/
    public Label email;
    /** * Pole typu Label wyswietlajace UserID*/
    public Label dob;
    /** * Pole typu Label wyswietlajace nazwe uzytkownika*/
    public Label username;
    /** * Pole typu Label wyswietlajace stan konta*/
    public Label accountbalance;
    /** * Pole typu Label wyswietlajace nazwe sceny*/
    public Label label;
    /** * Pole typu HBox odpowiadajace za rozmiar i polozenie sceny*/
    public HBox parent;
    /** * Pole typu JFXTextField odpowiadajace ze wpisywanie kwoty doladowania*/
    public JFXTextField usernamepanelrecharge;
    /** * Pole typu JFXTextField odpowiadajace ze wpisywanie nowego hasla*/
    public JFXPasswordField usernamepanelnewpass;
    /** * Pole typu JFXTextField odpowiadajace ze wpisywanie drugi raz nowego hasla*/
    public JFXPasswordField usernamepanelconfnewpass;
    /** * Pole typu Label wyswietlajace komunikat o poprawnej/zlej zmianie hasla */
    public Label passchanged;

    /** * Pole typu String okreslajace imie po edycji*/
    public String nameEdit;
    /** * Pole typu String okreslajace nazwisko po edycji*/
    public String surnameEdit;
    /** * Pole typu String okreslajace ID Karty podrozujacego po edycji*/
    public String cardidEdit;
    /** * Pole typu String okreslajace miasto po edycji*/
    public String cityEdit;
    /** * Pole typu String okreslajace kod pocztowy po edycji*/
    public String postalEdit;
    /** * Pole typu String okreslajace adres po edycji*/
    public String addressEdit;
    /** * Pole typu String okreslajace nr telefonu po edycji*/
    public String phoneEdit;
    /** * Pole typu String okreslajace email po edycji*/
    public String emailEdit;
    /** * Pole typu String okreslajace UserID po edycji*/
    public String dobEdit;
    /** * Pole typu String okreslajace nazwe uzytkownika po edycji*/
    public String usernameEdit;
    /** * Pole typu String okreslajace stan konta po doladowaniu*/
    public String accbal;

    /** * Pole typu Int okreslajace numer okna */
    public int window;
    /** * Pole typu double okreslajace pozycje x okna */
    public double xOffSet = 0;
    /** * Pole typu double okreslajace pozycje y okna */
    public double yOffSet = 0;

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
     * Metoda ustawiajaca na polach typu Label dane osobiste odpowiedniego uzytkownika.
     * @param id id klienta
     * @param nameee imie klienta
     * @param surnameee nazwisko klienta
     * @param cardidee ID Karty podrozujacego klienta
     * @param addressee adres klienta
     * @param phoneee nr telefonu klienta
     * @param emailee email klienta
     * @param usernameee nazwa uzytkownika klienta
     * @param dobee UserID klienta
     * @param accountbalanceee stan konta klienta
     * @param city miasto klienta
     * @param postal kod pocztowy klienta
     */
    public void myFunction(String id, String nameee, String surnameee, String cardidee,
                           String addressee, String phoneee, String emailee, String usernameee, String dobee, String accountbalanceee, String city, String postal) {

        idd.setText(id);
        name.setText(nameee.toString().substring(0, 1).toUpperCase() + nameee.substring(1));
        surname.setText(surnameee.toString().substring(0, 1).toUpperCase() + surnameee.substring(1));
        cardid.setText(cardidee);
        address.setText(addressee + ", " +postal+ " " +city);
        phone.setText(phoneee);
        email.setText(emailee);
        username.setText(usernameee);
        dob.setText(dobee);
        accountbalance.setText(accountbalanceee);
    }

    /***
     * Metoda sluzaca do doladowania konta z poziomu panelu uzytkownika.
     * Wczytuje ona kwote z przednialu 10-200 zl podana przez uzytkownika
     * i doladowuje jego konto.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatku IO
     * @throws ClassNotFoundException rzucanie wyjatku ClassNotFound
     */
    public void reCharge(MouseEvent event) throws IOException, ClassNotFoundException {


        if(usernamepanelrecharge.getText().length()>0 && usernamepanelrecharge.getText().matches("\\d{2,}") && Integer.parseInt(usernamepanelrecharge.getText())>9 && Integer.parseInt(usernamepanelrecharge.getText())<201){
            passchanged.setText("");
            Socket soket = new Socket("localhost", 9999);

            ObjectOutputStream send = new ObjectOutputStream(soket.getOutputStream());
            send.flush();
            ObjectInput receive = new ObjectInputStream(soket.getInputStream());
            window = 3;
            send.writeObject(window);
            send.writeObject(username.getText());
            send.flush();
            send.writeObject(usernamepanelrecharge.getText());
            send.flush();
            accbal = (String) receive.readObject();
            accountbalance.setText(accbal);
        }
        else{
            passchanged.setTextFill(Color.TOMATO);
            passchanged.setText("Wrong Value!");
        }

    }

    /***
     * Metoda pozwalajaca na zmiane hasla uzytkownikowi z panelu uzytkownika.
     * Sprawdza czy hasla sa takie same i jesli tak do wysyla nowe dane do serwera.
     * Jesli nie, wyswietla odpowiedni komunikat.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatku IO
     */
    public void changePassword(MouseEvent event) throws IOException {

        if(usernamepanelnewpass.getText().length()>1 && usernamepanelconfnewpass.getText().length()>1 && usernamepanelnewpass.getText().equals(usernamepanelconfnewpass.getText())) {
            Socket soket = new Socket("localhost", 9999);

            ObjectOutputStream send = new ObjectOutputStream(soket.getOutputStream());
            send.flush();
            window = 4;

            send.writeObject(window);
            send.writeObject(username.getText());
            send.flush();
            send.writeObject(usernamepanelnewpass.getText());
            send.flush();

            passchanged.setTextFill(Color.GREEN);
            passchanged.setText("Password changed!");
            usernamepanelnewpass.clear();
            usernamepanelconfnewpass.clear();
        }
        else {
            passchanged.setTextFill(Color.TOMATO);
            passchanged.setText("PASSWORDS ARE NOT EQUAL OR FIELDS ARE EMPTY !!");
            usernamepanelnewpass.clear();
            usernamepanelconfnewpass.clear();
        }
    }

    @FXML
    /***
     * Metoda wyswietlajaca dane w panelu do edycji danych, aby uzytkownik wiedzial
     * jakie sa aktualnie jego dane i co dokladnie chce zmienic.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatku IO
     * @throws ClassNotFoundException rzucanie wyjatku ClassNotFound
     */
    public void editAccount(MouseEvent event) throws IOException, ClassNotFoundException {

        window = 6;

        Socket soket = new Socket("localhost", 9999);

        ObjectOutputStream send = new ObjectOutputStream(soket.getOutputStream());
        send.flush();
        ObjectInput receive = new ObjectInputStream(soket.getInputStream());

        send.writeObject(window);
        send.writeObject(username.getText());
        send.writeObject(cardid.getText());


        nameEdit = (String) receive.readObject();
        surnameEdit = (String) receive.readObject();
        cardidEdit = (String) receive.readObject();
        cityEdit = (String) receive.readObject();
        postalEdit = (String) receive.readObject();
        addressEdit = (String) receive.readObject();
        phoneEdit = (String) receive.readObject();
        emailEdit = (String) receive.readObject();
        dobEdit = (String) receive.readObject();
        usernameEdit = (String) receive.readObject();


        goToEditPanel(event);
    }

    /***
     *  Metoda otwierajaca scene z edycja uzytkownika, uruchamiajaca metode editUser ktora
       wczytuje do pol tekstowych dane biezacego uzytkownika.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatku IO
     */
    public void goToEditPanel(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/User_register/User_register.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);

        User_registerController usrReg = loader.getController();
        usrReg.editUser(nameEdit, surnameEdit, cardidEdit, cityEdit, postalEdit, addressEdit, phoneEdit, emailEdit, dobEdit, usernameEdit);

        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window3.setScene(tableViewScene);
        window3.show();
    }
}
