package me.soeholm.android.fibonacciproject.fibonacciservice;

/**
 * Created by niels on 12/10/14.
 */

import android.os.RemoteException;
import android.os.SystemClock;

import me.soeholm.android.fibonacciproject.common.FibonacciRequest;
import me.soeholm.android.fibonacciproject.common.FibonacciResponse;
import me.soeholm.android.fibonacciproject.common.IFibonacciService;

public class IFibonacciServiceImpl extends IFibonacciService.Stub {

    @Override
    public long fibonacciJR(long n) throws RemoteException {
        return Fibonacci.fibJR(n);
    }

    @Override
    public long fibonacciJI(long n) throws RemoteException {
        return Fibonacci.fibJI(n);
    }

    @Override
    public long fibonacciNR(long n) throws RemoteException {
        return Fibonacci.fibNR(n);
    }

    @Override
    public long fibonacciNI(long n) throws RemoteException {
        return Fibonacci.fibNI(n);
    }

    @Override
    public FibonacciResponse fibonacci(FibonacciRequest request) throws RemoteException {
        long time = SystemClock.uptimeMillis();
        long result = -1;

        // Invoke correct implementation based on type defined in request
        switch (request.getType()) {
            case RECS_JAVA:
                result = this.fibonacciJR(request.getN());
                break;
            case ITER_JAVA:
                result = this.fibonacciJI(request.getN());
                break;
            case RECS_NATIVE:
                result = this.fibonacciNR(request.getN());
                break;
            case ITER_NATIVE:
                result = this.fibonacciNI(request.getN());
                break;
        }

        // Calculate elapsed time
        time = SystemClock.uptimeMillis() - time;

        // Return parcelable response object
        return new FibonacciResponse(result, time);
    }
}
