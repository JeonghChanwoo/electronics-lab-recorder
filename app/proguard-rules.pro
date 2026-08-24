# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames

# Keep line numbers for debugging stack traces
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Preserve all public applications
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Hilt rules
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel { *; }

# Kotlin rules
-keepclassmembers class kotlin.Metadata {
    *** componentN();
}