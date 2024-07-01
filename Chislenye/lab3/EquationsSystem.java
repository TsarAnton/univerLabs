public class EquationsSystem {
    public static Double[] findSolution(Double[][] matrix, int n) {
        double buff = matrix[0][0];
        matrix[0][0] = 1.0;
        for(int i = 1; i <= n; i++) {
            matrix[0][i] /= buff;
        }
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                buff = 0 - matrix[i][j];
                matrix[i][j] = 0.0;
                for(int k = j + 1; k <= n; k++) {
                    matrix[i][k] = matrix[j][k] * buff + matrix[i][k];
                }
            }
            for(int j = i + 1; j <= n; j++) {
                matrix[i][j] = matrix[i][j] / matrix[i][i];
            }
            matrix[i][i] = 1.0;
        }
        for(int i = n - 2; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                buff = 0 - matrix[i][j];
                matrix[i][j] = 0.0;
                for(int k = j + 1; k <= n; k++) {
                    matrix[i][k] = matrix[j][k] * buff + matrix[i][k];
                }
            }
        }
        Double[] res = new Double[n];
        for(int i = 0; i < n; i++) {
            res[i] = matrix[i][n];
        }
        return res;
    }
}
