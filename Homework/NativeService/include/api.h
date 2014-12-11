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
#define META_INTERFACE_NAME "android.binder.example.IHWService"

namespace hello_world {

    // function ID used for invocation
    enum DEMO_API_ENUM{
        GET_NAME=IBinder::FIRST_CALL_TRANSACTION, SAY_HELLO
    };

    /**
     * Binder Shared interface, stub code
     */
    class IHWService : public IInterface
    {
        public:
            DECLARE_META_INTERFACE(HWService);

            /*
             * Service API
             */
            virtual char* getName() = 0;
            virtual char* sayHello() = 0;
    };

    /*
     * The proxy between the Client and Server
     */
    class BpHWService : public BpInterface<IHWService>
    {
        public:
            BpHWService(const sp<IBinder>& impl );
            virtual char* getName();
            virtual char* sayHello();
    };


}//end of namespace hello_world

#endif //end of ifndef __DEMO_BINDER_API__
