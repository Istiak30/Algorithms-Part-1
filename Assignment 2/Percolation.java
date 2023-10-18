import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    private final boolean[][] opened;
    private final int size;
    private final int bottom;
    private int openSites;
    private final WeightedQuickUnionUF qf;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
        openSites = 0;
    }

    public void open(int row, int col) {
        checkException(row, col);
        opened[row - 1][col - 1] = true; // Open the box
        ++openSites; // Incrementing the number of open sites

        // Edge Case => If any of the top row boxes are opened => Union(box, top)
        if (row == 1) {
            qf.union(getQuickFindIndex(row, col), TOP);
        }

        // Edge Case => If any of the bottom row boxes are opened => Union(box, bottom)
        if (row == size) {
            qf.union(getQuickFindIndex(row, col), bottom);
        }

        // If any of the boxes in the middle rows (except top and bottom) are opened then check for neighbouring unions
        if (row > 1 && isOpen(row - 1, col)) {
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row - 1, col));
        }

        if (row < size && isOpen(row + 1, col)) {
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row + 1, col));
        }

        if (col > 1 && isOpen(row, col - 1)) {
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col - 1));
        }

        if (col < size && isOpen(row, col + 1)) {
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col + 1));
        }
    }

    private void checkException(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isOpen(int row, int col) {
        checkException(row, col);
        return opened[row - 1][col - 1];
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean isFull(int row, int col) {
        if ((row > 0 && row <= size) && (col > 0 && col <= size)) {
            return qf.find(TOP) == qf.find(getQuickFindIndex(row, col));
        }
        else throw new IllegalArgumentException();
    }

    private int getQuickFindIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    public boolean percolates() {
        return qf.find(TOP) == qf.find(
                bottom); // If top is connected to bottom, then system percolates
    }
}
