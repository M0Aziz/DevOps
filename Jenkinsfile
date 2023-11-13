pipeline {
    agent { label  "agent1" }

    environment {
        MYSQL_ROOT_PASSWORD = 'root'
        NEXUS_PASSWORD = 'nexus'
        NEXUS_USER = 'admin'
    }


 stage('Start Jenkins and MySQL') {
            steps {
                sh "sudo docker start mysql"
                sh "sudo docker start jenkins"
            }
        }

        stage('Maven test') {
                    steps {
                        sh "mvn -version"
                        sh "mvn test"
                    }
                }

               stage('SRC analysis tests') {
                    steps {
                      sh"mvn verify sonar:sonar -Dsonar.host.url=https://sonarcloud.io/ -Dsonar.organization=transbetter -Dsonar.token=87ac6a83de71fef8a59833d5c7af27ac9ac33f40"

                    }
                }
    stages {
        stage('Build JAR and Deploy to Nexus') {
            steps {
                script {
                    // Étape de build du projet Maven pour générer le JAR
                    sh "mvn clean package -DskipTests"

                    // Déploiement du JAR généré sur Nexus
                    sh "curl -u $NEXUS_USER:$NEXUS_PASSWORD --upload-file target/SkiStationProject.jar http://192.168.33.10:8081/repository/maven-snapshots/"
                }
            }
        }



        stage('Retrieve JAR from Nexus') {
            steps {
                script {
                                    sh "mkdir jar"

                    // Téléchargement du fichier JAR depuis Nexus
                    //sh "curl -u $NEXUS_USER:$NEXUS_PASSWORD -O http://192.168.33.10:8081/repository/maven-snapshots/SkiStationProject.jar"
                                    sh "curl -u $NEXUS_USER:$NEXUS_PASSWORD -o jar/SkiStationProject.jar http://192.168.33.10:8081/repository/maven-snapshots/SkiStationProject.jar"

                }
            }
        }

        stage('Build Docker image') {
            steps {
                sh "sudo docker build -t apptest ."
            }
        }

        stage('Tag Docker image') {
            steps {
                sh "sudo docker tag apptest 192.168.33.10:8082/apptest:1.0"
            }
        }

//test test
  stage('Run Docker image') {
            steps {
                sh "sudo docker run -p 9090:9090 apptest"

            }
        }
        stage('Deploy Docker image to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'Nexus-Credentials', usernameVariable: 'admin', passwordVariable: 'nexus')]) {
                    sh "sudo docker login -u $NEXUS_USER -p $NEXUS_PASSWORD 192.168.33.10:8082"
                    sh "sudo docker push 192.168.33.10:8082/apptest:1.0"
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
