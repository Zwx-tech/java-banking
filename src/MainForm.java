import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {

    // Element refs
    private JLabel MessageDisplayLabel;
    private JPanel mainPanel;
    private JTextField moneyAmountField;
    private JButton withdrawAllMoneyButton;
    private JButton depositMoneyButton;
    private JButton withdrawSomeMoneyButton;
    private JLabel moneyBalanceLabel;
    private JLabel depositSumLabel;
    private JLabel withdrawSumLabel;

    private double balance = 0.0;
    private double totalDeposits = 0.0;
    private double totalWithdrawals = 0.0;

    public MainForm() {
        initialiseComponents();

        depositMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountText = moneyAmountField.getText();
                try {
                    double amount = (double) Math.round(Double.parseDouble(amountText) * 100.0) / 100.0;
                    depositMoney(amount);
                } catch (NumberFormatException ex) {
                    // Display an error message if the user did not enter a valid number
                    MessageDisplayLabel.setText("Nie udało się zrealizować operacji. Wprowadź poprawną kwotę.");
                }
            }
        });

        withdrawSomeMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountText = moneyAmountField.getText();
                try {
                    double amount = (double) Math.round(Double.parseDouble(amountText) * 100.0) / 100.0;
                    withdrawMoney(amount);
                } catch (NumberFormatException ex) {
                    // Display an error message if the user did not enter a valid number
                    MessageDisplayLabel.setText("Nie udało się zrealizować operacji. Wprowadź poprawną kwotę.");
                }
            }
        });

        withdrawAllMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawAllMoney();
            }
        });
    }

    public void withdrawMoney(double amount) {
        if (balance >= amount) {
            balance -= amount;
            totalWithdrawals += amount;
            // Update the balance display
            moneyBalanceLabel.setText(String.format("%.2fzł", balance));
            withdrawSumLabel.setText(String.format("%.2fzł", totalWithdrawals));
            MessageDisplayLabel.setText("Wypłacono:  " + amount + "zł");
        } else {
            MessageDisplayLabel.setText("Nie udało się zrealizować operacji. Brak wystarczających środków.");
        }
    }

    public void withdrawAllMoney() {
        if (balance > 0) {
            double amount = balance;
            totalWithdrawals += amount;
            balance = 0;
            // Update the balance display
            moneyBalanceLabel.setText(String.format("%.2fzł", balance));
            withdrawSumLabel.setText(String.format("%.2fzł", totalWithdrawals));
            MessageDisplayLabel.setText("Wypłacono całość:  " + amount + "zł");
        } else {
            MessageDisplayLabel.setText("Nie udało się zrealizować operacji. Brak środków na koncie.");
        }
    }

    public void depositMoney(double amount) {
        balance += amount;
        totalDeposits += amount;
        // Update the balance display
        depositSumLabel.setText(String.format("%.2fzł", totalDeposits));
        moneyBalanceLabel.setText(String.format("%.2fzł", balance));
        MessageDisplayLabel.setText("Wpłacono:  " + amount + "zł");
    }

    public void initialiseComponents() {
        setTitle("Banking Application");
        setSize(new Dimension(500, 300));
        setLocationRelativeTo(null); // I don't know what this one does
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Insert gui from gui designer into out window
        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainForm mainForm = new MainForm();
                mainForm.setVisible(true);
            }
        });
    }
}
