pipeline {
    agent any
    stages {
        stage('Test') {
            agent {
                docker {
                    image 'gradle'
                    args "-e CT_URL=$CT_URL -e CT_ACCESS_KEY=$CT_ACCESS_KEY"

                    // To run the container on the node specified at the
                    // top-level of the Pipeline, in the same workspace,
                    // rather than on a new node entirely:
                    // reuseNode true
                }
            }
            steps {
                sh """
                  gradle -g gradle-user-home test --tests "quickStartTests.*"
                """
                // sh """
                //   gradle -g gradle-user-home --version
                // """
            }
        }
    }
}