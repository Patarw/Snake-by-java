package snake;
//made by ��չ������Ȼ��������Ƶ���....��
import javax.swing.*;
public class Snake {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(200,50,900,800);  //���������С
       frame.setResizable(true);   //��ֹ�ı��С
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //���˳���������      
      frame.add(new MP());  //��ӹ���
       frame.setVisible(true);  //ʹ���ڳ���
     
      
	}

}
