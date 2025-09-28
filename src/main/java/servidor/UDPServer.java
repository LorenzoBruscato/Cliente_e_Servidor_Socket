package servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Servidor UDP simples - Escuta mensagens na porta 5000 - Exibe na tela o IP,
 * porta e conteúdo recebido - Finaliza quando recebe "fim"
 */
public class UDPServer {

    public static void main(String[] args) throws Exception {
        System.out.println("Servidor UDP iniciado...");

        // Cria o socket do servidor na porta 5000
        DatagramSocket serverSocket = new DatagramSocket(5000);

        // Buffer para armazenar os dados recebidos
        byte[] receiveData = new byte[1024];

        while (true) {
            // Cria pacote vazio para receber dados
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Aguarda mensagem do cliente (bloqueia até receber)
            serverSocket.receive(receivePacket);

            // Converte os bytes recebidos em String
            String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Obtém informações do cliente (IP e porta)
            InetAddress clientIP = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            // Exibe a mensagem recebida
            System.out.println("Recebido de " + clientIP.getHostAddress() + ":" + clientPort + " → " + sentence);

            // Se recebeu "fim", encerra o servidor
            if (sentence.equalsIgnoreCase("fim")) {
                break;
            }
        }

        // Fecha o socket e libera os recursos
        serverSocket.close();
        System.out.println("Servidor encerrado.");
    }
}
