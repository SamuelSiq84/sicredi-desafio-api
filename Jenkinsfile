// If you use the [Declarative Pipeline syntax](https://www.jenkins.io/doc/book/pipeline/syntax/#declarative-pipeline), find the stage that runs the tests and insert a new `always` block into that stage's `post` block. This will make Allure Report run after the test launch regardless of how many tests succeeded.
pipeline {
    agent any
    triggers {
      cron '''TZ=America/Sao_Paulo
    @hourly'''
    }
    stages {
        stage('Build') {
            steps {

                sh './gradlew clean test'
                sh 'make check || true'
            }
            post {
                always {
                    allure includeProperties:
                     false,
                     jdk: '',
                     results: [[path: 'build/allure-results']]
                }
            }
        }
    }
}