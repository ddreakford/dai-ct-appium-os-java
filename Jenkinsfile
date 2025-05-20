pipeline {
  // agent { 
  //   docker  { image 'gradle' }
  // }
  stages {
    // stage('Env Prep') {
    //   steps {
    //   }
    // }
    stage('Test') {
      steps {       
        // Run the tests
        // sh '''
        //   gradle --version
        // '''
        docker.image('gradle').inside {
          git 'https://github.com/ddreakford/dai-ct-appium-os-java.git'
          sh 'gradle --version'
        }
      }
    }
  }
}