# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepclassmembers class com.bochi.kito.timbernotes.tools.AppConstString {
    public static final java.lang.String DATABASE_NAME;
    public static final int DATABASE_VERSION;
}

-keepclassmembers class * {
    @androidx.room.Query <methods>;
}
-keep class com.bochi.kito.timbernotes.data.db.NotesDatabase { *; }
-keepclassmembers class com.bochi.kito.timbernotes.data.db.NotesDatabase { *; }

-keep class com.bochi.kito.timbernotes.data.dao.NotesDao { *; }
-keepclassmembers class com.bochi.kito.timbernotes.data.dao.NotesDao { *; }

-keep class com.bochi.kito.timbernotes.data.dao.TaskDao { *; }
-keepclassmembers class com.bochi.kito.timbernotes.data.dao.TaskDao { *; }


-keep class com.bochi.kito.timbernotes.data.entity.Notes { *; }
-keepclassmembers class com.bochi.kito.timbernotes.data.entity.Notes { *; }

-keep class com.bochi.kito.timbernotes.data.entity.Task { *; }
-keepclassmembers class com.bochi.kito.timbernotes.data.entity.Task { *; }