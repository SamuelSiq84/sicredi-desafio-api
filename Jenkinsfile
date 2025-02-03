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
      stage ('Run Tests'){
            steps{
                sh "./gradlew test"
            }
        }
        }

        post{
            always{
            allure([
                includeProperties: false,
                properties       :[],
                reportBuildPolicy: 'ALWAYS',
                results          : [[path:  'build/allure-results']]
                ])
                }
            }
    }



