#include "server.h"

int main(int argc, char *argv[])
{
    sp<ProcessState> proc(ProcessState::self());
    sp<IServiceManager> sm = defaultServiceManager();

    // Register service
    sm->addService(String16(SERVICE_NAME),new hello_world::HWService());
    ALOGE("%s service is starting.....",SERVICE_NAME);
    
    /// Start and loop
    ProcessState::self()->startThreadPool();
    IPCThreadState::self()->joinThreadPool();

    return 0;
}

