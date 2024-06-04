import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel {
    public int[][] Data;
    public int x0 = 50, y0 = 50, size = 50, line = 16, chesssize = 50;

    //继承JPanel内的方法
    //重写paint方法
    public void paint(Graphics g) {
        //重绘窗体
        super.paint(g);
        //绘制棋盘
        for (int i = 0; i < line; i++) {
            g.setColor(Color.black);//设置颜色
            g.drawLine(x0, size * i + y0, (line - 1) * size + x0, i * size + y0);//画横线
            g.drawLine(size * i + x0, y0, size * i + x0, (line - 1) * size + y0);//画竖线
        }


        for (int i = 0; i < line; i++) {
            for (int j=0;j<line;j++){
                if (Data[i][j]==1)
                {
                    g.setColor(Color.BLACK);
                    g.fillOval(i*size+x0-chesssize/2,j*size+y0-chesssize/2,chesssize,chesssize);
                } else if (Data[i][j]==2) {
                    g.setColor(Color.white);
                    g.fillOval(i*size+x0-chesssize/2,j*size+y0-chesssize/2,chesssize,chesssize);
                }else if (Data[i][j]==0){

                }
            }

        }
   }
}