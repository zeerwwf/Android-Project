#include "server.h"
#include <binder/IPCThreadState.h>

namespace hello_world{
    char* HWService::getName(){
        return ("HWService");
    };

    char* HWService::sayHello(){
        return ("Hello World!");
    };

    // Called upon request (Basically, listen loop handler)
    status_t BnHWService::onTransact(uint32_t code, const Parcel& data, Parcel* reply, uint32_t flags)
    {
        // Who is calling?
        IPCThreadState* self = IPCThreadState::self();
        ALOGE("Calling MSG: PID=%d, UID=%d",self->getCallingPid(),self->getCallingUid());

        // Binder error code (0 if we got this far)
        reply->writeInt32(0);

        // Invoke approrirate function
        switch(code)
        {
            case GET_NAME:
                {
                    CHECK_INTERFACE(IHWService, data, reply);
                    reply->writeCString(getName());
                    return NO_ERROR;
                }break;
            case SAY_HELLO:
                {
                    CHECK_INTERFACE(IHWService, data, reply);
                    reply->writeCString(sayHello());
                    return NO_ERROR;
                }break;
            default:break;
        }
        return NO_ERROR;
    }
}//end of namespace hello_world
