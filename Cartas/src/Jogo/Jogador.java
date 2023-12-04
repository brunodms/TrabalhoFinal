package Jogo;

import java.util.*;

class Jogador {

  private String nome;
  private List<Carta> mao = new ArrayList<>();
  private List<Carta> monte = new ArrayList<>();
  private int pontos;
  private Carta cartaEscolhida;

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

  public int getPontuacao() {
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

  public Carta getCartaEscolhida() {
    return cartaEscolhida;
  }

  public void setCartaEscolhida(Carta cartaEscolhida) {
    this.cartaEscolhida = cartaEscolhida;
    mao.remove(cartaEscolhida);
  }

  public void adicionarCarta(Carta carta) {
    mao.add(carta);
  }

  public int jogarCarta(Jogador jogador, ArrayList<LinkedList<Carta>> tabuleiro) {
    int maior = 109, menor = 0, linha = -1, D;
    boolean P = false;

    for (int i = 0; i < 5; i++) {
      Carta ultima = tabuleiro.get(i).getLast();
      D = jogador.getCartaEscolhida().getValor() - ultima.getValor();
      if (D > 0) {
        if (D < maior) {
          maior = D;
          linha = i;
          P = true;
        }
      } else if (D < 0 && P == false) {
        if (D < menor) {
          menor = D;
          linha = i;
        }
      }
    }

    if (P == false || tabuleiro.get(linha).size() == 5) {
      pescarLinha(tabuleiro.get(linha), jogador);
      System.out.printf("\n\nLinha %d pescada", linha + 1);
    }
    return linha;
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
    return a.getCartaEscolhida().getValor() - b.getCartaEscolhida().getValor();
  }
}