import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{
private  JFrame frame;//游戏界面窗口


private ChessBoard chessboard=new ChessBoard();//创建棋盘


    public static void main(String[] args) throws InterruptedException {
           GameFrame frame=new GameFrame();//创建游戏
           frame.init();//游戏初始化

    }


    public void init(){
        frame=new JFrame("五子棋");//创建游戏界面窗口
        frame.setSize(1100,900);//设置界面的位置和大小
        frame.setLocationRelativeTo(null);//界面居中
        frame.setResizable(true);//不可改变界面大小
        frame.setVisible(true);//设置界面可见
        frame.setAlwaysOnTop(true);//界面总是在最上面
        frame.setDefaultCloseOperation(3);//停止运行

        //添加一个侧边蓝，用来放置按钮
        JPanel panel=new JPanel();//创建面板
        panel.setPreferredSize(new Dimension(200,0));//设置面板尺寸
        frame.add(panel,BorderLayout.EAST);//设置面板在界面中的对应方位
        panel.setBackground(Color.ORANGE);//设置面板的颜色

        //添加按钮功能
        JButton start=new JButton("开始");//添加“开始那游戏”按钮
        start.setFont(new Font("宋体",Font.BOLD,20));//设置按钮字体

        JButton regret=new JButton("悔棋");//添加“悔棋”按钮
        regret.setFont(new Font("宋体",Font.BOLD,20));//设置按钮字体

        JButton replay = new JButton("复盘");//添加“复盘”按钮
        replay.setFont(new Font("宋体",Font.BOLD,20));//设置按钮字体

        JButton exit = new JButton("退出");//添加“退出”按钮
        exit.setFont(new Font("宋体",Font.BOLD,20));//设置按钮字体

        JButton AI_play = new JButton("人机对战");//添加“人机对战”按钮
        AI_play.setFont(new Font("黑体",Font.BOLD,20));//设置按钮字体

        JButton human_play = new JButton("双人对战");//添加“双人对战”按钮
        human_play.setFont(new Font("黑体",Font.BOLD,20));//设置按钮字体

        //把按钮添加到面板
        panel.add(start);
        panel.add(regret);
        panel.add(replay);
        panel.add(exit);
        panel.add(AI_play);
        panel.add(human_play);
        panel.setLayout(null);//设置面板的布局为空

        //设置按钮在侧边栏里的位置
        start.setBounds(50,100,90,50);
        regret.setBounds(50,200,90,50);
        replay.setBounds(50,300,90,50);
        exit.setBounds(50,400,90,50);
        AI_play.setBounds(40,600,120,40);
        human_play.setBounds(40,700,120,40);

        //设置棋盘面板
        ChessBoard chessBoard = new ChessBoard();
        frame.add(chessBoard,BorderLayout.CENTER);//放在中间
        chessBoard.setBackground(Color.lightGray);//设置棋盘颜色

         class MyButtonLister implements ActionListener {
            //按钮处理事件类
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Object obj=e.getSource();//获取事件源
                if(obj==start) {//事件源是重新开始按钮
                    System.out.println("重新开始");
                }
                else if(obj==regret) {//事件源是悔棋按钮
                    System.out.println("悔棋！");

                }
                else if(obj==replay) {//事件源是退出按钮
                    System.out.println("再来一局");
                }
                else if(obj==exit) {//事件源是退出按钮
                    System.exit(0);
                }
                else if(obj==AI_play) {//事件源是退出按钮

                }
                else if(obj==human_play) {//事件源是退出按钮

                }
                }
            }

        Graphics g = chessBoard.getGraphics();//设置画笔

        GameMouse gameMouse=new GameMouse(chessboard,g);//添加鼠标监听器
        //传递数据
        chessboard.Data=gameMouse.Data;
        //创建对象时将构造方法初始化
        //将这里从窗口中获取得到的画笔用来给监听器中私有属性定义的画笔初始化
        chessboard.addMouseListener(gameMouse);
        //添加按钮监听器
        start.addMouseListener(gameMouse);
        regret.addMouseListener(gameMouse);
        replay.addMouseListener(gameMouse);
        exit.addMouseListener(gameMouse);
        AI_play.addMouseListener(gameMouse);
        human_play.addMouseListener(gameMouse);



















//        JMenuBar menuBar=new JMenuBar();//创建菜单栏
//        JMenu menu=new JMenu("选项");//创建菜单栏中的“选项”菜单




//        JDialog dialog = new JDialog(frame, "我是对话框", true);
//        dialog.setResizable(false);
//        dialog.add(new JLabel("确定是否要退出程序？"), BorderLayout.NORTH);   //对话框默认采用的是边界布局
//        JButton button1=new JButton("确定");
//        JButton button2=new JButton("取消");
//        dialog.add(button2, BorderLayout.WEST);
//        dialog.add(button1, BorderLayout.EAST);
//        dialog.setSize(200, 80);

//
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                dialog.setVisible(true);
//;            }
//
//        });//界面关闭退出游戏
//
//        button1.addActionListener(e->System.exit(0) );
//        button2.addActionListener(e-> {dialog.dispose();
//                });



//        JButton button = new JButton("开始游戏");
//        button.setBounds(20, 50, 100, 50);
//        frame.add(button);
//        button.addActionListener(e-> System.out.println("游戏马上开始"));


//
//        JMenuItem Repaly=new JMenuItem("重新开始游戏");
//        menu.add(Repaly);
//        menuBar.add(menu);
//        frame.setJMenuBar(menuBar);
//












    }
}