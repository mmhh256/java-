//棋子类
public class Chess {
    //定义
    public int x;//定义棋子x坐标
    public int y;//定义棋子y坐标
    public int flag;//用来判断棋子颜色或为空，0为空，1为黑子，2为白子

    //构造方法
    public Chess(int x, int y, int flag) {
        this.x = x;
        this.y = y;
        this.flag = flag;
    }
}
