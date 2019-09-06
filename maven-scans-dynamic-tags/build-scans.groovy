def buildScanApi = session.lookup("com.gradle.maven.extension.api.scan.BuildScanApi")

buildScanApi.executeOnce('build-scans') { api ->
    if(System.getenv('CI')) {
        api.tag('CI')
    } else {
        api.tag('LOCAL')
    }
}
