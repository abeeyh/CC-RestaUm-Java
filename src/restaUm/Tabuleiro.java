package restaUm;

import java.util.ArrayList;

public class Tabuleiro {

    public int[][] posicoes;

    private static final int INVALIDO = 2;
    private static final int VAZIO = 0;
    private static final int CHEIO = 1;

    public Tabuleiro(Tabuleiro tab) {
        this.posicoes = new int[7][7];
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                this.posicoes[i][j] = tab.posicoes[i][j];
            }
        }
    }

    public Tabuleiro(int x, int y) throws IllegalArgumentException {
        posicoes = new int[7][7];
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                posicoes[i][j] = INVALIDO;
            }
        }
        for (int i = 2; i <= 4; i++) {
            for (int j = 0; j <= 6; j++) {
                posicoes[i][j] = CHEIO;
            }
        }
        for (int i = 2; i <= 4; i++) {
            for (int j = 0; j <= 6; j++) {
                posicoes[j][i] = CHEIO;
            }
        }
        if (podeRetirar(x, y)){
            posicoes[x][y] = VAZIO;
        }else{
            throw new IllegalArgumentException("Posições Invalidas!");
        }
    }
    public boolean podeRetirar(int x, int y) {
        return (validaPosicao(x, y) && (posicoes[x][y] == CHEIO));
    }
    public boolean validaPosicao(int x, int y) {
        if (x < 0 || x > 6 || y < 0 || y > 6) {
            return false;
        } else if (posicoes[x][y] == INVALIDO) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Jogada> jogadasPossiveis() {
        ArrayList<Jogada> lista_jogadas = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                if (posicoes[i][j] != VAZIO) {
                    continue;
                }
                if (podeRetirar(i - 1, j) && podeRetirar(i - 2, j)) {
                    lista_jogadas.add(new Jogada(i - 2, j, i - 1, j, i, j));
                }
                if (podeRetirar(i, j + 1) && podeRetirar(i, j + 2)) {
                    lista_jogadas.add(new Jogada(i, j + 2, i, j + 1, i, j));
                }
                if (podeRetirar(i + 1, j) && podeRetirar(i + 2, j)) {
                    lista_jogadas.add(new Jogada(i + 2, j, i + 1, j, i, j));
                }
                if (podeRetirar(i, j - 1) && podeRetirar(i, j - 2)) {
                    lista_jogadas.add(new Jogada(i, j - 2, i, j - 1, i, j));
                }
            }
        }
        return lista_jogadas;
    }

    public static Tabuleiro aplicaJogada(Tabuleiro antes, Jogada jog) {
        Tabuleiro depois = new Tabuleiro(antes);
        depois.posicoes[jog.x_origem][jog.y_origem] = VAZIO;
        depois.posicoes[jog.x_meio][jog.y_meio] = VAZIO;
        depois.posicoes[jog.x_destino][jog.y_destino] = CHEIO;

        return depois;
    }

    public boolean verificaSolucao(int x, int y) {
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                if (i == x && j == y) {
                    if (posicoes[x][y] != CHEIO) {
                        return false;
                    }
                } else if (posicoes[x][y] == CHEIO) {
                    return false;
                }
            }
        }
        return true;
    }
        public boolean verificaSolucaoFacil() {
        int count = 0;
        for (int i = 0; i < 6 ;i++) {
            for (int j = 0; j < 6; j++) {
                if (posicoes[i][j] == CHEIO) {
                    count = count + 1;
                    if (count > 1) {
                        return false;
                    }
                }
            }
        }
        if (count == 1) {
            return true;
        }
        return true;
    }
    public void imprimir(){
        System.out.println("");
        System.out.println("##########");
        for (int i = 0; i <= 6; i++) {
            System.out.println("");
            for (int j = 0; j <= 6; j++) {
                System.out.print(posicoes[i][j]);
            }
        }
    }
}
