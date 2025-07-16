package perceptron_digitos;

public class Aprendizagem {
    private double x[][] = {
    {1, 1,1,1, 1,0,1, 1,0,1, 1,0,1, 1,1,1},
    {1, 0,1,0, 1,1,0, 0,1,0, 0,1,0, 1,1,1},
    {1, 1,1,1, 0,0,1, 1,1,1, 1,0,0, 1,1,1},
    {1, 1,1,1, 0,0,1, 1,1,1, 0,0,1, 1,1,1},
    {1, 1,0,1, 1,0,1, 1,1,1, 0,0,1, 0,0,1},
    {1, 1,1,1, 1,0,0, 1,1,1, 0,0,1, 1,1,1},
    {1, 1,1,1, 1,0,0, 1,1,1, 1,0,1, 1,1,1},
    {1, 1,1,1, 0,0,1, 0,0,1, 0,0,1, 0,0,1},
    {1, 1,1,1, 1,0,1, 1,1,1, 1,0,1, 1,1,1},
    {1, 1,1,1, 1,0,1, 1,1,1, 0,0,1, 1,1,1}
};
    
    private double t[][] = {
        {-1,-1,-1,-1},
        {-1,1,-1,-1},
        {-1,-1,1,-1},
        {-1,1,1,-1},
        {-1,1,1,1},
        {1,-1,-1,-1},
        {1,1,-1,-1},
        {1,-1,1,-1},
        {1,1,1,-1},
        {1,1,1,1} 
    };
    
    private double w[][] = new double[4][16];
    private int epocas;
    
    public Aprendizagem() {
        epocas = 0;
    }
    
    public double[][] getw() {
        return w;
    }
    
    public double[][] gett() {
        return t;
    }
    
    public int getepocas() {
        return epocas;
    }
    
    public double somatorio(int padrao, int perceptron) {
        double yent = 0;
        for(int j = 0; j < 16; j++) {
            yent += x[padrao][j] * w[perceptron][j];
        }
        return yent;
    }
    
    public double saida(double yent, double limiar) {
        return (yent > limiar) ? 1 : ((yent < -limiar) ? -1 : 0);
    }
    
    public void atualiza(double alfa, int padrao, double[] f) {
        for(int p = 0; p < 4; p++) {
            for(int j = 0; j < 16; j++) {
                w[p][j] += alfa * (t[padrao][p] - f[p]) * x[padrao][j];
            }
        }
    }
    
    public void algoritmo(double alfa, double limiar) {
        for(int p = 0; p < 4; p++) {
            for(int j = 0; j < 16; j++) {
                w[p][j] = Math.random() * 0.2 - 0.1;
            }
        }
        
        boolean mudou;
        do {
            mudou = false;
            for(int i = 0; i < 10; i++) {
                double[] f = new double[4];
                for(int p = 0; p < 4; p++) {
                    double yent = somatorio(i, p);
                    f[p] = saida(yent, limiar);
                    if(f[p] != t[i][p]) {
                        mudou = true;
                    }
                }
                if(mudou) {
                    atualiza(alfa, i, f);
                }
            }
            epocas++;
            if(epocas > 10000) break; 
        } while(mudou);
    }
}