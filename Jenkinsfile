pipeline {
    agent any

    stages {
        stage('Api Testing') {
            steps {
                git 'https://github.com/SamuelSiq84/sicredi-desafio-api.git'
                sh 'make check || true'
                junit '**/target/*.xml'

            }
        }
    }
}