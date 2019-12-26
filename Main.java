import java.util.*;

public class Main
{
        //gap: 0.007
        //range: -2 -> 2 everywhere
        public static void main(String[] args)
        {
                //read in the imaginary num used as the constant c.

                double reC = Double.parseDouble(args[0]);
                double imC = Double.parseDouble(args[1]);

                Complex c = new Complex(reC, imC);

                //drawMandelbrot();
                drawJulia(c);
        }

        //Methods for checking if a point is in a Julia set for some Complex c and drawing it.
        public static int inJulia(Complex z, Complex c)
        {
                Complex w = z;
                for(int i = 1; i <= 10000; i++) //i is the number of iterations
                {
                        w = Complex.add(w.square(), c);
                        if(w.mag() > 2) return i; //the number of iterations i corresponds to a certain color in the picture.
                }
                return -1;//the number is in the set, the pixel is colored black.
        }

        public static void drawJulia(Complex c)
        {
                StdDraw.setCanvasSize();
                //coord mapping: i from 0-> 1 -> 4x - 2 on complex plane
                StdDraw.setPenColor(StdDraw.BLACK);
                //iterate through all pixels to determine whether or not it's in the set.
                for(double i = 0; i <= 1; i+=0.001953125)
                {
                        for(double j = 0; j <= 1; j+=0.001953125)
                        {
                                Complex z = new Complex(4*i-2, 4*j-2);
                                if(inJulia(z, c) == -1)
                                {
                                        StdDraw.point(i, j); //plots i,j on the plane
                                        System.out.println(z);
                                }
                        }
                }
                System.out.println("end");
        }

        //Methods for checking if a point is in the Mandelbrot set and drawing it.
        public static int inMandelbrot(Complex z)
        {
                Complex w = new Complex(0,0);
                for(int i = 1; i <= 10000; i++) //i is the number of iterations
                {
                        w = Complex.add(w.square(), z);
                        if(w.mag() > 2) return i; //the number of iterations i corresponds to a certain color in the picture.
                }
                return -1;//the number is in the set, the pixel is colored black.
        }

        public static void drawMandelbrot()
        {
                StdDraw.setCanvasSize();
                //coord mapping: i from 0-> 1 -> 4x - 2 on complex plane
                StdDraw.setPenColor(StdDraw.BLACK);
                //iterate through all pixels to determine whether or not it's in the set.
                for(double i = 0; i <= 1; i+=0.001953125)
                {
                        for(double j = 0; j <= 1; j+=0.001953125)
                        {
                                Complex z = new Complex(4*i-2, 4*j-2);
                                if(inMandelbrot(z) == -1)
                                {
                                        StdDraw.point(i, j); //plots i,j on the plane
                                        System.out.println(z);
                                }
                        }
                }
        }
}
