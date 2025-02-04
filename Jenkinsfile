pipeline {
    agent any

    stages {
        stage('Clone project') {
            steps {
                script{
                    currentBuild.displayName = "#${BUID_NUMBER} [${GIT_BRANCH}]"
                }
                cleanWs()
                checkout scm
            }
        }
      stage ('Build'){
            steps{
                sh "./gradlew test"
            }
      }
    }



}