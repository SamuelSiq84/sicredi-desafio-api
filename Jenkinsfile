
node {
    stage('Build') {
        try {
            git 'https://github.com/SamuelSiq84/sicredi-desafio-api.git'
            sh './gradlew clean test'

        }
        finally {

            allure includeProperties:
               false,
               jdk: '',
               results: [[path: 'allure generate build/allure-results']]

        }
    }
}