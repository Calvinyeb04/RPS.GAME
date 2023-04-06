import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class RockPaperScissorsFrame extends JFrame {

    JLabel playerWinsLbl;
    JLabel computerWinsLbl;
    JLabel tiesLbl;

    JTextField playerWinsField;
    JTextField computerWinsField;
    JTextField tiesField;

    JTextArea resultsArea;

    JScrollPane scrollPane;

    String computerMove= "";

    public int playerWins = 0;
    public int computerWins = 0;
    public int ties = 0;
    public int gamesPlayed = 0;



    JPanel mainPnl = new JPanel();
    JPanel btnPnl = new JPanel();
    JPanel statsPnl = new JPanel();
    JPanel resultsPnl = new JPanel();

    JButton rockBtn = new JButton();
    JButton paperBtn = new JButton();
    JButton scissorsBtn = new JButton();
    JButton quitBtn = new JButton();

    ImageIcon rockIcon = new ImageIcon(new ImageIcon("src/Rock.jpeg").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
    ImageIcon paperIcon = new ImageIcon(new ImageIcon("src/P.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
    ImageIcon scissorsIcon =  new ImageIcon(new ImageIcon("src/SC.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));

    ImageIcon QuitIcon =new ImageIcon(new ImageIcon("src/quit.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));


    public RockPaperScissorsFrame() {
        // Set Frame
        setSize(600, 600);
        setTitle("RPS GAME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panels
        createBtnPnl();
       createStatspnl();
       createResultsPnl();

        // Add panels to main panel
        mainPnl.setLayout(new BorderLayout());
        mainPnl.add(statsPnl, BorderLayout.NORTH);
        mainPnl.add(resultsPnl, BorderLayout.CENTER);
        mainPnl.add(btnPnl, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPnl);

        setVisible(true);
    }


    private void createBtnPnl()
    {
        btnPnl.setLayout(new GridLayout(1, 3));
        btnPnl.setBorder(new TitledBorder(new EtchedBorder(), "Button Panel"));

        rockBtn.setIcon(rockIcon);
        paperBtn.setIcon(paperIcon);
        scissorsBtn.setIcon(scissorsIcon);
        quitBtn.setIcon(QuitIcon);




        btnPnl.add(rockBtn);
        rockBtn.addActionListener((ActionEvent e) -> {getMove("Rock");});

        btnPnl.add(paperBtn);
        paperBtn.addActionListener((ActionEvent e) -> {getMove("Paper");});

        btnPnl.add(scissorsBtn);
        scissorsBtn.addActionListener((ActionEvent e) -> {getMove("Scissors");});

        btnPnl.add(quitBtn);
    }

    private void getMove(String pMove)
    {
        Random random = new Random();
        int index = 0;
        ArrayList<String> moves = new ArrayList<>();
        moves.add("Rock");
        moves.add("Paper");
        moves.add("Scissors");

        index = random.nextInt(3);
        computerMove = moves.get(index);
        result(pMove, computerMove);
    }

    private void result(String pMove, String cMove)
    {
        gamesPlayed++;
        int result = 0;
        ArrayList<String> outcome = new ArrayList<>();
        outcome.add("There is a tie!");
        outcome.add("Player Wins!");
        outcome.add("Computer Wins!");

        if (pMove == "Rock" && cMove == "Rock")
        {
            result =  0;
        } else if (pMove == "Rock" && cMove == "Paper")
        {
            result =  2;
        } else if (pMove == "Rock" && cMove == "Scissors")
        {
            result =  1;
        } else if (pMove == "Paper" && cMove == "Paper")
        {
            result =  0;
        } else if (pMove == "Paper" && cMove == "Scissors")
        {
            result =  2;
        } else if (pMove == "Paper" && cMove == "Rock")
        {
            result =  1;
        } else if (pMove == "Scissors" && cMove == "Scissors")
        {
            result =  0;
        } else if (pMove == "Scissors" && cMove == "Rock")
        {
            result =  2;
        } else if (pMove == "Scissors" && cMove == "Paper")
        {
            result =  1;
        }

        resultsArea.append("Game # " + gamesPlayed +"\t Player Move: " + pMove + "\t Computer Move: " + cMove + "\t Result: " + outcome.get(result) + "\n");
        updateCounters(result);
    }


    private void updateCounters(int r)
    {
        switch(r) {
            case 0:
                ties += 1;
                tiesField.setText(Integer.toString(ties));
                break;
            case 1:
                playerWins += 1;
                playerWinsField.setText(Integer.toString(playerWins));
                break;
            case 2:
                computerWins += 1;
                computerWinsField.setText(Integer.toString(computerWins));
                break;
        }
    }




    private void createStatspnl() {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(2, 3, 25, 25));
        statsPnl.setBorder(new TitledBorder(new EtchedBorder(), "Stats Panel"));

        playerWinsLbl = new JLabel("Player Wins", SwingConstants.CENTER);
        playerWinsLbl.setFont(new Font("Montserrat", Font.PLAIN, 24));
        computerWinsLbl = new JLabel("Computer Wins", SwingConstants.CENTER);
        computerWinsLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        tiesLbl = new JLabel("Number of Ties", SwingConstants.CENTER);
        tiesLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        playerWinsField = new JTextField("0");
        playerWinsField.setHorizontalAlignment(JTextField.CENTER);
        playerWinsField.setFont(new Font("Arial", Font.BOLD, 18));
        computerWinsField = new JTextField("0");
        computerWinsField.setHorizontalAlignment(JTextField.CENTER);
        computerWinsField.setFont(new Font("Arial", Font.BOLD, 18));
        tiesField = new JTextField("0");
        tiesField.setHorizontalAlignment(JTextField.CENTER);
        tiesField.setFont(new Font("Arial", Font.BOLD, 18));

        playerWinsField.setEditable(false);
        computerWinsField.setEditable(false);
        tiesField.setEditable(false);

        statsPnl.add(playerWinsLbl);
        statsPnl.add(computerWinsLbl);
        statsPnl.add(tiesLbl);
        statsPnl.add(playerWinsField);
        statsPnl.add(computerWinsField);
        statsPnl.add(tiesField);
    }

    private void createResultsPnl()
    {
        resultsPnl = new JPanel();
        resultsPnl.setLayout(new GridLayout(1,1));
        resultsPnl.setBorder(new TitledBorder(new EtchedBorder(), "Results Panel"));
        resultsPnl.setSize(600,600);
        resultsArea = new JTextArea();
        scrollPane = new JScrollPane(resultsArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(350, 500);
        resultsPnl.add(scrollPane);
    }




    public static void main(String[] args) {
        new RockPaperScissorsFrame();
    }
}



