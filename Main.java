/*************************************************
* Compilation: javac Main.java
* Execution: java Main
*
* This is a program that can draw Mandelbrot and Julia
* sets, using StdDraw and a self-made complex number
* class for math operations. It makes some pretty high
* quality pictures. To view them later, all pictures
* are saved in the Drawings folder in this directory.
*
* Usage: After the program is run, answer the prompts
* provided in the terminal which configures the settings
* for the drawing. Once that's done, allow up to 5 minutes
* of render time. After the drawing is done, it will be
* saved in the Drawings folder.
**************************************************/

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;
import java.awt.Color;

public class Main
{
        //driver function
        public static void main(String[] args) throws IOException
        {
                float[] backgroundConsts = {0, 66, 210, 235};
                String[] backgrounds = {"red", "blue", "yellow", "green"};
                //background constants correspond to red, blue on the hsb color scale

                //starts the prompt session to configure settings for the drawing
                Scanner sc = new Scanner(System.in);

                String whichSet;
                float myBackground = 66;

                while(true)
                {
                        System.out.println("Please indicate whether you would like a Mandelbrot set or a Julia set.");
                        whichSet = sc.nextLine().toLowerCase();
                        if(whichSet.contains("julia") || whichSet.contains("mandelbrot"))
                        {
                                break;
                        }
                }

                //asks for background color
                System.out.print("What color background?\navailable: ");
                for(String i : backgrounds)
                {
                        System.out.print(i + " ");
                }
                System.out.println();

                outer:
                while(true)
                {
                        String myColor = sc.nextLine().toLowerCase();
                        for(int i = 0; i < backgrounds.length; i++){
                                if(myColor.contains(backgrounds[i])){
                                        myBackground = backgroundConsts[i];
                                        break outer;
                                }
                        }
                        System.out.println("Please choose an available color.");
                }
                Complex c = new Complex(0, 0);
                String filename = "";

                //if it's a julia set, asks for a complex number for the center.
                if(whichSet.contains("julia"))
                {
                        String temp;
                        //ask for the complex number to run the Julia Set function
                        System.out.println("Real part of a complex number:");
                        while(!isNumerical(temp = sc.nextLine()))
                        {
                                System.out.println("please input a number");
                        }
                        Double myRe = Double.parseDouble(temp);

                        System.out.println("Imaginary part of a complex number:");
                        while(!isNumerical(temp = sc.nextLine()))
                        {
                                System.out.println("please input a number");
                        }
                        Double myIm = Double.parseDouble(temp);

                        c = new Complex(myRe, myIm);
                        System.out.println("Please allow up to 5 minutes of render time. To cancel or save after the drawing is finished, press Ctrl + C.");
                        drawJulia(c, myBackground);
			StdDraw.save(filename = c.toString() + ".jpg");
                }
                //if it's a mandelbrot set, just draw it.
                else if(whichSet.contains("mandelbrot"))
                {
                        System.out.println("Please allow up to 5 minutes of render time. To cancel or save after the drawing is finished, press Ctrl + C.");
                        drawMandelbrot(myBackground);
			StdDraw.save(filename = "Mandelbrot.jpg");
                }
                //move the file to a Drawings folder.
                Path move = Files.move(Paths.get(filename), Paths.get("Drawings/" + filename));
        }

        //helper function: determines whether a string is completely numerical.
        public static boolean isNumerical(String strNum) {
            if (strNum == null) {
                return false;
            }
            try {
                double d = Double.parseDouble(strNum);
            }
            catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }

        //Methods for checking if a point is in a Julia set for some Complex c and drawing it.
        public static float inJulia(Complex z, Complex c)
        //z indicates the current pixel referenced, c is the complex constant used in z = z^2 + c.
        {
                Complex w = z;
                for(int i = 1; i <= 100; i++) //i is the number of iterations
                {
                        w = Complex.add(w.square(), c);
                        if(w.mag() > 2)
                        {
                                double m = w.mag();
                                return (float) (i + 1 - Math.log(Math.log(m)/Math.log(2)));
                        }
                        //the number of iterations i corresponds to a certain color in the picture.
                        //the log return format instead of linear makes for better color contrast in the picture.
                }
                return -1;
                //the number is in the set, the pixel is colored black.
        }

        //draws the julia set given parameters of a complex number center and a background color..
        public static void drawJulia(Complex c, float backgroundConst)
        {
                StdDraw.setCanvasSize();
                //iterate through all pixels to determine whether or not it's in the set.
                for(double i = 0; i <= 1; i+=0.001953125)
                {
                        for(double j = 0; j <= 1; j+=0.001953125)
                        {
                                Complex z = new Complex(4*i-2, 4*j-2);
                                if(inJulia(z, c) == -1)
                                {
                                        StdDraw.setPenColor(StdDraw.BLACK);
                                        StdDraw.point(i, j); //plots i,j on the plane
                                }
                                else
                                {
                                        //maps a color based on the number of iterations required to escape.
                                        StdDraw.setPenColor(Color.getHSBColor((inJulia(z, c) + backgroundConst) / 100, (float) 1, (float) 1));
                                        StdDraw.point(i,j);
                                }
                        }
                }
        }

        //Methods for checking if a point is in the Mandelbrot set and drawing it.
        public static float inMandelbrot(Complex z)
        {
                Complex w = new Complex(0,0);
                for(int i = 1; i <= 100; i++)
                //i is the number of iterations
                {
                        w = Complex.add(w.square(), z);
                        if(w.mag() > 2)
                        {
                                double m = w.mag();
                                return (float) (i + 1 - Math.log(Math.log(m)/Math.log(2)));
                        }
                        //the number of iterations i corresponds to a certain color in the picture.
                }
                return -1;//the number is in the set, the pixel is colored black.
        }

        //Draws the mandelbrot set.
        public static void drawMandelbrot(float backgroundConst)
        {
                StdDraw.setCanvasSize();
                //iterate through all pixels to determine whether or not it's in the set.
                for(double i = 0; i <= 1; i+=0.001953125)
                {
                        for(double j = 0; j <= 1; j+=0.001953125)
                        {
                                Complex z = new Complex(4*i-2, 4*j-2);
                                if(inMandelbrot(z) == -1)
                                {
                                        StdDraw.setPenColor(StdDraw.BLACK);
                                        StdDraw.point(i, j); //plots i,j on the plane
                                }
                                else
                                {
                                        //sets the color, determined by the number of iterations it takes to escape.
                                        StdDraw.setPenColor(Color.getHSBColor((inMandelbrot(z) + backgroundConst) / 100, (float)1, (float)1));
                                        StdDraw.point(i,j);
                                }
                        }
                }
        }

}
