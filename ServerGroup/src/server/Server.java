/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author iakas
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JFrame jf=new JFrame("Server");
        ServerWork sw=new ServerWork();
        
        jf.add(sw);
        jf.setSize(800,600);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sw.work();
    }
    
}
