pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'make'
                echo 'Building..'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }

    }
}