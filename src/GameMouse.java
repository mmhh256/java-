import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.IllegalFormatCodePointException;

public class GameMouse implements MouseListener, ActionListener {
    public int x0 = 50, y0 = 50, size = 50, line = 16, chesssize = 50;
    //棋子数量
    public int count=0;
    //存储棋盘棋子的数据
    public int[][]  Data=new int[line][line];
    //棋子落在网格上的位置
    public int ChessX=0,ChessY=0;

    public int flag=0,num=0,AI_flag=0;;

    public Chess[] arr=new Chess[line*line];//记录棋子数组

    private ChessBoard chessboard;
    private Graphics g;

    public GameMouse( ChessBoard chessboard, Graphics g) {
        this.chessboard = chessboard;
        this.g = g;
    }

    //棋盘初始化清空
    public void clean(){
        for (int i=0;i<line;i++)
        {
            for (int j=0;j<line;j++)
            {
                Data[i][j]=0;
            }
        }
        chessboard.repaint();
    }

    public void drawstr(String str){
        g.setColor(Color.RED);
        g.setFont(new Font("楷体",Font.BOLD,90));
        g.drawString(str,chessboard.getWidth()/2-200,chessboard.getHeight()/2-40);
    }

    //按钮监听器
    public void actionPerformed(ActionEvent e){
        //获取按钮内容
        String content=e.getActionCommand();
        switch (content)
        {
            case "开始" :
                flag=1;
                clean();
                count=0;
                num=0;
                AI_flag=0;
                for (int i=0;i<arr.length;i++){
                    arr[i]=null;
                }
                break;
            case "悔棋" :
                if (num>1 && flag==1)
                {
                    if (AI_flag==1)
                    {   //从一维数组删去
                        Chess chess=arr[num-1];
                        Data[chess.x][chess.y]=0;
                        chess.flag=0;
                        chess=arr[num-1];
                        Data[chess.x][chess.y]=0;
                        chess.flag=0;
                        count-=2;
                        num-=2;
                        chessboard.repaint();
                    }else{
                        Chess chess=arr[num-1];
                        Data[chess.x][chess.y]=0;
                        chess.flag=0;
                        num--;
                        count--;
                    }
                }
                break;
            case "复盘" :
                clean();
                chessboard.paint(g);
                for (int i=0;i<arr.length;i++)
                {
                    if (arr[i]!=null)
                    {
                        Chess chess=arr[i];
                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException e1){
                            throw new RuntimeException(e1);
                        }
                        if (chess.flag==1)
                        {
                            g.setColor(Color.BLACK);
                            g.fillOval(ChessX*size+x0-chesssize/2,ChessY*size+y0-chesssize/2,chesssize,chesssize);
                            Data[chess.x][chess.y]=1;
                        } else if (chess.flag==2) {
                            g.setColor(Color.white);
                            g.fillOval(ChessX*size+x0-chesssize/2,ChessY*size+y0-chesssize/2,chesssize,chesssize);
                            Data[chess.x][chess.y]=2;
                        }

                    }
                }
                break;
            case "退出" :
                System.exit(0);
                break;
            case "双人对战" :
                chessboard.repaint();
                drawstr("双人对战，启动！");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e1){
                    throw new RuntimeException(e1);
                }
                chessboard.repaint();
                break;
            case "人机对战" :
                chessboard.repaint();
                drawstr("人机对战，启动！");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e1){
                    throw new RuntimeException(e1);
                }
                chessboard.repaint();
                AI_flag=1;
                break;
        }

    }




    public void mouseClicked(MouseEvent e){
        //通过鼠标监听器获取点击的坐标
        int x=e.getX();
        int y=e.getY();

        //实现棋子落点在交点上
        if ((x - x0) % size > size / 2){
            ChessX=((x - x0) % size) + 1;
        }else {
            ChessX=(( x - x0) % size);
        }
        if (( x - x0) % size > size / 2){
            ChessY=((x - x0) % size) + 1;
        }else {
            ChessY=((x - x0) % size);
        }

        //判断该交点已落子，并提醒
        if (Data[ChessX][ChessY]==1 || Data[ChessX][ChessY]==2){
            System.out.println("无法下在这里");
        }else if (flag == 0){
            return ;
        }else{
            if (count % 2==0){
                g.setColor(Color.BLACK);
                g.fillOval(ChessX*size+x0-chesssize/2,ChessY*size+y0-chesssize/2,chesssize,chesssize);
                Chess chess=new Chess(ChessX,ChessY,1);
                arr[num]=chess;
                Data[ChessX][ChessY]=1;
            }
            if (count % 2==1){
                g.setColor(Color.white);
                g.fillOval(ChessX*size+x0-chesssize/2,ChessY*size+y0-chesssize/2,chesssize,chesssize);
                Chess chess=new Chess(ChessX,ChessY,2);
                arr[num]=chess;
                Data[ChessX][ChessY]=2;
            }
            num ++;
            count ++;
        }
        if(AI_flag==1)
        {

        }
        //判断输赢
        if(AI_flag==0)
        {
            if(whowin()>=4)
            {
                if(Data[ChessX][ChessY]==1)
                {
                    System.out.println("Black win!");
                    flag=0;
                    drawstr("黑棋赢！");
                }
                else if(Data[ChessX][ChessY]==2)
                {
                    System.out.println("White win!");
                    flag=0;
                    drawstr("白棋赢！");
                }
            }
        }
    }



    //判断谁赢函数
    public int whowin() {
        //记录最大连子个数
        int number = 0;

        //横方向
        //判断落子右边
        for (int i = ChessX + 1; i <= line; i++) {
            if (Data[i][ChessY] == Data[ChessX][ChessY]) {
                number++;
            } else break;
        }
        //判断落子左边
        for (int i = ChessX - 1; i >= 0; i--) {
            if (Data[i][ChessY] == Data[ChessX][ChessY]) {
                number++;
            } else break;
        }
        if (number == 4)
            return number;
        number = 0;//再初始化

        //竖方向
        //判断落子上边
        for (int i = ChessY + 1; i <= line; i++) {
            if (Data[ChessY][i] == Data[ChessX][ChessY]) {
                number++;
            } else break;
        }
        //判断落子下边
        for (int i = ChessY - 1; i >= 0; i--) {
            if (Data[ChessY][i] == Data[ChessX][ChessY]) {
                number++;
            } else break;
        }
        if (number == 4)
            return number;
        number = 0;//再初始化

        //斜下方向
        int i, j;
        for (i = ChessX + 1, j = ChessY + 1; i <= line && j <= line; i++, j++) {
            if (Data[i][j] == Data[ChessX][ChessY]) {
                number++;
            } else break;
        }
        for (i = ChessX - 1, j = ChessY - 1; i >= 0 && j >= 0; i--, j--) {
            if (Data[i][j] == Data[ChessX][ChessY]) {
                number++;
            } else break;
        }
        if (number == 4)
            return number;
        number = 0;//再初始化

        //斜上方向
        for (i = ChessX + 1, j = ChessY - 1; i <= line && j >= 0; i++, j--) {
            if (Data[i][j] == Data[ChessX][ChessY]) {
                number++;
            } else break;
        }
        for (i = ChessX - 1, j = ChessY + 1; i >= 0 && j <= line; i++, j--) {
            if (Data[i][j] == Data[ChessX][ChessY]) {
                number++;
            } else break;
        }
        if (number == 4)
            return number;
        number = 0;//再初始化
        return 0;
    }

























    public void mousePressed(MouseEvent e){};
    public void mouseReleased(MouseEvent e){};
    public void mouseEntered(MouseEvent e){};
    public void mouseExited(MouseEvent e){};
}

