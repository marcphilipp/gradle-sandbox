import org.apache.commons.lang3.StringUtils

def buildScanApi = session.lookup("com.gradle.maven.extension.api.scan.BuildScanApi")

buildScanApi.executeOnce('Build environment') { api ->
    if(System.getenv('CI')) {
        api.tag('CI')
    } else {
        api.tag('LOCAL')
    }
}

buildScanApi.executeOnce('Git version info') {
    buildScanApi.background({ api ->
        def projectDir = session.request.multiModuleProjectDirectory
        def commit = "git rev-parse --verify HEAD".execute(null, projectDir).text
        def branch = "git rev-parse --abbrev-ref HEAD".execute(null, projectDir).text

        api.value("Git Commit ID", commit)
        api.value("Git Branch", branch)

        def status = StringUtils.chomp('git status --porcelain'.execute().text)
        if (status) {
            api.value("Git Status", status)
            api.tag("DIRTY")
        }
    })
}
