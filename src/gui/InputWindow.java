package gui;

import database.Database;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.text.NumberFormat;

public class InputWindow extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Database database;

    private final JTextField name_input, frage_input, antwort_input;
    private final JFormattedTextField frage_nr_input;

    public InputWindow() {
        super("Lerngruppe - Chat");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);

        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);
        database = new Database();

        panel.add(new JLabel("Name"));
        name_input = new JTextField();
        panel.add(name_input);
        panel.add(new JLabel("Neue Frage"));
        frage_input = new JTextField();
        panel.add(frage_input);
        panel.add(new JLabel("Frage Nr."));
        frage_nr_input = new JFormattedTextField(formatter);
        panel.add(frage_nr_input);
        panel.add(new JLabel("Antwort"));
        antwort_input = new JTextField();
        panel.add(antwort_input);
        JButton commit_frage = new JButton("absenden");
        panel.add(commit_frage);
        commit_frage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = name_input.getText().trim();
                String frage = frage_input.getText().trim();
                int frage_nr = Integer.parseInt(frage_nr_input.getText());
                String antwort = antwort_input.getText().trim();

                if (name.length() > 64) {
                    System.out.println("name zu lang (64)");
                    return;
                }
                if (antwort.length() > 2) {
                    System.out.println("antwort zu lang (2)");
                    return;
                }

                database.setDetails(name, frage, frage_nr, antwort);
            }
        });

        this.setVisible(true);
    }
}