pipeline {
	agent any
	tools {
		maven "Maven-3.6.3"
		jdk "Java-11.0.9"
	}

	stages {
		stage ("Git checkout") {
			steps {
				echo "Checking out branch..."
				checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/DaviJam/MyResto.git']]])
			}
		}
		stage("Initialize") {
			steps {
				echo "Cleaning up..."
				bat "mvn clean"
			}
		}

		stage("Build") {
			steps {
				echo "Building..."
				bat "mvn install"
			}
		}
		stage("Test") {
			steps {
				echo "Testing and reporting..."
				bat "mvn test"
			}
			post {
				success {
					junit 'testReports/**/*.xml'
				}
			}
		}
		stage("Deploy") {
			steps {
				echo "Deploying..."
				script {
					try {
					    deploy adapters: [tomcat9(credentialsId: 'e8677a13-83bc-4756-8dc9-c76e332cb99b', url: 'http://vps-2489945c.vps.ovh.net:5789/manager/text/')], contextPath: '/myresto', war: '**/*.war'
					} catch (err) {
						echo "Error deploying war file..."
					}
				}
			}
		}
	}
}