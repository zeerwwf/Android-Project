package me.soeholm.android.fibonacciproject.common;

/**
 * Created by niels on 12/10/14.
 */


import android.os.Parcel;
import android.os.Parcelable;


public class FibonacciResponse implements Parcelable {

    // Actual object (Java fluff)
    private final long result;
    private final long time;
    public FibonacciResponse(long result, long time) {
        this.result = result;
        this.time = time;
    }
    public long getResult() {
        return result;
    }
    public long getTime() {
        return time;
    }

    // Mandatory override we chose not to implement
    @Override
    public int describeContents() {
        return 0;
    }

    // MARSHAL to parcel (called before response is sent)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(result);
        parcel.writeLong(time);
    }

    // UMARSHAL from parcel (called when response is received)
    public static final Parcelable.Creator<FibonacciResponse> CREATOR =
            new Parcelable.Creator<FibonacciResponse>() {
                @Override
                public FibonacciResponse createFromParcel(Parcel parcel) {
                    long result = parcel.readLong();
                    long time = parcel.readLong();
                    return new FibonacciResponse(result, time);
                }
                @Override
                public FibonacciResponse[] newArray(int i) {
                    return new FibonacciResponse[i];
                }
            };
}

