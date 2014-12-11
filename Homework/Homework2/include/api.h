#ifndef __DEMO_BINDER_API__
#define __DEMO_BINDER_API__ 1

#include <binder/Parcel.h>
#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include <binder/IBinder.h>
#include <binder/IInterface.h>

#include <utils/Log.h>
#include <utils/String16.h>

using namespace android;

#define SERVICE_NAME "android.binder.example"
#define META_INTERFACE_NAME "android.binder.example.IService"

namespace hello_world {

    enum DEMO_API_ENUM
    {
        GET_NAME=IBinder::FIRST_CALL_TRANSACTION, GET_HELLO
    };

    class IService : public IInterface
    {
        public:
            DECLARE_META_INTERFACE(Service);

            virtual char* getName() = 0;
            virtual char* hello() = 0;
    };


    class BpService : public BpInterface<IService>
    {
        public:
            BpService(const sp<IBinder>& impl );
            virtual char* getName();
            virtual char* hello();
    };


}

#endif //end of ifndef __DEMO_BINDER_API__
