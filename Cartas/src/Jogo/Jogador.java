package Jogo;

import java.util.*;

class Jogador {

  private String nome;
  private List<Carta> mao = new ArrayList<>();
  private List<Carta> monte = new ArrayList<>();
  private int pontos;
  private int cartaEscolhida;

  public Jogador(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  public List<Carta> getMao() {
    return mao;
  }

  public List<Carta> getMonte() {
    return monte;
  }
  public int getPontuacao(){
    return pontos;
  }
  public boolean validarCartaEscolhida(int carta) {
    for (Carta c : mao) {
      if (c.getValor() == carta) {
        return true;
      }
    }
    return false;
  }

  public int getCartaEscolhida() {
    return cartaEscolhida;
  }

  public void setCartaEscolhida(int cartaEscolhida) {
    this.cartaEscolhida = cartaEscolhida;
  }

  public void adicionarCarta(Carta carta) {
    mao.add(carta);
  }

  public static int jogarCarta(Jogador jogador, ArrayList<LinkedList<Carta>> tabuleiro) {
    int MDP = 109, MDN = 0, L = -1, D;
    boolean P = false;

    for (int i = 0; i < 5; i++) {
      Carta ultima = tabuleiro.get(i).getLast();
      D = jogador.getCartaEscolhida() - ultima.getValor();
      if (D > 0) {
        if (D < MDP) {
          MDP = D;
          L = i;
          P = true;
        }
      } else if (D < 0 && P == false) {
        if (D < MDN) {
          MDN = D;
          L = i;
        }
      }
    }

    if (P == false || tabuleiro.get(L).size() == 5) {
      pescarLinha(tabuleiro.get(L), jogador);
      System.out.printf("\n\n---LINHA %d PESCADA---", L + 1);
    }
    return L;
  }

  public static void pescarLinha(LinkedList<Carta> linha, Jogador jogador) {
    while (!linha.isEmpty()) {
      jogador.pontos += linha.getLast().getPontosDaCarta();
      jogador.monte.add(linha.removeLast());
    }
  }
}

class JogadorComp implements java.util.Comparator<Jogador> {
  @Override
  public int compare(Jogador a, Jogador b) {
    return a.getCartaEscolhida() - b.getCartaEscolhida();
  }
}