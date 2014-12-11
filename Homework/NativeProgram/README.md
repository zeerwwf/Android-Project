Homework #1: Native Program
===============

The solution is a pure C++ program that can be compiled using AOSP (Android Open Source Project) and run on Android devices without a java runtime (Dalvik). The challenge of the exercise was more to get a working environment than about coding. 

## Usage instructions

#### 1. Get your AOSP up and running
  * Install dependencies (https://source.android.com/source/initializing.html)
  * Download source (skip if you obtained tarball, https://source.android.com/source/downloading.html)
  * DON'T build the entire OS (unless you make plans to modify it and deploy on a device)

#### 2. Configure build environment

* Navigate to AOSP root

        cd <aosp root>

* Bind some useful functions provided by the Google engineers

        source build/envsetup.sh

* Choose target architecture (if you wish to use provided emulators ARM is a good choice)

        lunch aosp_arm-eng
        
#### 3. Build

* Copy our C++ program into externals

        cp -r <this repo>/Homework/NativeProgram <aosp root>/externals/

* Navigate to the program

        cd <aosp root>/externals/NativeProgram

* Build program and its dependencies (using one of those nifty Google functions)

        mma
        
* (tip) Further builds can just build program itself (assuming no new dependencies added)

        mm

#### 4. Prepare device

* Open Android Virtual Device Manager

        android avd

* Create new or start existing emulator using the GUI (Make sure device uses ARM architecture)
        

#### 5. Deploy and execute

* Use adb to copy from local machine to device

        adb push /home/niels/android/Android4.4AOSP/out/target/product/generic/system/bin/hello /data/hello/hello

* Make sure you are root

        adb root

* Connect a shell on device

        adb shell
        
* (On device) Execute program

        /data/hello/hello
        
* (On device) Check log for output

        logcat
        
* (tip) Multiple shells can be connected to a device, so keeping one open with logcat running can be useful.
