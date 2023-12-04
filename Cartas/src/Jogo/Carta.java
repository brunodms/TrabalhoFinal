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
        if (valor % 10 == 0) {
            pontos += 2;
        }
        if (temDigitosRepetidos(valor)) {
            pontos += 4;
        }
        return pontos;
    }
    public static boolean temDigitosRepetidos(int number) {
        String paraString = Integer.toString(number);
        char digito1 = paraString.charAt(0);
        for (int i = 1; i < paraString.length(); i++) {
            if (paraString.charAt(i) != digito1) {
                return false;
            }
        }
        return true;
    }
    
}
