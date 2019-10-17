public class UF {
    private int id[], size[];
    private int count;

    public UF(int n) {
        count = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];  /// path compression by halving
            i = id[i];
        }
        return i;
    }

    public boolean connected(int i, int j) {
        return root(i) == root(j);
    }

    public void union(int i, int j) {
        int rooti = root(i);
        int rootj = root(j);
        if (rooti == rootj)
            return;

        // make root of smaller size point to root of larger size
        if (size[rootj] >= size[rooti]) {
            id[rooti] = rootj;
            size[j] += size[i];
        } else {
            id[rootj] = rooti;
            size[i] += size[j];
        }
        count--;
    }
}
