import java.util.*;

public class Main
{
        static Complex c;
        //gap: 0.007
        //range: -2 -> 2 everywhere
        public static void main(String[] args)
        {
                //read in the imaginary num used as the constant c.
                /*
                double reC = Double.parseDouble(args[0]);
                double imC = Double.parseDouble(args[1]);
                */
                c = new Complex(.3, -.01);

                StdDraw.setCanvasSize();
                //coord mapping: x -> 4x - 2
                StdDraw.setPenColor(StdDraw.BLACK);
                for(double i = 0; i <= 1; i+=0.001953125)
                {
                        for(double j = 0; j <= 1; j+=0.001953125)
                        {
                                Complex z = new Complex(4*i-2, 4*j-2);
                                if(mandelbrot(z) == -1)
                                {
                                        StdDraw.point(i, j);
                                        System.out.println(z);
                                }
                                //else System.out.println("not" + z);
                        }
                }
        }

        public static int inSet(Complex z)
        {
                for(int i = 1; i <= 10000; i++) //i is the number of iterations
                {
                        z = Complex.add(z.square(), c);
                        if(z.mag() > 2) return i; //the number of iterations i corresponds to a certain color in the picture.
                }
                return -1;//the number is in the set, the pixel is colored black.
        }

        public static int mandelbrot(Complex z)
        {
                for(int i = 1; i <= 10000; i++) //i is the number of iterations
                {
                        z = Complex.add(z.square(), z);
                        if(z.mag() > 2) return i; //the number of iterations i corresponds to a certain color in the picture.
                }
                return -1;//the number is in the set, the pixel is colored black.
        }
}
