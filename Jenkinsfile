// If you use the [Declarative Pipeline syntax](https://www.jenkins.io/doc/book/pipeline/syntax/#declarative-pipeline), find the stage that runs the tests and insert a new `always` block into that stage's `post` block. This will make Allure Report run after the test launch regardless of how many tests succeeded.
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {

                sh './gradlew clean test'
            }
            post {
                always {
                    script {
                    sh '/usr/local/lib/allure-2.4.1/bin/allure'
                    allure includeProperties:
                     false,
                     jdk: '',
                     results: [[path: 'build/allure-results']]
                }
              }
            }
        }
    }
}