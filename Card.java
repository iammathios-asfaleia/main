import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Card extends JFrame {

    private JButton confirm,back;
    private JLabel label1,label2,label3,label4;
    private JTextField cardholder,type,cardnumber,date,cvv;

    public Card (){
        //==========FRAME================================
        super("Card");

        setSize(475,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));

        //===============BUTTONS==========================
        confirm = new JButton("Confirm");
        back = new JButton("Back");
        confirm.setBounds(130,515,90,30);
        back.setBounds(230,515,90,30);

        //===============TEXT_FIELDS======================

        //------------------------CARD_NUMBER_FILED----------------------
        cardnumber = new JTextField("");
        final String PL_card = "Insert your card number";
        cardnumber.setText(PL_card);

        cardnumber.addFocusListener(new FocusListener() {
            private boolean showingPlaceholder1 = true;

            @Override
            public void focusGained(FocusEvent e) {
                if(showingPlaceholder1){
                    showingPlaceholder1 = false;
                    cardnumber.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(cardnumber.getText().isEmpty()){
                    cardnumber.setText(PL_card);
                    showingPlaceholder1 =true;
                }
            }
        });
        cardnumber.setBounds(50,137,349,33);
        cardnumber.setFont(new Font("Arial",Font.PLAIN,19));
        cardnumber.setHorizontalAlignment(JTextField.CENTER);

        //------------------------DATE_TEXT_FIELD----------------------
        date = new JTextField();
        final String PL_date = "Date";
        date.setText(PL_date);

        date.addFocusListener(new FocusListener() {
            private boolean showingPlaceholder2 = true;

            @Override
            public void focusGained(FocusEvent e) {
                if(showingPlaceholder2){
                    showingPlaceholder2 = false;
                    date.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(date.getText().isEmpty()){
                    date.setText(PL_date);
                    showingPlaceholder2 =true;
                }
            }
        });
        date.setBounds(135,188,100,22);
        date.setFont(new Font("Arial",Font.BOLD,14));
        date.setHorizontalAlignment(JTextField.CENTER);

        //--------------------USER_NAME_CARD--------------------------
        DocumentFilter filter = new UpperCaseFilter();
        cardholder = new JTextField();
        final String PL_username = "Insert your full name";
        cardholder.setText(PL_username);

        cardholder.addFocusListener(new FocusListener() {
            private boolean showingPlaceholder3 = true;

            @Override
            public void focusGained(FocusEvent e) {
                if(showingPlaceholder3){
                    showingPlaceholder3 = false;
                    cardholder.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(cardholder.getText().isEmpty()){
                    cardholder.setText(PL_username);
                    showingPlaceholder3 =true;
                }
            }
        });
        cardholder.setBounds(47,210,280,22);
        cardholder.setFont(new Font("Arial",Font.PLAIN,14));
        cardholder.setHorizontalAlignment(JTextField.CENTER);
        ((AbstractDocument) cardholder.getDocument()).setDocumentFilter(filter); // Make key words Upper Case

        //----------------------CVV_CARD---------------------------
        cvv = new JTextField();
        cvv.setBounds(283,346,32,27);
        cvv.setFont(new Font("Arial",Font.PLAIN,14));
        cvv.setHorizontalAlignment(JTextField.CENTER);

        Container pane =  new Icon();;
        pane.setBackground(Color.decode("#99d9ea"));

        //==========Set_buttons_into_pane=================
        pane.add(confirm);
        pane.add(back);

        //==========Set_text_fields_into_pane=============
        pane.add(cardnumber);
        pane.add(cardholder);
        pane.add(date);
        pane.add(cvv);

        setContentPane(pane);
        pane.setLayout(null);
        pane.revalidate();
        setVisible(true);
    }


    private class Icon extends JPanel {

        Image img;
        public Icon() {
            try {
                img = ImageIO.read(new File("card.png"));
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }

        @Override
        public void paintComponent(Graphics G) {
            super.paintComponent(G);
            Dimension size = new Dimension(img.getWidth(null),img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
            G.drawImage(img, 0, 0,this.getWidth(),505,null);
        }
    }

    private class UpperCaseFilter extends DocumentFilter {

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            fb.insertString(offset, text.toUpperCase(), attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            fb.replace(offset, length, text.toUpperCase(), attrs);
        }
    }
}
