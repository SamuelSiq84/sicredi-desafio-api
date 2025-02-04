pipeline {
  agent any

  stages {
          stage('Checkout') {
              steps { //Checking out the repo
                  checkout changelog: true, poll: true, scm: [$class: 'GitSCM', branches: [[name: '*/master']], browser: [$class: 'BitbucketWeb', repoUrl: 'https://web.com/blah'], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git', url: 'ssh://git@git.giturl.com/test/test.git']]]
              }
          }
          stage('Unit & Integration Tests') {
                      steps {
                          script {
                              try {
                                  sh './gradlew clean test --no-daemon' //run a gradle task
                              } finally {
                                  junit '**/build/test-results/test/*.xml' //make the junit test results available in any case (success & failure)
                              }
                          }
                      }
          }
          stage('Publish Artifact to Nexus') {
                      steps {
                          sh './gradlew publish --no-daemon'
                      }
          }
  }
}