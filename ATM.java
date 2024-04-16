import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double checkBalance() {
        return balance;
    }
}

class ATMGUI extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JTextArea outputArea;

    public ATMGUI(BankAccount account) {
        this.account = account;

        setTitle("ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Amount:");
        amountField = new JTextField(10);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        JButton balanceButton = new JButton("Check Balance");
        balanceButton.addActionListener(this);

        outputArea = new JTextArea(5, 20);
        outputArea.setEditable(false);

        panel.add(label);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);

        add(panel, BorderLayout.CENTER);
        add(outputArea, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        String amountText = amountField.getText();
        double amount = 0;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException ex) {
            outputArea.setText("Invalid amount.");
            return;
        }

        if (e.getActionCommand().equals("Withdraw")) {
            if (account.withdraw(amount)) {
                outputArea.setText("Withdrawal successful.");
            } else {
                outputArea.setText("Withdrawal failed.");
            }
        } else if (e.getActionCommand().equals("Deposit")) {
            if (account.deposit(amount)) {
                outputArea.setText("Deposit successful.");
            } else {
                outputArea.setText("Deposit failed.");
            }
        } else if (e.getActionCommand().equals("Check Balance")) {
            double balance = account.checkBalance();
            outputArea.setText("Your current balance is: " + balance);
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Starting balance of $1000
        ATMGUI atmGUI = new ATMGUI(account);
        atmGUI.setVisible(true);
    }
}
