// Java Program to Implement the RSA Algorithm
import java.math.*;
import java.util.*;

class Main {
    public static void main(String args[])
    {
        int p, q, n, fi, d = 0, e, i, temp;


        int msg = 19;
        double c;
        BigInteger msgback;

        p = 3;
        System.out.println(p);
        q = 5;
        System.out.println(q);
        n = p * q;
        fi = (p - 1) * (q - 1);
        System.out.println("fi = " + fi);

        //Генерация e
        int a = 2; // Начальное значение диапазона e
        int b = fi; // Конечное значение диапазона e
        boolean isPrime;
        boolean isSmaller;
        do {
            isPrime = true;
            isSmaller = false;
            e = a + (int) (Math.random() * b);
            for (i = 2; i <= e/2; i++) {
                temp = e % i;
                if (temp == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (gcd(e, fi) == 1) {
                isSmaller = true;
            }
        } while (!isPrime || !isSmaller);

        System.out.println("e: " + e);

        d = modInverse(e, fi);
        System.out.println("d = " + d);

        c = (Math.pow(msg, e)) % n;
        System.out.println("Encrypted message is : " + c);


        BigInteger N = BigInteger.valueOf(n);
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        msgback = (C.pow(d)).mod(N);
        System.out.println("Decrypted message is : " + msgback);
    }

    static int modInverse(int e, int fi)
    {

        for (int x = 1; x < fi; x++)
            if (((e % fi) * (x % fi)) % fi == 1)
                return x;
        return 1;
    }

    static int gcd(int e, int fi)
    {
        if (e == 0)
            return fi;
        else
            return gcd(fi % e, e);
    }
}
