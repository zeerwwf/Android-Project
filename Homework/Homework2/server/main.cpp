#include "server.h"

int main(int argc, char *argv[])
{
    sp<ProcessState> proc(ProcessState::self());
    sp<IServiceManager> sm = defaultServiceManager();

    sm->addService(String16(SERVICE_NAME),new hello_world::Service());
    ALOGE("%s The service is starting.....",SERVICE_NAME);
    
    ProcessState::self()->startThreadPool();
    IPCThreadState::self()->joinThreadPool();

    return 0;
}

