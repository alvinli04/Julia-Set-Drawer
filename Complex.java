import java.util.*;

public class Complex{
        //a + bi = rcisx
        private static double a;
        private static double b;

        public Complex(double a, double b)
        {
                this.a = a;
                this.b = b;
        }

        public static double getRe()
        {
                return a;
        }

        public static double getIm()
        {
                return b;
        }

        public String toString()
        {
                return a + " + " + b + "i";
        }

        public static Complex pow(int x)
        {
                double r = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                double theta = Math.atan(b/a);
                double newR = Math.pow(r, x);
                double newTheta = x * theta;
                double newA = newR * Math.cos(newTheta);
                double newB = newR * Math.sin(newTheta);
                return new Complex(newA, newB);
        }

        public static Complex add(Complex w, Complex z)
        {
                Complex res = new Complex(w.getRe() + z.getRe(), w.getIm() + z.getIm());
                return res;
        }

        public static void main(String[] args) {
                Complex foo = new Complex(1, 2);
                System.out.println(foo.getIm().toString());
        }
}
