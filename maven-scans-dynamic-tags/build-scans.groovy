import org.apache.commons.lang3.StringUtils

def buildScanApi = session.lookup("com.gradle.maven.extension.api.scan.BuildScanApi")

buildScanApi.executeOnce('Build environment') { api ->
    if (System.getenv('CI')) {
        api.tag('CI')
    } else {
        api.tag('LOCAL')
    }
}

// Use environment variables set bei Jenkins CI
buildScanApi.executeOnce('Jenkins environment') { api ->
    if(System.getenv('CI')) {
        api.value("BUILD_NUMBER", System.getenv('BUILD_NUMBER'))
        api.link("Jenkins Job", System.getenv('BUILD_URL'))
    }
}

buildScanApi.executeOnce('Git version info') {
    buildScanApi.background({ api ->
        def projectDir = session.request.multiModuleProjectDirectory
        def commit = "git rev-parse --verify HEAD".execute(null, projectDir).text.trim()
        def branch = "git rev-parse --abbrev-ref HEAD".execute(null, projectDir).text

        api.value("Git Commit ID", commit)
        api.link("Source", "https://github.com/marcphilipp/gradle-sandbox/commit/$commit")
        api.value("Git Branch", branch)

        def status = StringUtils.chomp('git status --porcelain'.execute().text)
        if (status) {
            api.value("Git Status", status)
            api.tag("DIRTY")
        }
    })
}
