package analyzer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AnalyzerGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Smart Code Complexity Analyzer");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(25, 25, 25));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        frame.setContentPane(mainPanel);

        mainPanel.add(Box.createVerticalStrut(40));

        JLabel title = new JLabel("Smart Code Complexity Analyzer");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        mainPanel.add(title);

        mainPanel.add(Box.createVerticalStrut(20));

        JLabel subtitle = new JLabel(
                "Analyze Java files and estimate time complexity");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(180, 180, 180));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        mainPanel.add(subtitle);

        mainPanel.add(Box.createVerticalStrut(40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(25, 25, 25));

        JButton chooseButton = new JButton("📂 Choose File");

        chooseButton.setPreferredSize(new Dimension(250, 55));
        chooseButton.setMinimumSize(new Dimension(250, 55));
        chooseButton.setMaximumSize(new Dimension(250, 55));

        chooseButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
        chooseButton.setBackground(new Color(70, 70, 70));
        chooseButton.setForeground(Color.WHITE);
        chooseButton.setFocusPainted(false);
        chooseButton.setBorderPainted(false);

        JButton analyzeButton = new JButton("⚡ Analyze");

        analyzeButton.setPreferredSize(new Dimension(250, 55));
        analyzeButton.setMinimumSize(new Dimension(250, 55));
        analyzeButton.setMaximumSize(new Dimension(250, 55));

        analyzeButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
        analyzeButton.setBackground(new Color(0, 120, 215));
        analyzeButton.setForeground(Color.WHITE);
        analyzeButton.setFocusPainted(false);
        analyzeButton.setBorderPainted(false);

        buttonPanel.add(chooseButton);
        buttonPanel.add(Box.createHorizontalStrut(25));
        buttonPanel.add(analyzeButton);

        mainPanel.add(buttonPanel);

        mainPanel.add(Box.createVerticalStrut(50));

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(25, 25, 25));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        // LEFT PANEL

        JPanel reportPanel = new JPanel();
        reportPanel.setBackground(new Color(25, 25, 25));
        reportPanel.setLayout(
                new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
        reportPanel.setPreferredSize(new Dimension(550, 650));
        reportPanel.setMaximumSize(new Dimension(550, 650));

        JLabel reportTitle = new JLabel("Analysis Report");
        reportTitle.setForeground(Color.WHITE);
        reportTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        reportTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        reportPanel.add(reportTitle);
        reportPanel.add(Box.createVerticalStrut(30));

        JLabel statusLabel = new JLabel("No file selected");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));

        reportPanel.add(statusLabel);
        reportPanel.add(Box.createVerticalStrut(30));

        JLabel classLabel = new JLabel();
        JLabel methodLabel = new JLabel();
        JLabel loopLabel = new JLabel();
        JLabel nestedLabel = new JLabel();
        JLabel complexityLabel = new JLabel();
        JLabel scoreLabel = new JLabel();
        JLabel cyclomaticLabel = new JLabel();
        JLabel recursionLabel = new JLabel();

        JLabel[] reportLabels = {
                classLabel,
                methodLabel,
                loopLabel,
                nestedLabel,
                complexityLabel,
                cyclomaticLabel,
                recursionLabel,
                scoreLabel
        };

        for (JLabel label : reportLabels) {

            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Segoe UI", Font.BOLD, 22));
            label.setVisible(false);

            reportPanel.add(label);
            reportPanel.add(Box.createVerticalStrut(15));
        }

        // RIGHT PANEL

        JPanel suggestionPanel = new JPanel();
        suggestionPanel.setBackground(new Color(25, 25, 25));
        suggestionPanel.setLayout(
                new BoxLayout(suggestionPanel, BoxLayout.Y_AXIS));
        suggestionPanel.setPreferredSize(new Dimension(500, 450));

        JLabel suggestionTitle = new JLabel("Suggestions");
        suggestionTitle.setForeground(Color.WHITE);
        suggestionTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        suggestionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        suggestionPanel.add(suggestionTitle);
        suggestionPanel.add(Box.createVerticalStrut(40));

        JLabel suggestion1 = new JLabel("No suggestions yet");
        JLabel suggestion2 = new JLabel("");
        JLabel suggestion3 = new JLabel("");

        JLabel[] suggestions = {
                suggestion1,
                suggestion2,
                suggestion3
        };

        for (JLabel label : suggestions) {

            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Segoe UI", Font.BOLD, 20));

            suggestionPanel.add(label);
            suggestionPanel.add(Box.createVerticalStrut(35));
        }
        
        centerPanel.add(Box.createHorizontalStrut(100));
        centerPanel.add(reportPanel);
        centerPanel.add(Box.createHorizontalStrut(150));
        centerPanel.add(suggestionPanel);

        mainPanel.add(centerPanel);

        mainPanel.add(Box.createVerticalStrut(40));

        JButton exportButton = new JButton("Export Report");
        exportButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        exportButton.setPreferredSize(new Dimension(300, 60));
        exportButton.setMaximumSize(new Dimension(300, 60));
        exportButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exportButton.setBackground(new Color(34, 139, 34));
        exportButton.setForeground(Color.WHITE);
        exportButton.setVisible(false);
        suggestionPanel.add(Box.createVerticalStrut(40));
        suggestionPanel.add(exportButton);

        chooseButton.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {

                File selectedFile = chooser.getSelectedFile();

                statusLabel.setText(
                        "Selected File: " +
                                selectedFile.getName());

                analyzeButton.putClientProperty(
                        "selectedFile",
                        selectedFile);

                for (JLabel label : reportLabels)
                    label.setVisible(false);

                exportButton.setVisible(false);
            }
        });

        analyzeButton.addActionListener(e -> {

            File file = (File)
                    analyzeButton.getClientProperty(
                            "selectedFile");

            if (file == null) {

                statusLabel.setText(
                        "Please select a Java file first");
                return;
            }

            String report =
                    CodeAnalyzer.analyze(file);

            String[] data = report.split(",");

            statusLabel.setText("Analysis Completed");

            classLabel.setText(
                    "Classes Found : " + data[0]);

            methodLabel.setText(
                    "Methods Found : " + data[1]);

            loopLabel.setText(
                    "Loops Found : " + data[2]);

            nestedLabel.setText(
                    "Nested Loops Found : " + data[3]);

            complexityLabel.setText(
                    "Complexity : " + data[4]);

            
            cyclomaticLabel.setText(
                    "Cyclomatic Complexity : " + data[6]);

            recursionLabel.setText(
                    "Recursive Methods : " + data[7]);
            scoreLabel.setText(
                    "Quality Score : " +
                            data[5] + "/100");

            int score =
                    Integer.parseInt(data[5]);

            if (score >= 80)
                scoreLabel.setForeground(Color.GREEN);
            else if (score >= 50)
                scoreLabel.setForeground(Color.ORANGE);
            else
                scoreLabel.setForeground(Color.RED);

            if (Integer.parseInt(data[3]) > 0)
                suggestion1.setText(
                        "Reduce nested loops");
            else
                suggestion1.setText(
                        "No nested loops detected");

            if (Integer.parseInt(data[7]) > 0)
                suggestion2.setText(
                        "Recursive algorithm detected");
            else if (score >= 80)
                suggestion2.setText(
                        "Code quality is good");
            else
                suggestion2.setText(
                        "Improve code structure");

            if (Integer.parseInt(data[6]) > 10)
                suggestion3.setText(
                        "High cyclomatic complexity");
            else
                suggestion3.setText(
                        "Code is maintainable");

            for (JLabel label : reportLabels)
                label.setVisible(true);

            exportButton.setVisible(true);
        });

        exportButton.addActionListener(e -> {

            try {

                FileWriter writer =
                        new FileWriter(
                                "analysis_report.txt");

                writer.write(
                        statusLabel.getText() + "\n\n");

                for (JLabel label : reportLabels)
                    writer.write(
                            label.getText() + "\n");

                writer.close();

                JOptionPane.showMessageDialog(
                        frame,
                        "Report exported successfully!");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Error exporting report");
            }
        });

        frame.setVisible(true);
    }
}