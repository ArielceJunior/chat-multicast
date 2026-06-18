package br.edu.ifsuldeminas.sd.chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastSender implements Sender {

    private InetAddress group;
    private int port;
    private DatagramSocket socket;

    public MulticastSender(String groupIp, int port) throws ChatException {

        try {

            this.group = InetAddress.getByName(groupIp);
            this.port = port;

            socket = new DatagramSocket();

        } catch (Exception e) {

            throw new ChatException(
                    "Erro ao criar sender multicast.",
                    e
            );
        }
    }

    @Override
    public void send(String message) throws ChatException {

        try {

            byte[] buffer =
                    message.getBytes();

            DatagramPacket packet =
                    new DatagramPacket(
                            buffer,
                            buffer.length,
                            group,
                            port
                    );

            socket.send(packet);

        } catch (Exception e) {

            throw new ChatException(
                    "Erro ao enviar mensagem multicast.",
                    e
            );
        }
    }
}