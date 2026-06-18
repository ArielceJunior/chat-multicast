package br.edu.ifsuldeminas.sd.chat;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ChatFactory {
	private static int DEFAULT_RECEIVER_BUFFER_SIZE = 1000;
	private static String serverName;
	private static int serverPort;
	private static int localPort;
	private static MessageContainer container;

	public static Sender build(String protocol,
			String serverName,
			int serverPort,
			int localPort,
			MessageContainer container)
					throws ChatException {

		ChatFactory.serverName = serverName;
		ChatFactory.serverPort = serverPort;
		ChatFactory.localPort = localPort;
		ChatFactory.container = container;

		try {

			if (protocol.equalsIgnoreCase("UDP")) {

				new UDPReceiver(
						localPort,
						DEFAULT_RECEIVER_BUFFER_SIZE,
						container
						);

				return new UDPSender(
						InetAddress.getByName(serverName),
						serverPort
						);

			} else if (protocol.equalsIgnoreCase("TCP")) {

				new TCPReceiver(
						localPort,
						container
						);

				return new TCPSender(
						serverName,
						serverPort
						);

			} else if (protocol.equalsIgnoreCase("MULTICAST")) {

				new MulticastReceiver(
						serverName,
						serverPort,
						container
						);

				return new MulticastSender(
						serverName,
						serverPort
						);
			}

			throw new ChatException(
					"Protocolo inválido.",
					null
					);

		} catch (UnknownHostException e) {

			throw new ChatException(
					"Servidor não conhecido.",
					e
					);
		}
	}}