pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
            echo "Buiding My Resto"
                sh 'mvn -Pclient install'
            }
            post {
                success {
                    junit 'target/../testReports/**/*.xml'
                }
            }
        }
    }
}