##---------------Begin: proguard configuration for ejml  ----------
-keep class org.ejml.** { *; }
-dontwarn org.ejml.**
-keep class org.ddogleg.** { *; }
-dontwarn org.ddogleg.**
##---------------End: proguard configuration for ejml  ----------