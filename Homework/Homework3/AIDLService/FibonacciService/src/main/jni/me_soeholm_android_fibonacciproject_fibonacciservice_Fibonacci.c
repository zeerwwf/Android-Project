#include "include/me_soeholm_android_fibonacciproject_fibonacciservice_Fibonacci.h"

static jlong fib(jlong n) {
  return n <= 0 ? 0 : n == 1 ? 1 : fib(n - 1) + fib(n - 2);
}

JNIEXPORT jlong JNICALL Java_me_soeholm_android_fibonacciproject_fibonacciservice_Fibonacci_fibNR
  (JNIEnv *env, jclass clazz, jlong n) {
  return fib(n);
}
JNIEXPORT jlong JNICALL Java_me_soeholm_android_fibonacciproject_fibonacciservice_Fibonacci_fibNI
  (JNIEnv *env, jclass clazz, jlong n) {
  jlong previous = -1;
  jlong result = 1;
  jlong i;
  jlong sum;
  for (i = 0; i <= n; i++) {
    sum = result + previous;
    previous = result;
    result = sum;
  }
  return result;
}