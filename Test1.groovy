#!groovy

/*
======================================
Author:DevOps
Date: 17/12/2020
Purpose: To build frontend java project
=====================================
 */

pipeline {
    agent {

// which node you want to run this pipeline - master or slave Jenkins
        label 'master'
    }

    tools {
// Declare the tools used in this pipeline project
        maven 'mymaven'
        jdk 'java11'
    }

    stages {
        stage ('Code Checkout') {
            steps {
                git credentialsId: 'rajarajandayalan', url: 'https://github.com/rajarajandayalan/addressbook.git'
            }
        }

        stage ('Code Validate') {
            steps {
                script {

// bat or powershell for windows instead of sh

                    sh """    

                mvn validate
                
                        """
                }
            }
        }

        stage ('Code Compile') {
            steps {
                script {

                    sh """

                mvn compile
                
                        """
                }
            }
        }

        stage ('Code Test') {
            steps {
                script {

                    sh """

                mvn test
                
                        """
                }
            }
        }

        stage ('Code Package') {
            steps {
                script {
                    sh """

                mvn package
                
                        """
                }
            }
        }

        stage ('Code Zip') {
            steps {
                script {
                    sh """
                cd "${env.WORKSPACE}/target/"
                zip FrontEnd.zip addressbook-2.0.war
              
                       """
                }
            }
        }

    }
    post{

        always{

            dir("${env.WORKSPACE}@tmp"){
                deleteDir()
            }

        }
    }
}
