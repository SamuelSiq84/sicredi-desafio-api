pipeline {
    agent any


    stages {
        stage('Build') {
            steps {
                withGradle {
                sh './gradle clean build --stacktrace -i'
            }
        }
    }


     stage('Post') {
          steps {
            script {
              jacoco()
              junit 'build/test-results/test/*.xml'
              def pmd = scanForIssues tool: [$class: 'Pmd'], pattern: 'build/reports/pmd/*.xml'
              publishIssues issues: [pmd]
            }
          }
        }
    }
}