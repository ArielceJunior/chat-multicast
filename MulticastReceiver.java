package br.edu.ifsuldeminas.sd.chat;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver implements Receiver {

    private static final int BUFFER_SIZE = 1024;

    private MessageContainer container;
    private MulticastSocket socket;
    private boolean running;

    public MulticastReceiver(
            String groupIp,
            int port,
            MessageContainer container
    ) throws ChatException {

        try {

            this.container = container;

            socket =
                    new MulticastSocket(port);

            InetAddress group =
                    InetAddress.getByName(groupIp);

            socket.joinGroup(group);

            new Thread(this).start();

        } catch (Exception e) {

            throw new ChatException(
                    "Erro ao criar receiver multicast.",
                    e
            );
        }
    }

    @Override
    public void run() {

        running = true;

        while (running) {

            try {

                byte[] buffer =
                        new byte[BUFFER_SIZE];

                DatagramPacket packet =
                        new DatagramPacket(
                                buffer,
                                buffer.length
                        );

                socket.receive(packet);

                String message =
                        new String(
                                packet.getData(),
                                0,
                                packet.getLength()
                        );

                container.newMessage(
                        message
                );

            } catch (Exception e) {

                container.newMessage(
                        "Erro ao receber mensagem multicast."
                );
            }
        }
    }
}