
package javafxapplication1;

import com.gluonhq.charm.glisten.control.Icon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa implementujaca Initializable, ktora odpowiada za obsluge przyciskow ekranu glownego.
 * Przechowuje metody,pola oraz obiekty potrzebne do obslugi ekranu home.
 */
public class ZTMController implements Initializable {

    @FXML
    /** * Pole typu Label okreslajace nazwe ekranu glownego. */
    public Label label;
    /** * Pole typu HBox okreslajace pozycje i wielkosc ekranu glownego. */
    public HBox parent;
    /** * Pole typu Icon okreslajace ikone aplikacji. */
    public Icon Icon;

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

    @Override
    /***
     * Metoda sluzaca do inicjalizacji metody dzieki ktorej
     * scena bedzie mozliwa do przesuwania po ekranie.
     * @param location lokalizacja używana do rozwiązywania ścieżek względnych dla obiektu głównego lub null, jeśli lokalizacja nie jest znana.
     * @param resources zasoby używane do lokalizowania obiektu głównego lub null, jeśli obiekt główny nie został zlokalizowany.
     */
    public void initialize(URL url, ResourceBundle rb) {
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
     * Metoda oblugujaca przycisk logowania uzytkownika. Po kliknieciu przycisku otwiera odpowiednia scene
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     */
    public void user_login_btn(MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/User_login/User_login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    /***
     * Metoda obslugujaca przycisk do sprawdzania rozkladu jazdy. Po kliknieciu przycisku otwiera odpowiednia scene.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     */
    public void goToTimetable(MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Timetable/Timetable.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage timeTable = (Stage) ((Node) event.getSource()).getScene().getWindow();
        timeTable.setScene(tableViewScene);
        timeTable.show();
    }
}
