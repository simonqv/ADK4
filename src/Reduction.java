import java.util.Scanner;
import java.lang.StringBuilder;

public class Reduction {

    /**
     * Reduces Graph coloring problem
     * @param sc scanner for input
     * @return String to "send" to Rollbesättningsproblmet
     */
    public static String reduction(Scanner sc) {
        int v = sc.nextInt();
        int e = sc.nextInt();
        int m = sc.nextInt();

        // If number of colors larger than number of edges we know it's a "ja-instans"
        // If number of colors larger or equal to number of nodes we know it's a "ja-instans"
        if (e < m || v <= m) {
            return "3\n2\n3\n1 1\n1 2\n1 3\n2 1 3\n2 2 3";
        }
        int[][] edges = new int[e][2];

        v += 3;         // Add 3 vertices / roles for p1 p2 and p3 (p1 and p2 being the divas)
        e = e + v - 1;  // Add edges from p3 to all other nodes (to prevent monologues)
        m += 3;         // 3 new actors / colors

        for (int i = 0; i < e + 1 - v; i++) {
            // add 3 to a and b to not clash with divas and extra
            int a = sc.nextInt() + 3;
            int b = sc.nextInt() + 3;
            edges[i] = new int[]{a, b};
        }

        return inputForRoleAssignment(v, e, m, edges).toString();
    }

    /**
     * Build the output for the reduction, which is the input for Rollbesättningsproblemet
     * @param n number of roles (number of nodes in graph)
     * @param s number of scenes (number of edges in graph)
     * @param k number of actors (number of colors in graph)
     * @param edges scenes excluding p1, p2 and p3 (edges in graph)
     * @return StringBuilder containing entire input to Rollbesättningsproblemet
     */
    public static StringBuilder inputForRoleAssignment(int n, int s, int k, int[][] edges) {
        // Final output StringBuilder
        StringBuilder sb = new StringBuilder();
        // Add first 3 lines with values
        sb.append(n).append("\n").append(s).append("\n").append(k).append("\n");

        // ROLES
        // Add lines for divas and extra
        sb.append("1 1\n1 2\n1 3\n");
        // Rest of the roles
        for (int i = 3; i < n; i++) {
            sb.append(k - 3);
            for (int j = 4; j <= k; j++) {
                sb.append(" ").append(j);
            }
            sb.append("\n");
        }

        // SCENES
        // Add divas' and extra's scenes
        sb.append("2 1 3\n2 2 3\n");
        for (int x = 4; x <= n; x++) {
            sb.append("2 3 ").append(x).append("\n");
        }

        // Add the rest of the scenes in order
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
        System.out.println(reduction(sc));
    }
}
