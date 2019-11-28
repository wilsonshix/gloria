node{
  stage('SCM Checkout'){
  git 'https://github.com/wilsonshix/gloria.git'
  }
  stage('Compile-Package'){
  sh 'mvn package'
  }
}
