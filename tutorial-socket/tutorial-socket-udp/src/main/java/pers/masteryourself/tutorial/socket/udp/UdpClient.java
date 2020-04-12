package pers.masteryourself.tutorial.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * <p>description : UdpClient
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/12 13:28
 */
public class UdpClient {

    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] sendData = "hello".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, new InetSocketAddress("127.0.0.1", 6666));
        datagramSocket.send(sendPacket);
    }

}
