/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author iakas
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         JFrame jf=new JFrame(" ME ");
         ClientWork cw=new ClientWork();
         jf.add(cw);
         jf.setSize(900,700);
         jf.setLocationRelativeTo(null);
         jf.setVisible(true);
         jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         cw.Work();
    }
    
}
