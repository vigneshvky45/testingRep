pipeline {
  agent any
    
      stages {
        stage('Test') {
          steps {
            bat "mvn -D clean test"
          }

          post {
            success {
              cucumber buildStatus: 'null',
              customCssFiles: '',
              customJsFiles: ''
            }
          }
        }
      }
}
