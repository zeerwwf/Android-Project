package me.soeholm.android.fibonacciproject.common;

/**
 * Created by niels on 12/9/14.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class FibonacciRequest implements Parcelable {
    // Request Types
    public static enum Type {
        RECS_JAVA,
        ITER_JAVA,
        RECS_NATIVE,
        ITER_NATIVE
    }

    // Actual object (Java fluff)
    private final long n;
    private final Type type;
    public FibonacciRequest(long n, Type type) {
        this.n = n;
        this.type = type;
    }
    public long getN() { return n; }
    public Type getType() { return type; }

    // Mandatory override we chose not to implement
    @Override
    public int describeContents() {
        return 0;
    }

    // MARSHAL to parcel (called before request is sent)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.n);
        parcel.writeInt(this.type.ordinal());
    }

    // UMARSHAL from parcel (called when request is received)
    public static final Parcelable.Creator<FibonacciRequest> CREATOR =
            new Parcelable.Creator<FibonacciRequest>() {
                @Override
                public FibonacciRequest createFromParcel(Parcel parcel) {
                    long n = parcel.readLong();
                    Type type = Type.values()[parcel.readInt()];
                    return new FibonacciRequest(n, type);
                }
                @Override
                public FibonacciRequest[] newArray(int i) {
                    return new FibonacciRequest[i];
                }
            };
}
