  
import java.util.Scanner;

public class StudentGrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sum = 0;
        int max, low;
        

        System.out.println("How many Student data you want to Enter");
        int n = sc.nextInt();
        int a[] = new int[n];

        System.out.println("Enter Student IDs:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println("Enter the Students Grades");
        int g[] = new int[n];
        for (int i = 0; i < n; i++) {
            g[i] = sc.nextInt();
        }

        // ✅ initialize before loop
        max = g[0];
        low = g[0];

        for (int i = 0; i < n; i++) {
            sum = sum + g[i];

            if (g[i] > max) {
                max = g[i];
            }

            if (g[i] < low) {
                low = g[i];
            }
        }

        int avg = sum / n;

        System.out.println("The Maximum Grade is " + max);
        System.out.println("The Average of Grades is: " + avg);
        System.out.println("The Lowest Grade is: " + low);

        sc.close();
    }
}

