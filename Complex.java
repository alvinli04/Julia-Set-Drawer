import java.util.*;

//a simple complex number class
public class Complex
{
        //fields: real and imaginary parts
        final double re;
        final double im;

        //constructor
        public Complex(double re, double im)
        {
                this.re = re;
                this.im = im;
        }

        //static function that adds 2 complex numbers.
        public static Complex add(Complex z, Complex w)
        {
                double newRe = z.re + w.re;
                double newIm = z.im + w.im;
                return new Complex(newRe, newIm);
        }

        //static function that multiplies 2 complex numbers.
        public static Complex multiply(Complex z, Complex w)
        {
                return new Complex(z.re * w.re - z.im * w.im, z.re * w.im + z.im * w.re);
        }

        //returns the magnitude of a complex number.
        public double mag()
        {
                return Math.hypot(re, im);
        }

        //squares a complex number.
        public Complex square()
        {
                return multiply(this, this);
        }

        //prints the complex number as a String.
        public String toString()
        {
                return re + " + " + im + "i";
        }

}
