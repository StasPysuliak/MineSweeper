import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int height;
        int height1;
        int width;
        int width1;
        int bombs;
        int randomW;
        int randomH;
        char arr[][];
        char empty[][];

        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Height = ");
        height = scanner.nextInt();

        System.out.println("Widtg = ");
        width = scanner.nextInt();

        bombs = height*width * 20 / 100;

        arr   = new char[height][width];
        empty = new char[height][width];

        for (int p1 = 0; p1 < height; p1++) {
            for (int p2 = 0; p2 < width; p2++) {
                arr[p1][p2] = '0';
            }
        }
        for (int p1 = 0; p1 < height; p1++) {
            for (int p2 = 0; p2 < width; p2++) {
                empty[p1][p2] = '|';
            }
        }

        for (int b = 0; b < bombs; b++) {
            randomW = rand.nextInt(width);
            randomH = rand.nextInt(height);
            if(arr[randomH][randomW] == '*') b--;
            else {
                arr[randomH][randomW] = '*';
                for (int q1 = randomH - 1; q1 <= randomH + 1; q1++) {
                    if (q1 < 0 || q1 >= height) continue;
                    for (int q2 = randomW - 1; q2 <= randomW+ 1; q2++) {
                        if (q2 < 0 || q2 >= width) continue;
                        if (arr[q1][q2] == '*') continue;
                        if ((q1 == randomH) && (q2 == randomW)) continue;
                        else arr[q1][q2]++;
                    }
                }
            }
        }

        for (int i = 0; i < height*width-bombs; i++) {
            for (int j1 = 0; j1 < height; j1++) {
                for (int j2 = 0; j2 < width; j2++) {
                    System.out.print(empty[j1][j2]);
                }
                System.out.println();
            }
            System.out.println("Bombs count " + bombs);

            System.out.println("height = ");
            height1 = scanner.nextInt();
            System.out.println("width = ");
            width1 = scanner.nextInt();

            if (empty[height1][width1] != '|') i--;
            if (arr[height1][width1] == '*') {System.out.println("Game Over!!!"); break;}
            else empty[height1][width1] = arr[height1][width1];
            if (arr[height1][width1] == '0') {
                for (int k1 = 0; k1 < height; k1++) {
                    for (int k2 = 0; k2 < width; k2++) {
                        if (empty[k1][k2] == '0') {
                            for (int q1 = k1 - 1; q1 <= k1 + 1; q1++) {
                                if (q1 < 0 || q1 >= height) continue;
                                for (int q2 = k2 - 1; q2 <= k2 + 1; q2++) {
                                    if (q2 < 0 || q2 >= width) continue;
                                    if ((q1 == height1) && (q2 == width1)) continue;
                                    if (arr[q1][q2] == '*') continue;
                                    if (empty[q1][q2] == '|') {
                                        empty[q1][q2] = arr[q1][q2];
                                        i++;
                                    }
                                    else continue;
                                }
                            }
                        }
                    }
                }
            }
            if (i == height*width-bombs-1) System.out.println("You Win!!!");
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}