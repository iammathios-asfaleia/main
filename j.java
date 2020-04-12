
JFrame frame = new JFrame("Register");
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        Container pane = frame.getContentPane();
        GridLayout grid = new GridLayout(6,2);
        JTextField[] fields = new JTextField[5];
        String[] str = {"Name","Surname","Username","Password","Email"};
        JLabel[] lab = new JLabel[5];

        for(int i=0; i<str.length; i++){
            fields[i]=new JTextField();
            lab[i]=new JLabel();
            pane.add(lab[i]);
            lab[i].setText(str[i]);
            pane.add(fields[i]);
            pane.setLayout(grid);
        }

        JButton button = new JButton("Register");
        pane.add(button,BorderLayout.CENTER);
        frame.setContentPane(pane);
        frame.revalidate();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    
            }
        });