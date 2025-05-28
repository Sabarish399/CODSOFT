import javax.swing.*;
import java.net.*;
import java.io.*;
import org.json.JSONObject;

class CurrencyConverter {

    static final String API_KEY = "5ee3b6cbe2d7ce9ae598855f";

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel baseLabel = new JLabel("From:");
        baseLabel.setBounds(50, 30, 100, 25);
        frame.add(baseLabel);

        JLabel targetLabel = new JLabel("To:");
        targetLabel.setBounds(200, 30, 100, 25);
        frame.add(targetLabel);

        String[] currencies = {"USD", "EUR", "INR", "GBP", "JPY", "AUD", "CAD"};
        JComboBox<String> baseCurrency = new JComboBox<>(currencies);
        baseCurrency.setBounds(50, 60, 100, 25);
        frame.add(baseCurrency);

        JComboBox<String> targetCurrency = new JComboBox<>(currencies);
        targetCurrency.setBounds(200, 60, 100, 25);
        frame.add(targetCurrency);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 100, 100, 25);
        frame.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(120, 100, 180, 25);
        frame.add(amountField);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(140, 140, 100, 30);
        frame.add(convertButton);

        JLabel resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setBounds(50, 190, 300, 30);
        frame.add(resultLabel);

        convertButton.addActionListener(e -> {
            String from = (String) baseCurrency.getSelectedItem();
            String to = (String) targetCurrency.getSelectedItem();
            String amountText = amountField.getText();

            try {
                double amount = Double.parseDouble(amountText);
                double rate = getExchangeRate(from, to);
                double converted = rate * amount;

                resultLabel.setText("Converted Amount: " + String.format("%.2f", converted) + " " + to);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error fetching exchange rate.");
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

    public static double getExchangeRate(String from, String to) throws IOException {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + from;
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();

        JSONObject obj = new JSONObject(response.toString());
        JSONObject rates = obj.getJSONObject("conversion_rates");
        return rates.getDouble(to);
    }
}
