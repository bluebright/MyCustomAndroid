##---------------Begin: proguard configuration for AboutLibraries  ----------
-keep class .R
-keep class **.R$* {
    <fields>;
}
-keepclasseswithmembers class **.R$* {
    public static final int define_*;
}
##---------------End: proguard configuration for AboutLibraries  ----------