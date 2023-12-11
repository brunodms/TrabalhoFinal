package Jogo;

import java.util.*;

public class Main {
    private static final int NUM_CARTAS = 109;
    private static final int NUM_RODADAS = 12;
    private static List<Carta> baralho = new ArrayList<>();
    private static List<Jogador> jogadores = new ArrayList<>();
    private static ArrayList<LinkedList<Carta>> tabuleiro = new ArrayList<LinkedList<Carta>>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Passo 2: Inicializar o baralho
        for (int i = 1; i <= NUM_CARTAS; i++) {
            baralho.add(new Carta(i));
        }
        Collections.shuffle(baralho);
        // Passo 3: Inicializar jogadores
        int numJogadores;
        do {
            System.out.print("Digite o número de jogadores (3-6): ");
            numJogadores = scanner.nextInt();
            if (numJogadores < 3 || numJogadores > 6) {
                System.out.println("Número de jogadores inválido. O número de jogadores deve ser de 3 a 6!");
            }
        } while (numJogadores < 3 || numJogadores > 6);

        scanner.nextLine();
        for (int i = 1; i <= numJogadores; i++) {
            System.out.print("Digite o nome do jogador " + i + ": ");
            String nome = scanner.nextLine();
            jogadores.add(new Jogador(nome));
        }
        // Passo 4: Inicializar tabuleiro
        for (int i = 0; i < 5; i++) {
            tabuleiro.add(new LinkedList<Carta>());
            tabuleiro.get(i).add(baralho.remove(baralho.size() - 1));
        }
        // Passo 5b: Distribuir cartas
        for (Jogador jogador : jogadores) {
            for (int i = 0; i < 12; i++) {
                jogador.adicionarCarta(baralho.remove(0));
            }
        }
        printTabuleiro(tabuleiro);
        System.out.println("");
        // Passo 5: Jogar rodadas
        for (int r = 1; r <= NUM_RODADAS; r++) {
            System.out.println("Rodada " + r);
            System.out.println("----------");

            // Passo 5d: Escolher cartas
            for (Jogador jogador : jogadores) {
                System.out.println("Vez de " + jogador.getNome() + ".");
                System.out.println("Sua mão: " + jogador.getMao());
                int carta;
                do {
                    System.out.print("Digite a carta a ser jogada: ");
                    carta = scanner.nextInt();
                    scanner.nextLine();
                    if (!jogador.validarCartaEscolhida(carta)) {
                        System.out.println("Carta escolhida não está na mão do jogador. Escolha novamente.");
                    }
                } while (!jogador.validarCartaEscolhida(carta));
                jogador.setCartaEscolhida(new Carta(carta));
            }
            // Passo 5e:
            Collections.sort(jogadores, new JogadorComp());
            for (Jogador jogador : jogadores) {
                System.out.println("Jogador " + jogador.getNome() + "carta: " + jogador.getCartaEscolhida() + " pontos: " + jogador.getCartaEscolhida().getPontos());
            }
            // Passo 5f:
            for (Jogador jogador : jogadores) {
                int linha = jogador.jogarCarta(jogador, tabuleiro);
                tabuleiro.get(linha).addLast(jogador.getCartaEscolhida());
            }
            // Passo 5g: Mostrar tabuleiro e pontos
            printTabuleiro(tabuleiro);
            System.out.println("");
            for (Jogador jogador : jogadores) {
                System.out.println("Pontuação de " + jogador.getNome() + ": " + jogador.getPontuacao());
            }
            System.out.println();
        }
        // Passo 5h-i: Calcular vencedor
        Jogador vencedor = null;
        for (Jogador jogador : jogadores) {
            if (vencedor == null || jogador.getPontuacao() < vencedor.getPontuacao()) {
                vencedor = jogador;
            }
        }
        System.out.println("Fim do jogo!");
        System.out.println("Pontuações finais:");
        for (Jogador jogador : jogadores) {
            System.out.println("Pontuação de " + jogador.getNome() + ": " + jogador.getPontuacao());
            System.out.println("Cartas de " + jogador.getNome() + ": " + jogador.getMonte());
        }
        System.out.println("Vencedor: " + vencedor.getNome());
        scanner.close();
    }

    private static void printTabuleiro(ArrayList<LinkedList<Carta>> tabuleiro) {
        System.out.println("\nTabuleiro atual:");
        for (LinkedList<Carta> linha : tabuleiro) {
            for (Carta carta : linha) {
                System.out.printf("[%3d] ", carta.getValor());
            }
            System.out.println("");
        }
    }
}
