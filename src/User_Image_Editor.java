import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by ss2sa on 11/28/2016.
 */
public class User_Image_Editor {

    public static void main(String[] args) {
        System.out.println("Portable Pixmap (PPM) Image Editor");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        boolean invalidInput;
        String userInputFile = "";

        // Do while loop to continue asking user for proper .ppm file to be read
        do {

            System.out.print("Enter name of image file: ");
            invalidInput = false;

            // Try-Catch will catch file names that do not end with ".ppm"
            try {
                userInputFile = sc.nextLine();

                if (!userInputFile.substring(userInputFile.length() - 4, userInputFile.length()).equals(".ppm")) {
                    throw new InputMismatchException();
                }
            }
            catch (Exception e) {
                System.out.println("That file name does not end with \".ppm\"!");
                invalidInput = true;
            }
        } while (invalidInput);

        // Same loop but for the output file
        String userOutputFile = "";
        do {

            System.out.print("Enter name of output file: ");
            invalidInput = false;

            // Try-Catch will catch file names that do not end with ".ppm"
            try {
                userOutputFile = sc.nextLine();

                if (!userOutputFile.substring(userOutputFile.length() - 4, userOutputFile.length()).equals(".ppm")) {
                    throw new InputMismatchException();
                }
            }
            catch (Exception e) {
                System.out.println("That file name does not end with \".ppm\"!");
                invalidInput = true;
            }
        } while (invalidInput);

        // Tests to ensure proper user variables are used
        // System.out.println(userInputFile);
        // System.out.println(userOutputFile);

        System.out.println();
        System.out.println("Here are your choices:");
        System.out.println("[1]  convert to greyscale [2]  flip horizontally");
        System.out.println("[3]  negative of red [4]  negative of green [5]  negative of blue");
        System.out.println("[6]  just the reds [7]  just the greens [8]  just the blues");
        System.out.println();

        boolean[] userFunctionArray = new boolean[8];
        String yesOrNo = "";

        for (int i = 0; i < 8; i++) {

            do {
                System.out.print("Do you want [" + (i + 1) + "]? (y/n) ");
                invalidInput = false;

                // Try-Catch will catch any input that isn't "y" or "n"
                try {
                    yesOrNo = sc.nextLine();

                    if (!(yesOrNo.equals("y") || yesOrNo.equals("n"))) {
                        throw new InputMismatchException();
                    }
                }

                catch (Exception e) {
                    System.out.println("That input is not valid! Please type \"y\" for yes or \"n\" for no.");
                    invalidInput = true;
                }
            } while (invalidInput);

            if (yesOrNo.equals("y")) {
                userFunctionArray[i] = true;
            }

            else {
                userFunctionArray[i] = false;
            }

        }

        // Test to ensure that array is properly storing true and false values
        /*for (int i = 0; i < 8; i ++) {
            System.out.println(userFunctionArray[i]);
        }*/

        PPM userImage = new PPM(userInputFile);

        // Greyscale
        if (userFunctionArray[0]) {
            userImage.grey_scale();
        }

        // Flip horizontally
        if (userFunctionArray[1]) {
            userImage.flip_horizontally();
        }

        // Negate reds
        if (userFunctionArray[2]) {
            userImage.negate_red();
        }

        // Negate greens
        if (userFunctionArray[3]) {
            userImage.negate_green();
        }

        // Negate blues
        if (userFunctionArray[4]) {
            userImage.negate_blue();
        }

        // Just the reds
        if (userFunctionArray[5]) {
            userImage.flatten_green();
            userImage.flatten_blue();
        }

        // Just the greens
        if (userFunctionArray[6]) {
            userImage.flatten_red();
            userImage.flatten_blue();
        }

        // Just the blues
        if (userFunctionArray[7]) {
            userImage.flatten_green();
            userImage.flatten_red();
        }
    }
}
