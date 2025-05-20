pipeline {
  agent { 
    docker  { image 'gradle' }
  }
  stages {
    // stage('Env Prep') {
    //   steps {
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