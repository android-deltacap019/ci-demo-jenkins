# ci-demo-jenkins

# Install Jenkins
   Source: https://jenkins.io/doc/book/installing/
   mac OS:
       - brew install jenkins
   WAR File:
       - Web archive file can be used on any OS that has Java installed on it.
   Install wget:
       - brew install wget --with-libressl -> throwing error regarding --with-libressl
       - brew install wget (worked and installed required dependencies on it own)

# Web API for Jenkins
   - http://localhost:8080/
   - http://localhost:8080/exit
   - http://localhost:8080/restart
   - http://localhost:8080/reload

# Install Android SDK tools
   Source: https://github.com/codepath/android_guides/wiki/Installing-Android-SDK-Tools
   MAC OS Manual installation:
    - Go To: https://developer.android.com/studio/#downloads
    - Look for SDK tools compatible to mac and copy link for that:
        - https://dl.google.com/android/repository/sdk-tools-darwin-4333796.zip
    - Download the selected sdk tools file
        $ wget https://dl.google.com/android/repository/sdk-tools-darwin-4333796.zip
        // here we used below as per tutorial and file will be downloaded to "/Users/Shared/Jenkins/". 
        sudo -iu jenkins wget https://dl.google.com/android/repository/sdk-tools-darwin-4333796.zip
    - Create directory with name android-sdk, below command will create "android-sdk" inside ""/Users/Shared/Jenkins/""
        sudo -iu jenkins mkdir android-sdk
    - Create environment variable for android sdk location if required.
            export ANDROID_HOME=/usr/local/share/android-sdk
    - Extract the downloaded package to the directory "android-sdk" 
        sudo -iu jenkins unzip sdk-tools-darwin-4333796.zip -d android-sdk
    
# Setup
   Source: https://bugfender.com/blog/how-to-add-your-first-android-job-to-jenkins/

# Tasks:
   - Build from jenkins
   - Build with push trigger
   - Run tests from jenkins
   - Move generated build to different location.
   
# Run Observations
## First run : First Build
   - Log: Successfully compiled and generated a build inside "build/output" folder.
    `
    Building in workspace /Users/Shared/Jenkins/Home/workspace/ci-android-demo
    No credentials specified
     > git rev-parse --is-inside-work-tree # timeout=10
    Fetching changes from the remote Git repository
     > git config remote.origin.url https://github.com/android-deltacap019/ci-demo-jenkins.git # timeout=10
    Fetching upstream changes from https://github.com/android-deltacap019/ci-demo-jenkins.git
     > git --version # timeout=10
     > git fetch --tags --progress https://github.com/android-deltacap019/ci-demo-jenkins.git +refs/heads/*:refs/remotes/origin/*
     > git rev-parse refs/remotes/origin/master^{commit} # timeout=10
     > git rev-parse refs/remotes/origin/origin/master^{commit} # timeout=10
    Checking out Revision 4e29912f84177b099118a9b5dbedf96945939404 (refs/remotes/origin/master)
     > git config core.sparsecheckout # timeout=10
     > git checkout -f 4e29912f84177b099118a9b5dbedf96945939404
    Commit message: "Initial commit"
     > git rev-list --no-walk 4e29912f84177b099118a9b5dbedf96945939404 # timeout=10
    [Gradle] - Launching build.
    [ci-android-demo] $ /Users/Shared/Jenkins/Home/workspace/ci-android-demo/gradlew assembleDebug
    Starting a Gradle Daemon (subsequent builds will be faster)
    > Configure project :app
    File /Users/Shared/Jenkins/.android/repositories.cfg could not be loaded.
    Checking the license for package Android SDK Build-Tools 28.0.3 in /Users/Shared/Jenkins/android-sdk/licenses
    License for package Android SDK Build-Tools 28.0.3 accepted.
    Preparing "Install Android SDK Build-Tools 28.0.3 (revision: 28.0.3)".
    "Install Android SDK Build-Tools 28.0.3 (revision: 28.0.3)" ready.
    Installing Android SDK Build-Tools 28.0.3 in /Users/Shared/Jenkins/android-sdk/build-tools/28.0.3
    "Install Android SDK Build-Tools 28.0.3 (revision: 28.0.3)" complete.
    "Install Android SDK Build-Tools 28.0.3 (revision: 28.0.3)" finished.
    Checking the license for package Android SDK Platform 28 in /Users/Shared/Jenkins/android-sdk/licenses
    License for package Android SDK Platform 28 accepted.
    Preparing "Install Android SDK Platform 28 (revision: 6)".
    "Install Android SDK Platform 28 (revision: 6)" ready.
    Installing Android SDK Platform 28 in /Users/Shared/Jenkins/android-sdk/platforms/android-28
    "Install Android SDK Platform 28 (revision: 6)" complete.
    "Install Android SDK Platform 28 (revision: 6)" finished.
    Checking the license for package Android SDK Platform-Tools in /Users/Shared/Jenkins/android-sdk/licenses
    License for package Android SDK Platform-Tools accepted.
    Preparing "Install Android SDK Platform-Tools (revision: 28.0.1)".
    "Install Android SDK Platform-Tools (revision: 28.0.1)" ready.
    Installing Android SDK Platform-Tools in /Users/Shared/Jenkins/android-sdk/platform-tools
    "Install Android SDK Platform-Tools (revision: 28.0.1)" complete.
    "Install Android SDK Platform-Tools (revision: 28.0.1)" finished.
    > Task :app:preBuild UP-TO-DATE
    > Task :app:preDebugBuild
    > Task :app:compileDebugAidl NO-SOURCE
    > Task :app:compileDebugRenderscript
    > Task :app:checkDebugManifest
    > Task :app:generateDebugBuildConfig
    > Task :app:prepareLintJar
    > Task :app:generateDebugSources
    > Task :app:javaPreCompileDebug
    > Task :app:mainApkListPersistenceDebug
    > Task :app:generateDebugResValues
    > Task :app:generateDebugResources
    > Task :app:mergeDebugResources
    > Task :app:createDebugCompatibleScreenManifests
    > Task :app:processDebugManifest
    > Task :app:processDebugResources
    > Task :app:compileDebugJavaWithJavac
    > Task :app:compileDebugNdk NO-SOURCE
    > Task :app:compileDebugSources
    > Task :app:mergeDebugShaders
    > Task :app:compileDebugShaders
    > Task :app:generateDebugAssets
    > Task :app:mergeDebugAssets
    > Task :app:mergeExtDexDebug
    > Task :app:transformClassesWithDexBuilderForDebug
    > Task :app:mergeDexDebug
    > Task :app:validateSigningDebug
    > Task :app:signingConfigWriterDebug
    > Task :app:mergeDebugJniLibFolders
    > Task :app:transformNativeLibsWithMergeJniLibsForDebug
    > Task :app:processDebugJavaRes NO-SOURCE
    > Task :app:transformResourcesWithMergeJavaResForDebug
    > Task :app:packageDebug
    > Task :app:assembleDebug
    BUILD SUCCESSFUL in 13m 35s
    25 actionable tasks: 25 executed
    Build step 'Invoke Gradle script' changed build result to SUCCESS
    Finished: SUCCESS
    `
## Second run: Run Tests  

