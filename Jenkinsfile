pipeline {
  agent { 
    docker  { image 'gradle'}
  }
  stages {
    // stage('Env Prep') {
    //   steps {
    //     Write cloud.properties file
    //     script {
    //         withCredentials([string(credentialsId: 'CT_CLOUD_ACCESS_KEY_ID', variable: 'CT_ACCESS_KEY')]) {
    //             def cloudProperties = """
    //                 [cloud]
    //                 url=${env.CT_URL}
    //                 accessKey=${env.CT_ACCESS_KEY}
    //             """
    //             writeFile file: 'cloud.properties', text: cloudProperties
    //         }
    //     }
    //   }
    // }
    stage('Test') {
      steps {       
        // Run the tests
        sh '''
          gradle --version
        '''
      }
    }
  }
}