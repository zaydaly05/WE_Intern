############################################
# Day 10 - R8 / ProGuard Rules
############################################

# Keep API data classes.
#
# These classes are converted from JSON
# responses returned by the server.

-keep class com.example.securityrelease.data.model.** {
    *;
}

############################################
# Keep Gson serialized fields
############################################

-keepclassmembers class com.example.securityrelease.data.model.** {
    <fields>;
}

############################################
# Keep Retrofit interfaces
############################################

-keep interface com.example.securityrelease.data.ApiService {
    *;
}