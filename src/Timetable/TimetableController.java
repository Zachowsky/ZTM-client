package Timetable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafxapplication1.ZTM;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Klasa TimeTable implementujaca Initializable w ktorej znajduja sie metody, pola i obiekty
 * potrzebne do oblugi rozkladu jazdy.
 */
public class TimetableController implements Initializable {

    @FXML
    /** * Pole typu int odpowiadajace ze numer okna */
    public int window;
    /** * Pole typu HBox odpowiadajace ze rozmair i polozenie okna */
    public HBox parent;


    @FXML
    /** * Pole typu TableView okreslajace tabele rozkladu jazdy*/
    public TableView<LineController> timetable;
    @FXML
    /** * Pole typu TableColumn okreslajace kolumne czasow przyjazdu linii*/
    private TableColumn<LineController, String> time;
    @FXML
    /** * Pole typu TableColumn okreslajace kolumne nazw przystankow linii*/
    private TableColumn<LineController, String> bus_stop;
    @FXML
    /** * Pole typu Button bedace przyciskiem edycji linii. */
    private Button editButton;
    @FXML
    /** * Pole typu Button bedace przyciskiem dodania przystanku do linii. */
    private Button addNewButton;
    @FXML
    /** * Pole typu TextField bedace polem do wpisania czasu przybycia ktory ma byc dodany do lini. */
    private TextField addTime;
    @FXML
    /** * Pole typu TextField bedace polem do wpisania nazwy przystanka ktory ma byc dodany do lini. */
    private TextField addBusStop;
    @FXML
    /** * Pole typu Label bedace polem wyswietlajacym czas przybycia lini. */
    private Label labelTime;
    @FXML
    /** * Pole typu Label bedace polem wyswietlajacym przystanek na ktory jezdzi linia. */
    private Label labelBus;
    /** * Pole typu Int bedace polem okreslajacym numer lini. */
    private int line_number = 0;
    /** * Pole typu Boolean przyjmujace wartosc false jesli uzytkownik nie jest administratorem oraz true jesli nim jest. */
    public boolean isAdmin = false;
    /** * Lista przechowujaca wszystkie czasy przyjazdu.*/
    private ArrayList<String> timee;
    /** * Lista przechowujaca wszystkie nazwy przystankow.*/
    private ArrayList<String> busstop;
    /** * Lista pomocnicza */
    private ArrayList<LineController> List1 = new ArrayList<LineController>();
    /** * Lista pomocnicza typu ObservableList */
    private ObservableList<LineController> List2;
    /** * */
    ObservableList<String> linesToChoose = FXCollections.observableArrayList();

    /** * Pole typu double okreslajace pozycje x okna */
    public double xOffSet = 0;
    /** * Pole typu double okreslajace pozycje y okna */
    public double yOffSet = 0;

    @FXML
    /***
     * Metoda sluzoca do oblugi przycisku minimalizacji aplikacji.
     * @param event parametr do obslugi klikniecia myszka
     */
    public void minimize_btn(javafx.scene.input.MouseEvent event) {
        ZTM.stage.setIconified(true);
    }

    @FXML
    /***
     * Metoda sluzoca do oblugi przycisku zamykania aplikacji.
     * @param event parametr do obslugi klikniecia myszka
     */
    public void exit_btn(javafx.scene.input.MouseEvent event) {
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

    /***
     * Metoda uruchamiajace sie jesli uzytkownik jest administratorem. Zmienia ona układ sceny TimeTable
     * na taki przeznaczony tylko dla administratorow.
     */
    public void ifIsAdmin(){
        isAdmin = true;
        editButton.setVisible(true);
        addNewButton.setVisible(true);
        addTime.setVisible(true);
        addBusStop.setVisible(true);
        labelTime.setVisible(true);
        labelBus.setVisible(true);
    }


    @FXML
    /***
     * Metoda sluzaca do oblugi przycisku sluzacego do powrotu na ekran startowy.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     */
    public void home_btn(javafx.scene.input.MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/javafxapplication1/ZTM.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }


    @FXML
    /***
     * Metoda sluzaca do oblugi wyswietlania informacji o danej linii
     * po kliknieciu przycisku z numerem linii.
     * @param line_number numer linii ktora chcemy wyswietlic
     * @throws IOException rzucanie wyjatkow IO
     * @throws ClassNotFoundException rzucanie wyjatkow ClassNotFound
     */
    public void checkLine(int line_number) throws IOException, ClassNotFoundException {

        Socket soket = new Socket("localhost", 9999);

        ObjectOutputStream send = new ObjectOutputStream(soket.getOutputStream());
        send.flush();
        ObjectInput receive = new ObjectInputStream(soket.getInputStream());

        window = 5;
        send.writeObject(window);
        send.flush();
        send.writeObject(line_number);
        send.flush();

        timee = (ArrayList<String>) receive.readObject();
        busstop = (ArrayList<String>) receive.readObject();

        for(int i = 0; i<timee.size(); i++) {
            List1.add(new LineController(timee.get(i), busstop.get(i)));
        }
        List2 = FXCollections.observableArrayList(List1);


        time.setCellValueFactory(new PropertyValueFactory<LineController, String>("time"));
        bus_stop.setCellValueFactory(new PropertyValueFactory<LineController, String >("bus_stop"));

        timetable.getColumns().clear();
        timetable.getColumns().addAll(bus_stop, time);
        timetable.setItems(List2);
        timee.clear();
        busstop.clear();
        List1.clear();
    }

    @FXML
    /***
     * Metoda sluzaca do edycji podanej linii.
     * @param line_number numer linii ktora administrator chce edytowac
     * @throws IOException rzucanie wyjatkow IO
     * @throws ClassNotFoundException rzucanie wyjatkow ClassNotFound
     */
    public void editLine(int line_number) throws IOException, ClassNotFoundException {

        Socket soket = new Socket("localhost", 9999);

        ObjectOutputStream send = new ObjectOutputStream(soket.getOutputStream());
        send.flush();
        ObjectInput receive = new ObjectInputStream(soket.getInputStream());

        window = 5;
        send.writeObject(window);
        send.flush();
        send.writeObject(line_number);
        send.flush();

        timee = (ArrayList<String>) receive.readObject();
        busstop = (ArrayList<String>) receive.readObject();

        for(int i = 0; i<timee.size(); i++) {
            List1.add(new LineController(timee.get(i), busstop.get(i)));
        }
        List2 = FXCollections.observableArrayList(List1);


        time.setCellValueFactory(new PropertyValueFactory<LineController, String>("time"));
        bus_stop.setCellValueFactory(new PropertyValueFactory<LineController, String >("bus_stop"));

        time.setCellFactory(TextFieldTableCell.forTableColumn());
        time.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setTime(e.getNewValue());
         });

