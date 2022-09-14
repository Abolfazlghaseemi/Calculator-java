package ir.toplearn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Frame implements ActionListener {
    JFrame frame;
    JTextField text;
    JButton[] btns = new JButton[15];
    String[] numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    String[] operators = new String[]{"+", "-", "*", "/", "="};
    int btnW = 70, btnH = 70;
    String op;
    double lastNumber;

    public Frame() {
        frame = new JFrame("ماشین حساب");
        frame.setSize(500, 500);

        text = new JTextField("0");
        text.setBounds(10, 10, 320, 50);
        frame.add(text);

        InitialNumberBtns();
        InitialOperatorBtns();

        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void InitialNumberBtns() {
        int totalSize = 440;
        int x = 10, y = 70;
        int col = 0;
        for (int i = 0; i < numbers.length; i++) {
            JButton btn = new JButton(numbers[i]);
            btn.setBounds(x, y, btnW, btnH);

            btn.addActionListener(this);
            frame.add(btn);

            btns[i] = btn;

            col++;
            if (col == 3) {
                y += btnH + 10;
                x = 10;
                col = 0;
            } else {
                x += 10 + btnW;
            }
        }
    }

    public void InitialOperatorBtns() {
        int totalSize = 440;
        int defaultX = (3 * this.btnW) + 40;
        int x = defaultX, y = 70;
        int btnW = 80, btnH = 70;

        for (int i = 0; i < operators.length; i++) {
            JButton btn = new JButton(operators[i]);
            btn.setBounds(x, y, btnW, btnH);

            if (operators[i].equals("=")) {
                btn.setBounds(20 + this.btnW, y - (btnH + 10), (2 * this.btnW) + 10, btnH);
            }

            btn.addActionListener(this);
            frame.add(btn);

            btns[i] = btn;

            y += btnH + 10;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnLable = ((JButton) e.getSource()).getText();

        if (Arrays.asList(numbers).contains(btnLable)) {
            text.setText(text.getText() + btnLable);
        } else {
            if (btnLable == "+" || btnLable == "-" || btnLable == "*" || btnLable == "/") {
                lastNumber = Double.valueOf(text.getText());
                text.setText("0");
                op = btnLable;
            }
            if (btnLable == "=") {
                double res=0;
                switch (op) {
                    case "+":
                        res=lastNumber+Double.valueOf(text.getText());
                        break;
                    case "-":
                        res=lastNumber-Double.valueOf(text.getText());
                        break;
                    case "*":
                        res=lastNumber*Double.valueOf(text.getText());
                        break;
                    case "/":
                        res=lastNumber/Double.valueOf(text.getText());
                        break;
                }
                text.setText(String.valueOf(res));
            }
        }

    }
}
