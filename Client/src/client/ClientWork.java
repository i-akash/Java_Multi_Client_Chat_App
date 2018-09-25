/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author iakas
 */
public class ClientWork extends JPanel{
    private final static int PORT=1234;
    private Socket sock;
    private DataOutputStream dos;
    private DataInputStream dis;
    
    public ClientWork() throws IOException
    {
        sock=null;
    }
    
    public void Work()
    {
        this.setLayout(null);
        
        TextField tf=new TextField("Fill server IP");
        JLabel jl=new JLabel("Active :");
        
        JButton jbip=new JButton(" ok ");
        JButton jb=new JButton(" send ");
        
        TextArea tr=new TextArea();
        TextArea ts=new TextArea();
        TextArea active=new TextArea();
        
        jb.setBounds(760,590,85,30);
        jb.setBackground(Color.LIGHT_GRAY);
        jb.setFont(new Font("Sarif",Font.BOLD,17));
        
        jbip.setBounds(760,15,85,25);
        jbip.setBackground(Color.LIGHT_GRAY);
        jbip.setFont(new Font("Sarif",Font.BOLD,17));
        
        tf.setBounds(450,15,300,25);
        tf.setBackground(Color.LIGHT_GRAY);
        tf.setFont(new Font("Sarif",Font.BOLD,14));
        
        tr.setBounds(250,100,500,400);
        tr.setBackground(Color.LIGHT_GRAY);
        tr.setFont(new Font("Sarif",Font.BOLD,14));
        
        ts.setBounds(250,520,500,100);
        ts.setBackground(Color.LIGHT_GRAY);
        ts.setFont(new Font("Sarif",Font.BOLD,14));
        
        jl.setBounds(0,70,100,25);
        jl.setForeground(Color.LIGHT_GRAY);
        jl.setFont(new Font("Sarif",Font.BOLD,17));
        
        
        active.setBounds(0,100,100,600);
        active.setBackground(Color.LIGHT_GRAY);
        active.setFont(new Font("Sarif",Font.BOLD,14));
        
        this.add(jl);
        this.add(tf);
        this.add(active);
        this.add(jb);
        this.add(jbip);
        this.add(tr);
        this.add(ts);
       
        this.setBackground(Color.DARK_GRAY);
        
        Thread rec=new Thread(new Runnable(){
            @Override
            public void run() {
                
                try {
                    dis=new DataInputStream(sock.getInputStream());
                } catch (IOException ex) {
                    Logger.getLogger(ClientWork.class.getName()).log(Level.SEVERE, null, ex);
                }
                    while(true)
                    {                        
                      String message="";
                    try {
                        message = dis.readUTF();
                    } catch (IOException ex) {
                        Logger.getLogger(ClientWork.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     active.setText("");
                      for(int i=0;i<message.length();i++)
                      {
                          if(message.charAt(i)=='$')
                            {
                                message=message.substring(i+1);
                                break;
                            }
                            else
                             {
                                Character c=message.charAt(i); 
                                active.append(c.toString());
                                active.append("#\n");
                             }
                       }       
                      tr.append(message);
                      tr.append("\n");
                    }
                    
            }
        });
        
        
         jb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if(sock!=null)
                    {
                        dos=new DataOutputStream(sock.getOutputStream());
                        dos.writeUTF(ts.getText().toString());
                        ts.setText("");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClientWork.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
          });
          
        
        jbip.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {       
                    sock=new Socket(tf.getText().toString(),PORT);
                    rec.start();
                } catch (IOException ex) {
                    Logger.getLogger(ClientWork.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
}
