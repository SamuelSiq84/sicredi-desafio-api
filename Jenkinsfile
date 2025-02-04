pipeline {
    agent any

    stages {
        stage('Api Testing') {
            steps {
                git 'https://github.com/SamuelSiq84/sicredi-desafio-api.git'
                sh 'make check || true'

            }
        }
        stage('Generate Allure Report') {
            steps {
                script {
                     ws('/users/samuel/.jenkins/workspace/build/allure-reports/index.html') {
                         allure([  includeProperties: false,
                                   jdk: '',
                                   properties: [],
                                   reportBuildPolicy: 'ALWAYS',
                                  results: [[path: 'allure-results']] ])

                         }
                     }
                }
            }
        }
    }
}