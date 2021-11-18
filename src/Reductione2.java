import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;
/*
Testat att göra på ett annat sätt men lyckades ej :(
 */
public class Reductione2 {
    public static void reduction(Scanner sc) {
        int v = sc.nextInt();
        int e = sc.nextInt();
        int m = sc.nextInt();

        if (e < m || v <= m) {
            System.out.println("3\n2\n3\n1 1\n1 2\n1 3\n2 1 3\n2 2 3");
            return;
        }
        int[][] edges = new int[e][2];

        v += 3; // Add 3 vertices / roles for p1 p2 and p3 (p1 and p2 being the divas)
        e = e + 2; // Add 2 edges for p1 - p3 and p2 - p3
        m += 3; // 3 new actors / colors

        // Borde kunna ändra e till e = e + 2;

        ArrayList<Integer> listVertices = new ArrayList<>();
        listVertices.add(1);
        listVertices.add(2);
        listVertices.add(3);
        for (int i = 0; i < e - 2; i++) {
            int a = sc.nextInt() + 3;
            int b = sc.nextInt() + 3;
            edges[i] = new int[]{a, b};

            if (!listVertices.contains(a)) {
                listVertices.add(a);
            }
            if (!listVertices.contains(b)) {
                listVertices.add(b);
            }
        }
       System.out.println("SIZE " + listVertices.size() +  "  " + listVertices);
        String instance = foo(v, e, m, edges, listVertices.size()).toString();
        System.out.println(instance);
    }


    // Det som inte funkade som vi hade innan är typ att n blir knas, eftersom den säger mer än vad som är "sant" om vi har noder som inte har kanter!
    // Fixar vi den, borde vi inte behöva dra kant till ALLA för att ta bort monologer :o
    // Tror jag i alla fall, kom bara o tänka på detta på vägen hem!
    public static StringBuilder foo(int n, int s, int k, int[][] edges, int nonIsolatedNode) {
        StringBuilder sb = new StringBuilder();

        // ändra s till s + 2
        sb.append(nonIsolatedNode).append("\n").append(s).append("\n").append(k).append("\n");

        // Add lines for divas and extra
        sb.append("1 1\n1 2\n1 3\n");

        for (int i = 3; i < nonIsolatedNode; i++) {
            sb.append(k - 3);
            for (int j = 4; j <= k; j++) {
                sb.append(" ").append(j);
            }
            sb.append("\n");
        }

        // Add divas scenes
        sb.append("2 1 3\n2 2 3\n");
/*
        for (int x = 4; x <= nonIsolatedNode; x++) {
            sb.append("2 1 ").append(x).append("\n");
        }*/
        //sb.append("2 2 3\n");

        for (int[] edge : edges){
            sb.append("2").append(" ").append(edge[0]).append(" ").append(edge[1]).append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        reduction(sc);
    }
}
