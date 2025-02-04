pipeline {
  agent any

  stages {
          stage('Checkout') {
              steps { //Checking out the repo
                  checkout changelog: true, poll: true, scm: [$class: 'GitSCM', branches: [[name: '*/qaautomationSicredi']], browser: [$class: 'BitbucketWeb', repoUrl: 'https://'], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git', url: 'https://github.com/SamuelSiq84/sicredi-desafio-api.git']]]
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
          stage('Publish Artifact to Allure') {
                      steps {
                          sh './gradlew publish --no-daemon'
                      }
          }
  }
}