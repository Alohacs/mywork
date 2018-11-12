package com.gx.web.util.aiRobot.test;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MainClass extends JFrame implements ActionListener{
    JButton an1,an2;
    JTextField bjk;
    JTextArea area;
    JLabel bq1,bq2;
    JPanel p1;
    public static void main(String[] args){
        Write.read();
        //System.out.println("chushihua");
        new MainClass();
    }
    public MainClass(){
 
        an1=new JButton("发送");
        an2=new JButton("退出");
        bjk=new JTextField(10);
        area=new JTextArea();
        bq1=new JLabel("这里是[居正]出品的聊天机器人，快跟它打个招呼吧~");
        bq2=new JLabel("请输入内容并发送:");
 
        p1=new JPanel();
        p1.add(bq2);p1.add(bjk);p1.add(an1);p1.add(an2);
 
        an1.addActionListener(this);
        an1.setActionCommand("go");
         
        an2.addActionListener(this);
        an2.setActionCommand("exit");
        this.add(bq1,BorderLayout.NORTH);
        this.add(area);
        this.add(p1,BorderLayout.SOUTH);
        this.setTitle("[居正]聊天机器人");
        this.setSize(500,400);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand().equals("go")){
            String text=bjk.getText();
            //System.out.println(text);
            String texttest=robotedit();
            System.out.println("texttest:"+texttest);
            area.setText("    我说：\n"+text+"\n\n    [JUZEON]Robot说：\n"+texttest);
            bjk.setText("");
        }else if(e.getActionCommand().equals("exit")){
            System.exit(0);
        }
    }
    public String robotedit(){
        /////////////////////////////////////////////////////////////////////String re="我还在进步，有些问题回答不上来哦~Sorry~";
        try{
        for(int i=0;i<Write.wen.length;i++){
            String mywen=Write.wen[i];
            String tada=Write.da[i];
            System.out.println(mywen);
            System.out.println(tada);
            String text=bjk.getText();
            int bl=text.indexOf(mywen);
 
            if((bl)==(-1)){
 
            }else{
                return tada;
            }
 
 
        }
        }catch(Exception ergg){
            //return "我还在进步，有些问题回答不上来哦~Sorry~";
        }
        System.out.println("到了返回这里了~");
        return "我还在进步，有些问题回答不上来哦~Sorry~";
         
    }
 
}
class Write{
    static String wen[]=new String[1000];
    static String da[]=new String[1000];
 
    static FileWriter fw;
    static BufferedWriter bw;
 
    static FileReader fr;
    static BufferedReader br;
    static int shu=0;
    public static void write(){
        try{
            fw=new FileWriter("./talk.txt");
            bw=new BufferedWriter(fw);
            bw.write("你好 见到你真高兴~\n");
            bw.write("作者 是居正这个BT的家伙创造了我~\n");
            bw.write("住 我住在我家~\n");
            bw.write("石头 我出布，你输了~\n");
            bw.write("剪刀 我出石头，你输了~\n");
            bw.write("布 我出剪刀，我赢了~~\n");
            bw.write("玩 来玩石头剪刀布吧~\n");
        }catch(Exception e){}
        finally{
            try{
                bw.flush();
                fw.close();
                bw.close();
            }catch(Exception e){System.out.println(e.toString());}
        }
    }
    public static void read(){
        System.out.println("readStart");
        try{
            File f=new File("./talk.txt");
            System.out.println("set");
            if(f.exists()==false){
                write();
                System.out.println("write");
            }
            fr=new FileReader("./talk.txt");
            br=new BufferedReader(fr);
 
            String s="";
            int i=0;
            while((s=br.readLine())!=null){
                System.out.println(s);
                String[] st=s.split(" ");
                System.err.println(st[0]);
                System.err.println(st[1]);
                wen[i]=st[0];
                da[i]=st[1];
                i++;
                System.out.println("No."+i+":"+wen[i]);
                System.out.println("No."+i+":"+da[i]);
            }
 
        }catch(Exception e){System.err.println(e.toString());}
 
    }
}
