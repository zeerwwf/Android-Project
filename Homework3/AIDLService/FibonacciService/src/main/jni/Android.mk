LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SRC_FILES := me_soeholm_android_fibonacciproject_fibonacciservice_Fibonacci.c
LOCAL_LDLIBS += -llog
LOCAL_MODULE    := me_soeholm_android_fibonacciproject_fibonacciservice_Fibonacci
include $(BUILD_SHARED_LIBRARY)