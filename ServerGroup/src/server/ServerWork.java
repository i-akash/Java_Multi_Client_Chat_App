/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.ScrollPane;
import java.awt.TextArea;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author iakas
 */
public class ServerWork extends JPanel{
      
       private static final int PORT=1234;
       private ServerSocket ss;
       private Socket sock;
       private int id=1;
       public ArrayList<NewClient> client;
       
       public ServerWork() throws IOException
       {
           ss=new ServerSocket(PORT);
           client=new ArrayList<>();
       }
       
       public void work() throws IOException
       {
             this.setLayout(null);
             JLabel jb=new JLabel("Connected ID : ");
             ScrollPane sp=new ScrollPane();
             TextArea ta=new TextArea();
             sp.setBounds(100,100,500,300);
             ta.setBounds(100,100,500,300);
             jb.setBounds(100,80,300,20);
             sp.add(ta);
             this.add(sp);
             this.add(jb);
             
             while(true)
             {
                 sock=null;
                 sock=ss.accept();
                 if(sock==null)continue;
                 
                 Thread t1=new Thread(new NewClient(sock,id,client));
                 id++;
                 t1.start();
                 ta.setText("");
                 for(int i=0;i<client.size();i++)
                 {
                     if(client.get(i).status==true)
                      ta.append(client.get(i).id.toString());
                 }
             }
       }
}
