import java.util.*;
import java.io.*;
import java.awt.Color;

public class Main
{
        //driver function
        public static void main(String[] args) throws IOException
        {
                float[] backgroundConsts = {0, 66};
                String[] backgrounds = {"red", "blue"};
                //background constants correspond to red, blue on the hsb color scale

                float myBackground = 66;
                Scanner sc = new Scanner(System.in);
                System.out.println("Julia Set or Mandelbrot Set?");
                String whichSet = sc.nextLine().toLowerCase();
                //determines whether to draw a Mandelbrot or Julia set
                if(!whichSet.contains("julia") && !whichSet.contains("mandelbrot"))
                {
                        System.out.println("Please indicate whether you would like a Mandelbrot set or a Julia set.");
                        String[] placeholder = new String[0];
                        main(placeholder);
                }

                //asks for background color
                System.out.print("What color background?\navailable: ");
                for(String i : backgrounds)
                {
                        System.out.print(i + " ");
                }
                System.out.println();
                String myColor = sc.nextLine().toLowerCase();
                for(int i = 0; i < backgrounds.length; i++){
                        if(myColor.contains(backgrounds[i])){
                                myBackground = backgroundConsts[i];
                                break;
                        }
                        System.out.println("color not available, the image will be drawn in blue");
                }

                Complex c = new Complex(0, 0);
                if(whichSet.contains("julia"))
                {
                        //ask for the complex number to run the Julia Set function
                        System.out.println("Real part of a complex number:");
                        double myRe = sc.nextDouble();
                        System.out.println("Imaginary part of a complex number:");
                        double myIm = sc.nextDouble();
                        c = new Complex(myRe, myIm);
                        drawJulia(c, myBackground);
			StdDraw.save(c.toString() + ".jpg");
                }
                else if(whichSet.contains("mandelbrot"))
                {
                        drawMandelbrot(myBackground);
			StdDraw.save("Mandelbrot.jpg");
                }
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

        public static void drawJulia(Complex c, float backgroundConst)
        {
                StdDraw.setCanvasSize();
                //coord mapping: i from 0-1 -> 4x - 2 on complex plane
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
                                        StdDraw.setPenColor(Color.getHSBColor((inMandelbrot(z) + backgroundConst) / 100, (float)1, (float)1));
                                        StdDraw.point(i,j);
                                }
                        }
                }
        }

}
