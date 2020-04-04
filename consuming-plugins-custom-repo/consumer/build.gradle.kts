plugins {
    id("org.example.a") version "0.1"
    id("org.example.b") version "0.1"
}

defaultTasks = mutableListOf("a", "b")
