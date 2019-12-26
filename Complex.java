import java.util.*;

public class Complex
{
        final double re;
        final double im;

        public Complex(double re, double im)
        {
                this.re = re;
                this.im = im;
        }


        public static Complex add(Complex z, Complex w)
        {
                double newRe = z.re + w.re;
                double newIm = z.im + w.im;
                return new Complex(newRe, newIm);
        }

        public static Complex multiply(Complex z, Complex w)
        {
                return new Complex(z.re * w.re - z.im * w.im, z.re * w.im + z.im * w.re);
        }

        public double mag()
        {
                return Math.hypot(re, im);
        }

        public Complex square()
        {
                return multiply(this, this);
        }

        public String toString()
        {
                return re + " + " + im + "i";
        }
}
