import java.util.Stack;

public class ArrayNumerosAleatorios {
	
	
    public static void main(String[] args) {
        // Aquí colocamos los 100 números aleatorios dentro de un array.
    	int[] numerosAleatorios = {
                587, 254, 503, 818, 237, 215, 122, 653, 774, 202, 890, 657, 109, 859, 305, 789, 141, 788, 147, 598, 
                227, 133, 423, 947, 969, 27, 792, 572, 908, 339, 746, 243, 181, 279, 428, 96, 647, 545, 872, 174, 
                479, 934, 25, 931, 922, 678, 110, 292, 617, 104, 741, 726, 823, 503, 736, 581, 63, 852, 470, 121, 
                419, 866, 283, 328, 336, 454, 196, 461, 473, 77, 764, 612, 468, 855, 962, 798, 287, 25, 394, 204, 
                989, 293, 305, 361, 307, 154, 319, 803, 108, 538, 396, 227, 262, 949, 69, 123, 18, 255, 979, 493, 
                657, 581, 71
            };
        
        System.out.println("Arreglo original: ");
        for (int i = 0; i < numerosAleatorios.length; i++) {
            System.out.print(numerosAleatorios[i] + ", ");
        }
        
        bubbleSort(numerosAleatorios);
        mergeSort(numerosAleatorios);
        quickSort(numerosAleatorios);
        AVL(numerosAleatorios);
    }
    
    public static void bubbleSort(int[] numerosAleatorios) {
    	int n = numerosAleatorios.length;
    	long tiempoInicio = System.currentTimeMillis();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (numerosAleatorios[j] > numerosAleatorios[j + 1]) {
                    int temp = numerosAleatorios[j];
                    numerosAleatorios[j] = numerosAleatorios[j + 1];
                    numerosAleatorios[j + 1] = temp;
                }
            }
        }
        long tiempoFin = System.currentTimeMillis();
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        print(numerosAleatorios, "\nBubblesort", tiempoEjecucion);
    }
    
    public static void mergeSort(int[] numerosAleatorios) {
    	long tiempoInicio = System.currentTimeMillis();
    	int n = numerosAleatorios.length;
        int[] arregloTemp = new int[n];
        int subarraySize = 1;

        while (subarraySize < n) {
            for (int inicio = 0; inicio < n - 1; inicio += 2 * subarraySize) {
                int medio = Math.min(inicio + subarraySize - 1, n - 1);
                int fin = Math.min(inicio + 2 * subarraySize - 1, n - 1);

                fusionar(numerosAleatorios, arregloTemp, inicio, medio, fin);
            }

            System.arraycopy(arregloTemp, 0, numerosAleatorios, 0, n);
            subarraySize *= 2;
        }
        long tiempoFin = System.currentTimeMillis();
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        print(numerosAleatorios, "\nMergeSort", tiempoEjecucion);
    }

    public static void fusionar(int[] numerosAleatorios, int[] arregloTemp, int inicio, int medio, int fin) {
        int i = inicio;
        int j = medio + 1;

        for (int k = inicio; k <= fin; k++) {
            if (i > medio) {
                arregloTemp[k] = numerosAleatorios[j++];
            } else if (j > fin) {
                arregloTemp[k] = numerosAleatorios[i++];
            } else if (numerosAleatorios[i] <= numerosAleatorios[j]) {
                arregloTemp[k] = numerosAleatorios[i++];
            } else {
                arregloTemp[k] = numerosAleatorios[j++];
            }
        }
    }
    
    public static void quickSort(int[] numerosAleatorios) {
        int n = numerosAleatorios.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(n - 1);
        long tiempoInicio = System.currentTimeMillis();
        
        while (!stack.isEmpty()) {
            int fin = stack.pop();
            int inicio = stack.pop();

            int indiceParticion = particion(numerosAleatorios, inicio, fin);

            if (inicio < indiceParticion - 1) {
                stack.push(inicio);
                stack.push(indiceParticion - 1);
            }

            if (indiceParticion + 1 < fin) {
                stack.push(indiceParticion + 1);
                stack.push(fin);
            }
        }
        
        long tiempoFin = System.currentTimeMillis();
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        print(numerosAleatorios, "\nQuickSort", tiempoEjecucion);
    }

    public static int particion(int[] numerosAleatorios, int inicio, int fin) {
        int pivote = numerosAleatorios[fin];
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (numerosAleatorios[j] <= pivote) {
                i++;
                int temp = numerosAleatorios[i];
                numerosAleatorios[i] = numerosAleatorios[j];
                numerosAleatorios[j] = temp;
            }
        }

        int temp = numerosAleatorios[i + 1];
        numerosAleatorios[i + 1] = numerosAleatorios[fin];
        numerosAleatorios[fin] = temp;

        return i + 1;
    }
    
    public static void AVL(int[] numerosAleatorios) {
    	AVLTreeSort avl = new AVLTreeSort(numerosAleatorios);
    	long tiempoInicio = System.currentTimeMillis();
    	numerosAleatorios = AVLTreeSort.starter();
    	long tiempoFin = System.currentTimeMillis();
        long tiempoEjecucion = tiempoFin - tiempoInicio;
    	print(numerosAleatorios, "\nAVL Tree Sorto", tiempoEjecucion);
    }
    
    public static void print(int[] numerosAleatorios, String orden, long tiempoEjecucion) {
    	System.out.println(orden + ": ");
        for (int i = 0; i < numerosAleatorios.length; i++) {
            System.out.print(numerosAleatorios[i] + ", ");
        }
        System.out.println("\nTiempo de ejecución: " + tiempoEjecucion + " milisegundos\n\n");
    }
        
}
