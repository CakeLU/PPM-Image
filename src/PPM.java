import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ss2sa on 11/15/2016.
 */
public class PPM extends Image {

    // Attributes
    int[][][] pixels;

    // Default Constructor (This constructor just reads from a PPMTest.ppm file)
    public PPM() {
        try {
            Scanner sc = new Scanner(new File("cake.ppm"));
            magic = sc.next();
            width = sc.nextInt();
            height = sc.nextInt();
            depth = sc.nextInt();
            pixels = new int[height][width][3];

            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[0].length; j++) {
                    System.out.print("{ ");
                    for (int k = 0; k < 3; k++) {
                        pixels[i][j][k] = sc.nextInt();
                        System.out.print(pixels[i][j][k] + " ");
                    }
                    System.out.print("} ");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found: ");
        }
    }

    // Overloading Constructor
    public PPM(String userFile) {
        try {
            Scanner sc = new Scanner(new File(userFile));
            magic = sc.next();
            width = sc.nextInt();
            height = sc.nextInt();
            depth = sc.nextInt();
            pixels = new int[height][width][3];

            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[0].length; j++) {
//                    System.out.print("{ ");
                    for (int k = 0; k < 3; k++) {
                        pixels[i][j][k] = sc.nextInt();
//                        System.out.print(pixels[i][j][k] + " ");
                    }
//                    System.out.print("} ");
                }
//                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found: ");
        }
    }

    // Accessors: getMagic(), getWidth(), etc...
    public String getMagic() {
        return magic;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    // Modifiers: setMagic(string m), setWidth(int w), etc...
    public void setMagic(String m) {
        magic = m;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    public void setDepth(int d) {
        depth = d;
    }

    // Returns the pixel array
    public int[][][] getPixels() {
        return pixels;
    }

    // Prints all pixel values
    public void printPixels() {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                System.out.print("{ ");
                for (int k = 0; k < 3; k++) {
                    System.out.print(pixels[i][j][k] + " ");
                }
                System.out.print("} ");
            }
            System.out.println();
        }
    }

    // Takes the pixel array and flips it horizontally
    public void flip_horizontally() {
        int[][][] horizontalFlip = new int[height][width][3];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    horizontalFlip[i][j][k] = pixels[i][(width - 1) - j][k];
                }
            }
        }
        pixels = new int[height][width][3];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    pixels[i][j][k] = horizontalFlip[i][j][k];
                }
            }
        }
    }

    // Takes the pixel array and flips it vertically
    public void flip_vertically() {
        int[][][] verticalFlip = new int[height][width][3];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    verticalFlip[i][j][k] = pixels[(height - 1) - i][j][k];
                }
            }
        }

        pixels = new int[height][width][3];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    pixels[i][j][k] = verticalFlip[i][j][k];
                }
            }
        }
    }

    // Takes the pixel array and rotates it to the right 90 degrees
    public void rotate_right_90() {
        int[][][] rotatedPixels = new int[width][height][3];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    rotatedPixels[j][i][k] = pixels[(height - 1) - i][j][k];
                }
            }
        }

        height = rotatedPixels.length;
        width = rotatedPixels[0].length;

        pixels = new int[height][width][3];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    pixels[i][j][k] = rotatedPixels[i][j][k];
                }
            }
        }
    }

    // Takes the reds and negates them (chooses opposite value or 255 - current value)
    public void negate_red() {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j][0] = (255 - pixels[i][j][0]);
            }
        }
    }

    // Takes the greens and negates them (chooses opposite value or 255 - current value)
    public void negate_green() {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j][1] = (255 - pixels[i][j][1]);
            }
        }
    }

    // Takes the blues and negates them (chooses opposite value or 255 - current value)
    public void negate_blue() {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j][2] = (255 - pixels[i][j][2]);
            }
        }
    }

    // Converts image to grey scale by setting all RGB values to average of RGB values
    public void grey_scale() {
        int RGBSum = 0;
        int RGBAvg = 0;

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    RGBSum += pixels[i][j][k];
                }
                RGBAvg = (int) ((float) RGBSum / (float) 3);
                for (int k = 0; k < 3; k++) {
                    pixels[i][j][k] = RGBAvg;
                }
                RGBSum = 0;
                RGBAvg = 0;
            }
        }
    }

    // Negates red value to 0
    public void flatten_red() {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j][0] = 0;
            }
        }
    }

    // Negates green value to 0
    public void flatten_green() {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j][1] = 0;
            }
        }
    }

    // Negates blue value to 0
    public void flatten_blue() {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j][2] = 0;
            }
        }
    }

    // Takes each RGB value and makes it 255 or 0 dependent on the current value
    public void extreme_contrast() {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                for (int k = 0; k < 2; k++) {
                    if (pixels[i][j][k] > 127) {
                        pixels[i][j][k] = 255;
                    }

                    else {
                        pixels[i][j][k] = 0;
                    }
                }
            }
        }
    }

    // Adds a random number to every RGB value within the 0-255 boundary
    public void random_noise(int userNum) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                for (int k = 0; k < 3; k ++) {
                    int randNum = (int) (Math.random() * userNum) + 1;
                    double posOrNeg = Math.random();
                    if (posOrNeg < 0.5) {
                        pixels[i][j][k] += randNum;

                        if (pixels[i][j][k] > 255) {
                            pixels[i][j][k] = 255;
                        }
                    }
                    else {
                        pixels[i][j][k] -= randNum;
                        if (pixels[i][j][k] < 0) {
                            pixels[i][j][k] = 0;
                        }
                    }


                }
            }
        }
    }

}