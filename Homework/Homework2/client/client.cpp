#include "api.h"
using namespace android;
using namespace hello_world;

namespace hello_world {

    BpService::BpService(const sp<IBinder>& impl ):BpInterface<IService>(impl)
    {

    }

    char* BpService::getName()
    {
        Parcel data, reply;
        data.writeInterfaceToken(IService::getInterfaceDescriptor());
        remote()->transact(GET_NAME, data, &reply);
        reply.readExceptionCode();
        return (char*)reply.readCString();
    }

    char* BpService::hello()
    {
        Parcel data, reply;
        data.writeInterfaceToken(IService::getInterfaceDescriptor());
        remote()->transact(GET_HELLO, data, &reply);
        reply.readExceptionCode();
        return (char*)reply.readCString();
    }

}

int main(int argc, char *argv[])
{
    sp<IBinder> binder;
    sp<ProcessState> proc(ProcessState::self());
    sp<IServiceManager> sm = defaultServiceManager();

    do{
        binder = sm->getService(String16(SERVICE_NAME));
        if(binder != 0)
            break;
        sleep(1);
    }while(true);

    const sp<IService>& bts = interface_cast<IService>(binder);
    ALOGE("The Client is starting....."); 
    ALOGE("Service name: %s",bts->getName());
    ALOGE("Say hello: %s",bts->hello());

    return 0;
}

