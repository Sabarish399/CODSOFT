import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

class BankAccount
{
    private double balance;

    BankAccount (double balance)
    {
        this.balance = balance;
    }

    double getBalance() {return balance;}

    void withdraw(double amount)
    {
        if(balance<amount)
            JOptionPane.showMessageDialog(null, "Insufficient Balance!");
        else
        {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Withdrawn: " + amount);
        }
    }

    void deposit(double amount)
    {
        if(amount>0)
        {
            balance += amount;
            JOptionPane.showMessageDialog(null, "Deposited: " + amount);
        }
        else
            JOptionPane.showMessageDialog(null, "Invalid amount!");
    }
}

class ATMInterface
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        BankAccount account = new BankAccount(10000);

        Frame frame = new Frame("ATM Interface");

        Label w = new Label("To Withdraw Amount : ");
        Button withdraw = new Button("Click");
        w.setBackground(Color.DARK_GRAY);
        w.setForeground(Color.WHITE);
        withdraw.setBackground(Color.ORANGE);
        withdraw.setForeground(Color.BLACK);
        withdraw.addActionListener( e ->
        {
            String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
            if (input != null) {
                double amount = Double.parseDouble(input);
                account.withdraw(amount);
            }

        });

        Label d = new Label("To Deposit Amount : ");
        Button deposit = new Button("Click");
        d.setBackground(Color.DARK_GRAY);
        d.setForeground(Color.WHITE);
        deposit.setBackground(Color.ORANGE);
        deposit.setForeground(Color.BLACK);
        deposit.addActionListener(e ->
        {
            String input = JOptionPane.showInputDialog("Enter amount to deposit:");
            if (input != null) {
                double amount = Double.parseDouble(input);
                account.deposit(amount);
            }
        });

        Label c = new Label("To Check Balance : ");
        Button checkBalance = new Button("Click");
        c.setBackground(Color.DARK_GRAY);
        c.setForeground(Color.WHITE);
        checkBalance.setBackground(Color.ORANGE);
        checkBalance.setForeground(Color.BLACK);
        checkBalance.addActionListener(e ->
        {
            JOptionPane.showMessageDialog(null, "Current Balance: " + account.getBalance());
        });

        Label atmmenu = new Label("---ATM Menu---");
        atmmenu.setForeground(Color.BLACK);

        frame.add(atmmenu);
//        atmmenu.setBounds(140, 50, 80, 30);
        atmmenu.setBounds(700, 300, 80, 30);

        frame.add(w);
        frame.add(withdraw);
        w.setBounds(610, 350, 120, 30);
       withdraw.setBounds(760, 350, 100, 30);

        frame.add(d);
        frame.add(deposit);
        d.setBounds(610, 400, 120, 30);
        deposit.setBounds(760, 400, 100, 30);

        frame.add(c);
        frame.add(checkBalance);
        c.setBounds(610, 450, 120, 30);
        checkBalance.setBounds(760, 450, 100, 30);

        frame.setLayout(null);
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.setBackground(Color.DARK_GRAY);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
     }
}


