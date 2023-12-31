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

  public Carta getCartaEscolhida() {
    return cartaEscolhida;
  }

  public void setCartaEscolhida(Carta cartaEscolhida) {
    this.cartaEscolhida = cartaEscolhida;
  }

  public boolean validarCartaEscolhida(int carta) {
    for (Carta c : mao) {
      if (c.getValor() == carta) {
        return true;
      }
    }
    return false;
  }

  public void adicionarCarta(Carta carta) {
    mao.add(carta);
  }

  public int jogarCarta(Jogador jogador, ArrayList<LinkedList<Carta>> tabuleiro) {
    mao.remove(jogador.getCartaEscolhida());
    int  dif, maior = 109, menor = 0, linha = -1;
    boolean coleta = true;

    for (int i = 0; i < 5; i++) {
      Carta ultima = tabuleiro.get(i).getLast();
      dif = jogador.getCartaEscolhida().getValor() - ultima.getValor();
      if (dif > 0) {
        if (dif < maior) {
          maior = dif;
          linha = i;
          coleta = false;
        }
      } else if (dif < 0 && coleta == true) {
        if (dif < menor) {
          menor = dif;
          linha = i;
        }
      }
    }

    if (coleta || tabuleiro.get(linha).size() == 5) {
      coletaLinha(tabuleiro.get(linha), jogador);
      System.out.printf("Linha %d coletada\n", linha + 1);
    }
    return linha;
  }

  public static void coletaLinha(LinkedList<Carta> linha, Jogador jogador) {
    while (!linha.isEmpty()) {
      jogador.pontos += linha.getLast().getPontos();
      jogador.monte.add(linha.removeLast());
    }
    if(jogador.pontos > 0){
      if(jogador.pontos == 1){
        System.out.println(jogador.getNome() + " se deu mal e marcou " + jogador.pontos + " ponto!");
      }else{
        System.out.println(jogador.getNome() + " se deu mal e marcou " + jogador.pontos + " pontos!");
      }
    }
  }
}

class JogadorComp implements java.util.Comparator<Jogador> {
  @Override
  public int compare(Jogador a, Jogador b) {
    return a.getCartaEscolhida().getValor() - b.getCartaEscolhida().getValor();
  }
}

class JogadorCompPontos implements java.util.Comparator<Jogador> {
  @Override
  public int compare(Jogador a, Jogador b) {
    return a.getPontuacao() - b.getPontuacao();
  }
}