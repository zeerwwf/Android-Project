#include "server.h"
#include <binder/IPCThreadState.h>

namespace hello_world{

    char* Service::getName()
    {
        return ("Service");
    };

    char* Service::hello()
    {
        return ("Hello!");
    };


    status_t BnService::onTransact(uint32_t code, const Parcel& data, Parcel* reply, uint32_t flags)
    {

        IPCThreadState* self = IPCThreadState::self();
        ALOGE("Calling MSG: PID=%d, UID=%d",self->getCallingPid(),self->getCallingUid());


        reply->writeInt32(0);


        switch(code)
        {
            case GET_NAME:
                {
                    CHECK_INTERFACE(IService, data, reply);
                    reply->writeCString(getName());
                    return NO_ERROR;
                }break;
            case GET_HELLO:
                {
                    CHECK_INTERFACE(IService, data, reply);
                    reply->writeCString(hello());
                    return NO_ERROR;
                }break;
            default:break;
        }
        return NO_ERROR;
    }
}
