package perceptron_digitos;

public class Validacao {
    public Validacao() {}
    
    public double[] somatorio(int mat[][], double[][] w) {
        double[] yent = new double[4];
        double[] entrada = new double[16];
        
        entrada[0] = 1;
        int l = 1;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 3; j++) {
                entrada[l++] = mat[i][j];
            }
        }
        
        for(int p = 0; p < 4; p++) {
            yent[p] = 0;
            for(int j = 0; j < 16; j++) {
                yent[p] += entrada[j] * w[p][j];
            }
        }
        return yent;
    }
    
    public double[] saida(double[] yent, double limiar) {
        double[] f = new double[4];
        for(int p = 0; p < 4; p++) {
            f[p] = (yent[p] > limiar) ? 1 : ((yent[p] < -limiar) ? -1 : 0);
        }
        return f;
    }
    
    public String teste(int mat[][], double[][] w, double[][] t, double limiar) {
        double[] yent = somatorio(mat, w);
        double[] f = saida(yent, limiar);
        
        for(int digito = 0; digito < 10; digito++) {
            boolean match = true;
            for(int p = 0; p < 4; p++) {
                if(f[p] != t[digito][p]) {
                    match = false;
                    break;
                }
            }
            if(match) {
                return String.valueOf(digito);
            }
        }
        return "?";
    }
}