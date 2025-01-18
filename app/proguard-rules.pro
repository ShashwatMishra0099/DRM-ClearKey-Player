# Keep class names for ExoPlayer to prevent issues with reflection
-keep class com.google.android.exoplayer2.** { *; }

# Keep DRM-related classes (ClearKey DRM)
-keep class com.google.android.exoplayer2.drm.** { *; }

# Keep UI components of ExoPlayer
-keep class com.google.android.exoplayer2.ui.** { *; }

# Allow access to classes used via reflection
-dontwarn com.google.android.exoplayer2.**
-dontwarn com.google.android.exoplayer2.drm.**

# Keep class members needed for JSON parsing (if any)
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# General ProGuard optimizations
-keepattributes *Annotation*
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Prevent ProGuard from removing classes used in the app
-keep public class * extends android.app.Application { *; }

# Enable logging in debug mode
-assumenosideeffects class android.util.Log { *; }
