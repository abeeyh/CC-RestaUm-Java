package restaUm;

public class Jogada {

    public int x_origem, y_origem;
    public int x_destino, y_destino;
    public int x_meio, y_meio;

    public Jogada(int x_origem, int y_origem, int x_meio, int y_meio, int x_destino, int y_destino) {
        this.x_origem = x_origem;
        this.y_origem = y_origem;
        this.x_destino = x_destino;
        this.y_destino = y_destino;
        this.x_meio = x_meio;
        this.y_meio = y_meio;
    }

    @Override
    public String toString() {
        return "" + x_origem + ";" + y_origem
                + ";" + x_meio + ";" + y_meio
                + ";" + x_destino + ";" + y_destino ;
    }
}
