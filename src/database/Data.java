package database;

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
}