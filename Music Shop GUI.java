package musicshops;


/**
 *
 * @author yagmur
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Music Shop");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton customerButton = new JButton("Customer");
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ana pencereyi kapat
                new LoginForm("Customer Login", "customer", "123456", "Customer"); // Customer Login formunu aç
            }
        });
        panel.add(customerButton);

        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ana pencereyi kapat
                new LoginForm("Admin Login", "admin", "654321", "Admin"); // Admin Login formunu aç
            }
        });
        panel.add(adminButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}

class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private String correctUsername;
    private String correctPassword;
    private String userType;

    public LoginForm(String title, String username, String password, String type) {
        super(title);
        this.correctUsername = username;
        this.correctPassword = password;
        this.userType = type;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);
        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());
                if (enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword)) {
                    JOptionPane.showMessageDialog(null, "Login successful!"); // Giriş başarılıysa
                    // İlgili formu açabilirsiniz, örneğin:
                    if (userType.equals("Customer")) {
                        new CustomerFrame();
                    } else if (userType.equals("Admin")) {
                        new AdminFrame();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password!"); // Giriş başarısızsa
                }
            }
        });
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }
}

class CustomerFrame extends JFrame {
   private JTextField searchField;
    private JButton searchButton;
    private JList<String> searchResultsList;
    private JButton addToCartButton;
    private JButton purchaseButton;
    private JButton playButton;

    public CustomerFrame() {
        super("Customer Interface");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchButton = new JButton("Search");
        searchPanel.add(searchButton, BorderLayout.EAST);
        panel.add(searchPanel, BorderLayout.NORTH);

        searchResultsList = new JList<>(new String[]{"Tune 1", "Tune 2", "Tune 3", "Tune 4", "Tune 5"});
        panel.add(new JScrollPane(searchResultsList), BorderLayout.CENTER);

        addToCartButton = new JButton("Add to Cart");
        panel.add(addToCartButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
        
        playButton = new JButton("Play Music");
        panel.add(playButton, BorderLayout.EAST);

        purchaseButton = new JButton("Purchase");
        panel.add(purchaseButton, BorderLayout.SOUTH);
        
        
searchButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String searchText = searchField.getText();
        // Simulated search results
        String[] allTunes = {"Tune 1", "Tune 2", "Tune 3", "Tune 4", "Tune 5"};
        String[] searchTunes = searchText.split(", ");
        boolean found = false;
        for (String searchTune : searchTunes) {
            if (containsIgnoreCase(allTunes, searchTune)) {
                found = true;
            } else {
                found = false;
                break;
            }
        }
        if (found) {
            searchResultsList.setListData(searchTunes);
            JOptionPane.showMessageDialog(null, "Music found.");
        } else {
            searchResultsList.setListData(new String[0]);
            JOptionPane.showMessageDialog(null, "Music not found.");
        }
    }

    private boolean containsIgnoreCase(String[] array, String searchTerm) {
        for (String element : array) {
            if (element.equalsIgnoreCase(searchTerm.trim())) {
                return true;
            }
        }
        return false;
    }
});

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMusic = searchResultsList.getSelectedValue();
                if (selectedMusic != null) {
                    JOptionPane.showMessageDialog(null, "Playing sample for: " + selectedMusic);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a music to play.");
                }
            }
        });
        
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMusic = searchResultsList.getSelectedValue();
                if (selectedMusic != null) {
                    JOptionPane.showMessageDialog(null, "Music added to cart: " + selectedMusic);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a music to add to cart.");
                }
            }
        });

    }
}
  
class AdminFrame extends JFrame {
    private JTextField promotionField;
    private JButton setPromotionButton;

    public AdminFrame() {
        super("Admin Interface");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel promotionLabel = new JLabel("Promotion: ");
        panel.add(promotionLabel, BorderLayout.WEST);

        promotionField = new JTextField();
        panel.add(promotionField, BorderLayout.CENTER);

        setPromotionButton = new JButton("Set Promotion");
        panel.add(setPromotionButton, BorderLayout.EAST);

        add(panel);
        setVisible(true);

        setPromotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String promotionText = promotionField.getText();
                // Simulated promotion setting
                if (!promotionText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Promotion set: " + promotionText);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a promotion.");
                }
            }
        });
    }
}