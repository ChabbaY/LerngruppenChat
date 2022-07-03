package database;

import java.util.Objects;

public class Data {
    private final String mitglied, frage, antwort;
    private final int frage_nr;

    public Data(String mitglied, String frage, int frage_nr, String antwort) {
        this.mitglied = mitglied;
        this.frage = frage;
        this.frage_nr = frage_nr;
        this.antwort = antwort;
    }

    public String getMitglied() {
        return mitglied;
    }
    public String getFrage() {
        return frage;
    }
    public int getFrageNr() {
        return frage_nr;
    }
    public String getAntwort() {
        return antwort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return frage_nr == data.frage_nr && Objects.equals(mitglied, data.mitglied) && Objects.equals(frage, data.frage) && Objects.equals(antwort, data.antwort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mitglied, frage, antwort, frage_nr);
    }
}