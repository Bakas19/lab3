public class NewtonGregoryInterpolation {

    // Função para calcular as diferenças finitas
    public static double[][] calcularDiferencasFinitas(double[] y, int n) {
        double[][] tabela = new double[n][n];

        // Preencher a primeira coluna com os valores de y
        for (int i = 0; i < n; i++) {
            tabela[i][0] = y[i];
        }

        // Calcular as diferenças finitas
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                tabela[i][j] = tabela[i + 1][j - 1] - tabela[i][j - 1];
            }
        }

        return tabela;
    }

    // Função para calcular o polinômio interpolador de Newton-Gregory em um ponto dado
    public static double interpolarNewtonGregory(double[] y, double[][] tabela, double x, double x0, double h, int n) {
        // Calcular o valor de u
        double u = (x - x0) / h;
        double resultado = y[0];
        double termo = 1;

        // Iterar para construir o polinômio de Newton-Gregory
        for (int i = 1; i < n; i++) {
            termo *= (u - (i - 1)) / i; // Calcular o termo u(u-1)(u-2)... / i!
            resultado += termo * tabela[0][i];
        }

        return resultado;
    }

    public static void main(String[] args) {
        // Dados experimentais do Conjunto de Dados 1
        double[] y = {0.322, 0.284, 0.241, 0.193, 0.135, 0.063, -0.031, -0.164, -0.369, -0.741, -1.664};
        double x0 = 1.0; // Primeiro valor de x correspondente a y₀
        double h = 0.1; // Passo entre os valores de x
        double x = 0.98; // Ponto onde queremos interpolar

        int n = y.length; // Número de pontos

        // Calcular a tabela de diferenças finitas
        double[][] tabela = calcularDiferencasFinitas(y, n);

        // Calcular o valor interpolado em x = 0,98
        double resultado = interpolarNewtonGregory(y, tabela, x, x0, h, n);

        System.out.printf("O valor interpolado de y em x = %.2f é aproximadamente %.6f\n", x, resultado);
    }
}
