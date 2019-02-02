rootProject.name = "parallel-tempdir"

include("base")
(0..9).forEach { include("child$it") }
