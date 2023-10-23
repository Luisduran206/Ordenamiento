import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int key;
    TreeNode left;
    TreeNode right;
    int height;

    public TreeNode(int key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVLTreeSort {
	
	static int[] numerosAleatorios;
	
	public AVLTreeSort(int[] numerosAleatorios) {
		AVLTreeSort.numerosAleatorios = numerosAleatorios;
	}
	
    public static int[] starter() {

        int[] arregloOrdenado = avlTreeSort(numerosAleatorios);
        return arregloOrdenado;
    }
    
    public static int[] avlTreeSort(int[] arreglo) {
        TreeNode root = null;

        for (int num : arreglo) {
            root = insert(root, num);
        }

        List<Integer> sortedList = new ArrayList<>();
        inOrderTraversal(root, sortedList);

        int[] arregloOrdenado = new int[sortedList.size()];
        for (int i = 0; i < sortedList.size(); i++) {
            arregloOrdenado[i] = sortedList.get(i);
        }

        return arregloOrdenado;
    }

    public static TreeNode insert(TreeNode node, int key) {
        if (node == null) {
            return new TreeNode(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) {
            return rotateRight(node);
        }

        if (balance < -1 && key > node.right.key) {
            return rotateLeft(node);
        }

        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public static int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public static int getBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public static TreeNode rotateRight(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    public static TreeNode rotateLeft(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

        return y;
    }

    public static void inOrderTraversal(TreeNode node, List<Integer> list) {
        if (node != null) {
            inOrderTraversal(node.left, list);
            list.add(node.key);
            inOrderTraversal(node.right, list);
        }
    }

    public static void imprimirArreglo(int[] arreglo) {
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}