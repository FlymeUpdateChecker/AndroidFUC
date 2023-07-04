-keep class ru.transaero21.fuc.entity.state.DeviceState { *; }
-keep class ru.transaero21.fuc.entity.state.FieldState { *; }

# Kotlin Reflection
-keepattributes RuntimeVisibleAnnotations
-keep class kotlin.Metadata { *; }
-keep class kotlin.jvm.functions.** { *; }