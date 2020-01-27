import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI  implements ActionListener {
	
	private JFrame frame;
	private JPanel buttonPanel;
	private JButton buttonCheck;
	private JButton buttonHint;
	private JButton buttonUndo;
	private JButton buttonRedo;
	private JPanel gamePanel;
	private JTextField[][] sudokuGrids;
	private JMenuBar menuBar;
	private JMenu mnNew;
	private JMenuItem mnItmNewGame;
	private JLabel Hour;
	private JLabel Minute;
	private JLabel Second;
	private JLabel lblNewLabel;
	private JLabel label;
	public game sudokuGame = new game(9, 9);
	private long millisecond = System.currentTimeMillis();
	private int second = 0;
	private int minute = 0;
	private int hour = 0;
	public boolean state = true;
	
	DocumentListener documentListener = new DocumentListener() {

		@Override
		public void changedUpdate(DocumentEvent e) {	
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j<9; j++) {
				    if (e.getDocument() == sudokuGrids[i][j].getDocument()){
				    	if(sudokuGame.gameBoard[i][j]==0) {
				    		
				    	sudokuGame.setGameBoard(i,j,(Integer.parseInt(sudokuGrids[i][j].getText())));
				    	sudokuGame.moves.add(new move(i,j,(Integer.parseInt(sudokuGrids[i][j].getText())),0));
				    	System.out.println(sudokuGame);
				    	}
				    }
				}
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j<9; j++) {
				    if (e.getDocument() == sudokuGrids[i][j].getDocument()){
				    	sudokuGame.gameBoard[i][j]=0;
				    	System.out.println(sudokuGame);
				    }
				}
			}
		}
		
	};
	public GUI() {
		
		frame = new JFrame("Sudoku");
		frame.setSize(400, 600);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 420, 394, 130);
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		buttonCheck = new JButton("Check");
		buttonCheck.setBounds(10, 70, 100, 50);
		buttonPanel.add(buttonCheck);
		
		buttonHint = new JButton("Hint");
		buttonHint.setBounds(10, 10, 100, 50);
		buttonPanel.add(buttonHint);
		
		buttonUndo = new JButton("Undo");
		buttonUndo.setBounds(284, 70, 100, 50);
		buttonPanel.add(buttonUndo);
		
		buttonRedo = new JButton("Redo");
		buttonRedo.setBounds(284, 10, 100, 50);
		buttonPanel.add(buttonRedo);
		
		Hour = new JLabel("  00");
		Hour.setHorizontalAlignment(SwingConstants.CENTER);
		Hour.setFont(new Font("Tahoma", Font.BOLD, 18));
		Hour.setBounds(110, 27, 51, 58);
		buttonPanel.add(Hour);

		Minute = new JLabel("00");
		Minute.setHorizontalAlignment(SwingConstants.CENTER);
		Minute.setFont(new Font("Tahoma", Font.BOLD, 18));
		Minute.setBounds(171, 27, 51, 59);
		buttonPanel.add(Minute);

		Second = new JLabel("00");
		Second.setHorizontalAlignment(SwingConstants.CENTER);
		Second.setFont(new Font("Tahoma", Font.BOLD, 18));
		Second.setBounds(222, 34, 50, 44);
		buttonPanel.add(Second);

		lblNewLabel = new JLabel(":");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(148, 33, 35, 44);
		buttonPanel.add(lblNewLabel);

		label = new JLabel(":");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(207, 34, 35, 44);
		buttonPanel.add(label);
		
		gamePanel = new JPanel();
		gamePanel.setBounds(0, 21, 394, 370);
		frame.getContentPane().add(gamePanel);
		gamePanel.setLayout(new GridLayout(9, 9));
		
		sudokuGrids = new JTextField[9][9];
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j<9; j++) {
				sudokuGrids[i][j] = new JTextField();
				gamePanel.add(sudokuGrids[i][j]);
				sudokuGrids[i][j].setHorizontalAlignment(JTextField.CENTER);
				sudokuGrids[i][j].setDocument(new JTextFieldLimit(1));
				sudokuGrids[i][j].setFont(new Font("TimesRoman", Font.BOLD, 16));
			}
		}
		
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 3; i++) {

				Border borderBlack = BorderFactory.createLineBorder(Color.BLACK, 1);
				Border borderGreen = BorderFactory.createLineBorder(new Color(0, 203, 0), 1);

				if ((0 <= j && j <= 2)) {
					sudokuGrids[i][j].setBorder(borderBlack);
				} else if (3 <= j && j <= 5) {
				sudokuGrids[i][j].setBorder(borderGreen);
				} else if (6 <= j && j <= 8) {
					sudokuGrids[i][j].setBorder(borderBlack);
				}
			}
		}
		for (int j = 0; j < 9; j++) {
			for (int i = 3; i < 6; i++) {

				Border borderBlack = BorderFactory.createLineBorder(Color.BLACK,1);
				Border borderGreen = BorderFactory.createLineBorder(new Color(0, 203, 0), 1);
				if ((0 <= j && j <= 2)) {
					sudokuGrids[i][j].setBorder(borderGreen);
				} else if (3 <= j && j <= 5) {
					sudokuGrids[i][j].setBorder(borderBlack);
				} else if (6 <= j && j <= 8) {
					sudokuGrids[i][j].setBorder(borderGreen);
				}
			}
		}
		for (int j = 0; j < 9; j++) {
			for (int i = 6; i < 9; i++) {

				Border borderBlack = BorderFactory.createLineBorder(Color.BLACK, 1);
				Border borderGreen = BorderFactory.createLineBorder(new Color(0, 203, 0),1);
				if ((0 <= j && j <= 2)) {
					sudokuGrids[i][j].setBorder(borderBlack);
				} else if (3 <= j && j <= 5) {
					sudokuGrids[i][j].setBorder(borderGreen);
				} else if (6 <= j && j <= 8) {
					sudokuGrids[i][j].setBorder(borderBlack);
				}
			}
		}
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 394, 21);
		frame.getContentPane().add(menuBar);
		
		mnNew = new JMenu("New");
		menuBar.add(mnNew);
		
		mnItmNewGame = new JMenuItem("New Game");
		mnNew.add(mnItmNewGame);
		
		mnItmNewGame.addActionListener(this);
		buttonCheck.addActionListener(this);
		buttonHint.addActionListener(this); 
		buttonUndo.addActionListener(this);
		buttonRedo.addActionListener(this); 

		
		
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonCheck) {
			if(sudokuGame.checkGame()) {
				JOptionPane.showMessageDialog(frame,"Congratulations!");
			}else
				JOptionPane.showMessageDialog(frame,"Your game hasn't finished yet.");
				
		}else if(e.getSource() == buttonHint) {
		
			
			sudokuGame.hint();
				
			sudokuGrids[sudokuGame.hints.get(0).getY()][sudokuGame.hints.get(0).getX()].setText(Integer.toString(sudokuGame.gameBoard[sudokuGame.hints.get(0).getY()][sudokuGame.hints.get(0).getX()]));
			sudokuGame.hints.clear();
	    	System.out.println(sudokuGame);

			
		}else if(e.getSource() == buttonUndo) {
			if(!sudokuGame.moves.isEmpty()) {
				
			sudokuGrids[sudokuGame.moves.get(sudokuGame.moves.size()-1).getY()][sudokuGame.moves.get(sudokuGame.moves.size()-1).getX()].setText(null);
			sudokuGame.undo();
			sudokuGame.moves.remove(sudokuGame.moves.size()-1);
		}else {
			if(!sudokuGame.moves.isEmpty()){
			sudokuGrids[sudokuGame.moves.get(sudokuGame.moves.size()-1).getY()][sudokuGame.moves.get(sudokuGame.moves.size()-1).getX()].setText(null);
			}
		}
	    	System.out.println(sudokuGame);

		}else if(e.getSource() == buttonRedo) {
			
			
			if(!sudokuGame.undone.isEmpty()) {
				
				sudokuGrids[sudokuGame.undone.get(sudokuGame.undone.size()-1).getY()][sudokuGame.undone.get(sudokuGame.undone.size()-1).getX()].setText(Integer.toString(sudokuGame.undone.get(sudokuGame.undone.size()-1).getTempNumber()));
				sudokuGame.redo();
				sudokuGame.undone.remove(sudokuGame.undone.size()-1);
			}
	    	System.out.println(sudokuGame);

		}else if(e.getSource() == mnItmNewGame) {
			sudokuGame.prepareGameReady();
			for (int i = 0; i < 9; i++) {
				for (int j = 0;j<9; j++) {
					sudokuGrids[i][j].setText(Integer.toString(sudokuGame.gameBoard[i][j]));
					if(!(sudokuGame.gameBoard[i][j] == 0)) {
						sudokuGrids[i][j].setEditable(false);
						sudokuGrids[i][j].setForeground(Color.RED);
					} else {
						sudokuGrids[i][j].setText(null);
					}
					sudokuGrids[i][j].getDocument().addDocumentListener(documentListener);
				}
			}
			
			Thread t = new Thread() {
				public void run() {
					while (state == true) {
						try {
							sleep(1);

							if (millisecond > 600) {
								millisecond = 00;
								second++;
							} else if (second > 60) {
								millisecond = 00;
								second = 00;
								minute++;

							} else if (minute > 60) {
								millisecond = 00;
								second = 00;
								minute = 00;
								hour++;
							}
							millisecond++;

							Hour.setText(Integer.toString(hour));
							Minute.setText(Integer.toString(minute));
							Second.setText(Integer.toString(second));

						} catch (Exception e) {

						}

					}
				}
			};
			t.start();
			System.out.println(sudokuGame);
		}
	}
}