        bus_stop.setCellFactory(TextFieldTableCell.forTableColumn());
        bus_stop.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setBus_stop(e.getNewValue());
        });

        timetable.setEditable(true);


        timetable.getColumns().clear();
        timetable.getColumns().addAll(bus_stop, time);
        timetable.setItems(List2);
        timee.clear();
        busstop.clear();
        List1.clear();
    }

    /***
     * Metoda dodajaca przystanek i godzine do podanej przez administratora linii
     * po kliknieciu przycisku ADD.
     */
    public void addRecord(){
        LineController linecon = new LineController(addTime.getText(), addBusStop.getText());
        List2.add(linecon);
    }

    @FXML
    /***
     * Metoda zapisujaca zmiany wprowadzone przez administratora w rozkladzie jazdy
     * po kliknieciu przycisku SAVE.
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     */
    public void saveEditedLine(MouseEvent event) throws  IOException{

        Socket soket = new Socket("localhost", 9999);

        ObjectOutputStream send = new ObjectOutputStream(soket.getOutputStream());
        send.flush();
        ObjectInput receive = new ObjectInputStream(soket.getInputStream());

        window = 8;
        send.writeObject(window);
        send.flush();
        send.writeObject(line_number);
        send.flush();


        for(int i = 0; i<List2.size(); i++) {
            timee.add(time.getCellData(i));
            busstop.add(bus_stop.getCellData(i));
        }

        send.writeObject(timee);
        send.flush();
        send.writeObject(busstop);
        send.flush();

        timee.clear();
        busstop.clear();
        List1.clear();
    }

    @FXML
    /***
     * Metoda ustawiajaca zmienna line_number na 1 oraz uruchamiajaca funckje checkLine
     * po nacisnieciu przycisku przez uzytkownika
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     * @throws ClassNotFoundException rzucanie wyjatkow ClassNotFound
     */
    public void setLine1(MouseEvent event) throws IOException, ClassNotFoundException {
        line_number = 1;
        if(!(isAdmin)) {
            checkLine(this.line_number);
        }
        else{
            editLine(this.line_number);
        }
    }

    @FXML
    /***
     * Metoda ustawiajaca zmienna line_number na 2 oraz uruchamiajaca funckje checkLine
     * po nacisnieciu przycisku przez uzytkownika
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     * @throws ClassNotFoundException rzucanie wyjatkow ClassNotFound
     */
    public void setLine2(MouseEvent event) throws IOException, ClassNotFoundException {
        line_number = 2;
        if(!(isAdmin)) {
            checkLine(this.line_number);
        }
        else{
            editLine(this.line_number);
        }
    }

    @FXML
    /***
     * Metoda ustawiajaca zmienna line_number na 3 oraz uruchamiajaca funckje checkLine
     * po nacisnieciu przycisku przez uzytkownika
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     * @throws ClassNotFoundException rzucanie wyjatkow ClassNotFound
     */
    public void setLine3(MouseEvent event) throws IOException, ClassNotFoundException {
        line_number = 3;
        if(!(isAdmin)) {
            checkLine(this.line_number);
        }
        else{
            editLine(this.line_number);
        }
    }

    @FXML
    /***
     * Metoda ustawiajaca zmienna line_number na 4 oraz uruchamiajaca funckje checkLine
     * po nacisnieciu przycisku przez uzytkownika
     * @param event parametr do obslugi klikniecia myszka
     * @throws IOException rzucanie wyjatkow IO
     * @throws ClassNotFoundException rzucanie wyjatkow ClassNotFound
     */
    public void setLine4(MouseEvent event) throws IOException, ClassNotFoundException {
        line_number = 4;
        if(!(isAdmin)) {
            checkLine(this.line_number);
        }
        else{
            editLine(this.line_number);
        }
    }

}
