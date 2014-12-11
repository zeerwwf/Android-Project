#include "api.h"
using namespace android;
using namespace hello_world;

// Client proxy
namespace hello_world {

    BpHWService::BpHWService(const sp<IBinder>& impl ):BpInterface<IHWService>(impl)
    {

    }

    char* BpHWService::getName()
    {
        Parcel data, reply;
        data.writeInterfaceToken(IHWService::getInterfaceDescriptor());
        remote()->transact(GET_NAME, data, &reply);
        reply.readExceptionCode();
        return (char*)reply.readCString();
    }

    char* BpHWService::sayHello()
    {
        Parcel data, reply;
        data.writeInterfaceToken(IHWService::getInterfaceDescriptor());
        remote()->transact(SAY_HELLO, data, &reply);
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

    const sp<IHWService>& bts = interface_cast<IHWService>(binder);
    ALOGE("Helloworldclient is starting....."); 

    ALOGE("Asked service for name, it responded: %s",bts->getName());
    ALOGE("Asked to say hello, service responded: %s",bts->sayHello());

    return 0;
}

