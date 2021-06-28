pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
            echo "Buiding Gestion d'établissement"
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