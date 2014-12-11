#ifndef __DEMO_BINDER_API_SERVER__
#define __DEMO_BINDER_API_SERVER__ 1

#include "api.h"

namespace hello_world {
    
    class BnHWService: public BnInterface<IHWService>
    {
        public:
            virtual status_t onTransact( uint32_t code,
                    const Parcel& data,
                    Parcel* reply,
                    uint32_t flags = 0);
    };
    class HWService : public BnHWService
    {
        public:
            virtual char* getName();
            virtual char* sayHello();
    };
}

#endif
