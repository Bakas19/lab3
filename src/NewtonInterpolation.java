public class NewtonInterpolation {

    // Função para calcular as diferenças divididas
    public static double[][] calcularDiferencasDivididas(double[] x, double[] y, int n) {
        double[][] tabela = new double[n][n];

        // Preencher a primeira coluna com os valores de y
        for (int i = 0; i < n; i++) {
            tabela[i][0] = y[i];
        }

        // Calcular as diferenças divididas
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                tabela[i][j] = (tabela[i + 1][j - 1] - tabela[i][j - 1]) / (x[i + j] - x[i]);
            }
        }

        return tabela;
    }

    // Função para calcular o polinômio de Newton em um ponto dado
    public static double interpolarNewton(double[] x, double[][] tabela, double ponto, int n) {
        double resultado = tabela[0][0];
        double termo = 1;

        for (int i = 1; i < n; i++) {
            termo *= (ponto - x[i - 1]);
            resultado += tabela[0][i] * termo;
        }

        return resultado;
    }

    public static void main(String[] args) {
        // Dados experimentais
        double[] x = {0.35, 0.48, 0.97, 1.08}; // Valores de x
        double[] y = {1.419, 1.616, 2.637, 2.944}; // Valores de y correspondentes

        int n = x.length; // Número de pontos

        // Calcular a tabela de diferenças divididas
        double[][] tabela = calcularDiferencasDivididas(x, y, n);

        // Ponto intermediário onde queremos encontrar o valor de y
        double ponto = 0.58;

        // Calcular o valor interpolado em x = 0.58
        double resultado = interpolarNewton(x, tabela, ponto, n);

        System.out.printf("O valor de y em x = %.2f é aproximadamente %.6f\n", ponto, resultado);
    }
}
