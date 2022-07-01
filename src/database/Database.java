package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Database {
    private final String USER, PASS;
    private final Connection conn;

    public Database() {
        String user = null, pass = null;
        try (BufferedReader br = new BufferedReader(new FileReader("res/credentials.txt"))) {
            user = br.readLine();
            pass = br.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei");
            e.printStackTrace();
        }
        USER = user;
        PASS = pass;
        conn = getConnection();
    }
    public Database(String user, String pass) {
        USER = user;
        PASS = pass;
        conn = getConnection();
    }

    private Connection getConnection() {
        try {
            String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
            String DB_URL = "jdbc:mariadb://chabbay.de/";
            return DriverManager.getConnection(DB_URL + "lerngruppe", USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Treiber nicht gefunden");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Fehler bei Verbindungsaufbau");
            e.printStackTrace();
        }
        return null;
    }

    private int getMitgliedID(String name) {
        if (conn == null) return -1;
        String sql = "SELECT id FROM mitglied WHERE name=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet result = pstmt.executeQuery();
            result.next();
            return result.getInt("id");
        } catch (SQLException e) {
            System.out.println("Datenbankfehler");
            e.printStackTrace();
        }
        return -1;
    }
    private int getFrageID(String frage) {
        if (conn == null) return -1;
        String sql = "SELECT id FROM frage WHERE frage=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, frage);
            ResultSet result = pstmt.executeQuery();
            result.next();
            return result.getInt("id");
        } catch (SQLException e) {
            System.out.println("Datenbankfehler");
            e.printStackTrace();
        }
        return -1;
    }

    private void setMitglied(String name) {
        if (conn == null) return;
        String sql = "INSERT INTO mitglied (name) VALUES (?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Datenbankfehler");
            e.printStackTrace();
        }
    }
    private void setFrage(String frage) {
        if (conn == null) return;
        String sql = "INSERT INTO frage (frage) VALUES (?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, frage);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Datenbankfehler");
            e.printStackTrace();
        }
    }

    public Data[] getDetails() {
        if (conn == null) return null;
        Data[] data = new Data[countDetails()];
        String sql = "SELECT name, frage, frage_nr, antwort FROM mitglied_frage LEFT JOIN mitglied ON (mitglied_id=mitglied.id) LEFT JOIN frage ON (frage_id=frage.id) ORDER BY frage";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            int index = 0;
            while(result.next()) {
                ResultSet set = pstmt.getResultSet();
                data[index] = new Data(set.getString("name"), set.getString("frage"), set.getInt("frage_nr"), set.getString("antwort"));
                index++;
            }
            return data;
        } catch (SQLException e) {
            System.out.println("Datenbankfehler");
            e.printStackTrace();
        }
        return null;
    }
    public int countDetails() {
        if (conn == null) return 0;
        String sql = "SELECT COUNT(*) AS anzahl FROM mitglied_frage";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            result.next();
            return result.getInt("anzahl");
        } catch (SQLException e) {
            System.out.println("Datenbankfehler");
            e.printStackTrace();
        }
        return 0;
    }

    public void setDetails(String mitglied, String frage, int frage_nr, String antwort) {
        if (conn == null) return;
        boolean has_entry = false;
        //insert mitglied if not exists and get id
        if (getMitgliedID(mitglied) == -1) setMitglied(mitglied);
        int mitglied_id = getMitgliedID(mitglied);

        //insert frage if not exists and get id
        if (getFrageID(frage) == -1) setFrage(frage);
        int frage_id = getFrageID(frage);

        //insert or update data
        String sql = "SELECT * FROM mitglied_frage WHERE mitglied_id=? AND frage_id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, mitglied_id);
            pstmt.setInt(2, frage_id);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                has_entry = true;
            }
        } catch (SQLException e) {
            System.out.println("Datenbankfehler");
            e.printStackTrace();
        }
        if (!has_entry) {//Mitglied hat Frage noch nicht beantwortet
            sql = "INSERT INTO mitglied_frage (mitglied_id, frage_id, frage_nr, antwort) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, mitglied_id);
                pstmt.setInt(2, frage_id);
                pstmt.setInt(3, frage_nr);
                pstmt.setString(4, antwort);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Datenbankfehler");
                e.printStackTrace();
            }
        } else {
            sql = "UPDATE mitglied_frage SET frage_nr=?, antwort=?";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, frage_nr);
                pstmt.setString(2, antwort);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Datenbankfehler");
                e.printStackTrace();
            }
        }
    }
}