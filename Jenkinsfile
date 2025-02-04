pipeline {
  agent any

  stages {
    stage("Build") {
      steps {
        sh 'mvn -v'
      }
    }

    stage("Testing") {
        agent {'openjdk:7-jdk-alpine'}
        stage("Integration Tests") {
          steps {
            sh 'java -version'
          }
        }
    }
  }

}