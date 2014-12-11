LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= server.cpp main.cpp

LOCAL_SHARED_LIBRARIES := libutils libbinder liblog

LOCAL_C_INCLUDES := $(LOCAL_PATH) $(LOCAL_PATH)/../include $(LOCAL_PATH)/../lib

LOCAL_C_INCLUDES += $(BINDERDEMO_C_INCLUDES)

LOCAL_LDLIBS += $(BINDERDEMO_LDLIBS)

LOCAL_STATIC_LIBRARIES := helloWorldClient_Stub

LOCAL_MODULE:= helloWorldServer

include $(BUILD_EXECUTABLE)
