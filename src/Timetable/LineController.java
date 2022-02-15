
package Timetable;
import javafx.collections.ObservableList;

import java.io.Serializable;

/**
 * Klasa LineController implementujaca Serializable w ktorej znajduja sie metody, pola i obiekty
 * potrzebne do oblugi określonych linii rozkladu.
 */
public class LineController implements Serializable {

    private static final long serialVersionUID = 1L;
    /** * Pola odpowiadajace za czas oraz nazwe przystanku danej linii.  */
    public String time, bus_stop;

    /***
     * Metoda LineController
     * @param time czas przyjazdu linii
     * @param bus_stop nazwa przystanku
     */
    public LineController(String time, String bus_stop) {
        this.time = new String(time);
        this.bus_stop = new String(bus_stop);
    }

    /***
     * Metoda Metoda zapisujaca informacje o linii
     * @param line Lista obserwowalna
     */
    public static void writeObject(ObservableList<LineController> line) {
    }

    /***
     * Metoda pobierajaca czas przyjazdu
     * @return zwraca czas przyjazdu
     */
    public String getTime() {
        return time;
    }

    /***
     * Metoda ustawiajaca czas przyjazdu
     * @param time czas przyjazdu
     */
    public void setTime(String time) {
        this.time = time;
    }

    /***
     * Metoda pobierajaca nazwe przystanka
     * @return zwraca nazwe przystanka
     */
    public String getBus_stop() {
        return bus_stop;
    }

    /***
     * Metoda ustawiajaca nazwe przystanka
     * @param bus_stop nazwa przystanka
     */
    public void setBus_stop(String bus_stop) {
        this.bus_stop = bus_stop;
    }

}
