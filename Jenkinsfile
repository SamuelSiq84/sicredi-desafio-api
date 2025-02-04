pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/SamuelSiq84/sicredi-desafio-api.git'
                sh './gradlew clean test'
            }
        }


        stage('Generate Allure Report') {
            steps {
                script {
                    ws('**/build/allure-reports') {
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
