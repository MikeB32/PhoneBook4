#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_example_mike_phonebook4_EncDec_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "0000000000000020";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
jstring
Java_com_example_mike_phonebook4_mDataBase_DBAdapter_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "123";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
jstring
        Java_com_example_mike_phonebook4_mDataBase_DBAdapter2_stringFromJNI(
                JNIEnv *env,
        jobject /* this */) {
    std::string hello = "123";
    return env->NewStringUTF(hello.c_str());
}