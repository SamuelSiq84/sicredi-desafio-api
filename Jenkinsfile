pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/SamuelSiq84/sicredi-desafio-api.git'
                sh './gradlew clean test'
            }
        }


        stage('Publish') {
            echo 'Publish Allure report'
            publishHTML(
                    target: [
                            allowMissing         : false,
                            alwaysLinkToLastBuild: false,
                            keepAll              : true,
                            reportDir            : 'build/site/allure-gradle-plugin',
                            reportFiles          : 'index.html',
                            reportName           : "Allure Report"
                    ]
            )
   }    }
}