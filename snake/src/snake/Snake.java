package snake;
//made by 黎展鹏（虽然是照着视频编的....）
import javax.swing.*;
public class Snake {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(200,50,900,800);  //设置网格大小
       frame.setResizable(true);   //禁止改变大小
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //按退出键会怎样      
      frame.add(new MP());  //添加工具
       frame.setVisible(true);  //使窗口出现
     
      
	}

}
