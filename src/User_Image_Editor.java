import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by ss2sa on 11/28/2016.
 * This program allows a user to take a PPM image file and manipulate it based on the provided functions below.
 * This program only contains the user interface. For all functions and object characteristics, see PPM.java.
 * This program contains file manipulation. When the user provides an output file, it will write to it, or overwrite it if it is already present.
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

        System.out.println();
        System.out.println("Here are your choices:");
        System.out.println("[1]  convert to greyscale [2]  flip horizontally");
        System.out.println("[3]  negative of red [4]  negative of green [5]  negative of blue");
        System.out.println("[6]  just the reds [7]  just the greens [8]  just the blues");
        System.out.println("[9]  extreme contrast [10]  random noise");
        System.out.println();

        boolean[] userFunctionArray = new boolean[10];
        String yesOrNo = "";

        for (int i = 0; i < 10; i++) {

            // See above do-while loops
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

        PPM userImage = new PPM(userInputFile);

//        userImage.printPixels();

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

        // Extreme contrast
        if (userFunctionArray[8]) {
            userImage.extreme_contrast();
        }

        // Random noise
        if (userFunctionArray[9]) {
            int userNum = 0;

            // See above do-while loops
            do {
                System.out.print("What random number would you like to add to each RGB value? (0-255) ");
                invalidInput = false;

                try {
                    userNum = sc.nextInt();
                    if (userNum < 0 || userNum > 255) {
                        throw new IllegalArgumentException();
                    }

                    if (!(userNum == (int) userNum)) {
                        throw new IllegalArgumentException();
                    }
                }

                catch (Exception e) {
                    System.out.println("There was an error. Be sure you're inputting an integer between 0 and 255!");
                    invalidInput = true;
                }
            } while (invalidInput);

            userImage.random_noise(userNum);
        }

        // PrintWriter object will take the adjusted image and write it to the specified user output file
        try {
            PrintWriter printWriter = new PrintWriter(userOutputFile, "UTF-8");
            printWriter.println(userImage.getMagic());
            printWriter.println(userImage.getWidth() + " " + userImage.getHeight());
            printWriter.println(userImage.getDepth());

            int[][][] pixels = userImage.getPixels();

            for (int i = 0; i < userImage.getHeight(); i++) {
                for (int j = 0; j < userImage.getWidth(); j++) {
                    for (int k = 0; k < 3; k++) {
                        printWriter.print(pixels[i][j][k] + " ");
                    }
                }
                printWriter.println();
            }

            printWriter.close();
        }

        catch (Exception e) {
            System.out.println(e);
            System.out.print("In other words... SOMETHING BROKE! Dang it Billy...");
        }

        PPM outputTest = new PPM("PPMAdjusted.ppm");

        outputTest.printPixels();

    }
}
