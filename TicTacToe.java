import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Game code
public class TicTacToe extends JFrame{

    private JButton[] buttons;
    private boolean xTurn = true;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 400;

    public TicTacToe(){

        setTitle("GAME OF TICTACTOE");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,3));

        buttons = new JButton[9];

        //initialize buttons
        for(int i = 0; i < 9; i++){

            buttons[i] = new JButton("");
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            buttons[i].addActionListener(new ButtonClicked());
            add(buttons[i]);
        }

        setVisible(true);
    }

    //inner class
    private class ButtonClicked implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JButton clicked = (JButton)e.getSource();
              
            //checks if it has already been clicked.
            if(!clicked.getText().equals("")){
                return;
            }
            clicked.setText(xTurn ? "X" : "O"); //decides which turn it is either X or O.
            clicked.setForeground(xTurn ? Color.RED : Color.BLUE); //changes colour depending on which is clicked.

            if(checkWin()){
                JOptionPane.showMessageDialog(null, "Winner: " + (xTurn ? "X" : "O"));
                resetGame();
            }else if(isBoardFull()){
                JOptionPane.showMessageDialog(null, "DRAW!");
                resetGame();
            }

            //switches user is turn from X to O or from O to X.
            xTurn = !xTurn;
        }
    }

    //Check if someone has won
    private boolean checkWin(){
        //condition of a win using a double array
        String[][] patterns = { {get(0), get(1), get(2)}, 
                                {get(3), get(4), get(5)},
                                {get(6), get(7), get(8)},
                                {get(0), get(3), get(6)},
                                {get(1), get(4), get(7)},
                                {get(2), get(5), get(8)},
                                {get(0), get(4), get(8)},
                                {get(2), get(4), get(6)}};
        for(String[] line : patterns){
            if(line[0].equals(line[1]) && line[1].equals(line[2]) && !line[0].equals("")){
                return true;
            }
        }                   
        return false;     
    }

    private boolean isBoardFull(){
        for(JButton b : buttons){
            if(b.getText().equals(""))
            return false;
        }
        return true;
    }

    private void resetGame(){
        for(JButton b : buttons){
            b.setText("");
        }
        xTurn = true;
    }

    private String get(int index){
        return buttons[index].getText();
    }

    public static void main(String[] args){
        new TicTacToe();
    }//end of main method.
}//end of class