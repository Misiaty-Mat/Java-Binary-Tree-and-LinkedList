package abstractCls;

public class Main {
    public static void main(String[] args) {
        //MyLinkedList list = new MyLinkedList(null);
        SearchTree tree = new SearchTree(null);
        tree.traverse(tree.getRoot());

        String stringData = "4 2 6 8 0 9 5 7 1 3 1";
        String[] data = stringData.split(" ");

        for (String s: data) {
            tree.addItem(new Node(s.toLowerCase()));
        }

        tree.traverse(tree.getRoot());
        tree.removeItem(new Node("7"));
        tree.traverse(tree.getRoot());
    }
}
