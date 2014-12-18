// IFibonacciService.aidl
package me.soeholm.android.fibonacciproject.common;

import me.soeholm.android.fibonacciproject.common.FibonacciRequest;
import me.soeholm.android.fibonacciproject.common.FibonacciResponse;

interface IFibonacciService {
    long fibonacciJR(in long n);
    long fibonacciJI(in long n);
    long fibonacciNR(in long n);
    long fibonacciNI(in long n);

    FibonacciResponse fibonacci(in FibonacciRequest request);
}
