pipeline {
    agent any
    stages {
        stage('Test') {
            agent {
                docker {
                    image 'gradle'
                    args "-e CT_URL=$CT_URL"
                }
            }
            steps {
                withCredentials([string(credentialsId: 'CT_CLOUD_ACCESS_KEY_ID', variable: 'CT_ACCESS_KEY')]) {
                  // Run the quickstart tests
                  sh '''
                    gradle -g gradle-user-home clean test --tests "quickStartTests.*"
                  '''
                }
            }
        }
    }
}