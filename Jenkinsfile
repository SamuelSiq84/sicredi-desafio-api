pipeline {
    agent { label 'java' }
    stages {
        stage('Build') {
            steps {
                withGradle {
                sh 'gradle --version'
            }
        }
    }
         stage('Report') {
                    steps {
                        publishHTML([reportName  : 'Allure Report', reportDir: './allure-gradle-plugin', reportFiles: 'index.html',
                                     reportTitles: '', allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false])
                    }
                }
    }
    post {
        always {
            deleteDir()
        }
        failure {
            slackSend message: "${env.JOB_NAME} - #${env.BUILD_NUMBER} failed (<${env.BUILD_URL}|Open>)",
                    color: 'danger', teamDomain: 'qameta', channel: 'allure', tokenCredentialId: 'allure-channel'
        }
    }
}