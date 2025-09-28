package cliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 * Cliente UDP simples
 * - Envia mensagens para o servidor na porta 5000
 * - Usa JOptionPane para entrada de dados
 * - Finaliza quando o usuário digita "fim"
 */
public class UDPClient {

    public static void main(String[] args) throws Exception {
        // Cria o socket do cliente (sem porta fixa, o SO escolhe uma livre)
        DatagramSocket clientSocket = new DatagramSocket();

        // Endereço do servidor (localhost = mesma máquina)
        InetAddress IPAddress = InetAddress.getByName("localhost");

        while (true) {
            // Caixa de diálogo para o usuário digitar a mensagem
            String sentence = JOptionPane.showInputDialog("Digite:");

            // Converte a string em bytes para enviar
            byte[] sendData = sentence.getBytes();

            // Cria o pacote UDP para enviar (destino: IP e porta do servidor)
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5000);

            // Envia o pacote pelo socket
            clientSocket.send(sendPacket);

            // Se o usuário digitou "fim", encerra o cliente
            if (sentence.equalsIgnoreCase("fim")) {
                break;
            }
        }

        // Fecha o socket e libera os recursos
        clientSocket.close();
    }
}
