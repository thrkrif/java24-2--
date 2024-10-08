package decorator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaperDollApp extends JFrame implements ActionListener {
    // private JButton[] buttons = new JButton[3]; // 버튼 3개
    private JButton[] buttons = new JButton[2]; // 버튼 2개
    private Doll doll; // 기본 인형
    private JPanel displayPanel;
    private JPanel buttonPanel; // 버튼 패널을 클래스 변수로 선언

    public PaperDollApp() {
        setTitle("Paper Doll App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        displayPanel = new JPanel(new BorderLayout()); 

        // doll = new CatDoll(); // 기본 인형 설정
        doll = new RabbitDoll(); // 기본 인형 설정
        displayPanel.add(doll, BorderLayout.CENTER); // 인형 패널 중앙에 추가

        // buttonPanel = new JPanel(new GridLayout(1, 3)); // 버튼 패널
        // buttons[0] = new JButton("Hat");
        // buttons[1] = new JButton("Ball");
        // buttons[2] = new JButton("Toy");


        buttonPanel = new JPanel(new GridLayout(1, 2)); // 버튼 패널
        buttons[0] = new JButton("Carrot");
        buttons[1] = new JButton("Coffee");

        for (JButton button : buttons) {
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        displayPanel.add(buttonPanel, BorderLayout.SOUTH);  // 버튼 패널을 남쪽에 추가
        add(displayPanel);  // JFrame에 displayPanel을 추가한다.

        setVisible(true);   // 창을 화면에 보이게한다.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (doll == null) {
            // doll = new CatDoll(); // doll이 null일 경우 초기화
            doll = new RabbitDoll();
        }

        // 버튼에 따라 장식 추가
        // if (e.getSource() == buttons[0]) {
        //     doll = new HatDecorator(doll); // 모자 장식 추가
        // } else if (e.getSource() == buttons[1]) {
        //     doll = new BallDecorator(doll); // 공 장식 추가
        // } else if (e.getSource() == buttons[2]) {
        //     doll = new ToyDecorator(doll); // 장난감 장식 추가
        // }

        if (e.getSource() == buttons[0]) {
            doll = new CarrotDecorator(doll); // 당근 장식 추가
        } else if (e.getSource() == buttons[1]) {
            doll = new CoffeeDecorator(doll); // 커피 장식 추가
        }

        displayPanel.removeAll(); // 모든 컴포넌트 제거
        displayPanel.add(doll, BorderLayout.CENTER); // 새로운 인형 추가
        displayPanel.add(buttonPanel, BorderLayout.SOUTH); // 버튼 패널 재추가
        displayPanel.revalidate(); // 패널 갱신
        displayPanel.repaint(); // 패널 다시 그리기

    }
}
