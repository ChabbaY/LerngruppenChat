package gui;

import database.Database;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.io.Serial;
import java.text.NumberFormat;

public class Window extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    protected JPanel panel;
    protected final NumberFormatter formatter;

    protected Database database;

    public Window(Database database, int width, int height) {
        super("Lerngruppe - Chat");
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);

        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);

        this.database = database;
    }
}