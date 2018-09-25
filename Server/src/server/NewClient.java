/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iakas
 */
public class NewClient implements Runnable{
     public Integer id;
     public boolean status;
     public Socket ownSock;
     public DataInputStream dis;
     public ArrayList<NewClient> client;
     
     public NewClient(Socket ownSock,int id,ArrayList<NewClient>client)
     {
         this.id=id;
         this.ownSock=ownSock;
         status=true;
         this.client=client;
         
         client.add(this);
     }
     
    @Override
    public void run() {
         try {
                 dis=new DataInputStream(ownSock.getInputStream());
             } catch (IOException ex) {
                 Logger.getLogger(NewClient.class.getName()).log(Level.SEVERE, null, ex);
             }
         String message=" ";
         
         while(!message.equals("BYE"))
         {
             String s=" ";
             try {
                 s=dis.readUTF();
             } catch (IOException ex) {
                 Logger.getLogger(NewClient.class.getName()).log(Level.SEVERE, null, ex);
             
             }
             
             StringTokenizer st=new StringTokenizer(s,"#");
             int to=Integer.parseInt(st.nextToken());
             message=st.nextToken();
             
             int flag=-1;
             StringBuffer active=new StringBuffer("");
             for(Integer i=0;i<client.size();i++)
             {
                 if(client.get(i).id==to)
                 {
                     if(client.get(i).status==true)
                     {
                         flag=i;
                     }
                 }
                 if(client.get(i).status==true)
                     active.append(client.get(i).id.toString());
             }
             
             if(flag>-1)
             {
                        try {
                             new DataOutputStream(client.get(flag).ownSock.getOutputStream()).writeUTF(active.toString()+"$"+id.toString()+" # "+message);
                         } catch (IOException ex) {
                             Logger.getLogger(NewClient.class.getName()).log(Level.SEVERE, null, ex);
                         }
             }
             
         }
         status=false;
    }
}
