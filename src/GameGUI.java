
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JFrame implements ActionListener {
    int lx,ly;
    private World world;
    //用一个个按钮当作细胞
    private  JButton[][] TWorld;//世界界面
    private JLabel NowGeneration;
    private JButton randomInit,BeginAndOver,StopAndContinue,Next,Exit;//随机生成第一代、重新生成、开始游戏与结束游戏、暂停游戏与继续游戏、下一代、退出按钮
    private boolean isRunning;//游戏进程控制
    private Thread thread;//游戏线程
    private Cell[][] cell=new Cell[80][80];

    public GameGUI(String name,World world){
        super(name);
        this.lx=world.getLx();
        this.ly=world.getLy();
        this.world=world;
        initGameGUI();
    }
 
    public void initGameGUI(){
        JPanel backPanel,bottomPanel,centerPanel;
        backPanel= new JPanel(new BorderLayout());
        bottomPanel=new JPanel();
        centerPanel=new JPanel(new GridLayout(lx,ly));
        this.setContentPane(backPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        backPanel.add(centerPanel,"Center");
        backPanel.add(bottomPanel,"South");
 
        TWorld=new JButton[lx][ly];
        NowGeneration=new JLabel("当前代数：0");
        randomInit=new JButton("随机生成细胞");
        BeginAndOver=new JButton("开始游戏");
        StopAndContinue=new JButton("暂停游戏");
        Next=new JButton("下一代");
        Exit=new JButton("退出");
        for(int x=0;x<lx;x++){
            for(int y=0;y<ly;y++){
                TWorld[x][y]=new JButton("");
                TWorld[x][y].setBackground(Color.white);
                centerPanel.add(TWorld[x][y]);
            }
        }
 
        bottomPanel.add(randomInit);
        bottomPanel.add(BeginAndOver);
        bottomPanel.add(StopAndContinue);
      //  bottomPanel.add(Next);
        bottomPanel.add(NowGeneration);
        bottomPanel.add(Exit);
 
        //设置窗口
        int sizelx,sizely;
        sizelx=Math.min((lx+1)*40,400);
        sizely=Math.min(ly*40,600);
       // sizely=Math.max(ly*40,500);
        this.setSize(sizely,sizelx);
        this.setResizable(true);
        this.setLocationRelativeTo(null);//让窗口居中显示
        this.setVisible(true);
 
        //注册监听器
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e){
                System.exit(0);
            }
        });
        randomInit.addActionListener(this);
        BeginAndOver.addActionListener(this);
        StopAndContinue.addActionListener(this);
        Next.addActionListener(this);
        Exit.addActionListener(this);
    }
    
    public String get_btnBeginAndOver() {
    	return BeginAndOver.getText();
    }
    public String get_btnrandomInit() {
    	return  randomInit.getText();
    }
    public String get_btnStopAndContinue() {
    	return  StopAndContinue.getText();
    }
    public boolean Running() {
    	return isRunning;
    }
 //对按键处理
    
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == randomInit&&BeginAndOver.getText()=="开始游戏") {//随机生成第一代
            world.randonInitCell();
            showWorld();
            isRunning = false;
            thread = null;
            randomInit.setText("重新生成");
        } else if (e.getSource() == BeginAndOver && BeginAndOver.getText() == "开始游戏"&&randomInit.getText()=="重新生成") {//开始游戏
            isRunning = true;
            thread = new Thread(new Runnable() {   //实现Runnable接口的run方法去创建多线程
                @Override
                public void run() {
                    while (isRunning) {
                        Change();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
            
            thread.start();
            BeginAndOver.setText("结束游戏");
        } else if (e.getSource() == BeginAndOver && BeginAndOver.getText() == "结束游戏") {//结束游戏
            isRunning = false;
            thread = null;
            world.deleteAllCell();
            showWorld();
            BeginAndOver.setText("开始游戏");
            StopAndContinue.setText("暂停游戏");
            randomInit.setText("随机生成细胞");
            NowGeneration.setText("当前代数：0");
        } else if (e.getSource() == StopAndContinue && StopAndContinue.getText() == "暂停游戏") {//暂停
            isRunning = false;
            thread = null;
            StopAndContinue.setText("继续游戏");
        } else if (e.getSource() == StopAndContinue && StopAndContinue.getText() == "继续游戏") {//继续
            isRunning = true;
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        Change();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
            StopAndContinue.setText("暂停游戏");
        } /*else if (e.getSource() == Next && StopAndContinue.getText() == "继续游戏") {//下一代
            Change();
            isRunning = false;
            thread = null;
        }*/else if(e.getSource()==Exit){//退出游戏
            isRunning = false;
            thread = null;
            this.dispose();
            System.exit(0);
        }
    }
    public String Text_NowGeneration() {
    	return  NowGeneration.getText();
    }
    public int getNowGeneration() {
    	return world.getNowGeneration();
    }
    public void Change(){
        world.updateOfCell();
        showWorld();
        NowGeneration.setText("当前代数："+world.getNowGeneration());
    }

    public void showWorld(){
        for(int x=0;x<lx;x++){
            for(int y=0;y<ly;y++){
                if(world.getCellXY(x,y)){
                    TWorld[x][y].setBackground(Color.black);
                }
                else{
                    TWorld[x][y].setBackground(Color.white);
                }
            }
        }
    }
}

