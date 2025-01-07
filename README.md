# plugins

Plugin manager for Airbrush, separated into an open source library.

# Dependencies

First, add the Jitpack repository:
```kts
repositories {
    maven(url = "https://jitpack.io/")
}
```

Then, add the dependency.
```kt
dependencies {
    implementation("com.github.azaleateam:plugins:VERSION")
}
```

# Usage

When you start your server, just register & setup the plugins.<br>
Teardown the plugins when the server stops.
```kt
val pluginManager = PluginManager()
pluginManager.registerPlugins()
pluginManager.setupPlugins()

Runtime.getRuntime().addShutdownHook(Thread {
    pluginManager.teardownPlugins()
})
```

All done.
