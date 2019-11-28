package restaUm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class IniciaRestaUm {

    public static PrintWriter output;
    public static int count_fim_sem_jogada;
    public static int count_solucoes;
    public static String tab_name;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Posição inicial x entre 0 e 6:   ");
        int pos_inicio_x = scan.nextInt();
        System.out.print("Posição inicial y entre 0 e 6:   ");
        int pos_inicio_y = scan.nextInt();

        Tabuleiro tabuleiro_pronto = new Tabuleiro(pos_inicio_x, pos_inicio_y);
        tabuleiro_pronto.imprimir();

        System.out.print("Posição final x entre 0 e 6:   ");
        int pos_final_x = scan.nextInt();
        System.out.print("Posição final y entre 0 e 6:   ");
        int pos_final_y = scan.nextInt();

        ArrayList<Jogada> jogadas_passadas = new ArrayList<>();
        busca(pos_final_x, pos_final_y, tabuleiro_pronto, jogadas_passadas);

    }

    public static boolean busca(int x, int y, Tabuleiro tab_atual, ArrayList<Jogada> jog_passadas) throws FileNotFoundException {
        tab_atual.imprimir();

        ArrayList<Jogada> jog_possiveis = tab_atual.jogadasPossiveis();
        if (jog_possiveis.isEmpty()) {
            if (tab_atual.verificaSolucao(x, y)) {
                System.out.println("Sucesso!");
                output = new PrintWriter("solucao" + ".csv");
                for (Jogada j : jog_passadas) {
                    output.println(j.toString());
                }
                output.close();
                return true;
            } else if (tab_atual.verificaSolucaoFacil()) {
                IniciaRestaUm.count_solucoes++;
                System.out.println("Solução encontrada: " + IniciaRestaUm.count_solucoes);
                output = new PrintWriter("solucao" + "_" + IniciaRestaUm.count_solucoes + ".csv");
                for (Jogada j : jog_passadas) {
                    output.println(j.toString());
                }
                output.close();
                return true;
            }
        } else {
            for (Jogada j : jog_possiveis) {
                ArrayList<Jogada> jog_atuais = new ArrayList<>(jog_passadas);
                Tabuleiro tab_prox = Tabuleiro.aplicaJogada(tab_atual, j);
                jog_atuais.add(j);
                boolean acabou = IniciaRestaUm.busca(x, y, tab_prox, jog_atuais);
                if (acabou) {
                    return true;
                }
            }
        }
        return false;
    }
}
