package Jogo;

class Carta {
    private int valor;

    public Carta(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public String toString() {
        return String.format("%3d", valor);
    }

    public int getPontosDaCarta() {
        int pontos = 1;
        if (valor % 10 == 5) {
            pontos++;
        }
        if (valor % 10 == valor / 10 % 10 && valor != 100) {
            pontos += 3;
        }
        if (valor % 10 == 0) {
            pontos += 2;
        }
        return pontos;
    }
}