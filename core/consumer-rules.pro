-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

#room
-keep class * extends androidx.room.RoomDatabase

-keep class com.muhammhassan.core.api.model.** { *; }