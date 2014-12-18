## Making gradle work with NDK
Recent versions of Android-Studio come with gradle tasks to compile native libraries. It just requires a few settings.

Add module name (as specified in *Android.mk*) to Android module's graddle configuration (*module_name/build.gradle*):

	android {
	 ...
 		defaultConfig {
			ndk {
				moduleName "hello-jni"
			}
		}
	}

Tell it where your local NDK is located in your *local.properties* (found in the root of the solution after it has been opened in Android Studio):

	ndk.dir=C\:\\Android\\ndk

If you have issues, these links might be useful:
 * http://ph0b.com/android-studio-gradle-and-ndk-integration/
 * http://blog.gaku.net/ndk/


## Generate C-headers
*NOTE: Headers are already generated and committed in this repo, this is just a tip for future projects or refactoring*

When using Android Studio the Gradle build scripts will not leave class files in a /bin directory. This is required to generate C headers for native library invocation. So to make these headers we should compile relevant classes.

Navigate to source directory:

	cd FibonacciService/src/main/java/me/soeholm/android/fibonacciproject/fibonacciservice

Compile java source:

	javac Fibonacci.java -d bin/

Navigate to JNI includes folder:
	
	cd ../../../../../../jni/include

Generate headers:

	javah -classpath ../../java/me/soeholm/android/fibonacciproject/fibonacciservice/bin -o Fibonacci.h me.soeholm.android.fibonacciproject.fibonacciservice.Fibonacci
	
If you are curious about the details, here is the official reference from Oracle I used:
 * https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javah.html
