import java.util.Scanner;

/**
 * Created by ss2sa on 11/15/2016.
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter PPM file to read: ");
        String userFile = sc.nextLine();
        PPM ppm = new PPM(userFile);
        System.out.println();
        ppm.flip_horizontally();
        ppm.printPixels();
        System.out.println();
        ppm.grey_scale();
        System.out.println();
        ppm.printPixels();
        System.out.println();
        ppm.flatten_red();
        ppm.printPixels();
        System.out.println();
        ppm.flatten_green();
        ppm.printPixels();
        System.out.println();
        ppm.flatten_blue();
        ppm.printPixels();
        System.out.println();
    }
}
