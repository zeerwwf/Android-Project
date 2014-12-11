LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := hello
LOCAL_SRC_FILES := hello.cpp
LOCAL_SHARED_LIBRARIES := libutils libcutils libbinder
LOCAL_C_INCLUDES += frameworks/base/include system/core/include

include $(BUILD_EXECUTABLE)