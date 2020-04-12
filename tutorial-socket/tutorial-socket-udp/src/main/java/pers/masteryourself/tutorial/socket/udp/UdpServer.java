package pers.masteryourself.tutorial.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * <p>description : UdpServer
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/12 13:28
 */
public class UdpServer {

    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(6666);
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        datagramSocket.receive(receivePacket);
        System.out.println("客户端请求数据：" + new String(receiveData, 0, receivePacket.getLength()));
    }

}
