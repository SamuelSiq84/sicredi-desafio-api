pipeline {
    agent any

    stages {
        stage('Api Testing') {
            steps {
                sh 'make check || true'
                junit '**/target/*.xml'

            }
        }
    }
}