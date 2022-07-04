package gui;

import database.Database;
import threads.UpdateThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends Window {
    private final JTextField name_input;

    public MenuWindow(Database database) {
        super(database, 800, 200);

        panel.add(new JLabel("Name"));
        name_input = new JTextField();
        panel.add(name_input);
        JButton commit_frage = new JButton("absenden");
        panel.add(commit_frage);
        commit_frage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = name_input.getText().trim();
                name_input.setEnabled(false);

                new InputWindow(database, name);
                UpdateThread updater = new UpdateThread(database, name);
                updater.start();

                commit_frage.setEnabled(false);
            }
        });

        this.setVisible(true);
    }
}