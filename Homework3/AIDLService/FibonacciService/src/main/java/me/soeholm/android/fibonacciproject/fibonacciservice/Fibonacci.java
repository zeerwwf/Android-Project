package me.soeholm.android.fibonacciproject.fibonacciservice;

/**
 * Created by niels on 12/10/14.
 */
public class Fibonacci {
    
    public static long fibJR (long n)
    {
        if (n == 0) return 0;
        if (n<= 2) return 1;
        return fibJR(n - 1) + fibJR(n - 2);
    }

    public static long fibJI(long n) {
        long f = 0;
        long g = 1;
        for(long i = 1; i <= n; i++)
        {
            f = f + g;
            g = f - g;
        }
        return f;
    }

    public native static long fibNR(long n);

    public native static long fibNI(long n);

    static {
        System.loadLibrary("me_soeholm_android_fibonacciproject_fibonacciservice_Fibonacci");
    }
}
