import java.util.*;
import java.awt.Color;

public class Main
{
        //gap: 0.007
        //range: -2 -> 2 everywhere

        //driver function
        public static void main(String[] args)
        {
                //read in the imaginary num used as the constant c.
                /*
                double reC = Double.valueOf(args[0]);
                double imC = Double.valueOf(args[1]);
                */
                int[] backgroundConsts = {66, 0}; //background constants correspond to blue, red
                Complex c = new Complex(0, -.8);

                drawMandelbrot(66);
                //drawJulia(c);
                //test();
        }

        //Methods for checking if a point is in a Julia set for some Complex c and drawing it.
        public static float inJulia(Complex z, Complex c)
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

        public static void drawJulia(Complex c)
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
                                        System.out.println(z);
                                }
                                else
                                {
                                        StdDraw.setPenColor(Color.getHSBColor((inJulia(z, c) + 66.) / 100, (float) 1, (float) 1));
                                        StdDraw.point(i,j);
                                }
                        }
                }
                //System.out.println("done");
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

        public static void drawMandelbrot(int backgroundConst)
        {
                StdDraw.setCanvasSize();
                //coord mapping: i from 0-> 1 -> 4x - 2 on complex plane
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
                                        //System.out.println(z);
                                }
                                else
                                {
                                        StdDraw.setPenColor(Color.getHSBColor((inMandelbrot(z) + 66) / 100, (float)1, (float)1));
                                        StdDraw.point(i,j);
                                        System.out.println((inMandelbrot(z) / 100) + 66./100);
                                }
                        }
                        //System.out.println("done");
                }
        }

}
