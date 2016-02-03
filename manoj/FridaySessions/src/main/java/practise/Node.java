package practise;

public class Node {
    private final int x_cordinate;
    private final int y_cordinate;
    private final String name;

    public Node(int x_cordinate, int y_cordinate, String name) {
        assert name != null;
        this.x_cordinate = x_cordinate;
        this.y_cordinate = y_cordinate;
        this.name = name;
    }

    public int get_x_cordinate() {
        return x_cordinate;
    }

    public int get_y_cordinate() {
        return y_cordinate;
    }

    public String getName() {
        return name;
    }

}
