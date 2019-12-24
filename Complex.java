import java.util.*;

public class Complex
{
        double re;
        double im;

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
        

        public Complex power(double p)
        {
                double mag = Math.pow(Math.sqrt(re * re + im * im), p);
                double theta = p * Math.atan(im / re);
                double newRe = mag * Math.cos(theta);
                double newIm = mag * Math.sin(theta);
                return new Complex(newRe, newIm);
        }

        public String toString()
        {
                return re + " + " + im + "i";
        }
}
