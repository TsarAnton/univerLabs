package pack;

public class Matrix {
    
    public int matrix[][];
    public int N;

    public Matrix(int N) {
        this.N = N;
        matrix = new int[N][N];
    }

    public void addEdge(int v1, int v2) {
        matrix[v1][v2] = 1;
    }

    public int getElement(int i, int j) {
        return matrix[i][j];
    }
}
