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
                          sh './gradlew clean test'
                      }
          }

  }
           post {
                always {
                      allure includeProperties:
                       false,
                       jdk: '23',
                       results: [[path: 'build/allure-results']]
           }    }

}