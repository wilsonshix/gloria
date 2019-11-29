node{
  stage('SCM Checkout'){
  
  git url: 'https://github.com/wilsonshix/gloria.git', branch:'UsingJenkins'
  }
  stage('Compile-Package'){
    // Get maven home path
    def mvnHome = tool name: 'Maven_PATH', type: 'maven'
    sh "${mvnHome}/bin/mvn package"
  }
}
