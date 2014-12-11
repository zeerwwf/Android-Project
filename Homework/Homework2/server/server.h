#ifndef __DEMO_BINDER_API_SERVER__
#define __DEMO_BINDER_API_SERVER__ 1

#include "api.h"

namespace hello_world {
    
    class BnService: public BnInterface<IService>
    {
        public:
            virtual status_t onTransact( uint32_t code, const Parcel& data, Parcel* reply, uint32_t flags = 0);
    };
    class Service : public BnService
    {
        public:
            virtual char* getName();
            virtual char* hello();
    };
}

#endif
