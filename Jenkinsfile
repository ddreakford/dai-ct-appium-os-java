pipeline {
    agent any
    stages {
        stage('Test') {
            agent {
                docker {
                    image 'gradle'
                    args "-e CT_URL=$CT_URL -e CT_ACCESS_KEY=$CT_CLOUD_ACCESS_KEY_ID"

                    // To run the container on the node specified at the
                    // top-level of the Pipeline, in the same workspace,
                    // rather than on a new node entirely:
                    // reuseNode true
                }
            }
            steps {
                withCredentials([string(credentialsId: 'CT_CLOUD_ACCESS_KEY_ID', variable: 'CT_ACCESS_KEY')]) {
                  sh '''
                    gradle -g gradle-user-home test --tests "quickStartTests.*"
                  '''
                }
            }
        }
    }
}