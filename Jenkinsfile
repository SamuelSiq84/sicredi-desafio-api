pipeline {
    agent any

    stages {
        stage('sicredi-desafio-api')
        node{
            if(isUnix()){
            sh 'gradle build --info'

            }
            else{
                bat 'gradle build --info'
            }
        }
    }



}