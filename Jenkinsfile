pipeline {
  agent any

  stages {
          stage('Checkout') {
              steps { //Checking out the repo
                  checkout changelog: true, poll: true, scm: [$class: 'GitSCM', branches: [[name: '*/qaautomationSicredi']], browser: [$class: 'BitbucketWeb', repoUrl: 'https://'], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git', url: 'https://github.com/SamuelSiq84/sicredi-desafio-api.git']]]
              }
          }
          stage('API testing') {
                      steps {
                          script {
                              try {
                                  sh './gradlew clean test --no-daemon' //run a gradle task
                              } finally {
                                  junit '**/build/allure-results/*.xml' //make the junit test results available in any case (success & failure)
                              }
                          }
                      }
          }
          post {
                      always {
                          allure includeProperties:
                             false,
                             jdk: '',
                             results: [[path: 'build/allure-results']]
                          }
                      }
  }
}