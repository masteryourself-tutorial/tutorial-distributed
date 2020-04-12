package pers.masteryourself.tutorial.socket.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>description : TcpServer
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/12 13:13
 */
public class TcpServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 6666));
        PrintWriter out = null;
        BufferedReader in = null;
        while (true) {
            Socket socket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("客户端请求数据：" + in.readLine());
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("receive success");
            in.close();
            out.close();
            socket.close();
        }
    }

}
