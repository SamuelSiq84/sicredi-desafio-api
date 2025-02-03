pipeline {
    agent any


    stages {
        stage('Build') {
            steps {
                withGradle {
                sh './gradlew dependencies && ./gradlew assemble && find ./ -name "*.war"'
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