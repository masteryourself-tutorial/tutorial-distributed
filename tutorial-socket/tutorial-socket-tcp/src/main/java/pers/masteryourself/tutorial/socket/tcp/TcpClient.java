package pers.masteryourself.tutorial.socket.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <p>description : TcpClient
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/12 13:13
 */
public class TcpClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 6666);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("hello");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("服务单响应数据：" + in.readLine());
        in.close();
        socket.close();
    }

}
