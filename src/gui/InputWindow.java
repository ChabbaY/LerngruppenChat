package gui;

import database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow extends Window {
    private final JTextField name_input, frage_input, antwort_input;
    private final JFormattedTextField frage_nr_input;

    public InputWindow(Database database) {
        super("Lerngruppe - Chat", 1000, 800);

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