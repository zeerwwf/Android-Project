#ifndef __DEMO_BINDER_API_STUB__
#define __DEMO_BINDER_API_STUB__ 1

#include "api.h"

using namespace android;

namespace hello_world {

    /*
     * For META implementation
     *  Depend on API and Bp part
     */
    IMPLEMENT_META_INTERFACE(HWService,META_INTERFACE_NAME);

}//end of namespace hello_world

#endif
